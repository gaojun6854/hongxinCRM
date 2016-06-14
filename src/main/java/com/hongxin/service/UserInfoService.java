package com.hongxin.service;

import java.util.List;

import com.hongxin.entity.User;
public interface UserInfoService {
	User load(String id);

	User get(String id);

	List<User> findAll();

	void persist(User entity);

	String save(User entity);

	void saveOrUpdate(User entity);

	void delete(String id);

	void flush();
}
