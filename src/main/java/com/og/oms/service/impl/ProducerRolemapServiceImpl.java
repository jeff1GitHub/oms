package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.ProducerRolemapMapper;
import com.og.oms.enums.ProducerTypeEnum;
import com.og.oms.model.ProducerRolemap;
import com.og.oms.service.IProducerRolemapService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-18
 */
@Service
public class ProducerRolemapServiceImpl extends ServiceImpl<ProducerRolemapMapper, ProducerRolemap> implements IProducerRolemapService {

	@Override
	public void addProducerRoles(Integer id, List<ProducerTypeEnum> producerTypes, Integer producerId) {
		
		this.delRoleMapByProducerId(producerId);
		
		if(producerTypes != null && producerTypes.size()>0) {
			List<ProducerRolemap> entityList = new ArrayList<>();
			producerTypes.forEach(producerType -> {
				ProducerRolemap producerRolemap = new ProducerRolemap();
				producerRolemap.setProducerId(producerId);
				producerRolemap.setRoleId(producerType.getCode());
				entityList.add(producerRolemap);
			});
			this.insertBatch(entityList);
		}
	}
	
	@Override
	public List<ProducerRolemap> getRoleMapsByProducerId(Integer id) {
		EntityWrapper<ProducerRolemap> wrapper = new EntityWrapper<>();
		wrapper.eq("producer_id", id);
		return this.selectList(wrapper);
	}
	
	/**
	 * @param producerId
	 * @return
	 */
	@Override
	public boolean delRoleMapByProducerId(Integer producerId) {
		EntityWrapper<ProducerRolemap> wrapper = new EntityWrapper<>();
		wrapper.eq("producer_id", producerId);
		return this.delete(wrapper);
	}
	
}
