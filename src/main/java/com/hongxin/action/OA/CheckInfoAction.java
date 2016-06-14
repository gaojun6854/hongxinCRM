package com.hongxin.action.OA;
import java.util.List;

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
	
	@Override
	public String execute() throws Exception {
		//保存刚录取的用户信息置session方便事物
		HttpSession session=ServletActionContext.getRequest().getSession();
		String custIDS=(String) request.getParameter("param");
		
		CustomBaseInfo toAddCustomBaseInfo=(CustomBaseInfo)request.getSession().getAttribute("toAddCustomBaseInfo");//得到用户基础信息
		toAddCustomBaseInfo.setId(custIDS);
		CustomAccount toAddCustomAccount=(CustomAccount)request.getSession().getAttribute("toAddCustomAccount");//得到用户基础信息
		/**
		 * 富友API->>>>>>>>>>>>>>
		 */
		boolean fuyou=false;
		
		/*
		 * API
		 */
		/*CommonRspData comrsd=new CommonRspData();
		RegReqData regData=new RegReqData();
		regData.setMchnt_cd(Constants.MCHNT_CD);
		regData.setMchnt_txn_ssn("sinorfc"+TimeId.generateSequenceNo());
		regData.setCust_nm(toAddCustomBaseInfo.getCustname());
		regData.setCertif_id(toAddCustomBaseInfo.getPapernum());
		regData.setMobile_no(toAddCustomBaseInfo.getPhonenum());
		regData.setEmail(toAddCustomBaseInfo.getEmail());
		regData.setCity_id("2900");
		regData.setParent_bank_id("0308");
		regData.setBank_nm("招行");
		regData.setCapAcntNm(toAddCustomBaseInfo.getCustname());
		regData.setCapAcntNo("6214831217828899");
		regData.setRem("sinorfc创建客户:"+toAddCustomBaseInfo.getCustname());
		try {
			comrsd=FuiouService.reg(regData);
			System.out.println(comrsd.getResp_code());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("flag", "富友内部错误，代码："+comrsd.getResp_code());
			return SUCCESS;
		}
		if ("0000".equals(comrsd.getResp_code())) {*/
			int a=checkInfoService.createCustomInfos(toAddCustomBaseInfo,toAddCustomAccount);
			if(a==1){
				request.setAttribute("flag", "客户:"+toAddCustomBaseInfo.getCustname()+"信息添加成功");
			}else{
				request.setAttribute("flag", "客户:"+toAddCustomBaseInfo.getCustname()+"信息添加失败,请稍后再试");
			}
		//}
		//<<<<<<<<<<-
		
		return SUCCESS;
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
	
}
