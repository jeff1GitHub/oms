package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.GroupUser;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-20
 */
public interface IGroupUserService extends IService<GroupUser> {
    /**
     * 根据用户ID获取用户的权限组映射
     *
     * @param user
     * @return
     */
    List<GroupUser> getGroupUserByUser(Integer userId);

	/**
	 * 修改员工所属组
	 * 
	 * @param userId 当前登录用户id
	 * @param user 员工id
	 * @return
	 */
	boolean updateUserGroup(Integer userId, User user);

}
