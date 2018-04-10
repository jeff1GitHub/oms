package com.og.oms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.og.oms.model.Devices;
import com.og.oms.model.Producer;
import com.og.oms.model.Server;
import com.og.oms.model.User;
import com.og.oms.service.IDevicesService;
import com.og.oms.service.IProducerService;
import com.og.oms.service.IServerService;
import com.og.oms.service.IUserService;
import com.og.oms.utils.JsonResult;
import com.og.oms.utils.PasswordUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
@RestController
public class AttackMessageController {
	
	@Autowired
	private IServerService serverService;
	@Autowired
	private IProducerService producerService;
	@Autowired
	private IDevicesService deviceService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/encode")
	public JsonResult encodePasswordTemp() {
		// 加密服务器资料
    	List<Server> serverList = serverService.getServerList();
    	for(Server server : serverList) {
    		if(null != server.getPassword() && server.getPassword().length()>0) {
    			server.setPassword(PasswordUtil.encrypt(server.getPassword()));
    		}
    		if(null != server.getSuperPassword() && server.getSuperPassword().length()>0) {
    			server.setSuperPassword(PasswordUtil.encrypt(server.getSuperPassword()));
    		}
    		if(null != server.getIdarcPassword() && server.getIdarcPassword().length()>0) {
    			server.setIdarcPassword(PasswordUtil.encrypt(server.getIdarcPassword()));
    		}
    	}
    	serverService.updateBatchById(serverList);
    	
    	// 加密供应商密码
    	List<Producer> producerList = producerService.selectList(null);
    	for(Producer producer : producerList) {
    		if(null != producer.getLoginPassword() && producer.getLoginPassword().length()>0) {
    			producer.setLoginPassword(PasswordUtil.encrypt(producer.getLoginPassword()));
    		}
    	}
    	producerService.updateBatchById(producerList);
    	
    	//加密网设管理密码
    	List<Devices> devicesList = deviceService.getDevicesList();
    	for(Devices device : devicesList) {
    		if(null != device.getPassword() && device.getPassword().length()>0) {
    			device.setPassword(PasswordUtil.encrypt(device.getPassword()));
    		}
    	}
    	deviceService.updateBatchById(devicesList);
    	
    	// 加密员工登录密码
    	List<User> selectList = userService.selectList(null);
    	for(User user : selectList) {
    		if(null != user.getPassword() && user.getPassword().length()>0) {
    			user.setPassword(PasswordUtil.encrypt(user.getPassword()));
    		}
    	}
    	userService.updateBatchById(selectList);
    	
    	
    	return new JsonResult();
    }
	
	@RequestMapping("/decode")
	public JsonResult decodePasswordTemp() {
		// 加密服务器资料
    	List<Server> serverList = serverService.getServerList();
    	for(Server server : serverList) {
    		if(null != server.getPassword() && server.getPassword().length()>0) {
    			server.setPassword(PasswordUtil.decrypt(server.getPassword()));
    		}
    		if(null != server.getSuperPassword() && server.getSuperPassword().length()>0) {
    			server.setSuperPassword(PasswordUtil.decrypt(server.getSuperPassword()));
    		}
    		if(null != server.getIdarcPassword() && server.getIdarcPassword().length()>0) {
    			server.setIdarcPassword(PasswordUtil.decrypt(server.getIdarcPassword()));
    		}
    	}
    	serverService.updateBatchById(serverList);
    	
    	// 加密供应商密码
    	List<Producer> producerList = producerService.selectList(null);
    	for(Producer producer : producerList) {
    		if(null != producer.getLoginPassword() && producer.getLoginPassword().length()>0) {
    			producer.setLoginPassword(PasswordUtil.decrypt(producer.getLoginPassword()));
    		}
    	}
    	producerService.updateBatchById(producerList);
    	
    	//加密网设管理密码
    	List<Devices> devicesList = deviceService.getDevicesList();
    	for(Devices device : devicesList) {
    		if(null != device.getPassword() && device.getPassword().length()>0) {
    			device.setPassword(PasswordUtil.decrypt(device.getPassword()));
    		}
    	}
    	deviceService.updateBatchById(devicesList);
    	
    	// 加密员工登录密码
    	List<User> selectList = userService.selectList(null);
    	for(User user : selectList) {
    		if(null != user.getPassword() && user.getPassword().length()>0) {
    			user.setPassword(PasswordUtil.decrypt(user.getPassword()));
    		}
    	}
    	userService.updateBatchById(selectList);
    	
    	
    	return new JsonResult();
    }
}
