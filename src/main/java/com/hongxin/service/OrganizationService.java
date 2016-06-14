package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.Organization;

public interface OrganizationService 
{
	Organization load(String id);

	Organization get(String id);

	List<Organization> findAll();

	void persist(Organization entity);

	String save(Organization entity);

	void saveOrUpdate(Organization entity);

	void delete(String id);

	void flush();

}
