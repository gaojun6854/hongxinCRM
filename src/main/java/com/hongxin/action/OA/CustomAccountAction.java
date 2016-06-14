package com.hongxin.action.OA;
import java.util.UUID;

/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unused")
public class CustomAccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private CustomAccountService customAccountService;
	private CustomAccount customAccount;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		//保存刚录取的用户信息置session方便事物
		request.getSession().setAttribute("toAddCustomAccount", customAccount);
		request.setAttribute("url", "custom/addCheckInfo.action");//上传图片后跳转的地址
		request.setAttribute("custIDS", UUID.randomUUID().toString());//上传图片后跳转的地址
		request.getSession().setAttribute("picType", "1");
		return SUCCESS;
	}

	public CustomAccount getCustomAccount() {
		return customAccount;
	}

	public void setCustomAccount(CustomAccount customAccount) {
		this.customAccount = customAccount;
	}


}
