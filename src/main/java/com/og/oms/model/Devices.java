package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网络设备
 * </p>
 *
 * @author jeff
 * @since 2017-10-27
 */
@TableName("t_devices")
public class Devices extends Model<Devices> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 供应商
     */
	private ProducerEnum producer;
    /**
     * 品牌
     */
	private BrandEnum brand;
    /**
     * 平台
     */
	private PlatformEnum platform;
    /**
     * 类型
     */
	private DevicesTypeEnum type;
    /**
     * 型号
     */
	private String code;
    /**
     * 落地机房
     */
	private ServerRoomEnum serverroom;
    /**
     * 管理ip
     */
    @NotNull
	@Length(max = 15)
	private String ip;
    /**
     * 管理端口
     */
    @NotNull
	@Max(65535)
	private Integer port;
    /**
     * 管理帐号
     */
    @NotNull
	@Length(max = 30)
	private String account;
    /**
     * 管理密码
     */
    @NotNull
	@Length(max = 30)
	private String password;
    /**
     * 序列号
     */
	@Length(max = 30)
	private String number;
    /**
     * 添加人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 添加时间
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
	
	/**
	 * 详情设定
	 */
	@TableField("setting")
	private String setting;
	
	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProducerEnum getProducer() {
		return producer;
	}

	public void setProducer(ProducerEnum producer) {
		this.producer = producer;
	}

	public BrandEnum getBrand() {
		return brand;
	}

	public void setBrand(BrandEnum brand) {
		this.brand = brand;
	}

	public PlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformEnum platform) {
		this.platform = platform;
	}

	public DevicesTypeEnum getType() {
		return type;
	}

	public void setType(DevicesTypeEnum type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ServerRoomEnum getServerroom() {
		return serverroom;
	}

	public void setServerroom(ServerRoomEnum serverroom) {
		this.serverroom = serverroom;
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

	public void setPort(Integer prot) {
		this.port = prot;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return "Devices{" +
			"id=" + id +
			", producer=" + producer +
			", brand=" + brand +
			", platform=" + platform +
			", type=" + type +
			", code=" + code +
			", serverroom=" + serverroom +
			", ip=" + ip +
			", prot=" + port +
			", account=" + account +
			", password=" + password +
			", number=" + number +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", setting=" + setting +
			"}";
	}
}
