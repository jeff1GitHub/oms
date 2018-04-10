package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.WorkStatusEnum;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
@TableName("t_task_log")
public class TaskLog extends Model<TaskLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
	private Integer id;
    /**
     * 任务id
     */
	@TableField("task_id")
	private Integer taskId;
    /**
     * 内容
     */
	private String message;
    /**
     * 类型
     */
	private WorkStatusEnum type;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WorkStatusEnum getType() {
		return type;
	}

	public void setType(WorkStatusEnum type) {
		this.type = type;
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
		return "TaskLog{" +
			"id=" + id +
			", taskId=" + taskId +
			", message=" + message +
			", type=" + type +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
