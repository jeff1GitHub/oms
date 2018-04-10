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
 * @since 2017-10-28
 */
@TableName("sys_code_type")
public class CodeType extends Model<CodeType> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 父节点
     */
	@TableField("parent_id")
	private Integer pId;

	private String open;


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

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CodeType{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + pId +
			", open=" + open +
			"}";
	}
}
