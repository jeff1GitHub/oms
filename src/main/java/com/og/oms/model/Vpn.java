package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

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
@TableName("t_vpn")
public class Vpn extends Model<Vpn> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
    private Integer id;
    /**
     * 站点id
     */
    private String station;
    /**
     * vpn的ip
     */
    @TableField("vpn_ip")
    private String vpnIp;
    /**
     * vpn用户名
     */
    @TableField("vpn_name")
    private String vpnName;
    /**
     * vpn密码
     */
    @TableField("vpn_password")
    private String vpnPassword;
    /**
     * 是否禁用,1:禁用,0:正常
     */
    @TableField("is_disable")
    private Integer isDisable;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
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

    public Vpn() {
    }

    public Vpn(String createUser, String vpnIp, String vpnName, String vpnPassword, String station) {
        this.createUser = createUser;
        this.vpnIp = vpnIp;
        this.vpnName = vpnName;
        this.vpnPassword = vpnPassword;
        this.station = station;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getVpnIp() {
        return vpnIp;
    }

    public void setVpnIp(String vpnIp) {
        this.vpnIp = vpnIp;
    }

    public String getVpnName() {
        return vpnName;
    }

    public void setVpnName(String vpnName) {
        this.vpnName = vpnName;
    }

    public String getVpnPassword() {
        return vpnPassword;
    }

    public void setVpnPassword(String vpnPassword) {
        this.vpnPassword = vpnPassword;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Vpn{" + "id=" + id + ", station=" + station + ", vpnIp=" + vpnIp + ", vpnName=" + vpnName + ", vpnPassword=" + vpnPassword + ", isDisable=" + isDisable + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + "}";
    }
}
