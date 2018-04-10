package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Group;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
public interface IGroupService extends IService<Group> {
    /**
     * 获取所有的权限组
     *
     * @return
     */
    List<Group> getGroupList();

    /**
     * 获取用户所在权限组以及子孙权限组
     *
     * @param user
     * @return
     */
    List<Group> getChildGroupListForUser(User user);

    /**
     * 通过id获取权限组
     *
     * @param groupId
     * @return
     */
    Group getGroupById(Integer groupId);

    /**
     * 通过id获取用户可以管理的权限组
     *
     * @param user
     * @param groupId
     * @return
     */
    Group getGroupById(User user, Integer groupId);

    /**
     * 增加权限组
     *
     * @param user
     * @param group
     * @return
     */
    boolean addGroup(User user, Group group);

    /**
     * 设置权限组的权限
     *
     * @param user
     * @param groupId
     * @param permisIds
     * @return
     */
    boolean setGroupPermis(User user, Integer groupId, Integer[] permisIds);

    /**
     * 修改权限组
     *
     * @param user
     * @param group
     * @return
     */
    boolean updateGroup(User user, Group group);

    /**
     * 删除权限组
     *
     * @param user
     * @param groupId
     * @return
     */
    boolean delGroup(User user, Integer groupId);

    /**
     * 禁用权限组
     *
     * @param user
     * @param groupId
     * @return
     */
    boolean disableGroup(User user, Integer groupId, boolean isDisable);

    /**
     * 初始化权限组枚举
     * 
     * @return
     */
    boolean initGroupEnum();

	/**
	 * 获取用户所在的权限组们
	 * 
	 * @param userId
	 * @return
	 */
	List<Group> getGroupsForUserByUserId(Integer userId);
}
