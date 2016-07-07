package com.hongxin.action.OA;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.hongxin.entity.EmployStaff;
import com.hongxin.entity.EmployStaffDetail;
import com.hongxin.entity.Organization;
import com.hongxin.entity.User;
import com.hongxin.service.EmployStaffService;
import com.hongxin.service.OrganizationService;
import com.hongxin.service.UserInfoService;
import com.hongxin.utils.AjaxUtils;
import com.opensymphony.xwork2.ActionContext;
/**
 * 组织架构Controller
 */
import com.opensymphony.xwork2.ActionSupport;

public class OrganizationAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private EmployStaff EmployStaff;
	private Organization organization;
	private List<Organization> organizations;
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
	 * 查询组织架构通讯录信息
	 */
	public String OrgList(){
		List<Organization> orgs = organizationService.findById(null);
		organization=orgs.get(0);
		return "orgList";
	}
	/**
	 * 增加员工
	 */
	public void addEmployStaff() {
		// 查询所有组织及部门信息
		/* List<Organization>orgs=organizationService.findAll(); */

	}

	/**
	 * 组织架构通讯图
	 * 
	 * 通过父id查询下层组织
	 * 
	 * @throws IOException
	 */

	public void findById() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Organization> orgs = organizationService.findById(orgId);
		List<EmployStaff> emp = employStaffService.findById(orgId);
		User user = (User) ActionContext.getContext().getSession().get("login_user");
		
		response.getWriter().println("[");
		for (int i = 0; i < orgs.size(); i++) {
			String str = "{id:" + orgs.get(i).getOrgId() + ",name: \"" + orgs.get(i).getOrgName()
						+ "\",isParent:true}";
			
			response.getWriter().println(str);
				if (i < orgs.size()) {
					response.getWriter().println(",");
				}
		}
		if (emp.size()!=0) {
			for (int i = 0; i < emp.size(); i++) {
				
				String str = "{id:" + emp.get(i).getStaffId() + ",name: \"" + emp.get(i).getFullName()
						+ "\"}";
				response.getWriter().println(str);
				if (i < emp.size()-1) {
					response.getWriter().println(",");
				}
			}
		}
		response.getWriter().println("]");
	}
	
	/**
	 * 查询员工档案
	 * @throws IOException 
	 */
	public void findEmpByEmpId() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest();
		String empId=request.getParameter("empId");
		EmployStaff emp=employStaffService.get(empId);
		EmployStaffDetail empde=new EmployStaffDetail();
		empde.setAddress(emp.getAddress());
		empde.setBankPath(emp.getBankPath());
		empde.setBirthday(emp.getBirthday());
		empde.setCalculateWages(emp.getCalculateWages());
		empde.setCardId(emp.getCardId());
		empde.setCardPath(emp.getCardPath());
		empde.setCreateIp(emp.getCreateIp());
		empde.setCreateTime(emp.getCreateTime());
		empde.setCreator(emp.getCreator());
		empde.setDisabilityInfo(emp.getDisabilityInfo());
		empde.setDiseaseInfo(emp.getDiseaseInfo());
		empde.setEducationId(emp.getEducationId());
		empde.setEmployeeId(emp.getEmployeeId());
		empde.setEnglishName(emp.getEnglishName());
		empde.setFullName(emp.getFullName());
		empde.setGradeCode(emp.getGradeCode());
		empde.setHeight(emp.getHeight());
		empde.setIdNumber(emp.getIdNumber());
		empde.setMajor(emp.getMajor());
		empde.setMarriageId(emp.getMarriageId());
		empde.setNationId(emp.getNationId());
		empde.setNatives(emp.getNatives());
		empde.setPicPath(emp.getPicPath());
		empde.setPolicyId(emp.getPolicyId());
		empde.setRemark(emp.getRemark());
		empde.setSex(emp.getSex());
		empde.setStaffId(emp.getStaffId());
		empde.setStateId(emp.getStateId());
		empde.setStationCode(emp.getStationCode());
		empde.setStationDate(emp.getStationDate());
		empde.setStutas(emp.getStutas());
		empde.setTelephone(emp.getTelephone());
		empde.setUpdateIp(emp.getUpdateIp());
		empde.setUpdateTime(emp.getUpdateTime());
		empde.setUpdator(emp.getUpdator());
		empde.setUrgentContact(emp.getUrgentContact());
		empde.setUrgentRelation(emp.getUrgentRelation());
		empde.setUrgentTelephone(emp.getUrgentTelephone());
		empde.setWagesType(emp.getWagesType());
		empde.setWeight(emp.getWeight());
		System.out.println(JSON.toJSONString(empde));
		AjaxUtils.ajaxJSONResponse(empde);;
	}
	/**
	 * 修改员工信息
	 */

	

	/**
	 * 员工调职
	 */

	/**
	 * 员工离职
	 */

	/**
	 * 查询所有组织机构及每个部门下的员工
	 * 
	 * @return
	 */
	public void findAll() {
		organizations = organizationService.findAll();
		AjaxUtils.ajaxJSONResponse(organizations);
	}

	///////////////////////// get---set/////////////////////////
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