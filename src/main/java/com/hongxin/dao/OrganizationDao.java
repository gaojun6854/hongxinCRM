package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.Organization;

public interface OrganizationDao extends BaseDao<Organization, String>{
	//通过父id查询下层组织
	List<Organization> findById(String orgId);
}
