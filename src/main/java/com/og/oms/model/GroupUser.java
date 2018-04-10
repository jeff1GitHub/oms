package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-20
 */
@TableName("t_group_user")
public class GroupUser extends Model<GroupUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
	private Integer id;
    /**
     * 角色id
     */
	@TableField("group_id")
	private Integer groupId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;


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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "GroupUser{" +
			"id=" + id +
			", groupId=" + groupId +
			", userId=" + userId +
			"}";
	}
}
