package com.hongxin.entity;
// Generated 2016-6-2 16:21:57 by Hibernate Tools 4.3.1.Final

/**
 * TPactInform generated by hbm2java
 */
public class TPactInform implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pactId;
	private String productId;
	private String bussDate;
	private String bussTime;
	private String lostEff;
	private String state;

	private TPactInfo pactInfo;
	private TProductInfo product;
	private TAutoRepay autoRepay; 
	
	public TAutoRepay getAutoRepay() {
		return autoRepay;
	}

	public void setAutoRepay(TAutoRepay autoRepay) {
		this.autoRepay = autoRepay;
	}

	public TProductInfo getProduct() {
		return product;
	}

	public void setProduct(TProductInfo product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TPactInfo getPactInfo() {
		return pactInfo;
	}

	public void setPactInfo(TPactInfo pactInfo) {
		this.pactInfo = pactInfo;
	}

	public TPactInform() {
	}

	public TPactInform(String pactId, String productId, String lostEff, String state) {
		this.pactId = pactId;
		this.productId = productId;
		this.lostEff = lostEff;
		this.state = state;
	}

	public TPactInform(String pactId, String productId, String bussDate, String bussTime, String lostEff,
			String state) {
		this.pactId = pactId;
		this.productId = productId;
		this.bussDate = bussDate;
		this.bussTime = bussTime;
		this.lostEff = lostEff;
		this.state = state;
	}

	public String getPactId() {
		return this.pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBussDate() {
		return this.bussDate;
	}

	public void setBussDate(String bussDate) {
		this.bussDate = bussDate;
	}

	public String getBussTime() {
		return this.bussTime;
	}

	public void setBussTime(String bussTime) {
		this.bussTime = bussTime;
	}

	public String getLostEff() {
		return this.lostEff;
	}

	public void setLostEff(String lostEff) {
		this.lostEff = lostEff;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
