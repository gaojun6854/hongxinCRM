package com.hongxin.entity;
// Generated 2016-4-7 10:30:32 by Hibernate Tools 4.3.1.Final

/**
 * TIndexInfo generated by hbm2java
 */
public class TIndexInfo implements java.io.Serializable {

	private String workDate;
	private double indexs;

	public TIndexInfo() {
	}

	public TIndexInfo(String workDate, double indexs) {
		this.workDate = workDate;
		this.indexs = indexs;
	}

	public String getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public double getIndexs() {
		return this.indexs;
	}

	public void setIndexs(double indexs) {
		this.indexs = indexs;
	}

}
