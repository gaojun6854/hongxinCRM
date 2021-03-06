package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.ZhishuDao;
import com.hongxin.entity.TIndexInfo;
import com.hongxin.service.ZhishuService;

@Service("zhishuService")
public class ZhishuServiceImpl implements  ZhishuService{

	@Autowired
	private ZhishuDao zhishuDao;
	
	public TIndexInfo get(String id) {
		return zhishuDao.get(id);
	}

	public List<TIndexInfo> findAll() {
		List<TIndexInfo> customBaseInfos=zhishuDao.findAll();
		return customBaseInfos;
	}

	public void persist(TIndexInfo entity) {
		zhishuDao.persist(entity);
	}

	public String save(TIndexInfo entity) {
		return zhishuDao.save(entity);
	}

	public void saveOrUpdate(TIndexInfo entity) {
		zhishuDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		zhishuDao.delete(id);
	}

	public void flush() {
		zhishuDao.flush();
	}

	public String save4StrId(TIndexInfo indexInfo) {
		return zhishuDao.save4StrId(indexInfo);//保存customBaseInfo
	}

	public TIndexInfo getStrId(String id) {
		return zhishuDao.getStrId(id);
	}

	public TIndexInfo load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public TIndexInfo getByStrId(String id) {
		return zhishuDao.get(id);
	}

	public List<TIndexInfo> findByStartEnd(String start, String end) {
		return zhishuDao.findByStartEnd(start,end);
	}

}
