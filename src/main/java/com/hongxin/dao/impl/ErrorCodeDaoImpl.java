package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.ErrorCodeDao;
import com.hongxin.entity.TErrCode;
import com.hongxin.utils.QueryResult;

@Repository("errorCodeDao")
public class ErrorCodeDaoImpl implements ErrorCodeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TErrCode load(String id) {
		return (TErrCode) this.getCurrentSession().load(TErrCode.class, id);
	}

	public TErrCode get(String id) {
		return (TErrCode) this.getCurrentSession().get(TErrCode.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TErrCode> findAll() {
		List<TErrCode> TErrCode = this.getCurrentSession().createQuery("from TErrCode").list();
		return TErrCode;
	}

	public void persist(TErrCode entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(TErrCode entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TErrCode entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TErrCode entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TErrCode entity) {
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
