/*
 * Created on 2004-9-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.hongxin.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Lance
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomBaseInfo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String   id;//客户编号
	private String custname;//客户姓名
	private String phonenum;//手机号
	private String papertype;//证件类型
	private String papernum;//客户证件编号
	private String custgender;//性别
	private String custnationality;//客户国际
	private String custfrom;//客户来源信息
	private String qqnum;//QQ号
	private String weixin;//微信号
	private String Email;//邮箱
	private String curAddress;//当前地址
	private String curAddrPost;//当前邮政编码
	private String postAddr;//邮寄地址
	private String selectPost;
	private String pastEmail;//邮寄地址编码
	private String openFyAcount;
	private String managerId;//客户经理编号
	private String termId;//销售团队编号
	private String integral;//积分
	private String remark1;//备注1
	private String remark2;//备注2

	private CustomAccount customAccount;//账户信息
	private List<CheckInfo> checkInfos;//审核信息
	private List<CheckReceipts>checkReceipts;//客户状态
	private CustomStatus customStatus;

	public String getSelectPost() {
		return selectPost;
	}

	
	
	public String getIntegral() {
		return integral;
	}



	public void setIntegral(String integral) {
		this.integral = integral;
	}



	public void setSelectPost(String selectPost) {
		this.selectPost = selectPost;
	}

	public String getOpenFyAcount() {
		return openFyAcount;
	}

	public void setOpenFyAcount(String openFyAcount) {
		this.openFyAcount = openFyAcount;
	}

	
	
	
    public CustomStatus getCustomStatus() {
		return customStatus;
	}

	public void setCustomStatus(CustomStatus customStatus) {
		this.customStatus = customStatus;
	}

	public String getCurAddress() {
		return curAddress;
	}

	public String getCurAddrPost() {
		return curAddrPost;
	}

	public String getPostAddr() {
		return postAddr;
	}

	public String getPastEmail() {
		return pastEmail;
	}

	public String getManagerId() {
		return managerId;
	}

	public String getTermId() {
		return termId;
	}

	public List<CheckInfo> getCheckInfos() {
		return checkInfos;
	}

	public void setCheckInfos(List<CheckInfo> checkInfos) {
		this.checkInfos = checkInfos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark1() {
		return remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}

	public void setCurAddrPost(String curAddrPost) {
		this.curAddrPost = curAddrPost;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	public void setPastEmail(String pastEmail) {
		this.pastEmail = pastEmail;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public List<CheckReceipts> getCheckReceipts() {
		return checkReceipts;
	}

	public void setCheckReceipts(List<CheckReceipts> checkReceipts) {
		this.checkReceipts = checkReceipts;
	}


	public CustomAccount getCustomAccount() {
		return customAccount;
	}

	public void setCustomAccount(CustomAccount customAccount) {
		this.customAccount = customAccount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}
	
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getPapertype() {
		return papertype;
	}

	public void setPapertype(String papertype) {
		this.papertype = papertype;
	}

	public String getPapernum() {
		return papernum;
	}

	public void setPapernum(String papernum) {
		this.papernum = papernum;
	}

	public String getCustgender() {
		return custgender;
	}

	public void setCustgender(String custgender) {
		this.custgender = custgender;
	}

	public String getCustnationality() {
		return custnationality;
	}

	public void setCustnationality(String custnationality) {
		this.custnationality = custnationality;
	}

	public String getCustfrom() {
		return custfrom;
	}

	public void setCustfrom(String custfrom) {
		this.custfrom = custfrom;
	}

	public String getQqnum() {
		return qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("id=", this.getId())
			.append("custom_name=", this.getCustname())
			.append("Phone_Num=", this.getPhonenum())
			.toString();
    }
}
