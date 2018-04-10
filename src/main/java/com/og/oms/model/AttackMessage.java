package com.og.oms.model;

import java.io.Serializable;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
@TableName("t_attack_message")
public class AttackMessage extends Model<AttackMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
	private Integer id;
    /**
     * 攻击id
     */
	@TableField("attack_message_id")
	private Integer attackMessageId;
    /**
     * 域名
     */
	@TableField("attack_message_domain")
	private String attackMessageDomain;
    /**
     * 攻击次数
     */
	@TableField("attack_message_num")
	private Integer attackMessageNum;
    /**
     * 攻击强度
     */
	@TableField("attack_message_intensity")
	private Integer attackMessageIntensity;
    /**
     * 负责人
     */
	@TableField("attack_message_director")
	private String attackMessageDirector;
    /**
     * 攻击时间
     */
	@TableField("attack_message_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attackMessageTime;
    /**
     * 备注
     */
	@TableField("attack_message_remark")
	private String attackMessageRemark;
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

	public Integer getAttackMessageId() {
		return attackMessageId;
	}

	public void setAttackMessageId(Integer attackMessageId) {
		this.attackMessageId = attackMessageId;
	}

	public String getAttackMessageDomain() {
		return attackMessageDomain;
	}

	public void setAttackMessageDomain(String attackMessageDomain) {
		this.attackMessageDomain = attackMessageDomain;
	}

	public Integer getAttackMessageNum() {
		return attackMessageNum;
	}

	public void setAttackMessageNum(Integer attackMessageNum) {
		this.attackMessageNum = attackMessageNum;
	}

	public Integer getAttackMessageIntensity() {
		return attackMessageIntensity;
	}

	public void setAttackMessageIntensity(Integer attackMessageIntensity) {
		this.attackMessageIntensity = attackMessageIntensity;
	}

	public String getAttackMessageDirector() {
		return attackMessageDirector;
	}

	public void setAttackMessageDirector(String attackMessageDirector) {
		this.attackMessageDirector = attackMessageDirector;
	}

	public Date getAttackMessageTime() {
		return attackMessageTime;
	}

	public void setAttackMessageTime(Date attackMessageTime) {
		this.attackMessageTime = attackMessageTime;
	}

	public String getAttackMessageRemark() {
		return attackMessageRemark;
	}

	public void setAttackMessageRemark(String attackMessageRemark) {
		this.attackMessageRemark = attackMessageRemark;
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
		return "AttackMessage{" +
			"id=" + id +
			", attackMessageId=" + attackMessageId +
			", attackMessageDomain=" + attackMessageDomain +
			", attackMessageNum=" + attackMessageNum +
			", attackMessageIntensity=" + attackMessageIntensity +
			", attackMessageDirector=" + attackMessageDirector +
			", attackMessageTime=" + attackMessageTime +
			", attackMessageRemark=" + attackMessageRemark +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			"}";
	}
}
