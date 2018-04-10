package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.LoginLogMapper;
import com.og.oms.model.LoginLog;
import com.og.oms.model.User;
import com.og.oms.service.ILoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 登录日志服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Override
    public List<LoginLog> getLoginList(String account) {
        EntityWrapper<LoginLog> wrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(account)) {
            wrapper.like("user_account", "%" + account + "%");
        }
        return this.selectList(wrapper);
    }

    @Override
    public boolean addLogin(User user, String ip) {
        LoginLog log = new LoginLog();
        log.setUserId(user.getId());// 用户id
        log.setUserAccount(user.getAccount());// 用户帐号
        log.setLoginTime(new Date());// 登录时间
        log.setIp("0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip);// 登录ip
        return this.insert(log);
    }
}
