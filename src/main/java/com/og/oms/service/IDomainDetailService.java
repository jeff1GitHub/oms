package com.og.oms.service;

import com.og.oms.model.DomainDetail;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-28
 */
public interface IDomainDetailService extends IService<DomainDetail> {

	/**
	 * 根据域名ID获取域名资料详情
	 * 
	 * @param id
	 */
	List<DomainDetail> getDomainDetailByDomainId(Integer id);

	/**
	 * 根据域名ID删除域名资料详情
	 * 
	 * @param id
	 * @return
	 */
	boolean delDomainDetailByDomainId(Integer id);
	
}
