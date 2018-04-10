package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.AliasTypeEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-11-06
 */
@TableName("t_alias")
public class Alias extends Model<Alias> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
    private Integer id;
    /**
     * 别名
     */
    private String alias;
    /**
     * 解析ip
     */
    private String ip;
    /**
     * 站点id
     */
    @TableField("station")
    private String station;
    /**
     * 服务器id
     */
    @TableField("server")
    private Integer server;
    /**
     * 别名类型
     */
    private AliasTypeEnum type;
    /**
     * 是否禁用
     */
    @TableField("is_disable")
    private Integer isDisable;
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

    public Alias() {
    }

    public Alias(String userAccount, String alias, String station, AliasTypeEnum type) {
        this.alias = alias;
        this.station = station;
        this.type = type;
        this.createUser = userAccount;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getServer() {
        return server;
    }

    public void setServer(Integer server) {
        this.server = server;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public AliasTypeEnum getType() {
        return type;
    }

    public void setType(AliasTypeEnum type) {
        this.type = type;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
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
        return "Alias{" + "id=" + id + ", alias=" + alias + ", ip=" + ip + ", station=" + station + ", server=" + server + ", type=" + type + ", isDisable=" + isDisable + ", createUser=" + createUser + ", createTime=" + createTime + ", updateTime=" + updateTime + ", updateUser=" + updateUser + "}";
    }
}
