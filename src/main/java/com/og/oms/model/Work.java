package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-10-31
 */
@TableName("t_work")
public class Work extends Model<Work> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
	private Integer id;
    /**
     * 任务状态
     */
	private WorkStatusEnum status;
    /**
     * 故障类型
     */
    @NotNull
	@TableField("fault_type")
	private FaultTypeEnum faultType;
    /**
     * 优先级
     */
    @NotNull
	private PriorityEnum priority;
    /**
     * 优先级显示内容
     */
	@TableField("priority_name")
	private String priorityName;
    /**
     * 客户id
     */
	@TableField("customer_id")
	private Integer customerId;
    /**
     * 描述
     */
    @NotEmpty
	private String describe;
    /**
     * 所属平台
     */
	private PlatformEnum platform;
    /**
     * 站点id
     */
	@TableField("station_id")
	private Integer stationId;
    /**
     * 站点名称
     */
	@TableField("station_name")
	private String stationName;
    /**
     * 别名
     */
	@TableField("alias_name")
	private String aliasName;
    /**
     * 客户级别
     */
	private LevelEnum level;
    /**
     * 等级描述
     */
	@TableField("level_name")
	private String levelName;
    /**
     * 服务器id
     */
	@TableField("server_id")
	private Integer serverId;
    /**
     * 机房id
     */
	@TableField("server_room_id")
	private Integer serverRoomId;
    /**
     * 访问域名
     */
	private String domain;
    /**
     * 接收人
     */
	@TableField("receive_user")
	private String receiveUser;
    /**
     * 接收时间
     */
	@TableField("receive_time")
	@JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
	private Date receiveTime;
    /**
     * 创建人id
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
    /**
     * 接收人id
     */
	@TableField("update_user")
	private String updateUser;
    /**
     * 接收时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 完成时间
     */
	@TableField("finish_time")
	@JsonFormat(timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;
    /**
     * 完成状态
     */
	@TableField("finish_type")
	private Integer finishType;
	
	/**
	 * 任务操作日志
	 */
	@TableField(exist = false)
	private List<TaskLog> taskLogs;
	
	/**
	 * 任务备注
	 */
	@TableField(exist = false)
	private List<TaskRemark> taskRemarks;
	
	/**
	 * 任务附件信息
	 */
	@TableField(exist = false)
	private List<TaskAttachment> taskAttachments;

	public List<TaskAttachment> getTaskAttachments() {
		return taskAttachments;
	}

	public void setTaskAttachments(List<TaskAttachment> taskAttachments) {
		this.taskAttachments = taskAttachments;
	}

	public List<TaskRemark> getTaskRemarks() {
		return taskRemarks;
	}

	public void setTaskRemarks(List<TaskRemark> taskRemarks) {
		this.taskRemarks = taskRemarks;
	}

	public List<TaskLog> getTaskLogs() {
		return taskLogs;
	}

	public void setTaskLogs(List<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WorkStatusEnum getStatus() {
		return status;
	}

	public void setStatus(WorkStatusEnum status) {
		this.status = status;
	}

	public FaultTypeEnum getFaultType() {
		return faultType;
	}

	public void setFaultType(FaultTypeEnum faultType) {
		this.faultType = faultType;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public PlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformEnum platform) {
		this.platform = platform;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public LevelEnum getLevel() {
		return level;
	}

	public void setLevel(LevelEnum level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getServerRoomId() {
		return serverRoomId;
	}

	public void setServerRoomId(Integer serverRoomId) {
		this.serverRoomId = serverRoomId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
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

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getFinishType() {
		return finishType;
	}

	public void setFinishType(Integer finishType) {
		this.finishType = finishType;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Work{" +
			"id=" + id +
			", status=" + status +
			", faultType=" + faultType +
			", priority=" + priority +
			", priorityName=" + priorityName +
			", customerId=" + customerId +
			", describe=" + describe +
			", platform=" + platform +
			", stationId=" + stationId +
			", stationName=" + stationName +
			", aliasName=" + aliasName +
			", level=" + level +
			", levelName=" + levelName +
			", serverId=" + serverId +
			", serverRoomId=" + serverRoomId +
			", domain=" + domain +
			", receiveUser=" + receiveUser +
			", receiveTime=" + receiveTime +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", finishTime=" + finishTime +
			", finishType=" + finishType +
			"}";
	}
}
