package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.CustomStatusDao;
import com.hongxin.entity.CustomStatus;
import com.hongxin.utils.QueryResult;

@Repository("customStatusDao")
public class CustomStatusDaoImpl implements CustomStatusDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public CustomStatus load(Integer id) {
		return (CustomStatus) this.getCurrentSession().load(CustomStatus.class, id);
	}

	public CustomStatus get(Integer id) {
		return (CustomStatus) this.getCurrentSession().get(CustomStatus.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CustomStatus> findAll() {
		List<CustomStatus> CustomStatus = this.getCurrentSession().createQuery("from CustomStatus").list();
		return CustomStatus;
	}

	public void persist(CustomStatus entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(CustomStatus entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(CustomStatus entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		CustomStatus entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(CustomStatus entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public CustomStatus getByStrId(String id) {
		return (CustomStatus) this.getCurrentSession().createQuery("from CustomStatus where customId='"+id+"'").list().get(0);
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
