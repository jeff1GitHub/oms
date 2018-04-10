package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
@TableName("t_group_permis")
public class GroupPermis extends Model<GroupPermis> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
	private Integer id;
    /**
     * 权限组id
     */
	@TableField("group_id")
	private Integer groupId;
    /**
     * 权限id
     */
	@TableField("permis_id")
	private Integer permisId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("create_user")
	private Integer createUser;
    /**
     * 删除标记 0:未删除
     */
	@TableField("is_del")
	private Integer isDel;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPermisId() {
		return permisId;
	}

	public void setPermisId(Integer permisId) {
		this.permisId = permisId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
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
		return "GroupPermis{" +
			"id=" + id +
			", groupId=" + groupId +
			", permisId=" + permisId +
			", createTime=" + createTime +
			", createUser=" + createUser +
			", isDel=" + isDel +
			"}";
	}
}
