package com.og.oms.aspect;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Permis;
import com.og.oms.model.User;
import com.og.oms.service.IOperateLogService;
import com.og.oms.service.IUserService;
import com.og.oms.utils.JsonResult;

/**
 * 拦截器：记录用户操作日志，检查用户是否登录以及权限验证
 */
@Aspect
@Component
@Order(-99)
public class WebAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebAspect.class);

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IOperateLogService operateLogService;

    public WebAspect() {
        logger.info("初始化WebAspect");
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        // 记录方法开始的时间
        long beginTime = System.currentTimeMillis();

        // 获取方法签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取被拦截的方法
        Method method = signature.getMethod();
        // 获取被拦截的方法名
        String methodName = method.getName();
        // 获取类名
        String className = pjp.getTarget().getClass().getSimpleName();  

        Object result = null;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        // 先判断是否登录
        Integer userId = (Integer) session.getAttribute("userId");
        Object attribute = session.getAttribute("userPermis");
        boolean isAjaxRequest = false;
        if(!StringUtils.isEmpty(request.getHeader("x-requested-with")) && "XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
            isAjaxRequest = true;  
        }
       if(!methodName.toLowerCase().contains("login")){
           if(userId == null || attribute == null ) {
        	   logger.debug("Session已超时，正在跳转回登录页面");
        	   if(isAjaxRequest ) {
        		   return new JsonResult(ResultCode.NOT_LOGIN);
        	   } else {
                   return new ModelAndView("redirect:/login");
        	   }
           }
       }

        // 是否需要验证权限
        PermisAnnotation permis = method.getAnnotation(PermisAnnotation.class);
        // 是否有权限标志
        boolean hasPermis = false;
        
        // 如果有注解了
        if(permis != null) {
        	// 获得需要判断的权限ID
        	int permitId = permis.id();
        	@SuppressWarnings("unchecked")
        	
        	// 获取用户的权限列表
			List<Permis> userPermis = (List<Permis>) session.getAttribute("userPermis");
            // 判断是否有权限
        	hasPermis = checkPermis(userPermis, permitId);
            if(!hasPermis) {
            	// 没有权限
            	return new JsonResult(ResultCode.NO_PERMISSION, "您没有权限做此操作!");
            }
        }
        
        
        Object[] objarr = pjp.getArgs();
        if("indexPage".equals(methodName)) {
            objarr[0] = session.getAttribute("userId");
        }

        // 校验参数
        for(Object obj : pjp.getArgs()) {
            // 判断是否绑定验证
            if(obj instanceof BindingResult) {
                BindingResult br = (BindingResult) obj;
                //绑定验证消息不为空==>有错误
                if(br.hasErrors()) {
                    List<ObjectError> list = br.getAllErrors();
                    result = new JsonResult(ResultCode.PARAMS_ERROR, list.get(0).getDefaultMessage());
                }
            }
        }

        try {
            if(result == null) {
                // 一切正常的情况下，继续执行被拦截的方法
                result = pjp.proceed(objarr);
            }
        } catch(Throwable e) {
            if(!(e instanceof OmsException)) {
                logger.error("执行 {} 方法异常", e, methodName);
            }

            result = new JsonResult(ResultCode.EXCEPTION, "发生异常：" + e.getMessage());
        }
        
        //保存操作日志 只记录需要权限并排除菜单点击操作
        if(permis != null && !"WebController".equals(className)) {
            //获取用户
            User user = userService.selectById(userId);
            //获取操作参数 
            Object[] args = pjp.getArgs();
            if(user != null) {
            	operateLogService.addOperateLog(user, methodName, permis, args);
            }
        }
        

        long costMs = System.currentTimeMillis() - beginTime;
        logger.info("{} 请求结束，耗时： {} ms", methodName, costMs);

        return result;
    }
    
    /**
     * 递归验证是否有权限
     * 
     * @param permis 权限列表
     * @param permisId 权限id
     * @return false
     */
    private boolean checkPermis(List<Permis> permis, int permisId) {
    	boolean flag = false;
    	if(permis!=null && permis.size()>0) {
    		for(Permis p : permis) {
    			if(p.getId() == permisId || checkPermis(p.getChildrenPermis(), permisId)) {
    				return true;
    			}
    		}
    	}
		return flag;
    }
}
