package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.RoleSource;

public interface RoleSourceService 
{
	RoleSource load(String id);

	RoleSource get(String id);

	List<RoleSource> findAll();

	void persist(RoleSource entity);

	String save(RoleSource entity);

	void saveOrUpdate(RoleSource entity);

	void delete(String id);

	void flush();

	void deleteByRoleId(String roleId);

	List<RoleSource> getMyResourceByroleId(String roleId);

}
