package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.EmployStaffDao;
import com.hongxin.entity.EmployStaff;
import com.hongxin.utils.QueryResult;

@Repository("employStaffDao")
public class EmployStaffDaoImpl implements EmployStaffDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public EmployStaff load(String id) {
		return (EmployStaff) this.getCurrentSession().load(EmployStaff.class, id);
	}

	public EmployStaff get(String id) {
		return (EmployStaff) this.getCurrentSession().get(EmployStaff.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<EmployStaff> findAll() {
		List<EmployStaff> EmployStaff = this.getCurrentSession().createQuery("from EmployStaff").list();
		return EmployStaff;
	}

	public void persist(EmployStaff entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(EmployStaff entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(EmployStaff entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		EmployStaff entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(EmployStaff entity) {
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

	@SuppressWarnings("unchecked")
	public List<EmployStaff> getByOrgId(String orgId) {
		List<EmployStaff> EmployStaffs = this.getCurrentSession().createSQLQuery("select * from employ_staff where org_id='"+orgId+"'").addEntity(EmployStaff.class).list();
		return EmployStaffs;
	}

}
