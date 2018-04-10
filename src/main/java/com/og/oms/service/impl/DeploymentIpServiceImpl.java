package com.og.oms.service.impl;

import com.og.oms.model.DeploymentIp;
import com.og.oms.dao.DeploymentIpMapper;
import com.og.oms.service.IDeploymentIpService;
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
 * @since 2017-12-06
 */
@Service
public class DeploymentIpServiceImpl extends ServiceImpl<DeploymentIpMapper, DeploymentIp> implements IDeploymentIpService {

	@Override
	public List<DeploymentIp> getIpsByDeploymentId(Integer id) {
		EntityWrapper<DeploymentIp> wrapper = new EntityWrapper<>();
		wrapper.eq("deployment_id", id);
		return this.selectList(wrapper);
	}
}
