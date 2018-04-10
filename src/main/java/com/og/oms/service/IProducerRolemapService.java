package com.og.oms.service;

import com.og.oms.enums.ProducerTypeEnum;
import com.og.oms.model.ProducerRolemap;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-18
 */
public interface IProducerRolemapService extends IService<ProducerRolemap> {

	/**
	 * 增加对应供应商的角色
	 * 
	 * @param id 操作用户id
	 * @param list 角色list
	 * @param producerId 供应商id
	 */
	void addProducerRoles(Integer id, List<ProducerTypeEnum> list, Integer producerId);

	/**
	 * 通过供应商id获得角色list
	 * 
	 * @param id 供应商id
	 * @return
	 */
	List<ProducerRolemap> getRoleMapsByProducerId(Integer id);

	/**
	 * 根据供应商id删除供应商的角色
	 * 
	 * @param producerId
	 * @return
	 */
	boolean delRoleMapByProducerId(Integer producerId);
}
