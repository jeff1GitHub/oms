package com.og.oms.controller;

import com.og.oms.common.Context;
import com.og.oms.exception.OmsException;
import com.og.oms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private Context context;

    /**
     * 获取当前操作用户
     * 
     * @param userId
     * @return
     */
    protected User getUser(Integer userId) {
        User user = context.getUserById(userId);
        if(user == null) {
            logger.error("用户对象不存在!");
            throw new OmsException("用户对象不存在!");
        }
        return user;
    }
}
