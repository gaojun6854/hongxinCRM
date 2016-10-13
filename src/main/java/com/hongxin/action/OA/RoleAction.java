package com.hongxin.action.OA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongxin.entity.ResourceBak;
import com.hongxin.entity.Role;
import com.hongxin.entity.RoleActionFun;
import com.hongxin.entity.RoleSource;
import com.hongxin.entity.User;
import com.hongxin.entity.UsrRole;
import com.hongxin.service.ActionFunService;
import com.hongxin.service.MenuService;
import com.hongxin.service.RoleActionFunService;
import com.hongxin.service.RoleService;
import com.hongxin.service.RoleSourceService;
import com.hongxin.service.UserRoleService;
import com.hongxin.utils.AjaxUtils;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private ActionFunService actionFunService;
	@Autowired
	private RoleSourceService roleSourceService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleActionFunService roleActionFunService;
	
	private String roleId;
	private String msg;
	private List<Role> roleList;
	private List<UsrRole> userRoleList;
	List<RoleActionFun> RoleActionFunList;
	private Role role;
	private List<ResourceBak> menus;
	private List<RoleActionFun> myroleactionList;
	
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
		
	}	
		/**
		 * 新增角色
		 */
		public String addRole()
		{
			int i=roleService.selectByName(role.getRoleName());
			if(i>0)
			{
				msg = "<script type='text/javascript'>alert('名称已经存在');</script>";
				return "addRole";
			}else
			{
				role.setRoleId(TimeId.generateSequenceNo().toString());
				try {
					roleService.save(role);
					msg = "<script type='text/javascript'>alert('保存成功');</script>";
				} catch (Exception e) {
					msg = "<script type='text/javascript'>alert('系统错误,保存失败');</script>";
				}
			}
			return "forward_allInfo";
		}	
		
		
		/**
		 * 删除角色
		 */
		public void deleteRole()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String[] idss=request.getParameter("ids").toString().split(",");
			
			if(idss.length>0){
				for (int i = 0; i < idss.length; i++) {
					try {
						//删除，用户角色表
						if (userRoleService.get(idss[i])!=null)
							userRoleService.delete(idss[i]);
						//删除，角色功能表
						if (actionFunService.get(idss[i])!=null)
							actionFunService.delete(idss[i]);
						//删除，角色菜单
						if (roleSourceService.get(idss[i])!=null)
							roleSourceService.delete(idss[i]);
						//删除，角色表
						if (roleService.get(idss[i])!=null)
							roleService.delete(idss[i]);
					} catch (Exception e) {
						e.printStackTrace();
						AjaxUtils.ajaxResponse("msg = \"<script>alert(\"操作失败\");</script>\"");
					}
				}
			}else{
				AjaxUtils.ajaxResponse("msg = \"<script>alert(\"请至少选择一条数据\");</script>\"");
			}
		}
		
		/**
		 * 普通 用户
		 * 查看权限分配按钮
		 * @param roleId
		 * @return
		 */
		public String selectResourceBakByroleId()
		{
			User user = (User) ActionContext.getContext().getSession().get("login_user");
			String admin = user.getUserName().toString();
			if(admin.equals("admin")||admin.equals("gaojun")){
				return "forward_allResourceAndAction";
			}
			//找到我的菜单
			roleList = roleService.getByRoleId(roleId);
			//通过菜单找到我的菜单功能点
			RoleActionFunList = roleActionFunService.selectByRoleId(roleId);
			
			//将所有的菜单权限与当前用户的相比较
			return "myresourceActionList";
		}
		
		/**
		 * 超级管理员
		 * 查看所有菜单及其权限,并可以分配
		 */
		public String allResourceAndAction()
		{
		//	List<ResourceBak> alllist = ms.selectAllFuntionAndResourceBak();//直接列出所有菜单和功能点
			menus = menuService.getMenuListFunlist();///获取菜单的功能点
			//得到对应角色的id
			//通过角色id得到对应的关联关系，然后比对角色功能表中的“功能编号“与功能表中的”功能编号“，
			//相同则checkbox为checked
			myroleactionList = roleActionFunService.selectByRoleId(roleId);
			//map.put("resourceAction", alllist);
			return "menuLimitInfo";
		}
		
		/**
		 * 将菜单权限分配给角色
		 * @param roleId
		 * @param request
		 * @return
		 */
		public String addLimitToRole()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String[] limit = request.getParameterValues("limit");
			//插入之前，先删掉所有与该角色相关的权限
			roleActionFunService.deleteByRoleId(roleId);
			//根据角色id删掉所有的角色菜单表中的关联关系
			roleSourceService.deleteByRoleId(roleId);
			/**
			 * 1.先通过sql在功能表中找到所有的对应的菜单id
			 * 2.通过菜单id和roleid想菜单角色表中插入关联关系
			 */
			List<String> actionIdList = new ArrayList<String>();
			if(limit!=null)
			{
				for(int i=0;i<limit.length;i++){
					actionIdList.add(limit[i]);
				}
			}else{
				//如果页面传值为空，则随机传入uuid，可以返回空数据
				actionIdList.add(TimeId.generateSequenceNo());
			}
			List sourceList = actionFunService.selectSourceIdByactionIds(actionIdList);
			
			if(sourceList.size()>0){
				//然后根据，菜单的id添加角色菜单表中的关联关系
				RoleSource rolesource = new RoleSource();
				rolesource.setRoleId(roleId);

				//将List中的Map集合遍历出来
				for(int i=0;i<sourceList.size();i++){
					System.out.println(sourceList.get(i));
						//打印出菜单id--System.out.println(map.get(it.next())); 
						rolesource.setRoleSourceId(TimeId.generateSequenceNo());
						rolesource.setSourceId(sourceList.get(i).toString());
						roleSourceService.save(rolesource);
				}
				
				//实例化RoleActionFun，插入对象
				RoleActionFun roleaction = new RoleActionFun();
				roleaction.setRoleId(roleId);
				for(int i=0;i<limit.length;i++){
					roleaction.setRoleFuncId(TimeId.generateSequenceNo());
					roleaction.setActionId(limit[i]);
					roleActionFunService.saveOrUpdate(roleaction);
				}
			}
			
			request.setAttribute("msg", "<script>alert('分配成功！');</script>");
			return "forward_resourceBakByroleId";
		}
		
		
		
		
		
		
		
		
		
		
		
		//////////////get----set//////////
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<RoleActionFun> getRoleActionFunList() {
		return RoleActionFunList;
	}
	public void setRoleActionFunList(List<RoleActionFun> roleActionFunList) {
		RoleActionFunList = roleActionFunList;
	}
	public List<ResourceBak> getMenus() {
		return menus;
	}
	public void setMenus(List<ResourceBak> menus) {
		this.menus = menus;
	}
	public List<RoleActionFun> getMyroleactionList() {
		return myroleactionList;
	}
	public void setMyroleactionList(List<RoleActionFun> myroleactionList) {
		this.myroleactionList = myroleactionList;
	}
	
}
