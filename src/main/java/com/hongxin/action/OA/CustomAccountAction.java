package com.hongxin.action.OA;
import java.util.List;
import java.util.UUID;

/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.TAreaCode;
import com.hongxin.entity.TBankCode;
import com.hongxin.service.AreaCodeService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.utils.AjaxUtils;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unused")
public class CustomAccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private CustomAccountService customAccountService;
	@Autowired
	private AreaCodeService areaCodeService;
	private CustomAccount customAccount;
	private TAreaCode areaCode;
	private TBankCode bankCode;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		//保存刚录取的用户信息置session方便事物
		String provinceCode=request.getParameter("provinceCode");//省份
		String cityCode=request.getParameter("cityCode");//城市
		String bankCode=request.getParameter("bankCode");//银行代码
		String bankName=request.getParameter("bankName");//支行机构名
		request.getSession().setAttribute("toAddCustomAccount", customAccount);
		request.setAttribute("url", "custom/addCheckInfo.action");//上传图片后跳转的地址
		request.setAttribute("custIDS", UUID.randomUUID().toString());//上传图片后跳转的地址
		request.getSession().setAttribute("picType", "1");
		return SUCCESS;
	}

	
	public void findCity(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String areaId=request.getParameter("areaId");
		List<TAreaCode>cityList=areaCodeService.getProvinceList(areaId);
		AjaxUtils.ajaxJSONResponse(cityList);
	}
	
	
	public CustomAccount getCustomAccount() {
		return customAccount;
	}

	public void setCustomAccount(CustomAccount customAccount) {
		this.customAccount = customAccount;
	}

	public TAreaCode getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(TAreaCode areaCode) {
		this.areaCode = areaCode;
	}

	public TBankCode getBankCode() {
		return bankCode;
	}

	public void setBankCode(TBankCode bankCode) {
		this.bankCode = bankCode;
	}


}
