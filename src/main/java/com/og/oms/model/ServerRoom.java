package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.ProducerEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-10-26
 */
@TableName("t_server_room")
public class ServerRoom extends Model<ServerRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
    private Integer id;
    /**
     * 机房名称
     */
    @NotEmpty
    @Length(max = 30)
    private String name;
    /**
     * 供应商
     */
    @NotNull
    @TableField("producer")
    private ProducerEnum producer;
    /**
     * 地区
     */
    @NotEmpty
    @Length(max = 30)
    private String area;
    /**
     * 是否删除
     */
    @TableField("is_del")
    private Integer isDel;
    /**
     * 备注
     */
    @Length(max = 150)
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
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;


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

    public ProducerEnum getProducer() {
        return producer;
    }

    public void setProducer(ProducerEnum producer) {
        this.producer = producer;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    @Override
    public String toString() {
        return "ServerRoom{" + "id=" + id + ", name=" + name + ", producerId=" + producer + ", area=" + area + ", isDel=" + isDel + ", remark=" + remark + ", createUser=" + createUser + ", createTime=" + createTime + ", updateTime=" + updateTime + ", updateUser=" + updateUser + "}";
    }
}
