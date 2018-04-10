package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
@TableName("t_network")
public class Network extends Model<Network> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private Integer id;
    /**
     * 所属平台
     */
    @NotNull
	private PlatformEnum platform;
    /**
     * 线路品牌
     */
    @NotNull
	private NetworkBrandEnum brand;
    /**
     * 线路类型
     */
    @NotNull
	private NetworkTypeEnum type;
    /**
     * 带宽大小
     */
    @NotNull
	@Min(0)
	private Integer bandwidth;
    /**
     * ip
     */
    @NotEmpty
	private String ip;
    /**
     * 作用范围
     */
	private String range;
    /**
     * 合同
     */
    @NotEmpty
	private String contract;
    /**
     * 供应商
     */
    @NotNull
	private ProducerEnum producer;
    /**
     * 落地机房
     */
    @NotNull
	private ServerRoomEnum serverroom;
    /**
     * 购买时间
     */
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("start_time")
	private Date startTime;
    /**
     * 到期时间
     */
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("end_time")
	private Date endTime;
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
    /**
     * 修改人
     */
	@TableField("update_user")
	private String updateUser;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformEnum platform) {
		this.platform = platform;
	}

	public NetworkBrandEnum getBrand() {
		return brand;
	}

	public void setBrand(NetworkBrandEnum brand) {
		this.brand = brand;
	}

	public NetworkTypeEnum getType() {
		return type;
	}

	public void setType(NetworkTypeEnum type) {
		this.type = type;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public ProducerEnum getProducer() {
		return producer;
	}

	public void setProducer(ProducerEnum producer) {
		this.producer = producer;
	}

	public ServerRoomEnum getServerroom() {
		return serverroom;
	}

	public void setServerroom(ServerRoomEnum serverroom) {
		this.serverroom = serverroom;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Network{" +
			"id=" + id +
			", platform=" + platform +
			", brand=" + brand +
			", type=" + type +
			", bandwidth=" + bandwidth +
			", ip=" + ip +
			", range=" + range +
			", contract=" + contract +
			", producer=" + producer +
			", serverroom=" + serverroom +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			"}";
	}
}
