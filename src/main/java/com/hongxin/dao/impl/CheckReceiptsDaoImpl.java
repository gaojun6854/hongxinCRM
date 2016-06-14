package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.CheckReceiptsDao;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.utils.QueryResult;

@Repository("checkReceiptsDao")
public class CheckReceiptsDaoImpl implements CheckReceiptsDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public CheckReceipts load(String id) {
		return (CheckReceipts) this.getCurrentSession().load(CheckReceipts.class, id);
	}

	public CheckReceipts get(String id) {
		return (CheckReceipts) this.getCurrentSession().get(CheckReceipts.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CheckReceipts> findAll() {
		List<CheckReceipts> checkReceipts = this.getCurrentSession().createQuery("from CheckReceipts").list();
		return checkReceipts;
	}

	public void persist(CheckReceipts entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(CheckReceipts entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(CheckReceipts entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		CheckReceipts entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(CheckReceipts entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	/**
	 * 通过主键查询该信息
	 * return list
	 */
	@SuppressWarnings("unchecked")
	public List<CheckReceipts> getByStrId(String id) {
		List<CheckReceipts> checkReceipts = this.getCurrentSession().createQuery("from CheckReceipts where recNum = '"+id+"' ").list();
		return checkReceipts;
	}

	public List<CheckReceipts> getByStrIdType(String id, String no) {
		@SuppressWarnings("unchecked")
		List<CheckReceipts> checkReceipts = this.getCurrentSession().createQuery(" from CheckReceipts where recId = '"+id+"' ").list();
		return checkReceipts;
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult, String wherejpql,
			Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteByStrId(String recNum) {
		//this.getCurrentSession().createSQLQuery("delete from t_check_receipts where rec_num = '"+recNum+"' ");
		CheckReceipts checkReceipts = (CheckReceipts) this.getCurrentSession().createQuery("from CheckReceipts where recNum = '"+recNum+"' ").list().get(0);
		this.getCurrentSession().delete(checkReceipts);
	}

}
