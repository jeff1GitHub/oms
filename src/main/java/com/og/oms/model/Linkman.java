package com.og.oms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.ProducerEnum;

/**
 * <p>
 * 联系人信息
 * </p>
 *
 * @author jeff
 * @since 2017-11-11
 */
@TableName("t_linkman")
public class Linkman extends Model<Linkman> {

    private static final long serialVersionUID = 1L;

    /**
     * 联系人id
     */
    private Integer id;
    /**
     * 联系人名称
     */
    @NotEmpty
    private String name;
    /**
     * 所属供应商
     */
    @NotNull
    private ProducerEnum prodoucer;
    /**
     * 是否默认 0:正常 1:默认
     */
    @NotNull
    @TableField("is_default")
    private Integer isDefault;
    /**
     * 是否删除 0:正常 1:删除
     */
    @TableField("is_del")
    private Integer isDel;
    /**
     * 默认联系方式内容
     */
    @TableField(exist = false)
    private String defaultContactInfo;
    
    /**
     * 默认联系方式
     */
    @TableField(exist = false)
    private Integer defaultContactType;
    
	/**
     * 地址
     */
    private String addr;
    
    @TableField(exist = false)
    private List<LinkmanInformation> linkmanInformations;

	public List<LinkmanInformation> getLinkmanInformations() {
		return linkmanInformations;
	}

	public void setLinkmanInformation(List<LinkmanInformation> linkmanInformations) {
		this.linkmanInformations = linkmanInformations;
	}

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

    public ProducerEnum getProdoucer() {
        return prodoucer;
    }

    public void setProdoucer(ProducerEnum prodoucer) {
        this.prodoucer = prodoucer;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    

    public String getDefaultContactInfo() {
		return defaultContactInfo;
	}

	public void setDefaultContactInfo(String defaultContactInfo) {
		this.defaultContactInfo = defaultContactInfo;
	}
    
    public Integer getDefaultContactType() {
		return defaultContactType;
	}

	public void setDefaultContactType(Integer defaultContactType) {
		this.defaultContactType = defaultContactType;
	}


	@Override
	public String toString() {
		return "Linkman [id=" + id + ", name=" + name + ", prodoucer=" + prodoucer + ", isDefault=" + isDefault
				+ ", isDel=" + isDel + ", defaultContactInfo=" + defaultContactInfo + ", addr=" + addr
				+ ", linkmanInformations=" + linkmanInformations + "]";
	}
}
