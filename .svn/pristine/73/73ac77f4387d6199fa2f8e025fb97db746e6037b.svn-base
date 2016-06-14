package com.hongxin.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.CustomAccountDao;
import com.hongxin.entity.CustomAccount;
import com.hongxin.service.CustomAccountService;

@Service("customAccountService")
public class CustomAccountServiceImpl implements CustomAccountService {

	@Autowired
	private CustomAccountDao customAccountDao;
	
	public CustomAccount get(Integer id) {
		return customAccountDao.get(id);
	}

	public List<CustomAccount> findAll() {
		List<CustomAccount> customBaseInfos=customAccountDao.findAll();
		return customBaseInfos;
	}

	public void persist(CustomAccount entity) {
		customAccountDao.persist(entity);
	}

	public Integer save(CustomAccount entity) {
		return customAccountDao.save(entity);
	}

	public void saveOrUpdate(CustomAccount entity) {
		customAccountDao.save(entity);
		
	}

	public void delete(Integer id) {
		customAccountDao.delete(id);
	}

	public void flush() {
		customAccountDao.flush();
	}

	public CustomAccount load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(CustomAccount customAccount) {
		customAccount.setCustomId(UUID.randomUUID().toString());//主键默认为uuid方式生成
		
		return customAccountDao.save4StrId(customAccount);//保存customBaseInfo
	}

	public CustomAccount getStrId(String id) {
		return customAccountDao.getStrId(id);
	}

}
