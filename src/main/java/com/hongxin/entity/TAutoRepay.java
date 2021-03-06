package com.hongxin.entity;
// Generated 2016-4-7 10:30:32 by Hibernate Tools 4.3.1.Final

/**
 * TAutoRepay generated by hbm2java
 */
public class TAutoRepay implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pactId;
	private String productId;
	private String workDate;
	private String productName;
	private String takeEff;
	private String lostEff;
	private String repayAccout;
	private String payerAccout;
	private double capital;
	private Double amount;
	private String bussDate;
	private String bussTime;
	private char bussStart;
	private String state;
	private String backTime;
	private String rebuyFlag;
	private String rebuyPactid;
	private String remark1;
	private String remark2;

	private TPactInfo pactInfo;

	public TPactInfo getPactInfo() {
		return pactInfo;
	}

	public void setPactInfo(TPactInfo pactInfo) {
		this.pactInfo = pactInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPactId() {
		return pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTakeEff() {
		return takeEff;
	}

	public void setTakeEff(String takeEff) {
		this.takeEff = takeEff;
	}

	public String getLostEff() {
		return lostEff;
	}

	public void setLostEff(String lostEff) {
		this.lostEff = lostEff;
	}

	public String getRepayAccout() {
		return repayAccout;
	}

	public void setRepayAccout(String repayAccout) {
		this.repayAccout = repayAccout;
	}

	public String getPayerAccout() {
		return payerAccout;
	}

	public void setPayerAccout(String payerAccout) {
		this.payerAccout = payerAccout;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBussDate() {
		return bussDate;
	}

	public void setBussDate(String bussDate) {
		this.bussDate = bussDate;
	}

	public String getBussTime() {
		return bussTime;
	}

	public void setBussTime(String bussTime) {
		this.bussTime = bussTime;
	}

	public char getBussStart() {
		return bussStart;
	}

	public void setBussStart(char bussStart) {
		this.bussStart = bussStart;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getRebuyFlag() {
		return rebuyFlag;
	}

	public void setRebuyFlag(String rebuyFlag) {
		this.rebuyFlag = rebuyFlag;
	}

	public String getRebuyPactid() {
		return rebuyPactid;
	}

	public void setRebuyPactid(String rebuyPactid) {
		this.rebuyPactid = rebuyPactid;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
}
