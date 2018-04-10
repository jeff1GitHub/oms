package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.DomainPurposeEnum;
import com.og.oms.enums.PlatformEnum;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 域名信息
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
@TableName("t_domain_main")
public class DomainMain extends Model<DomainMain> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private Integer id;
	/**
	 * 域名
	 */
	private String domain;
	
	/**
	 * 域名
	 */
	private PlatformEnum platform;
	/**
	 * 用途
	 */
	private DomainPurposeEnum purpose;
	/**
	 * 带公网HTTPS证书
	 */
	private Integer https;
	/**
	 * 带隐私保护
	 */
	private Integer privacy;
	/**
	 * 注册供应商网站
	 */
	@TableField("regedit_web")
	private String regeditWeb;
	/**
	 * 注册供应商帐号
	 */
	@TableField("regedit_account")
	private String regeditAccount;
	/**
	 * 解析供应商网站
	 */
	@TableField("resolution_web")
	private String resolutionWeb;
	/**
	 * 解析供应商帐号
	 */
	@TableField("resolution_account")
	private String resolutionAccount;
	/**
	 * 过期时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("end_time")
	private Date endTime;
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
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public PlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformEnum platform) {
		this.platform = platform;
	}

	public DomainPurposeEnum getPurpose() {
		return purpose;
	}

	public void setPurpose(DomainPurposeEnum purpose) {
		this.purpose = purpose;
	}

	public Integer getHttps() {
		return https;
	}

	public void setHttps(Integer https) {
		this.https = https;
	}

	public Integer getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Integer privacy) {
		this.privacy = privacy;
	}

	public String getRegeditWeb() {
		return regeditWeb;
	}

	public void setRegeditWeb(String regeditWeb) {
		this.regeditWeb = regeditWeb;
	}

	public String getRegeditAccount() {
		return regeditAccount;
	}

	public void setRegeditAccount(String regeditAccount) {
		this.regeditAccount = regeditAccount;
	}

	public String getResolutionWeb() {
		return resolutionWeb;
	}

	public void setResolutionWeb(String resolutionWeb) {
		this.resolutionWeb = resolutionWeb;
	}

	public String getResolutionAccount() {
		return resolutionAccount;
	}

	public void setResolutionAccount(String resolutionAccount) {
		this.resolutionAccount = resolutionAccount;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
		return "DomainMain{" +
				"id=" + id +
				", domain=" + domain +
				", platform=" + platform +
				", purpose=" + purpose +
				", https=" + https +
				", privacy=" + privacy +
				", regeditWeb=" + regeditWeb +
				", regeditAccount=" + regeditAccount +
				", resolutionWeb=" + resolutionWeb +
				", resolutionAccount=" + resolutionAccount +
				", endTime=" + endTime +
				", createTime=" + createTime +
				", createUser=" + createUser +
				", updateTime=" + updateTime +
				", updateUser=" + updateUser +
				"}";
	}
}
