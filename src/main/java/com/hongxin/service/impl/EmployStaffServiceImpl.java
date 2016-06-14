package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.EmployStaffDao;
import com.hongxin.entity.EmployStaff;
import com.hongxin.service.EmployStaffService;

@Service("employStaffService")
public class EmployStaffServiceImpl implements EmployStaffService {

	@Autowired
	private EmployStaffDao employStaffDao;

	public EmployStaff load(String id) {
		return employStaffDao.load(id);
	}

	public EmployStaff get(String id) {
		return employStaffDao.get(id);
	}

	public List<EmployStaff> findAll() {
		return employStaffDao.findAll();
	}

	public void persist(EmployStaff entity) {
		employStaffDao.persist(entity);
		
	}

	public String save(EmployStaff entity) {
		return employStaffDao.save(entity);
	}

	public void saveOrUpdate(EmployStaff entity) {
		employStaffDao.saveOrUpdate(entity);		
	}

	public void delete(String id) {
		employStaffDao.delete(id);		
	}

	public void flush() {
		employStaffDao.flush();		
	}
	
}
