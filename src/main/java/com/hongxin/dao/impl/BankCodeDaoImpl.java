package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.BankCodeDao;
import com.hongxin.entity.TBankCode;
import com.hongxin.utils.QueryResult;

@Repository("bankCodeDao")
public class BankCodeDaoImpl implements BankCodeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TBankCode load(String id) {
		return (TBankCode) this.getCurrentSession().load(TBankCode.class, id);
	}

	public TBankCode get(String id) {
		return (TBankCode) this.getCurrentSession().get(TBankCode.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TBankCode> findAll() {
		List<TBankCode> TBankCode = this.getCurrentSession().createQuery("from TBankCode").list();
		return TBankCode;
	}

	public void persist(TBankCode entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(TBankCode entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TBankCode entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TBankCode entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TBankCode entity) {
		return (String) this.getCurrentSession().save(entity);
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
}
