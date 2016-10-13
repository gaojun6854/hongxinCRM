package com.hongxin.action.OA;
import java.util.List;

import java.util.Random;

/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.fuiou.data.ArtifRegReqData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.RegReqData;
import com.fuiou.service.FuiouService;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.Picture;
import com.hongxin.service.CheckInfoService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.utils.Constants;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unused")
public class CheckInfoAction extends ActionSupport implements ServletRequestAware{
    private HttpServletRequest request;  
	private static final long serialVersionUID = 1L;
	@Autowired
	private CheckInfoService checkInfoService;
	private List<Picture> picList;
	private String msg;
	
	@Override
	public String execute() throws Exception {
		//保存刚录取的用户信息置session方便事物
		HttpSession session=ServletActionContext.getRequest().getSession();
		String custIDS=(String) request.getParameter("param");
		
		CustomBaseInfo toAddCustomBaseInfo=(CustomBaseInfo)request.getSession().getAttribute("toAddCustomBaseInfo");//得到用户基础信息
		toAddCustomBaseInfo.setId(custIDS);
		CustomAccount toAddCustomAccount=(CustomAccount)request.getSession().getAttribute("toAddCustomAccount");//得到用户基础信息
		
		int a=checkInfoService.createCustomInfos(toAddCustomBaseInfo,toAddCustomAccount);
		
		if(a==1)
			msg= "客户:"+toAddCustomBaseInfo.getCustname()+"信息添加成功";
		else
			msg="客户:"+toAddCustomBaseInfo.getCustname()+"信息添加失败,请稍后再试";

		return SUCCESS;
	}

	/**
	 * 富有注册信息测试
	 * @param args
	 */
	public static void main(String[] args) {
		CustomBaseInfo toAddCustomBaseInfo=new CustomBaseInfo();
		toAddCustomBaseInfo.setCustname("测试用户1");
		toAddCustomBaseInfo.setPapernum("340222198810022902");
		toAddCustomBaseInfo.setPhonenum("15385538980");
		toAddCustomBaseInfo.setEmail("gaojun6854@126.com");
		
		CustomAccount toAddCustomAccount=new CustomAccount();
		toAddCustomAccount.setPayBankName("招商银行股份有限公司上海安亭支行");
		toAddCustomAccount.setAccountBank("6214831217827056");
		
		CommonRspData comrsd=new CommonRspData();
		RegReqData regData=new RegReqData();
		regData.setMchnt_cd(Constants.MCHNT_CD);//商户代码
		regData.setMchnt_txn_ssn("sinorfc"+TimeId.generateSequenceNo());//流水号
		regData.setCust_nm(toAddCustomBaseInfo.getCustname());//客户姓名
		regData.setCertif_id(toAddCustomBaseInfo.getPapernum());//客户身份证
		regData.setMobile_no(toAddCustomBaseInfo.getPhonenum());//客户手机号码
		regData.setEmail(toAddCustomBaseInfo.getEmail());//邮箱
		regData.setCity_id("2900");//开户地区代码--附件---上海
		regData.setParent_bank_id("0308");//开户行----招商银行
		regData.setBank_nm(toAddCustomAccount.getPayBankName());//支行名
		regData.setCapAcntNm(toAddCustomBaseInfo.getCustname());//银行户名
		regData.setCapAcntNo(toAddCustomAccount.getAccountBank());//账号
		regData.setRem("sinorfc创建客户:"+toAddCustomBaseInfo.getCustname());//备注
		try {
			comrsd=FuiouService.reg(regData);
		} catch (Exception e) {
			
		}
		if ("0000".equals(comrsd.getResp_code())) 
			System.err.println("注册成功,返回码："+comrsd.getResp_code());
		else
			System.err.println("注册失败,返回码："+comrsd.getResp_code());
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request=req; 
		
	}

	public List<Picture> getPicList() {
		return picList;
	}

	public void setPicList(List<Picture> picList) {
		this.picList = picList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
