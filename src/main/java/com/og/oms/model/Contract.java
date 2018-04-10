package com.og.oms.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.og.oms.enums.ContractTypeEnum;
import com.og.oms.enums.ProducerEnum;

/**
 * <p>
 *     合同资料对象
 * </p>
 *
 * @author jeff
 * @since 2017-10-15
 */
@TableName("t_contract")
public class Contract extends Model<Contract> {

    private static final long serialVersionUID = 1L;

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
     * 合同结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;
    /**
     * 流水编号
     */
    private Integer id;
    /**
     * 合同保存路径
     */
    @NotEmpty(message="合同保存路径不能为空!")
    @Size(max = 150, message = "长度不能超过150位")
    private String path;
    /**
     * 供应商id
     */
    @TableField("producer_id")
    private ProducerEnum producer;
    /**
     * 备注
     */
    private String remark;
    /**
     * 签章公司
     */
    @TableField("signature_company")
    private String signatureCompany;
    /**
     * 签章人
     */
    @TableField("signature_person")
    private String signaturePerson;
    /**
     * 合同开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;
    /**
     * 合同类型
     */
    private ContractTypeEnum type;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProducerEnum getProducer() {
        return producer;
    }

    public void setProducer(ProducerEnum producer) {
        this.producer = producer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignatureCompany() {
        return signatureCompany;
    }

    public void setSignatureCompany(String signatureCompany) {
        this.signatureCompany = signatureCompany;
    }

    public String getSignaturePerson() {
        return signaturePerson;
    }

    public void setSignaturePerson(String signaturePerson) {
        this.signaturePerson = signaturePerson;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public ContractTypeEnum getType() {
        return type;
    }

    public void setType(ContractTypeEnum type) {
        this.type = type;
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

}
