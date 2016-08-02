package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.UserRoleDao;
import com.hongxin.entity.UsrRole;
import com.hongxin.utils.QueryResult;

@Repository("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public UsrRole load(String id) {
		return (UsrRole) this.getCurrentSession().load(UsrRole.class, id);
	}

	public UsrRole get(String id) {
		return (UsrRole) this.getCurrentSession().get(UsrRole.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<UsrRole> findAll() {
		List<UsrRole> UsrRole = this.getCurrentSession().createQuery("from UsrRole").list();
		return UsrRole;
	}

	public void persist(UsrRole entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(UsrRole entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(UsrRole entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		UsrRole entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(UsrRole entity) {
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

	@SuppressWarnings("unchecked")
	public List<UsrRole> selectByUserId(String userId) {
		return  this.getCurrentSession().createQuery("from UsrRole where userId='"+userId+"'").list();
	}

}
