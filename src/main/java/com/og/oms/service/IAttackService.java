package com.og.oms.service;

import com.og.oms.model.Attack;
import com.og.oms.model.User;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
public interface IAttackService extends IService<Attack> {

	/**
	 * 添加攻击防御信息
	 * 
	 * @param user
	 * @param attack
	 * @return
	 */
	boolean addAttack(User user, Attack attack);

	/**
	 * 更新攻击防御信息
	 * 
	 * @param user
	 * @param attack
	 * @return
	 */
	boolean updateAttack(User user, Attack attack);

	/**
	 * 查询攻击防御信息
	 * 
	 * @param id
	 * @return
	 */
	Attack getAttack(Integer id);

	/**
	 * 根据查询条件查询防御信息列表
	 * 
	 * @param platform
	 * @param level
	 * @param search
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Attack> getAttackList(Integer platform, Integer level, String search, String startTime, String endTime);
	
}
