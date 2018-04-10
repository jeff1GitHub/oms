package com.og.oms.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-29
 */
@TableName("t_task_attachment")
public class TaskAttachment extends Model<TaskAttachment> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	private Integer id;
    /**
     * 任务id
     */
	@TableField("task_id")
	private Integer taskId;
    /**
     * 文件名
     */
	@TableField("file_name")
	private String fileName;
    /**
     * url地址
     */
	private String url;
    /**
     * 是否删除 0 正常 1 删除
     */
	@TableField("is_del")
	private Integer isDel;


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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TaskAttachment{" +
			"id=" + id +
			", taskId=" + taskId +
			", fileName=" + fileName +
			", url=" + url +
			", isDel=" + isDel +
			"}";
	}
}
