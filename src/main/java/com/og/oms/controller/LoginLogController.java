package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.service.ILoginLogService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录日志前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@RestController
@RequestMapping("loginLog")
public class LoginLogController {
    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 获取登录日志
     *
     * @param account
     * @return
     */
    @PermisAnnotation(id = 504, name = "登录日志-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getLoginLogList(String account) {
        JsonResult result = new JsonResult(this.loginLogService.getLoginList(account));
        return result;
    }
}
