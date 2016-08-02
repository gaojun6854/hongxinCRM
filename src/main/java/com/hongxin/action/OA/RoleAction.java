package com.hongxin.action.OA;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.Role;
import com.hongxin.entity.User;
import com.hongxin.entity.UsrRole;
import com.hongxin.service.RoleService;
import com.hongxin.service.UserInfoService;
import com.hongxin.service.UserRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	
	private List<Role> roleList;
	private List<UsrRole> userRoleList;
	
	public String roleList()
	{
		//判断登录者是否为超级管理员，只有超级管理员才能分配权限
		User user = (User) ActionContext.getContext().getSession().get("login_user");
		String admin = user.getUserName().toString();
		//管理员需要固定角色不变，并且不能删除
		if(admin.equals("admin")||admin.equals("gaojun")){
			//管理员登录，可以给其他用户分配权限，并可以查看所有菜单权限列表
			try {
				roleList= roleService.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "role_allInfo";
		}else{
			//普通用户，只能查看自己的菜单和权限
			String userId = user.getUserId().toString();
			userRoleList= userRoleService.selectByUserId(userId);
			for (UsrRole usrRole : userRoleList) {
				usrRole.setRoles(roleService.getByRoleId(usrRole.getRoleId()));
			}
			return "myRole_allInfo";
	}
		
		
		
		
		//////////////get----set//////////
		
}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<UsrRole> getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List<UsrRole> userRoleList) {
		this.userRoleList = userRoleList;
	}
	
}
