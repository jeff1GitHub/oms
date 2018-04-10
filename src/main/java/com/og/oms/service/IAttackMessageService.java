package com.og.oms.service;

import com.og.oms.model.AttackMessage;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
public interface IAttackMessageService extends IService<AttackMessage> {

	/**
	 * 根据攻击ID获取攻击详情
	 * 
	 * @param attackId
	 */
	List<AttackMessage> getAttackMessageService(Integer attackId);
	
}
