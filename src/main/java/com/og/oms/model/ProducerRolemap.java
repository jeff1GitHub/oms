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
 * @since 2017-11-18
 */
@TableName("t_producer_rolemap")
public class ProducerRolemap extends Model<ProducerRolemap> {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商角色映射流水id
     */
	private Integer id;
	@TableField("producer_id")
	private Integer producerId;
	@TableField("role_id")
	private Integer roleId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProducerId() {
		return producerId;
	}

	public void setProducerId(Integer producerId) {
		this.producerId = producerId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProducerRolemap{" +
			"id=" + id +
			", producerId=" + producerId +
			", roleId=" + roleId +
			"}";
	}
}
