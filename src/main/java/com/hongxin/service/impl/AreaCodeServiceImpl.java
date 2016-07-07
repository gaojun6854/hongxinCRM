package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.AreaCodeDao;
import com.hongxin.entity.TAreaCode;
import com.hongxin.service.AreaCodeService;

@Service("areaCodeService")
public class AreaCodeServiceImpl implements AreaCodeService {

	@Autowired
	private AreaCodeDao areaCodeDao;
	
	public TAreaCode get(String id) {
		return areaCodeDao.get(id);
	}

	public List<TAreaCode> findAll() {
		return areaCodeDao.findAll();
	}

	public void persist(TAreaCode entity) {
		areaCodeDao.persist(entity);
	}

	public String save(TAreaCode entity) {
		return areaCodeDao.save(entity);
	}

	public void saveOrUpdate(TAreaCode entity) {
		areaCodeDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		areaCodeDao.delete(id);
	}

	public void flush() {
		areaCodeDao.flush();
	}

	public TAreaCode load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询省份信息
	 */
	public List<TAreaCode> getProvinceList(String id) {
		return areaCodeDao.getProvinceList(id);
	}

}
