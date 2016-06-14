package com.hongxin.entity;
// Generated 2016-4-7 10:30:32 by Hibernate Tools 4.3.1.Final

/**
 * TRebuypactInfo generated by hbm2java
 */
public class TRebuypactInfo implements java.io.Serializable {

	private static final long serialVersionUID = -2770934454551631008L;
	private String pactId;
	private String pactDate;
	private String pactTime;
	private String custId;
	private String custName;
	private Character paperType;
	private String paperNum;
	private Character investType;
	private String productId;
	private String fuyouAccout;
	private String expireDate;
	private String purchaseDays;
	private Double amount;
	private String managerNum;
	private String managerName;
	private String termNum;
	private String termName;
	private String pactEff;
	private String countEff;
	private String pactDue;
	private String contractNumber;
	private Double recruitmentDate;
	private double rateFix;
	private String remark1;
	private String remark2;
	
	private CustomBaseInfo customBaseInfo;
	private TProductInfo productInfo;
	
	
	public CustomBaseInfo getCustomBaseInfo() {
		return customBaseInfo;
	}

	public void setCustomBaseInfo(CustomBaseInfo customBaseInfo) {
		this.customBaseInfo = customBaseInfo;
	}

	public TProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(TProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public TRebuypactInfo() {
	}

	public TRebuypactInfo(String pactId, String custId, String productId, String fuyouAccout, String purchaseDays,
			String pactEff, String countEff, String pactDue, String contractNumber, double rateFix) {
		this.pactId = pactId;
		this.custId = custId;
		this.productId = productId;
		this.fuyouAccout = fuyouAccout;
		this.purchaseDays = purchaseDays;
		this.pactEff = pactEff;
		this.countEff = countEff;
		this.pactDue = pactDue;
		this.contractNumber = contractNumber;
		this.rateFix = rateFix;
	}

	public TRebuypactInfo(String pactId, String pactDate, String pactTime, String custId, String custName,
			Character paperType, String paperNum, Character investType, String productId, String fuyouAccout,
			String expireDate, String purchaseDays, Double amount, String managerNum, String managerName,
			String termNum, String termName, String pactEff, String countEff, String pactDue, String contractNumber,
			Double recruitmentDate, double rateFix, String remark1, String remark2) {
		this.pactId = pactId;
		this.pactDate = pactDate;
		this.pactTime = pactTime;
		this.custId = custId;
		this.custName = custName;
		this.paperType = paperType;
		this.paperNum = paperNum;
		this.investType = investType;
		this.productId = productId;
		this.fuyouAccout = fuyouAccout;
		this.expireDate = expireDate;
		this.purchaseDays = purchaseDays;
		this.amount = amount;
		this.managerNum = managerNum;
		this.managerName = managerName;
		this.termNum = termNum;
		this.termName = termName;
		this.pactEff = pactEff;
		this.countEff = countEff;
		this.pactDue = pactDue;
		this.contractNumber = contractNumber;
		this.recruitmentDate = recruitmentDate;
		this.rateFix = rateFix;
		this.remark1 = remark1;
		this.remark2 = remark2;
	}

	public String getPactId() {
		return this.pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	public String getPactDate() {
		return this.pactDate;
	}

	public void setPactDate(String pactDate) {
		this.pactDate = pactDate;
	}

	public String getPactTime() {
		return this.pactTime;
	}

	public void setPactTime(String pactTime) {
		this.pactTime = pactTime;
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Character getPaperType() {
		return this.paperType;
	}

	public void setPaperType(Character paperType) {
		this.paperType = paperType;
	}

	public String getPaperNum() {
		return this.paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	public Character getInvestType() {
		return this.investType;
	}

	public void setInvestType(Character investType) {
		this.investType = investType;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFuyouAccout() {
		return this.fuyouAccout;
	}

	public void setFuyouAccout(String fuyouAccout) {
		this.fuyouAccout = fuyouAccout;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getPurchaseDays() {
		return this.purchaseDays;
	}

	public void setPurchaseDays(String purchaseDays) {
		this.purchaseDays = purchaseDays;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getManagerNum() {
		return this.managerNum;
	}

	public void setManagerNum(String managerNum) {
		this.managerNum = managerNum;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getTermNum() {
		return this.termNum;
	}

	public void setTermNum(String termNum) {
		this.termNum = termNum;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getPactEff() {
		return this.pactEff;
	}

	public void setPactEff(String pactEff) {
		this.pactEff = pactEff;
	}

	public String getCountEff() {
		return this.countEff;
	}

	public void setCountEff(String countEff) {
		this.countEff = countEff;
	}

	public String getPactDue() {
		return this.pactDue;
	}

	public void setPactDue(String pactDue) {
		this.pactDue = pactDue;
	}

	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Double getRecruitmentDate() {
		return this.recruitmentDate;
	}

	public void setRecruitmentDate(Double recruitmentDate) {
		this.recruitmentDate = recruitmentDate;
	}

	public double getRateFix() {
		return this.rateFix;
	}

	public void setRateFix(double rateFix) {
		this.rateFix = rateFix;
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

}
