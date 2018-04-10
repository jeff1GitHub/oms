package com.og.oms.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Ip;
import com.og.oms.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-16
 */
public interface IIpService extends IService<Ip> {

    /**
     * 通过ServerId获取ip
     * 
     * @param serverId 服务器ID
     * @return 对应服务器的所有ip
     */
    List<Ip> getIpListByServerId(Integer serverId);

	/**
	 * 增加Ip地址
	 * 
	 * @param user 操作用户
	 * @param interIp 公网IP串，如1.1.1.1,2.2.2.2
	 * @param intraIp 内网IP串，如1.1.1.1,2.2.2.2
	 * @param serverId 对应的服务器ID
	 * @return
	 */
	boolean addIp(User user, String interIp, String intraIp, Integer serverId);

	/**
	 * 根据服务器ID删除关联的IP
	 * 
	 * @param serverId 服务器ID
	 * @return 
	 */
	boolean delIpByServerId(Integer serverId);

	/**
	 * 根据ip串查找ip相关内容
	 * 
	 * @param ip
	 * @return
	 */
	Ip getIpByIpStr(String ip);

}
