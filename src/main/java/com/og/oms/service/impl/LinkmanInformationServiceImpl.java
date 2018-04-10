package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.og.oms.dao.LinkmanInformationMapper;
import com.og.oms.enums.LinkTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Linkman;
import com.og.oms.model.LinkmanInformation;
import com.og.oms.service.ILinkmanInformationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-11
 */
@Service
public class LinkmanInformationServiceImpl extends ServiceImpl<LinkmanInformationMapper, LinkmanInformation> implements ILinkmanInformationService {

	/**
	 * 新增或修改联系人的联系方式
	 * 
	 * @param userId
	 * @param linkman
	 * @param qq
	 * @param email
	 * @param phone
	 * @param mob
	 * @param vchat
	 * @param tg
	 * @param skype
	 * @param whatapp
	 * @param defaultContactType
	 * @return
	 */
	@Override
	public boolean addLinkmanInformation(Integer userId, Linkman linkman, String qq, String email, String mob,
			String phone, String vchat, String tg, String skype, String whatapp, Integer defaultContactType) {
		List<LinkmanInformation> entityList = new ArrayList<LinkmanInformation>();
		
		//删除所有联系人联系方式
		this.delByLinkmanId(linkman.getId());
		
		//根据输入的内容，增加对应的联系联系方式
		addContactInfo(linkman, qq, defaultContactType, 2, entityList);
		addContactInfo(linkman, email, defaultContactType, 3, entityList);
		addContactInfo(linkman, mob, defaultContactType, 4, entityList);
		addContactInfo(linkman, phone, defaultContactType, 5, entityList);
		addContactInfo(linkman, vchat, defaultContactType, 6, entityList);
		addContactInfo(linkman, tg, defaultContactType, 7, entityList);
		addContactInfo(linkman, skype, defaultContactType, 8, entityList);
		addContactInfo(linkman, whatapp, defaultContactType, 9, entityList);
		
		if(entityList.size()>0) {
			return this.insertBatch(entityList);
		} else {
			return false;
		}
		
	}

	/**
	 * 增加联系方式
	 * 
	 * @param linkman
	 * @param message
	 * @param defaultContactType
	 * @param type
	 * @param entityList
	 */
	private void addContactInfo(Linkman linkman, String message, Integer defaultContactType, Integer type,
			List<LinkmanInformation> entityList) {
		
		if(StringUtils.checkValNotNull(message)) {
			EntityWrapper<LinkmanInformation> wrapper = new EntityWrapper<>();
			wrapper.eq("message", message);
			wrapper.eq("type", type);
			if(this.selectCount(wrapper)>0) {
				throw new OmsException("联系方式"+LinkTypeEnum.getNameByCode(type)+"已存在！");
			}
			
			LinkmanInformation linkmanInformation = new LinkmanInformation();
			linkmanInformation.setLinkmanId(linkman.getId());
			linkmanInformation.setMessage(message);
			linkmanInformation.setType(type);
			if(defaultContactType.equals(type)) {
				linkmanInformation.setIsDefault(1);
			}
			entityList.add(linkmanInformation);
		}
	}

	@Override
	public void delByLinkmanId(Integer linkmanId) {
		Wrapper<LinkmanInformation> wrapper = new EntityWrapper<LinkmanInformation>();
		wrapper.eq("linkman_id", linkmanId);
		this.delete(wrapper);
	}

	@Override
	public List<LinkmanInformation> getLinkmanInfoByLinkmanId(Integer linkmanId) {
		Wrapper<LinkmanInformation> wrapper = new EntityWrapper<LinkmanInformation>();
		wrapper.eq("linkman_id", linkmanId);
		return this.selectList(wrapper);
	}
}
