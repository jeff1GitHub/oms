package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Deployment;
import com.og.oms.model.DeploymentIp;
import com.og.oms.model.Ip;
import com.og.oms.model.Server;
import com.og.oms.service.IDeploymentIpService;
import com.og.oms.service.IDeploymentService;
import com.og.oms.service.IIpService;
import com.og.oms.service.IServerService;
import com.og.oms.utils.JsonResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 服务部署资料前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-15
 */
@RestController
@RequestMapping("deployment")
@SessionAttributes(names = "userId", types = Integer.class)
public class DeploymentController extends BaseController {

	@Autowired
    private IDeploymentService deploymentService;
	
	@Autowired
    private IIpService ipService;
	
	@Autowired
	private IDeploymentIpService deploymentIpService;
	
	@Autowired
	private IServerService serverService;

    /**
     * 获取所有部署信息
     *
     * @return
     */
	@PermisAnnotation(id = 309, name = "部署信息-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getDeploymentList(Integer platform, Integer environment, Integer project, Integer service) {
		
        return new JsonResult(this.deploymentService.getDeploymentList(platform, environment, project, service));
    }

    /**
     * 通过id获取部署信息
     *
     * @param id
     * @return
     */
	@PermisAnnotation(id = 309, name = "部署信息-查看")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getDeploymentById(@PathVariable Integer id) {
    	
    	Deployment deployment = this.deploymentService.getDeploymentById(id);
    	JsonResult jsonResult = new JsonResult(deployment);
    	
    	
    	List<DeploymentIp> deploymentIps = deploymentIpService.getIpsByDeploymentId(id);
    	Server server = null;
    	Ip ipEntity = null;
    	for(DeploymentIp dIp : deploymentIps) {
    		ipEntity = ipService.getIpByIpStr(dIp.getIp());
    		server = serverService.getServerById(ipEntity.getServerId());
    		if(server != null) {
    			dIp.setServerName(server.getName());
    		}
    	}
    	
    	jsonResult.getOthers().put("ips", deploymentIps);

        return jsonResult;
    }

    /**
     * 增加服务器部署信息
     *
     * @param userId
     * @param deployment
     * @return
     */
	@PermisAnnotation(id = 30901, name = "部署信息-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addDeployment(@ModelAttribute("userId") Integer userId, Deployment deployment) {
        JsonResult ret;
        if(this.deploymentService.addDeployment(super.getUser(userId), deployment)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改服务部署信息
     *
     * @param userId
     * @param deployment
     * @return
     */
	@PermisAnnotation(id = 30902, name = "部署信息-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateDeployment(@ModelAttribute("userId") Integer userId, Deployment deployment) {
        JsonResult ret;
        if(this.deploymentService.updateDeployment(super.getUser(userId), deployment)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除服务部署信息
     *
     * @param userId
     * @param id
     * @return
     */
	@PermisAnnotation(id = 30903, name = "部署信息-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delDeployment(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(this.deploymentService.delDeployment(super.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
