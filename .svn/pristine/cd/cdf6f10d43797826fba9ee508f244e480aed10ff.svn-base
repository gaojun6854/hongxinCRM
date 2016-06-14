package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.ZhishuDao;
import com.hongxin.entity.TIndexInfo;
import com.hongxin.utils.QueryResult;

@Repository("zhishuDao")
public class ZhishuDaoImpl implements ZhishuDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<TIndexInfo> findAll() {
		List<TIndexInfo> entity = this.getCurrentSession().createQuery("from TIndexInfo").list();
		return entity;
	}

	public void persist(TIndexInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TIndexInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TIndexInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}


	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TIndexInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public TIndexInfo getStrId(String id) {
		return  (TIndexInfo) this.getCurrentSession().createQuery("from TIndexInfo where workDate='"+id+"'").list().get(0);
	}

	public TIndexInfo load(String id) {
		return (TIndexInfo) this.getCurrentSession().load(TIndexInfo.class, id);
	}

	public TIndexInfo get(String id) {
		return (TIndexInfo) this.getCurrentSession().get(TIndexInfo.class, id);
	}

	public void delete(String id) {
		this.getCurrentSession().delete(id);
	}

	@SuppressWarnings("unchecked")
	public List<TIndexInfo> findByStartEnd(String start, String end) {
		List<TIndexInfo> entity = this.getCurrentSession().createQuery("from TIndexInfo where workDate >= '"+start+"' and workDate<='"+end+"'").list();
		return entity;
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
