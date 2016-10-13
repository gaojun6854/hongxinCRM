package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.RoleActionFun;

public interface RoleActionFunService 
{
	RoleActionFun load(String id);

	RoleActionFun get(String id);

	List<RoleActionFun> findAll();

	void persist(RoleActionFun entity);

	String save(RoleActionFun entity);

	void saveOrUpdate(RoleActionFun entity);

	void delete(String id);

	void flush();

	List<RoleActionFun> selectByRoleId(String roleId);

	void deleteByRoleId(String roleId);

}
