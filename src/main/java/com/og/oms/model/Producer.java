package com.og.oms.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.og.oms.enums.BankEnum;
import com.og.oms.enums.ProducerTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
@TableName("t_producer")
public class Producer extends Model<Producer> {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 角色id
     */
	@TableField("role")
	private ProducerTypeEnum role;
    /**
     * 公司名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 签章人
     */
	@TableField("signature_person")
	private String signaturePerson;
    /**
     * 支付宝帐号
     */
	@TableField("ali_pay")
	private String aliPay;
    /**
     * 银行id
     */
	@TableField("bank_type")
	private BankEnum bankType;
    /**
     * 银行帐号
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 登录域名
     */
	@TableField("login_domain")
	private String loginDomain;
    /**
     * 登录帐号
     */
	@TableField("login_account")
	private String loginAccount;
    /**
     * 登录密码
     */
	@TableField("login_password")
	private String loginPassword;
    /**
     * 是否删除 0:正常 1:删除
     */
	@TableField("is_del")
	private Integer isDel;
	
	@TableField(exist = false)
	private List<ProducerTypeEnum> producerType;
	
	@TableField(exist = false)
	private String producerTypeInfo;
	
	public String getProducerTypeInfo() {
		return producerTypeInfo;
	}

	public void setProducerTypeInfo(String producerTypeInfo) {
		this.producerTypeInfo = producerTypeInfo;
	}

	public List<ProducerTypeEnum> getProducerType() {
		return producerType;
	}

	public void setProducerType(List<ProducerTypeEnum> producerType) {
		this.producerType = producerType;
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

	public ProducerTypeEnum getRole() {
		return role;
	}

	public void setRole(ProducerTypeEnum role) {
		this.role = role;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSignaturePerson() {
		return signaturePerson;
	}

	public void setSignaturePerson(String signaturePerson) {
		this.signaturePerson = signaturePerson;
	}

	public String getAliPay() {
		return aliPay;
	}

	public void setAliPay(String aliPay) {
		this.aliPay = aliPay;
	}

	public BankEnum getBankType() {
		return bankType;
	}

	public void setBankType(BankEnum bankType) {
		this.bankType = bankType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getLoginDomain() {
		return loginDomain;
	}

	public void setLoginDomain(String loginDomain) {
		this.loginDomain = loginDomain;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Producer{" +
			"id=" + id +
			", name=" + name +
			", roleId=" + role +
			", companyName=" + companyName +
			", signaturePerson=" + signaturePerson +
			", aliPay=" + aliPay +
			", bankType=" + bankType +
			", bankAccount=" + bankAccount +
			", loginDomain=" + loginDomain +
			", loginAccount=" + loginAccount +
			", loginPassword=" + loginPassword +
			", isDel=" + isDel +
			"}";
	}
}
