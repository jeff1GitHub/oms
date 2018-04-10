package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.IpMapper;
import com.og.oms.enums.IpTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Alias;
import com.og.oms.model.Ip;
import com.og.oms.model.User;
import com.og.oms.service.IIpService;
import com.og.oms.utils.CommUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oscar
 * @since 2017-11-16
 */
@Service
public class IpServiceImpl extends ServiceImpl<IpMapper, Ip> implements IIpService {

	/**
	 * 增加Ip地址
	 * 
	 * @param user 操作用户
	 * @param interIp 公网IP串，如1.1.1.1,2.2.2.2
	 * @param intraIp 内网IP串，如1.1.1.1,2.2.2.2
	 * @param serverId 对应的服务器ID
	 * @return
	 * @see com.og.oms.service.IIpService#addIp(com.og.oms.model.User, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean addIp(User user, String intraIp, String interIp, Integer serverId) {
		List<Ip> entityList = new ArrayList<>();
		
		List<String> checkExistList = new ArrayList<>();
		
		//分割内网IP串组成单个IP Entity
		if(!StringUtils.isEmpty(intraIp)) {
			intraIp = intraIp.replace("\n","").replace("，", ",");
			List<String> intraIps = Arrays.asList(intraIp.split(","));
			intraIps.forEach(ipStr -> {  
	            Ip ip = new Ip();
	            ip.setIp(ipStr);
	            ip.setType(IpTypeEnum.INTRA);
	            ip.setServerId(serverId);
	            ip.setCreateTime(new Date());
	            ip.setCreateUser(user.getAccount());
	            entityList.add(ip);
	            checkExistList.add(ipStr);
	        });
		}
		
		//分割外网IP串组成单个IP Entity
		if(!StringUtils.isEmpty(interIp)) {
			interIp = interIp.replace("\n","").replace("，", ",");
			List<String> interIps = Arrays.asList(interIp.split(","));
			interIps.forEach(ipStr -> {  
				Ip ip = new Ip();
				Ip ipCk = new Ip();
	            ip.setIp(ipStr);
	            ip.setType(IpTypeEnum.INTER);
	            ip.setServerId(serverId);
	            ip.setCreateTime(new Date());
	            ip.setCreateUser(user.getAccount());
	            entityList.add(ip);
	            ipCk.setIp(ipStr);
	            checkExistList.add(ipStr);
	        });
		}
		
		if(CommUtil.cheakListIsRepeat(checkExistList)) {
			throw new OmsException("IP重复，请检查输入！");
		}
		
		EntityWrapper<Ip> wrapper = new EntityWrapper<>();
		// checkExistList至少需要一条，否则会查出全部
		checkExistList.add("-1.-1.-1.-1");
		wrapper.in("ip", checkExistList);
		int count = this.selectCount(wrapper);
		if(count > 0) {
			throw new OmsException("IP已存在!");
		}
		
		if(entityList.size()>0) {
			return this.insertBatch(entityList);
		}
		
		return false;
	}
	
    /**
     * 通过ServerId获取ip
     * 
     * @param serverId 服务器ID
     * @return 对应服务器的所有ip
     * 
	 * @see com.og.oms.service.IIpService#getIpListByServerId(java.lang.Integer)
	 */
	@Override
	public List<Ip> getIpListByServerId(Integer serverId) {
		EntityWrapper<Ip> wrapper = new EntityWrapper<Ip>();
		wrapper.eq("server_id", serverId);
        return this.selectList(wrapper);
	}

	/**
	 * 根据服务器ID删除关联的IP
	 * 
	 * @param serverId 服务器ID
	 * @return 
	 * @see com.og.oms.service.IIpService#delIpByServerId(java.lang.Integer)
	 */
	@Override
	public boolean delIpByServerId(Integer serverId) {
		EntityWrapper<Ip> wrapper = new EntityWrapper<Ip>();
		wrapper.eq("server_id", serverId);
		return this.delete(wrapper);
	}

	@Override
	public Ip getIpByIpStr(String ip) {
		EntityWrapper<Ip> wrapper = new EntityWrapper<>();
		wrapper.in("ip", ip);
		return this.selectOne(wrapper);
	}
}
