package com.og.oms.aspect;

import com.og.oms.exception.OmsException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @AfterThrowing(value = "execution(* com.og.oms.service.impl.*.*(..))", throwing = "e")
    public void handleThrowing(Exception e) {
        // 判断是否自定义异常
        if(e instanceof OmsException) {
            throw new OmsException(e.getMessage());
        } else {
            logger.error("service error",e);
            throw new OmsException("系统异常!");
        }
    }
}
