package com.og.oms.model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.LevelEnum;
import com.og.oms.enums.PlatformEnum;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
@TableName("t_attack")
public class Attack extends Model<Attack> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
	private Integer id;
    /**
     * 站点编号
     */
	@TableField("station_code")
	private String stationCode;
    /**
     * 客户等级
     */
	private LevelEnum level;
    /**
     * 平台
     */
	private PlatformEnum platform;
    /**
     * 别名
     */
	private String alias;
    /**
     * 攻击时间
     */
	@TableField("attack_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attackTime;
    /**
     * 攻击强度
     */
	@TableField("attack_intensity")
	private Integer attackIntensity;
    /**
     * 高防服务器
     */
	@TableField("defense_server")
	private String defenseServer;
    /**
     * 负责人
     */
	private String director;
    /**
     * 备注
     */
	private String remark;
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
    /**
     * 高防时间
     */
	@TableField("defense_num")
	private Integer defenseNum;
    /**
     * 高防服务器ip
     */
	@TableField("defense_ip")
	private String defenseIp;
    /**
     * 是否显示
     */
	private Integer show;
    /**
     * 任务id
     */
	@TableField("task_id")
	private Integer taskId;
	
	/**
	 * 被攻击次数
	 */
	@TableField("attack_num")
	private Integer attackNum;
	
	/**
	 * 最新被攻击域名
	 */
	@TableField(exist = false)
	private String recentlyAttackedDomain;

	/**
	 * 攻击详情
	 */
	@TableField(exist = false)
	private List<AttackMessage> attackMessages;
	
	
	public String getRecentlyAttackedDomain() {
		return recentlyAttackedDomain;
	}

	public void setRecentlyAttackedDomain(String recentlyAttackedDomain) {
		this.recentlyAttackedDomain = recentlyAttackedDomain;
	}

	
	public List<AttackMessage> getAttackMessages() {
		return attackMessages;
	}

	public void setAttackMessages(List<AttackMessage> attackMessages) {
		this.attackMessages = attackMessages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public LevelEnum getLevel() {
		return level;
	}

	public void setLevel(LevelEnum level) {
		this.level = level;
	}

	public PlatformEnum getPlatform() {
		return platform;
	}

	public void setPlatform(PlatformEnum platform) {
		this.platform = platform;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(Date attackTime) {
		this.attackTime = attackTime;
	}

	public Integer getAttackIntensity() {
		return attackIntensity;
	}

	public void setAttackIntensity(Integer attackIntensity) {
		this.attackIntensity = attackIntensity;
	}

	public String getDefenseServer() {
		return defenseServer;
	}

	public void setDefenseServer(String defenseServer) {
		this.defenseServer = defenseServer;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getDefenseNum() {
		return defenseNum;
	}

	public void setDefenseNum(Integer defenseNum) {
		this.defenseNum = defenseNum;
	}

	public String getDefenseIp() {
		return defenseIp;
	}

	public void setDefenseIp(String defenseIp) {
		this.defenseIp = defenseIp;
	}

	public Integer getShow() {
		return show;
	}

	public void setShow(Integer show) {
		this.show = show;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getAttackNum() {
		return attackNum;
	}

	public void setAttackNum(Integer attackNum) {
		this.attackNum = attackNum;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Attack{" +
			"id=" + id +
			", stationCode=" + stationCode +
			", level=" + level +
			", platform=" + platform +
			", alias=" + alias +
			", attackTime=" + attackTime +
			", attackIntensity=" + attackIntensity +
			", defenseServer=" + defenseServer +
			", director=" + director +
			", remark=" + remark +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", defenseNum=" + defenseNum +
			", defenseIp=" + defenseIp +
			", show=" + show +
			", taskId=" + taskId +
			", attackNum=" + attackNum +
			"}";
	}
}
