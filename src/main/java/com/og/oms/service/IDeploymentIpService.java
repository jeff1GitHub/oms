package com.og.oms.service;

import com.og.oms.model.DeploymentIp;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jeff
 * @since 2017-12-06
 */
public interface IDeploymentIpService extends IService<DeploymentIp> {

	/**
	 * 根据部署ID获取部署ip
	 * 
	 * @param id
	 * @return
	 */
	List<DeploymentIp> getIpsByDeploymentId(Integer id);
	
}
