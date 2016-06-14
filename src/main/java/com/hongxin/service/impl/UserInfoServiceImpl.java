package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.UserInfoDao;
import com.hongxin.entity.User;
import com.hongxin.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	public User load(String id) {
		return null;
	}

	public User get(String id) {
		return userInfoDao.get(id);
	}

	public List<User> findAll() {
		return userInfoDao.findAll();
	}

	public void persist(User entity) {
		userInfoDao.persist(entity);
	}

	public String save(User entity) {
		return userInfoDao.save(entity);
	}

	public void saveOrUpdate(User entity) {
		userInfoDao.save(entity);
		
	}

	public void delete(String id) {
		userInfoDao.delete(id);
		
	}

	public void flush() {
		userInfoDao.flush();
	}

}
