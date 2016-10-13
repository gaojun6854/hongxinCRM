package com.hongxin.action.OA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.ActionFun;
import com.hongxin.entity.ResourceBak;
import com.hongxin.entity.RoleActionFun;
import com.hongxin.entity.RoleSource;
import com.hongxin.entity.User;
import com.hongxin.entity.UsrRole;
import com.hongxin.service.ActionFunService;
import com.hongxin.service.MenuService;
import com.hongxin.service.RoleActionFunService;
import com.hongxin.service.RoleService;
import com.hongxin.service.RoleSourceService;
import com.hongxin.service.UserInfoService;
import com.hongxin.service.UserRoleService;
import com.hongxin.utils.MD5;
import com.opensymphony.xwork2.ActionContext;

public class UserLoginAction extends BaseAction {

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
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleActionFunService roleActionFunService;
	@Autowired
	private ActionFunService actionFunService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleSourceService roleSourceService;
	private List<ResourceBak>menuList;
	private List<ActionFun> actionfunlist;
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id = ServletActionContext.getRequest().getParameter("id");
		String passWord = ServletActionContext.getRequest().getParameter("passWord");
		
		User user=userInfoService.get(id);
		if (user==null) {
			msg= "0";
			return LOGIN;
		}
		
		//用userID查询用户自己的UserROle
		List<UsrRole> userRoles=new ArrayList<UsrRole>();
		userRoles=userRoleService.selectByUserId(user.getUserId());
		
		if (userRoles!=null) {
			//查询出来的UserRole在去查询Role中对应的角色
			for (UsrRole usrRole : userRoles) {
				usrRole.setRoles(roleService.getByRoleId(usrRole.getRoleId()));
			}
		}
		user.setUserole(userRoles);
		
		//MD5加密
		MD5 md5=new MD5();
		String inputStr=md5.getMD5ofStr(passWord);
		if (!inputStr.equals(user.getPassword())) {
			msg= "0";
			return LOGIN;
		}
		//保存用户至session
		setSessionUser(user);
		
		if (user.getIsEnable() == 0) {
			if (!user.getUserName().equals("admin") && !user.getUserName().equals("gaojun") ) {
				List<String> roleIdList=new ArrayList<String>();
				for (int i = 0; i < user.getUserole().size()&&user.getUserole().size()!=0; i++) {
					String roleId = user.getUserole().get(i).getRoles().get(0).getRoleId();
					roleIdList.add(roleId);// 将角色的roleId存放在list中
				}
				request.getSession().setAttribute("roleIdList", roleIdList);
				// 我的角色，通过角色查找权限
				return "redirect_getLimit";
			}
			return "redirect_getAllLimit";
		}else {
			//用户被锁住 没有权限登陆
			ActionContext.getContext().put("tip", "<script>alert('该用户已被禁用，请联系管理员！')</script>");
			msg= "1";
			return LOGIN;
		}
		/*User user = (User) ActionContext.getContext().getSession().get("login_user");
		LOGGER.info("查询登录用户");
		if (user!=null) {
			menuList=menuService.findAll();
			ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);
			return SUCCESS;
		}else if (id==null) {
			msg= "3";
			return LOGIN;
		}
		userInfo = userInfoService.get(id);
		if (userInfo==null) {
			LOGGER.info("登录失败，重新登录");
			msg= "1";
			return LOGIN;
		}
		ServletActionContext.getRequest().setAttribute("logRec", "用户-"+userInfo.getUserName()+"-登录");
		
		if (!userInfo.getPassword().equals(name)) {
			LOGGER.info("登录失败，重新登录");
			msg= "0";
			return LOGIN;
		}
		LOGGER.info("登录成功");
		menuList=menuService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);*/
	}
	
	/**
	 * 获取普通用户权限
	 * @param map
	 * @param session
	 * @return
	 */
	public String getLimit() {
		HttpServletRequest request=ServletActionContext.getRequest();
		
		List<String> roleIdList=new ArrayList<String>();
		List<String> mylimits=new ArrayList<String>();
		roleIdList=(List<String>) request.getSession().getAttribute("roleIdList");
		List<String> actionIdList=new ArrayList<String>();
		// 将权限存储在集合中
		if (roleIdList!=null) {
			for (int i = 0; i < roleIdList.size(); i++) {
				// 联合查询
				List<RoleActionFun> limitList = roleActionFunService.selectByRoleId(roleIdList.get(i));
				for (RoleActionFun roleActionFun : limitList) {
					roleActionFun.setActionfun(actionFunService.get(roleActionFun.getActionId()));
				}
				for (int j = 0; j < limitList.size(); j++) {
					// 获取对应的功能点，并放入集合中
					String function = limitList.get(j).getActionfun().getFunc();
					mylimits.add(function);
				}
			}
		}
		request.getSession().setAttribute("mylimits", mylimits);
		return "redirect_myInit";
	}
	
	public String getAllLimit() {
		HttpServletRequest request=ServletActionContext.getRequest();
		// 将权限存储在集合中
		List<String> mylimits=new ArrayList<String>();
		
		// 获取所有的功能点
		actionfunlist = actionFunService.findAll();
		
		for (int i = 0; i < actionfunlist.size(); i++) {
			String func = actionfunlist.get(i).getFunc();
			mylimits.add(func);
		}

		request.getSession().setAttribute("mylimits", mylimits);
		return "redirect_init";
	}
	
	/**
	 * 获取全部菜单
	 * 管理员
	 * @return
	 */
	public String init()
	{
		List<ResourceBak> menuList = menuService.findAll();
		getSession2().setAttribute("menuList", menuList);
		return "main";
	}
	
	/**
	 * 获取用户该有权限下对应的菜单
	 * @param map
	 * @param session
	 * @return
	 */
	public String myInit()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取角色idList
		List<String> roleIdList = (List)request.getSession().getAttribute("roleIdList");
		//用角色id在角色菜单表中查询
		//将普通用户的菜单id放入list中
		List<String> sourceIdList = new ArrayList<String>();
		for(String roleId: roleIdList){
			List<RoleSource> roleSourceList= roleSourceService.getMyResourceByroleId(roleId);
			for(RoleSource roleSou: roleSourceList){
				sourceIdList.add(roleSou.getSourceId());
			}
		}
		List<ResourceBak> menuList = menuService.findAll();
		getSession2().setAttribute("sourceIdList", sourceIdList);
		getSession2().setAttribute("menuList", menuList);
		return "main";
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
	
	public static void main(String[] args) {
		System.out.println(new MD5().getMD5ofStr("xiongjian"));
	}

	public List<ActionFun> getActionfunlist() {
		return actionfunlist;
	}

	public void setActionfunlist(List<ActionFun> actionfunlist) {
		this.actionfunlist = actionfunlist;
	}
	
}
