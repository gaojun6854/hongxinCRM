package com.hongxin.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.EmployStaffDao;
import com.hongxin.dao.OrganizationDao;
import com.hongxin.entity.EmployStaff;
import com.hongxin.entity.Organization;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.OrganizationService;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private EmployStaffDao employStaffDao;

	public Organization load(String id) {
		return organizationDao.load(id);
	}

	public Organization get(String id) {
		return organizationDao.get(id);
	}

	public List<Organization> findAll() {
		List<Organization> organizationList=organizationDao.findAll();
		for (Organization organization : organizationList) {
			if (!organization.getEmployStaffs().isEmpty()) {
				for (EmployStaff emp : organization.getEmployStaffs()) {
					organization.getEmployStaffs2().add(emp);
				}
			}
			List<Organization> organizationChilds=new ArrayList<Organization>();
			for (Organization organization1 : organization.getOrganizations()) {
				organizationChilds.add(organization1);//加个排序
			}
			//////////////////////////////////////////////
			//插入排序  对下层子菜单进行排序
			Organization key;
			int i;
			int j;
			for (j = 1; j < organizationChilds.size(); j++) {
				key=organizationChilds.get(j);
				i=j-1;
				while (i>=0 && organizationChilds.get(i).getSeq()>key.getSeq()) {
					organizationChilds.set(i+1, organizationChilds.get(i));
					i--;
				}
				organizationChilds.set(i+1, key);
			}
			////////////////////////////////////////////排序结束
			
			organization.setChildOrganization(organizationChilds);//添加到每个主菜单下作为子菜单的成员
		}
		List<Organization> menus=new ArrayList<Organization>();
		for (Organization organization : organizationList) {
			if (organization.getParentOrganization()==null) {
				menus.add(organization);
			}
		}
		return menus;
	}

	public void persist(Organization entity) {
		organizationDao.persist(entity);
		
	}

	public String save(Organization entity) {
		return organizationDao.save(entity);
	}

	public void saveOrUpdate(Organization entity) {
		organizationDao.saveOrUpdate(entity);		
	}

	public void delete(String id) {
		organizationDao.delete(id);		
	}

	public void flush() {
		organizationDao.flush();		
	}
	
}
