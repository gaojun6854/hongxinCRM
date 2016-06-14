package com.hongxin.action.OA;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.UserInfo;
import com.hongxin.service.UserInfoService;
import com.hongxin.utils.AjaxUtils;
import com.opensymphony.xwork2.ActionSupport;

public class UserinfoAction extends ActionSupport
		 {
	private static final long serialVersionUID = -2301203156032690317L;

	private static final Logger LOGGER = Logger.getLogger(UserinfoAction.class);
	private Integer id;
	private UserInfo userInfo;
	private List<UserInfo> userInfos;

	@Autowired
	private UserInfoService userInfoService;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("logRec", "查询所有用户信息");
		LOGGER.info("查询所有用户");
		//userInfos = userInfoService.findAll();
		return SUCCESS;
		
	}

	public void detail() {
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("logRec", "查用户xiangxi");
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看用户详情：" + id);
		//userInfo = userInfoService.get(Integer.valueOf(id));
		AjaxUtils.ajaxJSONResponse(userInfo);

	}

	public void prepare() throws Exception {
		
	}
	
}