package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.UserRoleDao;
import com.hongxin.entity.UsrRole;
import com.hongxin.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	public UsrRole get(String id) {
		return userRoleDao.get(id);
	}

	public List<UsrRole> findAll() {
		return userRoleDao.findAll();
	}

	public void persist(UsrRole entity) {
		userRoleDao.persist(entity);
	}

	public String save(UsrRole entity) {
		return userRoleDao.save(entity);
	}

	public void saveOrUpdate(UsrRole entity) {
		userRoleDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		userRoleDao.delete(id);
	}

	public void flush() {
		userRoleDao.flush();
	}

	public UsrRole load(String id) {
		return null;
	}

	public List<UsrRole> selectByUserId(String userId) {
		return userRoleDao.selectByUserId(userId);
	}

}
