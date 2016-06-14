package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.PactInfoDao;
import com.hongxin.dao.RebuyPactDao;
import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TRebuypactInfo;
import com.hongxin.service.ReBuyPactService;

@Service("reBuyPactService")
public class ReBuyPactServiceImpl implements ReBuyPactService
{
	@Autowired
	private RebuyPactDao rebuyPactDao;
	@Autowired
	private PactInfoDao pactInfoDao;

	public TRebuypactInfo load(String id) {
		return rebuyPactDao.load(id);
	}

	public TRebuypactInfo get(String id) {
		return rebuyPactDao.get(id);
	}

	public List<TRebuypactInfo> findAll() {
		return rebuyPactDao.findAll();
	}

	public void persist(TRebuypactInfo entity) {
		rebuyPactDao.persist(entity);
	}

	public String save(TRebuypactInfo entity) {
		return rebuyPactDao.save(entity);
	}

	public void saveOrUpdate(TRebuypactInfo entity) {
		rebuyPactDao.saveOrUpdate(entity);
	}

	public void delete(String id) {
		rebuyPactDao.delete(id);
	}

	public void flush() {
		rebuyPactDao.flush();
	}

	/**
	 * 回购表
	 * 更新原合同信息
	 */
	public void saveNewOld(TRebuypactInfo rebuyPact, TPactInfo oldPact) {
		rebuyPactDao.save(rebuyPact);
		pactInfoDao.saveOrUpdate(oldPact);
	}
}
