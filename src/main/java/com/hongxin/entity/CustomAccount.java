package com.hongxin.entity;
import java.io.Serializable;

// Generated 2016-2-24 22:43:49 by Hibernate Tools 3.4.0.CR1

/**
 * TCustomAccountId generated by hbm2java
 */
public class CustomAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customId;
	private String phoneNum;
	private Character paperType;
	private String accountBank;
	private Character accountStart;
	private String accountFuyou;
	private String bankHead;
	private String payBank;
	private String payBankName;
	private String chageStaffNum;
	private String staffNum;
	private String checkId;
	private Integer checkIdSer;
	private String checkIdDesc;
	private String provinceCode;
	private String areaCode;
	private String remark1;
	private String remark2;

	public CustomAccount() {
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomAccount(String customId, String phoneNum,
			Character paperType, String accountBank, Character accountStart,
			String accountFuyou, String bankHead, String payBank,
			String payBankName, String chageStaffNum, String staffNum,
			String checkId, Integer checkIdSer, String checkIdDesc,
			String remark1, String remark2) {
		this.customId = customId;
		this.phoneNum = phoneNum;
		this.paperType = paperType;
		this.accountBank = accountBank;
		this.accountStart = accountStart;
		this.accountFuyou = accountFuyou;
		this.bankHead = bankHead;
		this.payBank = payBank;
		this.payBankName = payBankName;
		this.chageStaffNum = chageStaffNum;
		this.staffNum = staffNum;
		this.checkId = checkId;
		this.checkIdSer = checkIdSer;
		this.checkIdDesc = checkIdDesc;
		this.remark1 = remark1;
		this.remark2 = remark2;
	}

	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Character getPaperType() {
		return this.paperType;
	}

	public void setPaperType(Character paperType) {
		this.paperType = paperType;
	}

	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public Character getAccountStart() {
		return this.accountStart;
	}

	public void setAccountStart(Character accountStart) {
		this.accountStart = accountStart;
	}

	public String getAccountFuyou() {
		return this.accountFuyou;
	}

	public void setAccountFuyou(String accountFuyou) {
		this.accountFuyou = accountFuyou;
	}

	public String getBankHead() {
		return this.bankHead;
	}

	public void setBankHead(String bankHead) {
		this.bankHead = bankHead;
	}

	public String getPayBank() {
		return this.payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getPayBankName() {
		return this.payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public String getChageStaffNum() {
		return this.chageStaffNum;
	}

	public void setChageStaffNum(String chageStaffNum) {
		this.chageStaffNum = chageStaffNum;
	}

	public String getStaffNum() {
		return this.staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Integer getCheckIdSer() {
		return this.checkIdSer;
	}

	public void setCheckIdSer(Integer checkIdSer) {
		this.checkIdSer = checkIdSer;
	}

	public String getCheckIdDesc() {
		return this.checkIdDesc;
	}

	public void setCheckIdDesc(String checkIdDesc) {
		this.checkIdDesc = checkIdDesc;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CustomAccount))
			return false;
		CustomAccount castOther = (CustomAccount) other;

		return ((this.getCustomId() == castOther.getCustomId()) || (this
				.getCustomId() != null && castOther.getCustomId() != null && this
				.getCustomId().equals(castOther.getCustomId())))
				&& ((this.getPhoneNum() == castOther.getPhoneNum()) || (this
						.getPhoneNum() != null
						&& castOther.getPhoneNum() != null && this
						.getPhoneNum().equals(castOther.getPhoneNum())))
				&& ((this.getPaperType() == castOther.getPaperType()) || (this
						.getPaperType() != null
						&& castOther.getPaperType() != null && this
						.getPaperType().equals(castOther.getPaperType())))
				&& ((this.getAccountBank() == castOther.getAccountBank()) || (this
						.getAccountBank() != null
						&& castOther.getAccountBank() != null && this
						.getAccountBank().equals(castOther.getAccountBank())))
				&& ((this.getAccountStart() == castOther.getAccountStart()) || (this
						.getAccountStart() != null
						&& castOther.getAccountStart() != null && this
						.getAccountStart().equals(castOther.getAccountStart())))
				&& ((this.getAccountFuyou() == castOther.getAccountFuyou()) || (this
						.getAccountFuyou() != null
						&& castOther.getAccountFuyou() != null && this
						.getAccountFuyou().equals(castOther.getAccountFuyou())))
				&& ((this.getBankHead() == castOther.getBankHead()) || (this
						.getBankHead() != null
						&& castOther.getBankHead() != null && this
						.getBankHead().equals(castOther.getBankHead())))
				&& ((this.getPayBank() == castOther.getPayBank()) || (this
						.getPayBank() != null && castOther.getPayBank() != null && this
						.getPayBank().equals(castOther.getPayBank())))
				&& ((this.getPayBankName() == castOther.getPayBankName()) || (this
						.getPayBankName() != null
						&& castOther.getPayBankName() != null && this
						.getPayBankName().equals(castOther.getPayBankName())))
				&& ((this.getChageStaffNum() == castOther.getChageStaffNum()) || (this
						.getChageStaffNum() != null
						&& castOther.getChageStaffNum() != null && this
						.getChageStaffNum()
						.equals(castOther.getChageStaffNum())))
				&& ((this.getStaffNum() == castOther.getStaffNum()) || (this
						.getStaffNum() != null
						&& castOther.getStaffNum() != null && this
						.getStaffNum().equals(castOther.getStaffNum())))
				&& ((this.getCheckId() == castOther.getCheckId()) || (this
						.getCheckId() != null && castOther.getCheckId() != null && this
						.getCheckId().equals(castOther.getCheckId())))
				&& ((this.getCheckIdSer() == castOther.getCheckIdSer()) || (this
						.getCheckIdSer() != null
						&& castOther.getCheckIdSer() != null && this
						.getCheckIdSer().equals(castOther.getCheckIdSer())))
				&& ((this.getCheckIdDesc() == castOther.getCheckIdDesc()) || (this
						.getCheckIdDesc() != null
						&& castOther.getCheckIdDesc() != null && this
						.getCheckIdDesc().equals(castOther.getCheckIdDesc())))
				&& ((this.getRemark1() == castOther.getRemark1()) || (this
						.getRemark1() != null && castOther.getRemark1() != null && this
						.getRemark1().equals(castOther.getRemark1())))
				&& ((this.getRemark2() == castOther.getRemark2()) || (this
						.getRemark2() != null && castOther.getRemark2() != null && this
						.getRemark2().equals(castOther.getRemark2())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCustomId() == null ? 0 : this.getCustomId().hashCode());
		result = 37 * result
				+ (getPhoneNum() == null ? 0 : this.getPhoneNum().hashCode());
		result = 37 * result
				+ (getPaperType() == null ? 0 : this.getPaperType().hashCode());
		result = 37
				* result
				+ (getAccountBank() == null ? 0 : this.getAccountBank()
						.hashCode());
		result = 37
				* result
				+ (getAccountStart() == null ? 0 : this.getAccountStart()
						.hashCode());
		result = 37
				* result
				+ (getAccountFuyou() == null ? 0 : this.getAccountFuyou()
						.hashCode());
		result = 37 * result
				+ (getBankHead() == null ? 0 : this.getBankHead().hashCode());
		result = 37 * result
				+ (getPayBank() == null ? 0 : this.getPayBank().hashCode());
		result = 37
				* result
				+ (getPayBankName() == null ? 0 : this.getPayBankName()
						.hashCode());
		result = 37
				* result
				+ (getChageStaffNum() == null ? 0 : this.getChageStaffNum()
						.hashCode());
		result = 37 * result
				+ (getStaffNum() == null ? 0 : this.getStaffNum().hashCode());
		result = 37 * result
				+ (getCheckId() == null ? 0 : this.getCheckId().hashCode());
		result = 37
				* result
				+ (getCheckIdSer() == null ? 0 : this.getCheckIdSer()
						.hashCode());
		result = 37
				* result
				+ (getCheckIdDesc() == null ? 0 : this.getCheckIdDesc()
						.hashCode());
		result = 37 * result
				+ (getRemark1() == null ? 0 : this.getRemark1().hashCode());
		result = 37 * result
				+ (getRemark2() == null ? 0 : this.getRemark2().hashCode());
		return result;
	}

}
