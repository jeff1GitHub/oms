package com.og.oms.dao;

import com.og.oms.model.Group;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
public interface GroupMapper extends BaseMapper<Group> {
	/**
	 * 根据用户id获取所在所有权限组的所有子孙权限组
	 * 
	 * @param ids 用户权限组们的id
	 * @return
	 */
	List<Group> getChildGroupListForUser(List<Integer> ids);

	/**
	 * 根据用户id获取
	 * 
	 * @param userId
	 * @return
	 */
	List<Group> getGroupsForUserByUserId(Integer userId);
}