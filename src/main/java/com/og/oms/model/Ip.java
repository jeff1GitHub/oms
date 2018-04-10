package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.IpTypeEnum;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-16
 */
@TableName("t_ip")
public class Ip extends Model<Ip> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
	private Integer id;
    /**
     * ip
     */
	private String ip;
    /**
     * ip类型
     */
	private IpTypeEnum type;
    /**
     * 服务器id
     */
	@TableField("server_id")
	private Integer serverId;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public IpTypeEnum getType() {
		return type;
	}

	public void setType(IpTypeEnum type) {
		this.type = type;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
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
		return "Ip{" +
			"id=" + id +
			", ip=" + ip +
			", type=" + type.getCode() +
			", serverId=" + serverId +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
