package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 站点信息
 * </p>
 *
 * @author jeff
 * @since 2017-11-05
 */
@TableName("t_station")
public class Station extends Model<Station> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水编号
     */
    private Integer id;
    /**
     * 父站code
     */
    private String pcode;
    /**
     * 站点durl
     */
    private String code;
    /**
     * 站点名称
     */
    private String name;
    /**
     * 站点文件夹
     */
    private String folder;
    /**
     * 服务器id
     */
    private Integer server;
    /**
     * 客户级别
     */
    private LevelEnum level;
    /**
     * 站点类型
     */
    private StationTypeEnum type;
    /**
     * 所属平台
     */
    private PlatformEnum platform;
    /**
     * 六个月报表查询
     */
    @TableField("six_search")
    private String sixSearch;
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
    
    /**
     * 是否禁用,1:禁用,0:正常
     */
    @TableField("is_disable")
    private StatusType isDisable;
    
    /**
     * 主域名
     */
    @TableField(exist = false)
    private String domain;

    /**
     * 支付别名
     */
    @TableField(exist = false)
    private String payalias;
    /**
     * 新支付别名
     */
    @TableField(exist = false)
    private String payment;
    /**
     * vpn帐号
     */
    @TableField(exist = false)
    private String vpnaccount;
    /**
     * vpn密码
     */
    @TableField(exist = false)
    private String vpnpassword;
    /**
     * 别名
     */
    @TableField(exist = false)
    private List<String> alias;
    /**
     * 后台域名
     */
    @TableField(exist = false)
    private List<String> managedomain;
    /**
     * 客户测试域名
     */
    @TableField(exist = false)
    private List<String> clientsDomain;
    /**
     * vpn列表
     */
    @TableField(exist = false)
    private List<String> vpnip;
    /**
     * 测试域名
     */
    @TableField(exist = false)
    private List<String> testdomain;
    
    /**
     * 增加别名
     *
     * @param aliasList
     */
    public void addAlias(List<Alias> aliasList) {
        if(this.alias == null) {
            this.alias = new ArrayList<>();
        }

        for(Alias alias : aliasList) {
            if(alias.getType() == AliasTypeEnum.ALIAS_TYPE_NORMAL) {
                this.alias.add(alias.getAlias());
            } else if(alias.getType() == AliasTypeEnum.ALIAS_TYPE_PAY) {
                this.payalias = alias.getAlias();
            } else if(alias.getType() == AliasTypeEnum.ALIAS_TYPE_PAYMENT) {
                this.payment = alias.getAlias();
            }
        }
    }

    public void addDomain(List<Domain> list) {
        if(this.clientsDomain == null) {
            this.clientsDomain = new ArrayList<>();
        }
        if(this.testdomain == null) {
            this.testdomain = new ArrayList<>();
        }
        if(this.managedomain == null) {
            this.managedomain = new ArrayList<>();
        }

        for(Domain tmp : list) {
            if(tmp.getType() == DomainTypeEnum.DOMAIN_MAIN) {
                this.domain = tmp.getDomain();
            } else if(tmp.getType() == DomainTypeEnum.DOMAIN_TEST) {
                this.testdomain.add(tmp.getDomain());
            } else if(tmp.getType() == DomainTypeEnum.DOMAIN_CLIENT) {
                this.clientsDomain.add(tmp.getDomain());
            } else if(tmp.getType() == DomainTypeEnum.DOMAIN_MANAGE) {
                this.managedomain.add(tmp.getDomain());
            }
        }
    }

    public void addVpn(List<Vpn> list) {
        if(this.vpnip == null) {
            this.vpnip = new ArrayList<>();
        }
        for(Vpn vpn : list) {
            this.vpnip.add(vpn.getVpnIp());
            this.vpnaccount = vpn.getVpnName();
            this.vpnpassword = vpn.getVpnPassword();
        }
    }

    public StatusType getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(StatusType isDisable) {
		this.isDisable = isDisable;
	}

	public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Integer getServer() {
        return server;
    }

    public void setServer(Integer server) {
        this.server = server;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public StationTypeEnum getType() {
        return type;
    }

    public void setType(StationTypeEnum type) {
        this.type = type;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public String getSixSearch() {
        return sixSearch;
    }

    public void setSixSearch(String sixSearch) {
        this.sixSearch = sixSearch;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPayalias() {
        return payalias;
    }

    public void setPayalias(String payalias) {
        this.payalias = payalias;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getVpnaccount() {
        return vpnaccount;
    }

    public void setVpnaccount(String vpnaccount) {
        this.vpnaccount = vpnaccount;
    }

    public String getVpnpassword() {
        return vpnpassword;
    }

    public void setVpnpassword(String vpnpassword) {
        this.vpnpassword = vpnpassword;
    }

    public List<String> getManagedomain() {
        return managedomain;
    }

    public void setManagedomain(List<String> managedomain) {
        this.managedomain = managedomain;
    }

    public List<String> getClientsDomain() {
        return clientsDomain;
    }

    public void setClientsDomain(List<String> clientsDomain) {
        this.clientsDomain = clientsDomain;
    }

    public List<String> getVpnip() {
        return vpnip;
    }

    public void setVpnip(List<String> vpnip) {
        this.vpnip = vpnip;
    }

    public List<String> getTestdomain() {
        return testdomain;
    }

    public void setTestdomain(List<String> testdomain) {
        this.testdomain = testdomain;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", pcode=" + pcode + ", code=" + code + ", name=" + name + ", folder=" + folder + ", server=" + server + ", level=" + level + ", type=" + type + ", platform=" + platform + ", sixSearch=" + sixSearch + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + "}";
    }
}
