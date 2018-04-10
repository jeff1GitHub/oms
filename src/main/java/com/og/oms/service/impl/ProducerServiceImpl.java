package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.ProducerMapper;
import com.og.oms.enums.BankEnum;
import com.og.oms.enums.LinkTypeEnum;
import com.og.oms.enums.ProducerEnum;
import com.og.oms.enums.ProducerTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Producer;
import com.og.oms.model.ProducerRolemap;
import com.og.oms.model.User;
import com.og.oms.service.ILinkmanService;
import com.og.oms.service.IProducerRolemapService;
import com.og.oms.service.IProducerService;
import com.og.oms.utils.EnumUtil;
import com.og.oms.utils.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements IProducerService {

	@Autowired
	private IProducerRolemapService producerRolemapService;
	
	@Autowired
	private ILinkmanService linkmanService;
	
    @Override
    public List<Map<String, Object>> getProducerList() {
    	//获取全部的供应商
        List<Map<String, Object>> list = this.baseMapper.getProducerList();

        Integer type;
        Integer banktype;
        for(Map<String, Object> map : list) {
            // 替换type
            type = (Integer) map.get("type");
            //用name替换code，用于页面展示
            for(LinkTypeEnum tmp : LinkTypeEnum.values()) {
                if(tmp.getCode() .equals(type)) {
                    map.put("type", tmp.getName());
                    continue;
                }
            }
            
            banktype = (Integer) map.get("banktype");
            for(BankEnum tmp : BankEnum.values()) {
                if(tmp.getCode().equals(banktype)) {
                    map.put("banktype", tmp.getName());
                    continue;
                }
            }
            //根据供应商ID获取供应商的角色
            List<ProducerRolemap> roleMaps = producerRolemapService.getRoleMapsByProducerId((Integer) map.get("id"));
            String producerTypeInfos = "";
            //将角色组装成页面展示用
            for(ProducerRolemap roleMap : roleMaps) {
            	String nameByCode = ProducerTypeEnum.getProducerTypeEnum(roleMap.getRoleId()).getName();
            	producerTypeInfos += nameByCode + " ";
            }
            map.put("producerTypeInfos", producerTypeInfos);
        }
        return list;

    }

    @Override
    public Producer getProducerById(Integer id) {
    	//根据id获得对应的供应商
    	Producer producer = this.selectById(id);
    	if(!StringUtils.isEmpty(producer.getLoginPassword())) {
    		producer.setLoginPassword(PasswordUtil.decrypt(producer.getLoginPassword()));
    	}
    	
    	//根据供应商id获取对应的角色
    	List<ProducerRolemap> roleMaps = producerRolemapService.getRoleMapsByProducerId(id);
    	//将角色entity转换为枚举，用于页面展示
    	List<ProducerTypeEnum> pts = new ArrayList<>();
    	roleMaps.forEach(roleMap -> {
    		ProducerTypeEnum producerTypeEnum = ProducerTypeEnum.getProducerTypeEnum(roleMap.getRoleId());
    		if(producerTypeEnum!=null) {
        		pts.add(producerTypeEnum);
    		}
    	});
    	producer.setProducerType(pts);
        return producer;
    }

    @Override
    @Transactional
    public boolean addProducer(User user, Producer producer) {
    	
    	// 判断供应商的支付宝唯一
    	EntityWrapper<Producer> wrapper = new EntityWrapper<>();
    	if(producer.getAliPay() != null) {
    		wrapper.eq("ali_pay", producer.getAliPay());
    		if(this.selectCount(wrapper) > 0) {
    			throw new OmsException("支付宝账号已经存在！");
    		}
    	}
    	
    	// 判断供应商的银行卡号唯一
    	if(producer.getBankAccount() != null) {
    		wrapper = new EntityWrapper<>();
    		wrapper.eq("bank_account", producer.getBankAccount());
    		if(this.selectCount(wrapper) > 0) {
    			throw new OmsException("银行卡号已经存在！");
    		}
    	}
    	
        // 保存供应商信息
    	if(!StringUtils.isEmpty(producer.getLoginPassword())) {
    		producer.setLoginPassword(PasswordUtil.encrypt(producer.getLoginPassword()));
    	}
        boolean ret = this.insert(producer);

        // 增加或修改供应商的角色
        producerRolemapService.addProducerRoles(user.getId(), producer.getProducerType(), producer.getId());

        // 增加供应商枚举
        ProducerEnum.addEnum(producer.getId(), producer.getName());
        return ret;
    }

    @Override
    public boolean updateProducer(User user, Producer producer) {
    	//更新供应商的信息
    	if(!StringUtils.isEmpty(producer.getLoginPassword())) {
    		producer.setLoginPassword(PasswordUtil.encrypt(producer.getLoginPassword()));
    	}
        boolean ret = this.updateById(producer);
        
        //增加或修改供应商的角色
        producerRolemapService.addProducerRoles(user.getId(), producer.getProducerType(), producer.getId());
        
        //修改供应商枚举用于显示的name
        this.updateProducerEnum(producer);
        return ret;
    }

    @Override
    @Transactional
    public boolean delProducer(User user, Integer id) {
    	//删除供应商对应的角色
    	producerRolemapService.delRoleMapByProducerId(id);
    	//删除供应商联系人
    	linkmanService.delLinkmanByProducerId(user, id);
    	
    	//删除供应商
    	boolean ret =this.deleteById(id);
    	//如果删除成功，删除对应的供应商枚举
         if(ret) {
        	 EnumUtil.clearEnum(ProducerEnum.class);
        	 this.initProducerEnum();
         };
         return ret; 
         
    }

    @Override
    public Map<Integer, String> getProducerSelect() {
		Map<Integer, String> map = new HashMap<>(ProducerEnum.values().length);
		for (ProducerEnum producerEnum : ProducerEnum.values()) {
            map.put(producerEnum.getCode(), producerEnum.getName());
        }
        return map;
    }

    @Override
    public void initProducerEnum() {
        for(Producer producer : this.selectList(null)) {
            ProducerEnum.addEnum(producer.getId(), producer.getName());
        }
    }

    /**
     * 修改供应商枚举
     *
     * @param producer
     */
    private void updateProducerEnum(Producer producer) {
        for(ProducerEnum anEnum : ProducerEnum.values()) {
            if(anEnum.getCode().equals(producer.getId())) {
                anEnum.setName(producer.getName());
                break;
            }
        }
    }

}
