package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.IpTypeEnum;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Ip;
import com.og.oms.model.Server;
import com.og.oms.service.IIpService;
import com.og.oms.service.IServerService;
import com.og.oms.utils.JsonResult;
import com.og.oms.utils.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
@RestController
@RequestMapping("server")
@SessionAttributes(names = "userId", types = Integer.class)
public class ServerController extends BaseController {

	@Autowired
    private IServerService serverService;
	
	@Autowired
    private IIpService ipService;

    /**
     * 获取服务器列表
     *
     * @return
     */
//    @PermisAnnotation(id = 301, name = "服务器资料")
//    @RequestMapping(method = RequestMethod.GET)
//    public JsonResult getServerList() {
//        return new JsonResult(serverService.getServerList());
//    }
    
    /**
     * 根据条件查询服务器列表
     * 
     * @param serverCode 服务器编号
     * @param startDay 购置开始时间
     * @param endDay 过期时间
     * @return
     */
    @PermisAnnotation(id = 301, name = "服务器资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getserverList(String serverCode, String startDay, String endDay, String linkAddr) {
    	return new JsonResult(serverService.getServerList(serverCode, startDay, endDay, linkAddr));
    }

    /**
     * 通过id获取服务器信息
     *
     * @param id 服务器id
     * @return
     */
    @PermisAnnotation(id = 301, name = "服务器资料-查看")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getServerById(@PathVariable Integer id) {
    	
    	Server serverInfo = serverService.getServerById(id);
    	
    	//通过serverId获取对应的 外网Ip 和内网Ip
    	String interIps = "";
    	String intraIps = "";
    	List<Ip> ips = ipService.getIpListByServerId(id);
    	for(Ip ip : ips) {
    		if(ip.getType() == IpTypeEnum.INTER) {
    			interIps += ip.getIp() + ",\n";
    		} else {
    			intraIps += ip.getIp() + ",\n";
    		}
    	}
    	//外网ips
    	String interIpss = (!"".equals(interIps))?interIps.substring(0, interIps.length()-2):"";
    	//内网ips
    	String intraIpss = (!"".equals(intraIps))?intraIps.substring(0, intraIps.length()-2):"";
    	JsonResult jsonResult = new JsonResult(serverInfo);
    	
    	jsonResult.getOthers().put("interIps", interIpss);
    	jsonResult.getOthers().put("intraIps", intraIpss);
    	
        return jsonResult;
    }

    /**
     * 增加服务器信息
     *
     * @param userId
     * @param server
     * @return
     */
    @PermisAnnotation(id = 30101, name = "服务器资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addServer(@ModelAttribute("userId") Integer userId, @Valid Server server, BindingResult result,
    		@RequestParam("intraIps") String intraIps, @RequestParam("interIps") String interIps) {
        JsonResult ret;
        if(serverService.addServer(this.getUser(userId), server)) {
        	//TODO 放置在Controller层可以Service解耦，但是会出现事务问题，将事务放到Controller层比较...需要研究
            ipService.addIp(this.getUser(userId), intraIps, interIps, server.getId()); 
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        
        
        return ret;
    }

    /**
     * 修改服务器信息
     *
     * @param userId
     * @param server
     * @return
     */
    @PermisAnnotation(id = 30102, name = "服务器资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateServer(@ModelAttribute("userId") Integer userId, @Valid Server server, BindingResult result,
    		@RequestParam("intraIps") String intraIps, @RequestParam("interIps") String interIps) {
        JsonResult ret;
        if(serverService.updateServer(this.getUser(userId), server)) {
        	//删除server所有的IP，然后重新插入
            ipService.delIpByServerId(server.getId());
            ipService.addIp(this.getUser(userId), intraIps, interIps, server.getId());
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        
        return ret;
    }

    /**
     * 删除服务器
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30103, name = "服务器资料-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delServer(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(serverService.delServer(this.getUser(userId), id)) {
        	ipService.delIpByServerId(id);
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
