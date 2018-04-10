package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@TableName("t_login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    @TableId("f_id")
    private Integer id;
    /**
     * 登录人id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 登录人账号
     */
    @TableField("user_account")
    private String userAccount;
    /**
     * 登录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("login_time")
    private Date loginTime;
    /**
     * 登录ip
     */
    @TableField("f_ip")
    private String ip;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "fId=" + id +
                ", userId=" + userId +
                ", userAccount=" + userAccount +
                ", loginTime=" + loginTime +
                ", fIp=" + ip +
                "}";
    }
}
