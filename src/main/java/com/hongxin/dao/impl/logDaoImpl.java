package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.logDao;
import com.hongxin.entity.OperationDiary;
import com.hongxin.utils.QueryResult;

@Repository("logDao")
public class logDaoImpl implements logDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public OperationDiary load(Integer id) {
		return (OperationDiary) this.getCurrentSession().load(OperationDiary.class, id);
	}

	public OperationDiary get(Integer id) {
		return (OperationDiary) this.getCurrentSession().get(OperationDiary.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<OperationDiary> findAll() {
		List<OperationDiary> OperationDiarys = this.getCurrentSession().createQuery("from OperationDiary").list();
		return OperationDiarys;
	}

	public void persist(OperationDiary entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(OperationDiary entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(OperationDiary entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		OperationDiary entity = this.load(id);
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
