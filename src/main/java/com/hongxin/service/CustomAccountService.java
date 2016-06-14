package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.CustomAccount;

public interface CustomAccountService 
{
	CustomAccount load(Integer id);

	CustomAccount get(Integer id);

	List<CustomAccount> findAll();

	void persist(CustomAccount entity);

	Integer save(CustomAccount entity);

	void saveOrUpdate(CustomAccount entity);

	void delete(Integer id);

	void flush();

	String save4StrId(CustomAccount customAccount);

	CustomAccount getStrId(String id);
}
