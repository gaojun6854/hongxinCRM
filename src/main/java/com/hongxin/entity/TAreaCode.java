package com.hongxin.entity;
// Generated 2016-6-16 13:21:34 by Hibernate Tools 4.3.1.Final

/**
 * TAreaCode generated by hbm2java
 */
public class TAreaCode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String provinceCode;
	private String areaCode;
	private String areaName;
	private String supAreaCode;
	private String remark1;
	private String remark2;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getSupAreaCode() {
		return supAreaCode;
	}
	public void setSupAreaCode(String supAreaCode) {
		this.supAreaCode = supAreaCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "[provinceCode=" + provinceCode + ", areaCode="
				+ areaCode + ", areaName=" + areaName + ", supAreaCode="
				+ supAreaCode + ", remark1=" + remark1 + ", remark2=" + remark2
				+ "]";
	}
	

}