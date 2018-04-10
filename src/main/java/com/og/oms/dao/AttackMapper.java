package com.og.oms.dao;

import com.og.oms.model.Attack;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
public interface AttackMapper extends BaseMapper<Attack> {

	/** 
	 * 根据查询条件查询攻击防御列表
	 * 
 	 * @param map
	 * @return
	 */
	List<Attack> getAttackList(Map<String, Object> map);

}