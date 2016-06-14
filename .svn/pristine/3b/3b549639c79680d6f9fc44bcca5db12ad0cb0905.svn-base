package com.hongxin.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.CustomStatusDao;
import com.hongxin.entity.CustomStatus;
import com.hongxin.service.CustomStatusService;

@Service("customStatusService")
public class CustomStatusServiceImpl implements CustomStatusService {

	@Autowired
	private CustomStatusDao customStatusDao;
	
	public CustomStatus get(Integer id) {
		return customStatusDao.get(id);
	}

	public List<CustomStatus> findAll() {
		List<CustomStatus> customBaseInfos=customStatusDao.findAll();
		return customBaseInfos;
	}

	public void persist(CustomStatus entity) {
		customStatusDao.persist(entity);
	}

	public Integer save(CustomStatus entity) {
		return customStatusDao.save(entity);
	}

	public void saveOrUpdate(CustomStatus entity) {
		customStatusDao.save(entity);
		
	}

	public void delete(Integer id) {
		customStatusDao.delete(id);
	}

	public void flush() {
		customStatusDao.flush();
	}

	public CustomStatus load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(CustomStatus customStatus) {
		customStatus.setCustomId(UUID.randomUUID().toString());//主键默认为uuid方式生成
		return customStatusDao.save4StrId(customStatus);//保存customBaseInfo
	}

	public CustomStatus getByStrId(String id) {
		return customStatusDao.getByStrId(id);
	}

}
