package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.BankCodeDao;
import com.hongxin.entity.TBankCode;
import com.hongxin.service.BankCodeService;

@Service("bankCodeService")
public class BankCodeServiceImpl implements BankCodeService {

	@Autowired
	private BankCodeDao bankCodeDao;
	
	public TBankCode get(String id) {
		return bankCodeDao.get(id);
	}

	public List<TBankCode> findAll() {
		return bankCodeDao.findAll();
	}

	public void persist(TBankCode entity) {
		bankCodeDao.persist(entity);
	}

	public String save(TBankCode entity) {
		return bankCodeDao.save(entity);
	}

	public void saveOrUpdate(TBankCode entity) {
		bankCodeDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		bankCodeDao.delete(id);
	}

	public void flush() {
		bankCodeDao.flush();
	}

	public TBankCode load(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
