package com.og.oms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.og.oms.model.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-15
 */
public interface UserMapper extends com.baomidou.mybatisplus.mapper.BaseMapper<User> {

    /**
     * 通过条件查询用户列表
     *
     * @param map
     * @return
     */
    List<User> selectUserList(@Param("params") Map<String, Object> map);

    /**
     * 通过id查询用户
     * 
     * @param id
     * @return
     */
    User selectUserById(@Param("id") Integer id);

	/**
	 * 通过查询条件查询用户列表
	 * 
	 * @param map
	 * @return
	 */
	List<User> getUserListByParam(Map<String, Object> map);

	/**
	 * 获取用户
	 * 
	 * @return
	 */
	List<User> getUsersByGroups();
}