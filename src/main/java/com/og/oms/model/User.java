package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.GroupEnum;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String account;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人账号
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 最后一次登录得ip
     */
    private String ip;
    /**
     * 是否禁用,1:禁用,0:正常
     */
    @TableField("is_disable")
    private Integer isDisable;
    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 备注
     */
    private String remark;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人账号
     */
    @TableField("update_user")
    private String updateUser;

    @TableField(exist = false)
    private String groupInfos;

    /**
     * 用户组id
     */
    @TableField(exist = false)
    private List<GroupEnum> groups;
    
    
    @TableField(exist = false)
    private Map<Integer, Group> groupMap = new HashMap<>();

    public Collection<Group> getGroupMap() {
        return groupMap.values();
    }

    public List<GroupEnum> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEnum> groups) {
		this.groups = groups;
	}

//    public void setGroups(Map<Integer, Group> groups) {
//        this.groups = groups;
//    }
//
//    public void addGroups(Group group) {
//        this.groups.put(group.getId(), group);
//    }

    public String getGroupInfos() {
		return groupInfos;
	}

	public void setGroupInfos(String groupInfos) {
		this.groupInfos = groupInfos;
	}

	public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

}
