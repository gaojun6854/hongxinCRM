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
		String pactNum=(String) map.get("pactNum");
		String custName=(String) map.get("custName");
		String phoneNum=(String) map.get("phoneNum");
		String paperNum=(String) map.get("paperNum");
		
		String hql = "select pactform.* from t_pact_inform pactform,t_pact_info pact where pactform.pact_id=pact.pact_id and pactform.state='01'";	
		if ("".equals(phoneNum)&&"".equals(paperNum)&&"".equals(custName)&&"".equals(pactNum)) {
			hql = "select pactform.* from t_pact_inform pactform,t_pact_info pact where pactform.pact_id=pact.pact_id and pactform.state='01'";	
		}else{
			if (!"".equals(phoneNum))
				hql=hql+" and pact.phone_num='"+phoneNum+"'";
			if (!"".equals(paperNum)) 
				hql=hql+" and pact.paper_num='"+paperNum+"'";
			if (!"".equals(custName))
				hql=hql+" and pact.cust_name='"+custName+"'";
			if (!"".equals(pactNum))
				hql=hql+" and pact.contract_number='"+pactNum+"'";
		}
		
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
		
		String pactNum=(String) map.get("pactNum");
		String custName=(String) map.get("custName");
		String phoneNum=(String) map.get("phoneNum");
		String paperNum=(String) map.get("paperNum");

		String hql = "select back.* from t_back_acct back,t_pact_info pact where back.pact_id=pact.pact_id and  back.state='00'";	
		if ("".equals(phoneNum)&&"".equals(paperNum)&&"".equals(custName)&&"".equals(pactNum)) {
			hql = "select back.* from t_back_acct back,t_pact_info pact where back.pact_id=pact.pact_id and  back.state='00'";	
		}else{
			if (!"".equals(phoneNum))
				hql=hql+" and pact.phone_num='"+phoneNum+"'";
			if (!"".equals(paperNum)) 
				hql=hql+" and pact.paper_num='"+paperNum+"'";
			if (!"".equals(custName))
				hql=hql+" and pact.cust_name='"+custName+"'";
			if (!"".equals(pactNum))
				hql=hql+" and pact.contract_number='"+pactNum+"'";
		}
		
		PageBean<TBackAcct> pageBean = new PageBean<TBackAcct>();
		
		int allRows = pactInformDao.getPaymentToCustomRecordSize(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TBackAcct> list = pactInformDao.getPaymentToCustomRecord(hql, offset, pageSize);
		for (TBackAcct backAccount : list) {
			backAccount.setPactInfo(pactInfoDao.get(backAccount.getPactId()));
		}
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
