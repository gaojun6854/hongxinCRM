package com.hongxin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.AutoRepayDao;
import com.hongxin.dao.PactInfoDao;
import com.hongxin.dao.PactInformDao;
import com.hongxin.dao.ProductDao;
import com.hongxin.dao.RebuyPactDao;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInform;
import com.hongxin.service.AutoRepayService;
import com.hongxin.service.PactInformService;
@Service("pactInformService")
public class PactInformServiceImpl implements PactInformService
{
	@Autowired
	private PactInformDao pactInformDao;
	@Autowired
	private PactInfoDao pactInfoDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private AutoRepayDao autoRepayDao;
	@Autowired
	private RebuyPactDao rebuyPactDao;
	public TPactInform load(String id) {
		return pactInformDao.load(id);
	}

	public TPactInform get(String id) {
		return pactInformDao.get(id);
	}

	public List<TPactInform> findAll() {
		return pactInformDao.findAll();
	}

	public void persist(TPactInform entity) {
		pactInformDao.persist(entity);
	}

	public String save(TPactInform entity) {
		return pactInformDao.save(entity);
	}

	public void saveOrUpdate(TPactInform entity) {
		pactInformDao.saveOrUpdate(entity);
	}

	public void delete(String id) {
		pactInformDao.delete(id);
	}

	public void flush() {
		pactInformDao.flush();
	}

	/**
	 * 还款通知分页
	 */
	public PageBean<TPactInform> getPageBean(int pageSize, int page, Map<String, Object> map) {
		String hql = "select pactInform.* from t_pact_inform pactInform where pactInform.state='01'";	
		PageBean<TPactInform> pageBean = new PageBean<TPactInform>();
		
		int allRows = pactInformDao.findAllPaymentListSize(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TPactInform> list = pactInformDao.queryByPage(hql, offset, pageSize);
		for (TPactInform pactInform : list) {
			pactInform.setPactInfo(pactInfoDao.get(pactInform.getPactId()));
			pactInform.setProduct(productDao.get(pactInform.getProductId()));
			pactInform.setAutoRepay(autoRepayDao.get(pactInform.getPactId()));
			pactInform.getPactInfo().setRebuypactInfo(rebuyPactDao.get(pactInform.getPactId()));
		}
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	/**
	 * 查询还款客户账信息分页
	 */
	public PageBean<TBackAcct> getPaymentToCustomRecord(int pageSize, int page, Map<String, Object> map) {
		String hql = "select backAccount.* from t_back_acct backAccount where backAccount.state='00'";	
		PageBean<TBackAcct> pageBean = new PageBean<TBackAcct>();
		
		int allRows = pactInformDao.getPaymentToCustomRecordSize(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TBackAcct> list = pactInformDao.getPaymentToCustomRecord(hql, offset, pageSize);
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	/**
	 * 查找客户账信息
	 */
	public TBackAcct getBackAcct(String id) {
		return pactInformDao.getBackAcct(id);
	}

	/**
	 * 更新还款客户账信息
	 */
	public void saveOrUpdate4BackAccount(TBackAcct backAcct) {
		pactInformDao.saveOrUpdate4BackAccount(backAcct);
	}

	

}
