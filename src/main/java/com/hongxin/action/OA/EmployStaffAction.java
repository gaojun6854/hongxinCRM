package com.hongxin.action.OA;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.EmployStaff;
import com.hongxin.entity.Organization;
import com.hongxin.service.EmployStaffService;
import com.hongxin.service.OrganizationService;
import com.hongxin.service.UserInfoService;
import com.hongxin.utils.AjaxUtils;
/**
 * 员工档案实体类
 */
import com.opensymphony.xwork2.ActionSupport;

public class EmployStaffAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private EmployStaff EmployStaff;
	private Organization organization;
	private List<Organization>organizations;
	private String orgId;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private EmployStaffService employStaffService;
	@Autowired
	private UserInfoService userInfoService;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 增加员工
	 */
	public String addEmployStaff(){
		//查询所有组织及部门信息
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Organization>orgs=organizationService.findAll();
		request.setAttribute("orgs", orgs);
		return "addEmployStaff";
	}
	
	/**
	 * 通过父id查询下层组织
	 */
	public void findById(){
		/*List<Organization>orgs=organizationService.findById(orgId);
		AjaxUtils.ajaxJSONResponse(orgs);*/
		String s1 = "{id:1, pId:0, name:\"test1\" , open:true}";  
        String s2 = "{id:2, pId:1, name:\"test2\" , open:true}";  
        String s3 = "{id:3, pId:1, name:\"test3\" , open:true}";  
        String s4 = "{id:4, pId:2, name:\"test4\" , open:true}";  
        List<String> lstTree = new ArrayList<String>();  
        lstTree.add(s1);  
        lstTree.add(s2);  
        lstTree.add(s3);  
        lstTree.add(s4);  
        //利用Json插件将Array转换成Json格式  
        AjaxUtils.ajaxJSONResponse(lstTree);
	}
	
	/**
	 * 修改员工信息
	 */
	
	/**
	 * 查询员工档案
	 */
	
	/**
	 * 员工调职
	 */
	
	/**
	 * 员工离职
	 */
	
	/**
	 * 查询所有组织机构及每个部门下的员工
	 * @return
	 */
	public void findAll(){
		organizations=organizationService.findAll();
		AjaxUtils.ajaxJSONResponse(organizations);
	}
	/////////////////////////get---set/////////////////////////
	public EmployStaff getEmployStaff() {
		return EmployStaff;
	}
	public void setEmployStaff(EmployStaff employStaff) {
		EmployStaff = employStaff;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}