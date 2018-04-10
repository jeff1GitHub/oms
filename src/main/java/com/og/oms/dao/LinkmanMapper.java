package com.og.oms.dao;

import com.og.oms.model.Linkman;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
public interface LinkmanMapper extends BaseMapper<Linkman> {
	
	/**
	 * 返回带具体联系方式的联系人
	 * 
	 * @param id 联系人id
	 * @return
	 */
	public Linkman getLinkmanWithInfoById(Integer id);
	
	/**
	 * 根据设置非默认的isDefault标识为0
	 * 
	 * @param id 供应商id
	 * @return
	 */
	public Integer updateLinkmanIsNotDefault(Integer id);

	public void getLinkmanList();
	
	public List<Linkman> getLinkmanWithDefaulContactInfo(Integer linkmanId);
}