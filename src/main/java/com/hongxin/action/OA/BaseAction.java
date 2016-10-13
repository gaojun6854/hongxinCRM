package com.hongxin.action.OA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hongxin.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有Action的基类
 * 
 * 
 */
public class BaseAction extends ActionSupport{
	
	private static final long serialVersionUID = 3959667273533043265L;

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected User getSessionUser() {
		User user = (User) ActionContext.getContext().getSession().get("login_user");
		return user;
	}
	
	/**
	 * 保存用户对象到Session中
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(User user) {
		ActionContext.getContext().getSession().put("login_user",user);
	}
	
	/**
	 * 获取Request
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	/**
	 * 获取Response
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	/**
	 * 获取Session
	 */
	public HttpSession getSession2() {
		return ServletActionContext.getRequest().getSession();
	}
}
