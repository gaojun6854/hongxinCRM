package com.hongxin.action.OA;
import java.util.List;
import java.util.UUID;

/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fuiou.data.CommonRspData;
import com.fuiou.data.RegReqData;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.TAreaCode;
import com.hongxin.entity.TBankCode;
import com.hongxin.service.AreaCodeService;
import com.hongxin.service.BankCodeService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.utils.AjaxUtils;
import com.hongxin.utils.Constants;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unused")
public class CustomAccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private CustomAccountService customAccountService;
	@Autowired
	private AreaCodeService areaCodeService;
	@Autowired
	private BankCodeService bankCodeService;
	private CustomAccount customAccount;
	private TAreaCode areaCode;
	private TBankCode bankCode;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();

		//修改用户本地信息
		TBankCode bank=bankCodeService.get(customAccount.getPayBank());//银行总行信息
		customAccount.setBankHead(bank.getBankName());//总行名称
		
		request.getSession().setAttribute("toAddCustomAccount", customAccount);
		request.setAttribute("url", "custom/addCheckInfo.action");//上传图片后跳转的地址
		request.setAttribute("custIDS", UUID.randomUUID().toString());//上传图片后跳转的地址
		
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
