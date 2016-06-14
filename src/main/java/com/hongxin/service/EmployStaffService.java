package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.EmployStaff;

public interface EmployStaffService 
{
	EmployStaff load(String id);

	EmployStaff get(String id);

	List<EmployStaff> findAll();

	void persist(EmployStaff entity);

	String save(EmployStaff entity);

	void saveOrUpdate(EmployStaff entity);

	void delete(String id);

	void flush();

}
