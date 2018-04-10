package com.og.oms.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Ip;
import com.og.oms.model.Server;
import com.og.oms.service.IIpService;
import com.og.oms.service.IServerService;
import com.og.oms.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-16
 */
@RestController
public class IpController {
	
	@Autowired
	private IIpService ipService;
	
	@Autowired
	private IServerService serverService;
	
	@RequestMapping(value = "ip", method = RequestMethod.GET)
	public JsonResult getIp(String ip) {
		Ip ipEntity = ipService.getIpByIpStr(ip);
		if(null == ipEntity) {
            return new JsonResult(ResultCode.EXCEPTION, "IP不存在！");
		}
		// 根据ip所在serverId 获取 serverName
		Server server = serverService.getServerById(ipEntity.getServerId());
		if(null == server) {
			return new JsonResult(ResultCode.EXCEPTION, "服务器不存在！");
		}
		
		return new JsonResult(server.getName());
	}
}
