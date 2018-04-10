package com.og.oms.service.impl;

import com.og.oms.model.DomainDetail;
import com.og.oms.dao.DomainDetailMapper;
import com.og.oms.service.IDomainDetailService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-28
 */
@Service
public class DomainDetailServiceImpl extends ServiceImpl<DomainDetailMapper, DomainDetail> implements IDomainDetailService {

	@Override
	public List<DomainDetail> getDomainDetailByDomainId(Integer id) {
		EntityWrapper<DomainDetail> wrapper = new EntityWrapper<>();
		wrapper.eq("domain_id", id);
		return this.selectList(wrapper);
		
	}

	@Override
	public boolean delDomainDetailByDomainId(Integer id) {
		EntityWrapper<DomainDetail> wrapper = new EntityWrapper<>();
		wrapper.eq("domain_id", id);
		return this.delete(wrapper);
	}
	
}
