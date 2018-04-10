package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.PlatformEnum;
import com.og.oms.enums.RaidTypeEnum;
import com.og.oms.enums.ServerRoomEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
@TableName("t_server")
public class Server extends Model<Server> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 服务器编号
     */
    @Size(max = 30)
    private String code;
    /**
     * 服务器名称
     */
    @Size(max = 30)
    private String name;
    /**
     * 平台
     */
    private PlatformEnum platform;
    /**
     * 地区
     */
    @Size(max = 30)
    private String area;
    /**
     * 机房
     */
    private ServerRoomEnum serverroom;
    /**
     * 连接类型
     */
    @TableField("link_type")
    @Size(max = 30)
    private String linkType;
    /**
     * 连接地址
     */
    @TableField("link_addr")
    @Size(max = 30)
    private String linkAddr;
    /**
     * 连接端口
     */
    @TableField("link_port")
    @Max(65535)
    private Integer linkPort;
    /**
     * 宿主机
     */
    @Size(max = 30)
    private String host;
    /**
     * 序列号
     */
    @Size(max = 30)
    private String series;
    /**
     * 带宽
     */
    private Integer bandwidth;
    /**
     * 普通用户帐号
     */
    @Size(max = 30)
    private String account;
    /**
     * 普通用户密码
     */
    @Size(max = 32)
    private String password;
    /**
     * 超级用户帐号
     */
    @TableField("super_account")
    @Size(max = 30)
    private String superAccount;
    /**
     * 超级用户密码
     */
    @TableField("super_password")
    @Size(max = 32)
    private String superPassword;
    /**
     * 机型
     */
    @TableField("server_type")
    @Size(max = 30)
    private String serverType;
    /**
     * cpu型号
     */
    @Size(max = 30)
    private String cpu;
    /**
     * 内存大小
     */
    private Integer memory;
    /**
     * 硬盘大小
     */
    private Integer disk;
    /**
     * 是否磁盘阵列
     */
    private RaidTypeEnum raid;
    /**
     * 操作系统
     */
    private Integer system;
    /**
     * 是否双网卡
     */
    private Integer nic;
    /**
     * 远程控制卡访问地址
     */
    @TableField("idarc_addr")
    @Size(max = 30)
    private String idarcAddr;
    /**
     * 远程控制卡访问帐号
     */
    @TableField("idarc_account")
    @Size(max = 30)
    private String idarcAccount;
    /**
     * 远程控制卡访问密码
     */
    @TableField("idarc_password")
    @Size(max = 32)
    private String idarcPassword;
    /**
     * 权属
     */
    private Integer ownership;
    /**
     * 购买时间
     */
    @TableField("buy_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyTime;
    /**
     * 购买到期时间
     */
    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
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
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 修改时间
     */
    @TableField("update_create")
    private Date updateCreate;

    /**
     * 租凭价格
     */
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ServerRoomEnum getServerroom() {
        return serverroom;
    }

    public void setServerroom(ServerRoomEnum serverroom) {
        this.serverroom = serverroom;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public Integer getLinkPort() {
        return linkPort;
    }

    public void setLinkPort(Integer linkPort) {
        this.linkPort = linkPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuperAccount() {
        return superAccount;
    }

    public void setSuperAccount(String superAccount) {
        this.superAccount = superAccount;
    }

    public String getSuperPassword() {
        return superPassword;
    }

    public void setSuperPassword(String superPassword) {
        this.superPassword = superPassword;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    public RaidTypeEnum getRaid() {
        return raid;
    }

    public void setRaid(RaidTypeEnum raid) {
        this.raid = raid;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public Integer getNic() {
        return nic;
    }

    public void setNic(Integer nic) {
        this.nic = nic;
    }

    public String getIdarcAddr() {
        return idarcAddr;
    }

    public void setIdarcAddr(String idarcAddr) {
        this.idarcAddr = idarcAddr;
    }

    public String getIdarcAccount() {
        return idarcAccount;
    }

    public void setIdarcAccount(String idarcAccount) {
        this.idarcAccount = idarcAccount;
    }

    public String getIdarcPassword() {
        return idarcPassword;
    }

    public void setIdarcPassword(String idarcPassword) {
        this.idarcPassword = idarcPassword;
    }

    public Integer getOwnership() {
        return ownership;
    }

    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Date getUpdateCreate() {
        return updateCreate;
    }

    public void setUpdateCreate(Date updateCreate) {
        this.updateCreate = updateCreate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Server{" + "id=" + id + ", code=" + code + ", name=" + name + ", platform=" + platform + ", area=" + area + ", serverroom=" + serverroom + ", linkType=" + linkType + ", linkAddr=" + linkAddr + ", linkPort=" + linkPort + ", host=" + host + ", series=" + series + ", bandwidth=" + bandwidth + ", account=" + account + ", password=" + password + ", superAccount=" + superAccount + ", superPassword=" + superPassword + ", serverType=" + serverType + ", cpu=" + cpu + ", memory=" + memory + ", disk=" + disk + ", raid=" + raid + ", system=" + system + ", nic=" + nic + ", idarcAddr=" + idarcAddr + ", idarcAccount=" + idarcAccount + ", idarcPassword=" + idarcPassword + ", ownership=" + ownership + ", buyTime=" + buyTime + ", endTime=" + endTime + ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser + ", updateCreate=" + updateCreate + "}";
    }
}
