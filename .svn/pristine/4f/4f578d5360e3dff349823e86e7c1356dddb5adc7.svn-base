package com.hongxin.action.OA;
/**
 * 菜单加载
 */
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.MenuService;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport{

	private static final long serialVersionUID = 8051545551005559302L;
	@Autowired
	private MenuService menuService;
	private List<ResourceBak>menuList;
	
	@Override
	public String execute() throws Exception {
		menuList=menuService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);
		return SUCCESS;
	}

	public List<ResourceBak> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<ResourceBak> menuList) {
		this.menuList = menuList;
	}
	
}
