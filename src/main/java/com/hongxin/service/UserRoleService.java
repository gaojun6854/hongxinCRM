package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.UsrRole;

public interface UserRoleService 
{
	UsrRole load(String id);

	UsrRole get(String id);

	List<UsrRole> findAll();

	void persist(UsrRole entity);

	String save(UsrRole entity);

	void saveOrUpdate(UsrRole entity);

	void delete(String id);

	void flush();

	List<UsrRole> selectByUserId(String userId);

}
