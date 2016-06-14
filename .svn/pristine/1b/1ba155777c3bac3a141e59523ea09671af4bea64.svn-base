package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.FundInfoDao;
import com.hongxin.entity.TFundInfo;
import com.hongxin.service.FundInfoService;

@Service("fundInfoService")
public class FundInfoServiceImpl implements FundInfoService
{
	@Autowired
	private FundInfoDao fundInfoDao;
	
	public TFundInfo get(String id) {
		return fundInfoDao.get(id);
	}

	public List<TFundInfo> findAll() {
		return fundInfoDao.findAll();
	}

	public void persist(TFundInfo entity) {
		fundInfoDao.persist(entity);
	}

	public String save(TFundInfo entity) {
		return fundInfoDao.save(entity);
	}

	public void saveOrUpdate(TFundInfo entity) {
		fundInfoDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		fundInfoDao.delete(id);
	}

	public void flush() {
		fundInfoDao.flush();
	}

	public TFundInfo load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TFundInfo> findByStartEnd(String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
