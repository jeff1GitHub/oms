package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.DeploymentMapper;
import com.og.oms.enums.ProjectEnum;
import com.og.oms.enums.ServiceTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Deployment;
import com.og.oms.model.DeploymentIp;
import com.og.oms.model.Ip;
import com.og.oms.model.User;
import com.og.oms.service.IDeploymentIpService;
import com.og.oms.service.IDeploymentService;
import com.og.oms.service.IIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-15
 */
@Service
public class DeploymentServiceImpl extends ServiceImpl<DeploymentMapper, Deployment> implements IDeploymentService {
	
	@Autowired
	private IDeploymentIpService deploymentIpService;
	
	@Autowired
	private IIpService ipService;

    @Override
    public List<Deployment> getDeploymentList(Integer platform, Integer environment, Integer project, Integer service) {
    	EntityWrapper<Deployment> wrapper = new EntityWrapper<>();
    	if(platform != null) {
    		wrapper.eq("platform", platform);
		}
		if(environment != null) {
			wrapper.eq("environment", environment);
		}
		if(project != null) {
			wrapper.eq("project", project);
		}
    	if(service != null) {
    		wrapper.eq("service", service);
    	}
    	
    	List<Deployment> selectList = super.selectList(wrapper);
//    	List<Map<String, Object>> selectMaps = this.selectMaps(wrapper);
    	
    	/* 
    	 * TODO Integer大于127问题，临时解决方案，需处理 
    	 * mybatis映射127以上的code到枚举类的时候，因为Integer的==比较问题导致枚举类属性为null
    	 * 
    	 */
//    	selectList.forEach(s ->{
//    		selectMaps.forEach(y -> {
//    			if(s.getId().equals(y.get("id"))) {
//    				if(y.get("project")!=null) {
//    					s.setProject(ProjectEnum.getProjectEnum((Integer)y.get("project")));
//    				}
//    				if(y.get("service")!=null) {
//    					s.setService(ServiceTypeEnum.getServiceTypeEnum((Integer)y.get("service")));
//    				}
//
//    			}
//    		});
//    	});
    	
        return selectList;
    }

    @Override
    public Deployment getDeploymentById(Integer id) {
    	
    	Deployment deployment = super.selectById(id);
//    	Map<String, Object> resultMap = this.baseMapper.getDeplymentById(id);
//
//    	/*
//    	 * TODO Integer大于127问题，临时解决方案，需处理
//    	 * com.baomidou.mybatisplus.toolkit.EnumUtils 的方法 valueOf
//    	 * 当value为非String的时候，使用==判断, Integer 大于127会不等，应该用equals
//    	 *
//    	 */
//    	if(resultMap.get("project")!=null) {
//    		deployment.setProject(ProjectEnum.getProjectEnum((Integer)resultMap.get("project")));
//    	}
//    	if(resultMap.get("service")!=null) {
//    		deployment.setService(ServiceTypeEnum.getServiceTypeEnum((Integer)resultMap.get("service")));
//    	}
    	
    	EntityWrapper<DeploymentIp> wrapper = new EntityWrapper<>();
    	wrapper.eq("deployment_id", id);
    	List<DeploymentIp> deploymentIps = deploymentIpService.selectList(wrapper);
    	
    	
    	List<String> ips = new ArrayList<>();
    	List<Integer> ports = new ArrayList<>();
    	for(DeploymentIp dIp : deploymentIps) {
    		ips.add(dIp.getIp());
    		ports.add(dIp.getPort()); 
    	}
    	
    	deployment.setDeploymentIp(ips);
    	deployment.setPorts(ports);
    	
        return deployment;
    }

    @Transactional
    @Override
    public boolean addDeployment(User user, Deployment deployment) {
    	List<String> deploymentIps = deployment.getDeploymentIp();
    	List<Integer> ports = deployment.getPorts();
    	
    	super.insert(deployment);
    	
    	// ip与port是否数量相同
    	if(null != deploymentIps && null != ports) {
    		if(ports.size() != deploymentIps.size()) {
    			throw new OmsException("IP与端口必须成对设置！");
    		} else {
    			
    			// 检查ip是否存在
    	    	EntityWrapper<Ip> wrapper = new EntityWrapper<>(); 
    	    	wrapper.in("ip", deploymentIps);
    	    	List<Ip> ipList = ipService.selectList(wrapper);
    	    	if(null == ipList || ipList.size() ==0 || ipList.size() < deploymentIps.size()) {
    	    		throw new OmsException("IP不存在!");
    	    	}
    	    	
    	    	DeploymentIp deploymentIp = null;
    			for(int i = 0; i < deploymentIps.size(); i++) {
    				deploymentIp = new DeploymentIp();
    				deploymentIp.setDeploymentId(deployment.getId());
    				deploymentIp.setIp(deploymentIps.get(i));
    				deploymentIp.setPort(ports.get(i));
    				deploymentIp.setCreateTime(new Date());
    				deploymentIp.setCreateUser(user.getAccount());
    				deploymentIpService.insert(deploymentIp);
    			}
    		}
    	}
    	return true;
    }

    @Override
    public boolean updateDeployment(User user, Deployment deployment) {
    	
    	super.updateById(deployment);
    	
    	List<String> deploymentIps = deployment.getDeploymentIp();
    	List<Integer> ports = deployment.getPorts();
    	
    	Integer deploymentId = deployment.getId();
    	
    	EntityWrapper<DeploymentIp> wrapper1 = new EntityWrapper<>();
    	wrapper1.eq("deployment_id", deploymentId);
    	deploymentIpService.delete(wrapper1);
    	
    	// ip与port是否数量相同
    	if(null != deploymentIps && null != ports) {
    		if(ports.size() != deploymentIps.size()) {
    			throw new OmsException("IP与端口必须成对设置！");
    		} else {
    			
    			// 检查ip是否存在
    	    	EntityWrapper<Ip> wrapper = new EntityWrapper<>();
    	    	wrapper.in("ip", deploymentIps);
    	    	List<Ip> ipList = ipService.selectList(wrapper);
    	    	if(null == ipList || ipList.size() ==0 || ipList.size() < deploymentIps.size()) {
    	    		throw new OmsException("IP不存在!");
    	    	}
    	    	
    	    	DeploymentIp deploymentIp = null;
    			for(int i = 0; i < deploymentIps.size(); i++) {
    				deploymentIp = new DeploymentIp();
    				deploymentIp.setDeploymentId(deploymentId);
    				deploymentIp.setIp(deploymentIps.get(i));
    				deploymentIp.setPort(ports.get(i));
    				deploymentIp.setCreateTime(new Date());
    				deploymentIp.setCreateUser(user.getAccount());
    				deploymentIpService.insert(deploymentIp);
    			}
    		}
    	}
    	return true;
    }

    @Override
    public boolean delDeployment(User user, Integer id) {
        return super.deleteById(id);
    }
}
