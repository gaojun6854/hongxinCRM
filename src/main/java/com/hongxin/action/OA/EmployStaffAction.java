package com.hongxin.action.OA;

import java.util.List;

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
		List<Organization>orgs=organizationService.findAll();
		return "addEmployStaff";
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
}