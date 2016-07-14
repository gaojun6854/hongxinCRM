package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.ErrorCodeDao;
import com.hongxin.entity.TErrCode;
import com.hongxin.service.ErrorCodeService;

@Service("errorCodeService")
public class ErrorCodeServiceImpl implements ErrorCodeService {

	@Autowired
	private ErrorCodeDao errorCodeDao;
	
	public TErrCode get(String id) {
		return errorCodeDao.get(id);
	}

	public List<TErrCode> findAll() {
		return errorCodeDao.findAll();
	}

	public void persist(TErrCode entity) {
		errorCodeDao.persist(entity);
	}

	public String save(TErrCode entity) {
		return errorCodeDao.save(entity);
	}

	public void saveOrUpdate(TErrCode entity) {
		errorCodeDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		errorCodeDao.delete(id);
	}

	public void flush() {
		errorCodeDao.flush();
	}

	public TErrCode load(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
