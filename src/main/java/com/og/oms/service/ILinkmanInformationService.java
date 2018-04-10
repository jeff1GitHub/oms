package com.og.oms.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Linkman;
import com.og.oms.model.LinkmanInformation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-11
 */
public interface ILinkmanInformationService extends IService<LinkmanInformation> {
	
	/**
	 * 新增或修改联系人的联系方式
	 * 
	 * @param userId 操作用户id
	 * @param linkman 联系人
	 * @param qq
	 * @param email
	 * @param phone
	 * @param mob
	 * @param vchat
	 * @param tg
	 * @param skype
	 * @param whatapp
	 * @param defaultContactType 是否为默认联系信息标识
	 * @return
	 */
	public boolean addLinkmanInformation(Integer userId, Linkman linkman, String qq, String email, String phone,
			String mob, String vchat, String tg, String skype, String whatapp, Integer defaultContactType);

	/**
	 * 根据联系人删除联系方式
	 * 
	 * @param linkmanId
	 */
	public void delByLinkmanId(Integer linkmanId);

	/**
	 * 根据联系人ID获取该联系人所有的联系方式
	 * 
	 * @param linkmanId 联系人ID
	 * @return
	 */
	public List<LinkmanInformation> getLinkmanInfoByLinkmanId(Integer linkmanId);
}
