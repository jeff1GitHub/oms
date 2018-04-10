package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.OperLogTypeEnum;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
@TableName("t_operate_log")
public class OperateLog extends Model<OperateLog> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 用户账号
     */
	@TableField("user_account")
	private String userAccount;
    /**
     * 操作类型
     */
	@TableField("log_type")
	private OperLogTypeEnum logType;
    /**
     * 日志内容
     */
	private String context;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public OperLogTypeEnum getLogType() {
		return logType;
	}

	public void setLogType(OperLogTypeEnum logType) {
		this.logType = logType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
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
		return "OperateLog{" +
			"id=" + id +
			", userId=" + userId +
			", userAccount=" + userAccount +
			", logType=" + logType.getName() +
			", context=" + context +
			", createTime=" + createTime +
			"}";
	}
}
