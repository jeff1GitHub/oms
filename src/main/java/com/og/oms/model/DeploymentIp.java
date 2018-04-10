package com.og.oms.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-12-06
 */
@TableName("t_deployment_ip")
public class DeploymentIp extends Model<DeploymentIp> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
	private Integer id;
    /**
     * 部署信息id
     */
	@TableField("deployment_id")
	private Integer deploymentId;
    /**
     * ip
     */
	private String ip;
    /**
     * 端口号
     */
	private Integer port;
    /**
     * 创建用户
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
	
	@TableField(exist = false)
	private String serverName;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(Integer deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DeploymentIp{" +
			"id=" + id +
			", deploymentId=" + deploymentId +
			", ip=" + ip +
			", port=" + port +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
