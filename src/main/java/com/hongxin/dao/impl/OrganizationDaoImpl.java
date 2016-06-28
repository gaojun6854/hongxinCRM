package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongxin.dao.OrganizationDao;
import com.hongxin.entity.Organization;
import com.hongxin.utils.QueryResult;

@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Organization load(String id) {
		return (Organization) this.getCurrentSession().load(Organization.class, id);
	}

	public Organization get(String id) {
		return (Organization) this.getCurrentSession().get(Organization.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Organization> findAll() {
		List<Organization> Organization = this.getCurrentSession().createQuery("from Organization").list();
		return Organization;
	}

	public void persist(Organization entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(Organization entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Organization entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		Organization entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(Organization entity) {
		return (String) this.getCurrentSession().save(entity);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

	/*
	 * 通过父id查询下层组织
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> findById(String orgId) {
		return this.getCurrentSession().createQuery("from Organization").list();
	}

}
