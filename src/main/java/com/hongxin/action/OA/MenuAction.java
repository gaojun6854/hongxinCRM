package com.hongxin.action.OA;
/**
 * 菜单
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.ActionFun;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.ActionFunService;
import com.hongxin.service.MenuService;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport{

	private static final long serialVersionUID = 8051545551005559302L;
	@Autowired
	private MenuService menuService;
	@Autowired
	private ActionFunService actionFunService;
	private List<ResourceBak>menuList;
	private ResourceBak resourceBak;
	private String menuid;
	private String msg;
	private String sourceId;
	private List<ActionFun> funList;
	private ActionFun actionFun;
	private String actionId;
	@Override
	public String execute() throws Exception {
		menuList=menuService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("menuList", menuList);
		return SUCCESS;
	}

	/**
	 * 获取菜单列表
	 * @return
	 */
	public String menuList()
	{
		menuList = menuService.findAll();
		return "menuInfo";
	}

	/**
	 * 初始化增加一级菜单
	 * @return
	 */
	public String addMenu( )
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String method_=request.getParameter("method_");
		String parentSourceId=request.getParameter("menuid");
		request.setAttribute("parentSourceId", parentSourceId);
		if (method_!=null) 
			request.setAttribute("method_", "updateMenu");
		else
			request.setAttribute("method_", "saveMenu");
		if(sourceId != null)
		{
			resourceBak = menuService.get(Integer.parseInt(sourceId));
		}
		return "addMenu"; 
	}
	/**
	 * 增加菜单
	 * @return
	 */
	public String saveMenu()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String resCode=request.getParameter("resCode");
		resourceBak.setSourceCode(resCode);
			//新增
			if(0==resourceBak.getParentSourceId())
			{
				//新增一级菜单
				resourceBak.setParentSourceId(null);
				menuService.save(resourceBak);
			}else
			{
				//新增子菜单
				try {
					Integer resId=menuService.save4parentSourceId(resourceBak);
					ResourceBak resourceBakOld=new ResourceBak();
					resourceBakOld=menuService.get(resId);
					resourceBakOld.setParentSourceId(resourceBak.getParentSourceId());
					menuService.saveOrUpdate(resourceBakOld);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return "redirect_menuList";
	}
	
	/**
	 * 更新菜单
	 * @param resourceBak
	 * @return
	 */
	public String updateMenu()
	{
		//编辑菜单信息
		HttpServletRequest request=ServletActionContext.getRequest();
		String resCode=request.getParameter("resCode");
		resourceBak.setSourceCode(resCode);
		if(0==resourceBak.getParentSourceId())
		{
			resourceBak.setParentSourceId(null);
		}
		menuService.saveOrUpdate(resourceBak);
		
		return "redirect_menuList";
	}
	
	public String deleteMenu()
	{
		List<ResourceBak> list = menuService.getallMenuList(Integer.parseInt(sourceId));
		/*if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++)
			{
//				menuService.deleteMenu(list.get(i).getSourceId());
				List<ResourceBak> list1 = menuService.getallMenuList(list.get(i).getSourceId());
				for(int j=0;j<list.size();j++){
					menuService.delete(list1.get(j).getSourceId());
				}
				menuService.delete(list.get(i).getSourceId()); 
			}
		}*/
		//删除关联关系(菜单，角色)
		//rs.deleteBySourceId(sourceId);
		//删除关联关系（菜单，功能）
		//afs.deleteBysourceId(sourceId);
		//删除角色功能点关联关系（角色，功能）
		
		//删除菜单
		if (list!=null && list.size()>0) {
			msg="请删除该一级菜单下子菜单方可删除该父菜单!";
		}else{
			try {
				menuService.delete(Integer.parseInt(sourceId));
			} catch (Exception e) {
				e.printStackTrace();
				msg="系统故障,稍后再试!";
			}
		}
		return "redirect_menuList";
	}
	
	
	/**
	 * 获取菜单的功能点列表信息
	 * @return
	 */
	public String functionList()
	{
		funList=menuService.getFunctionList(sourceId);
		return "funInfo";
	}
	
	/**
	 * 新增功能点
	 * @return
	 */
	public String addFun()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("method_", request.getParameter("method_"));
		return "addFun";
	}
	
	/**
	 * 保存功能点
	 * @return
	 */
	public String saveFun()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		actionFun.setActionId(TimeId.generateSequenceNo());
		actionFunService.save(actionFun);
		request.setAttribute("sourceId", actionFun.getSourceId());
		return "redirect_functionList";
	}
	
	/**
	 * 编辑功能点
	 * @return
	 */
	public String editFun()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("method_", request.getParameter("method_"));
		actionFun = actionFunService.get(actionId);
		sourceId=actionFun.getSourceId();
		return "addFun";
	}
	/**
	 * 更新功能点
	 * @return
	 */
	public String updateFun()
	{
		try {
			actionFunService.saveOrUpdate(actionFun);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sourceId = actionFun.getSourceId();
		return "redirect_functionList";
	}
	
	
	/**
	 * 删除功能点信息
	 * @return
	 */
	public String deleteFun()
	{
		try {
			actionFunService.delete(actionId);
		} catch (Exception e) {
			msg = "系统异常,稍后再试";
		}
		return "redirect_functionList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////get---set///////////////////////
	public List<ResourceBak> getMenuList() {
		return menuList;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public ActionFun getActionFun() {
		return actionFun;
	}

	public void setActionFun(ActionFun actionFun) {
		this.actionFun = actionFun;
	}

	public void setMenuList(List<ResourceBak> menuList) {
		this.menuList = menuList;
	}

	public ResourceBak getResourceBak() {
		return resourceBak;
	}

	public void setResourceBak(ResourceBak resourceBak) {
		this.resourceBak = resourceBak;
	}

	public String getMenuid() {
		return menuid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getSourceId() {
		if (sourceId==null) {
			return actionFun.getSourceId();
		}
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public List<ActionFun> getFunList() {
		return funList;
	}

	public void setFunList(List<ActionFun> funList) {
		this.funList = funList;
	}
	
}
