package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Group;
import com.og.oms.model.User;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
public interface IUserService extends IService<User> {
    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getUserList(Integer group, Integer status, String search, String datemin, String datemax) throws ParseException;

    /**
     * 通过id获取user对象
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 通过帐号密码获取角色
     *
     * @param account
     * @param password
     * @return
     */
    User getUserByAccount(String account, String password, String ip);

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改角色
     *
     * @param user   操作者
     * @param target 被修改者
     * @return
     */
    boolean updateUser(User user, User target);

    /**
     * 禁用/启用用户
     * @param user 操作人
     * @param userId 目标用户id
     * @param status 状态
     * @return
     */
    boolean disableUser(User user, Integer userId, Integer status);
    
	/**
	 * 根据组别id获取所有相关的用户
	 */
	List<User> getUsersByGroups();

	/**
	 * 获取用户以及用户的所有组字符串
	 * 
	 * @param id
	 * @return
	 */
	User selectUserById(Integer id);

	/**
	 * 检查用户所在的组是否存在没有被禁用
	 * 
	 * @param user
	 * @return
	 */
	boolean checkUserGroupsIsDisable(User user);
}
