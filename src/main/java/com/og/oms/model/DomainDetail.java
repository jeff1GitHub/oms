package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-28
 */
@TableName("t_domain_detail")
public class DomainDetail extends Model<DomainDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private Integer id;
    /**
     * 主域id
     */
	@TableField("domain_id")
	private String domainId;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 解析类型
     */
	@TableField("analyze_type")
	private Integer analyzeType;
    /**
     * 解析地址
     */
	@TableField("analyze_addr")
	private String analyzeAddr;
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

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAnalyzeType() {
		return analyzeType;
	}

	public void setAnalyzeType(Integer analyzeType) {
		this.analyzeType = analyzeType;
	}

	public String getAnalyzeAddr() {
		return analyzeAddr;
	}

	public void setAnalyzeAddr(String analyzeAddr) {
		this.analyzeAddr = analyzeAddr;
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
		return "DomainDetail{" +
			"id=" + id +
			", domainId=" + domainId +
			", status=" + status +
			", analyzeType=" + analyzeType +
			", analyzeAddr=" + analyzeAddr +
			", createTime=" + createTime +
			", createUser=" + createUser +
			", updateTime=" + updateTime +
			", updateUser=" + updateUser +
			"}";
	}
}
