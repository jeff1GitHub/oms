package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.DomainTypeEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-11-07
 */
@TableName("t_domain")
public class Domain extends Model<Domain> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
    private Integer id;
    /**
     * 域名
     */
    private String domain;
    /**
     * 站点id
     */
    private String station;
    /**
     * 域名类型
     */
    private DomainTypeEnum type;
    /**
     * 创建者
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改者
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 是否禁用
     */
    @TableField("is_disable")
    private Integer isDisable;

    public Domain() {
    }

    public Domain(String userName, String domain, String station, DomainTypeEnum type) {
        this.domain = domain;
        this.station = station;
        this.type = type;
        this.createUser = userName;
        this.createTime = new Date();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public DomainTypeEnum getType() {
        return type;
    }

    public void setType(DomainTypeEnum type) {
        this.type = type;
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

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Domain{" + "id=" + id + ", domain=" + domain + ", station=" + station + ", type=" + type + ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + ", isDisable=" + isDisable + "}";
    }
}
