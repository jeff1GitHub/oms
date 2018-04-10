package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-27
 */
@TableName("t_task_remark")
public class TaskRemark extends Model<TaskRemark> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
	private Integer id;
    /**
     * 任务id
     */
	@TableField("task_id")
	private Integer taskId;
    /**
     * 创建人账号
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建人用户组
     */
	@TableField("create_group")
	private String createGroup;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 备注内容
     */
	private String remark;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateGroup() {
		return createGroup;
	}

	public void setCreateGroup(String createGroup) {
		this.createGroup = createGroup;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TaskRemark{" +
			"id=" + id +
			", taskId=" + taskId +
			", createUser=" + createUser +
			", createGroup=" + createGroup +
			", createTime=" + createTime +
			", remark=" + remark +
			"}";
	}
}
