package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.LinkmanMapper;
import com.og.oms.enums.LinkTypeEnum;
import com.og.oms.model.Linkman;
import com.og.oms.model.User;
import com.og.oms.service.ILinkmanInformationService;
import com.og.oms.service.ILinkmanService;

/**
 * <p>
 * 联系人服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
@Service
public class LinkmanServiceImpl extends ServiceImpl<LinkmanMapper, Linkman> implements ILinkmanService {
	
	@Autowired
	private LinkmanMapper linkmanMapper;
	
	@Autowired
	private ILinkmanInformationService linkmanInformationService;
	
    @Override
    public List<Linkman> getLinkmanList(Integer id) {
    	//根据供应商id，获取联系人列表，以及默认的联系方式
        List<Linkman> linkmanWithDefaulContactInfo = linkmanMapper.getLinkmanWithDefaulContactInfo(id);
        List<Linkman> linkmans = new ArrayList<>();
        //将默认联系方式的类型code,转换为具体内容如"QQ"用于在页面展示
        linkmanWithDefaulContactInfo.forEach(linkman -> {
        	if(linkman.getDefaultContactType() != null) {
        		linkman.setDefaultContactInfo(LinkTypeEnum.getNameByCode(linkman.getDefaultContactType())+": "+linkman.getDefaultContactInfo());
        	} else {
        		linkman.setDefaultContactInfo("");
        	}
        	linkmans.add(linkman);
        });
        return linkmans;
    }

    @Override
    public Linkman getLinkmanById(Integer id) {
        return this.selectById(id);
    }
    
    @Override
    public Linkman getLinkmanWithInfoById(Integer id) {
        return linkmanMapper.getLinkmanWithInfoById(id);
    }

    @Override
    public boolean addLinkman(User user, Linkman linkman, String qq, String email, String mob, String phone,
			String vchat, String tg, String skype, String whatapp, Integer defaultContactType) {
    	
    	boolean ret = this.insert(linkman);
    	
    	// 增加该联系人的联系信息
    	linkmanInformationService.addLinkmanInformation(user.getId(), linkman, qq, email, mob, phone, vchat, tg, skype, whatapp, defaultContactType);
    	
    	//如果该联系人选为默认，更新其他的为非默认
    	if(linkman.getIsDefault() == 1) {
    		this.updateLinkmanIsDefault(user, linkman);
    	}
    	
        return ret;
    }

    @Override
    public boolean updateLinkman(User user, Linkman linkman) {
        return this.updateById(linkman);
    }

    @Override
    public boolean delLinkman(User user, Integer id) {
    	// 删除联系人的所有联系方式
    	linkmanInformationService.delByLinkmanId(id);
    	
        return this.deleteById(id);
    }

    @Override
    @Transactional
    public boolean delLinkmanByProducerId(User user, Integer id) {
        EntityWrapper<Linkman> wrapper = new EntityWrapper<>();
        wrapper.eq("prodoucer", id);
        //获取所有联系人
        List<Linkman> selectList = this.selectList(wrapper);
        
        //删除所有联系人的所有联系方式
        for(Linkman linkman : selectList) {
        	linkmanInformationService.delByLinkmanId(linkman.getId());
        }
        return this.delete(wrapper);
    }

	@Override
	public boolean updateLinkmanIsDefault(User user, Linkman linkman) {
		//修改其他联系人为非默认
		linkmanMapper.updateLinkmanIsNotDefault(linkman.getProdoucer().getCode());
		return this.updateById(linkman);
	}

}
