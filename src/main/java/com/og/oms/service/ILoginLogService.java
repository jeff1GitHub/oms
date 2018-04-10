package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.LoginLog;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 登录日志服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
public interface ILoginLogService extends IService<LoginLog> {
    /**
     * 查询登录日志
     *
     * @param account
     * @return
     */
    List<LoginLog> getLoginList(String account);

    /**
     * 增加登录日志
     *
     * @param user
     * @param ip
     * @return
     */
    boolean addLogin(User user, String ip);
}
