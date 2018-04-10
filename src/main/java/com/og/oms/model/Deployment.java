package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-15
 */
@TableName("t_deployment")
public class Deployment extends Model<Deployment> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 服务平台
     */
	private DeploymentPlatformEnum platform;
    /**
     * 环境
     */
	private EnvironmentEnum environment;
    /**
     * 项目名
     */
	private ProjectEnum project;
    /**
     * 工程
     */
	private ServiceTypeEnum service;
    /**
     * 平台内工程编号
     */
	@TableField("project_name")
	private Integer projectName;
    /**
     * 占用系统端口 @废弃
     */
	private Integer port;
    /**
     * 服务器id @废弃
     */
	private Integer server;
    /**
     * 测试方式
     */
	private String test;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 修改人
     */
	@TableField("update_user")
	private String updateUser;
	
	/**
     * 部署IP @废弃
     */
	@TableField("deployed_ip")
	private String deployedIp;
	
	/**
	 * 部署ip
	 */
	@TableField(exist = false)
	private List<String> deploymentIp;
	
	/**
	 * 部署端口
	 */
	@TableField(exist = false)
	private List<Integer> ports;
	
	public List<String> getDeploymentIp() {
		return deploymentIp;
	}

	public void setDeploymentIp(List<String> deploymentIp) {
		this.deploymentIp = deploymentIp;
	}

	public List<Integer> getPorts() {
		return ports;
	}

	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	public String getDeployedIp() {
		return deployedIp;
	}
	
	public void setDeployedIp(String deployedIp) {
		this.deployedIp = deployedIp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DeploymentPlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(DeploymentPlatformEnum platform) {
		this.platform = platform;
	}

	public EnvironmentEnum getEnvironment() {
		return environment;
	}

	public void setEnvironment(EnvironmentEnum environment) {
		this.environment = environment;
	}

	public ProjectEnum getProject() {
		return project;
	}

	public void setProject(ProjectEnum project) {
		this.project = project;
	}

	public ServiceTypeEnum getService() {
		return service;
	}

	public void setService(ServiceTypeEnum service) {
		this.service = service;
	}

	public Integer getProjectName() {
		return projectName;
	}

	public void setProjectName(Integer projectName) {
		this.projectName = projectName;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getServer() {
		return server;
	}

	public void setServer(Integer server) {
		this.server = server;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Deployment{" +
			"id=" + id +
			", platform=" + platform +
			", environment=" + environment +
			", project=" + project +
			", service=" + service +
			", projectName=" + projectName +
			", port=" + port +
			", server=" + server +
			", test=" + test +
			", createTime=" + createTime +
			", createUser=" + createUser +
			", updateTime=" + updateTime +
			", updateUser=" + updateUser +
			"}";
	}
}
