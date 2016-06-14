package com.hongxin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.AutoRepayDao;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInfo;
import com.hongxin.service.AutoRepayService;

@Service("autoRepayService")
public class AutoRepayServiceImpl implements AutoRepayService {

	@Autowired
	private AutoRepayDao autoRepayDao;
	
	public TAutoRepay get(String id) {
		return autoRepayDao.get(id);
	}

	public List<TAutoRepay> findAll() {
		return autoRepayDao.findAll();
	}

	public void persist(TAutoRepay entity) {
		autoRepayDao.persist(entity);
	}

	public String save(TAutoRepay entity) {
		return autoRepayDao.save(entity);
	}

	public void saveOrUpdate(TAutoRepay entity) {
		autoRepayDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		autoRepayDao.delete(id);
	}

	public void flush() {
		autoRepayDao.flush();
	}

	public TAutoRepay load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(TAutoRepay TAutoRepay) {
		return autoRepayDao.save4StrId(TAutoRepay);//保存TAutoRepay
	}
	/**
	 * 通过主键查询所有的改信息
	 * 结果为List
	 */
	public List<TAutoRepay> getByStrId(String Id) {
		return autoRepayDao.getByStrId(Id);//保存TAutoRepay
	}

	public List<TAutoRepay> getByStrIdType(String id, String no) {
		return autoRepayDao.getByStrIdType(id,no);//保存TAutoRepay
	}

	public List<TAutoRepay> findAllRepayment() {
		return autoRepayDao.findAllRepayment();
	}

	public PageBean<TAutoRepay> findReCheckPact(int pageSize, int page,Map<String, Object> map) {
		String cust_Name=(String) map.get("custName");
		String pact_Due=(String) map.get("pactDue");
		String hql = "select auto.* from t_pact_info pact,t_auto_repay auto where pact.pact_id=auto.pact_id and pact.pact_flow in (7,8) and auto.buss_start='5'";	
		if (!"".equals(cust_Name)&&cust_Name!=null)
			hql+=" AND pact.cust_name='"+cust_Name+"'";
		if (!"".equals(pact_Due)&&pact_Due!=null)
			hql+=" AND pact.pact_due='"+pact_Due+"'";
		///////////////////////////////////////////////////////
		PageBean<TAutoRepay> pageBean = new PageBean<TAutoRepay>();
		
		int allRows = autoRepayDao.getfindReCheckPactAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TAutoRepay> list = autoRepayDao.queryByPage(hql, offset, pageSize);
		
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	public PageBean<TAutoRepay> findFailAutoRepay(int pageSize, int page,Map<String, Object> map) {
		String custName= (String) map.get("custName");
		String pactDue=(String) map.get("pactDue");
		String hql = "select auto.* from t_pact_info pact,t_auto_repay auto where pact.pact_id=auto.pact_id and pact.pact_flow in (6,8) and auto.buss_start in (2,4,6)";
		if (!"".equals(custName)&&custName!=null)
			hql+=" AND pact.cust_name='"+custName+"'";
		if (!"".equals(pactDue)&&pactDue!=null)
			hql+=" AND pact.pact_due='"+pactDue+"'";
		///////////////////////////////////////////////////////
		PageBean<TAutoRepay> pageBean = new PageBean<TAutoRepay>();
		
		int allRows = autoRepayDao.getfindReCheckPactAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TAutoRepay> list = autoRepayDao.queryByPage(hql, offset, pageSize);
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	public PageBean<TAutoRepay> findFirstCheck(int pageSize, int page,Map<String, String> map) {
		String custName= map.get("custName");
		String pactDue=map.get("pactDue");
		
		String hql = "select auto.* from t_pact_info pact,t_auto_repay auto where pact.pact_id=auto.pact_id and pact.pact_flow in(5,6) and auto.buss_start='0'";	
		
		if (!"".equals(custName)&&custName!=null)
			hql+=" AND pact.cust_name='"+custName+"'";
		if (!"".equals(pactDue)&&pactDue!=null)
			hql+=" AND pact.pact_due='"+pactDue+"'";
		
		///////////////////////////////////////////////////////
		PageBean<TAutoRepay> pageBean = new PageBean<TAutoRepay>();
		
		int allRows = autoRepayDao.getfindReCheckPactAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TAutoRepay> list = autoRepayDao.queryByPage(hql, offset, pageSize);
		
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	public void saveBackAccount(TBackAcct backAccount) {
		autoRepayDao.saveBackAccount(backAccount);
	}

	public TBackAcct getBackAccount(String id) {
		return autoRepayDao.getBackAccount(id);
	}

	public void saveOrUpdateForBackAccount(TBackAcct backAcct) {
		 autoRepayDao.saveOrUpdateForBackAccount(backAcct);
		
	}

}
