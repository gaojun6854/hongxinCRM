package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.logDao;
import com.hongxin.entity.OperationDiary;
import com.hongxin.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService
{	
	@Autowired
	private logDao logDao;
	
	public Integer save(OperationDiary entity) {
		return logDao.save(entity);
	}
	
	public OperationDiary load(Integer id) {
		return null;
	}

	public OperationDiary get(Integer id) {
		return logDao.get(id);
	}

	public List<OperationDiary> findAll() {
		return logDao.findAll();
	}

	public void persist(OperationDiary entity) {
		logDao.persist(entity);
	}

	public void saveOrUpdate(OperationDiary entity) {
		logDao.save(entity);
		
	}

	public void delete(Integer id) {
		logDao.delete(id);
		
	}

	public void flush() {
		logDao.flush();
	}

}
