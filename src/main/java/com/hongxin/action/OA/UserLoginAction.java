package com.hongxin.action.OA;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.ResourceBak;
import com.hongxin.entity.User;
import com.hongxin.service.MenuService;
import com.hongxin.service.UserInfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport{

	/**
	 * 用户登录Action
	 */
	private static final long serialVersionUID = 8051545551005559302L;
	private static final Logger LOGGER = Logger.getLogger(UserLoginAction.class);
	private Integer id;
	private String name;
	private User userInfo;
	private String msg;

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private MenuService menuService;
	private List<ResourceBak>menuList;
	
	@Override
	public String execute() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		String name = ServletActionContext.getRequest().getParameter("name");
		User user = (User) ActionContext.getContext().getSession().get("login_user");
		LOGGER.info("查询登录用户");
		if (user!=null) {
			menuList=menuService.findAll();
			ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);
			return SUCCESS;
		}else if (id==null) {
			ServletActionContext.getRequest().getSession().setAttribute("msg", "3");
			return LOGIN;
		}
		userInfo = userInfoService.get(id);
		if (userInfo==null) {
			LOGGER.info("登录失败，重新登录");
			ServletActionContext.getRequest().getSession().setAttribute("msg", "1");
			return LOGIN;
		}
		ServletActionContext.getRequest().setAttribute("logRec", "用户-"+userInfo.getUserName()+"-登录");
		
		if (!userInfo.getPassword().equals(name)) {
			LOGGER.info("登录失败，重新登录");
			ServletActionContext.getRequest().getSession().setAttribute("msg", "0");
			return LOGIN;
		}
		LOGGER.info("登录成功");
		menuList=menuService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);
		ActionContext.getContext().getSession().put("login_user",userInfo);
		return SUCCESS;
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("login_user");
		return LOGIN;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public List<ResourceBak> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<ResourceBak> menuList) {
		this.menuList = menuList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
