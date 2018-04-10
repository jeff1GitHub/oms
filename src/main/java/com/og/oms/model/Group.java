package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.StatusType;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@TableName("t_group")
public class Group extends Model<Group> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
    @TableId("f_id")
    private Integer id;
    /**
     * 显示名称
     */
    private String name;
    /**
     * 是否禁用,1:禁用.0:未禁用
     */
    @TableField("is_disable")
    private StatusType isDisable;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建者id
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改者id
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 备注
     */
    private String remark;
    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 删除标记,0:未删除
     */
    @TableField("is_del")
    private Integer isDel;

    /**
     * 子权限组
     */
    @TableField(exist = false)
    private Map<Integer, Group> childrenGroupMap = new HashMap<>();

    /**
     * 组别权限
     */
    @TableField(exist = false)
    private Map<Integer, Permis> permisMap = new HashMap<>();
    
    /**
     * 成员数
     */
    @TableField(exist = false)
    private Integer userCount;

    /**
     * 增加权限
     *
     * @param permis
     */
    public void addPermis(Permis permis) {
        permisMap.put(permis.getId(), permis);
    }

    /**
     * 增加子权限组
     *
     * @param group
     */
    public void addChildrenGroup(Group group) {
        childrenGroupMap.put(group.getId(), group);
    }

    /**
     * 删除子权限组
     *
     * @param groupId
     * @return
     */
    public Group delChildrenGroup(Integer groupId) {
        return childrenGroupMap.remove(groupId);
    }

    /**
     * 获取所有子权限组
     *
     * @return
     */
    public Map<Integer, Group> getChildrenGroup() {
        return childrenGroupMap;
    }

    public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusType getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(StatusType isDisable) {
        this.isDisable = isDisable;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        return "Group{" + "fId=" + id + ", name=" + name + ", isDisable=" + isDisable + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", remark=" + remark + ", parentId=" + parentId + ", isDel=" + isDel + "}";
    }
}
