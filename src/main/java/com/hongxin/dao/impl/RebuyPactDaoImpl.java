package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.RebuyPactDao;
import com.hongxin.entity.TRebuypactInfo;
import com.hongxin.utils.QueryResult;

@Repository("rebuyPactDao")
public class RebuyPactDaoImpl implements RebuyPactDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TRebuypactInfo load(String id) {
		return (TRebuypactInfo) this.getCurrentSession().load(TRebuypactInfo.class, id);
	}

	public TRebuypactInfo get(String id) {
		return (TRebuypactInfo) this.getCurrentSession().get(TRebuypactInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TRebuypactInfo> findAll() {
		List<TRebuypactInfo> TRebuypactInfo = this.getCurrentSession().createQuery("from TRebuypactInfo").list();
		return TRebuypactInfo;
	}

	public void persist(TRebuypactInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TRebuypactInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TRebuypactInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TRebuypactInfo entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
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
