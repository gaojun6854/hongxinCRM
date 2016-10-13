package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.RoleSourceDao;
import com.hongxin.entity.RoleSource;
import com.hongxin.utils.QueryResult;

@Repository("roleSourceDao")
public class RoleSourceDaoImpl implements RoleSourceDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public RoleSource load(String id) {
		return (RoleSource) this.getCurrentSession().load(RoleSource.class, id);
	}

	public RoleSource get(String id) {
		return (RoleSource) this.getCurrentSession().get(RoleSource.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<RoleSource> findAll() {
		List<RoleSource> RoleSource = this.getCurrentSession().createQuery("from RoleSource").list();
		return RoleSource;
	}

	public void persist(RoleSource entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(RoleSource entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(RoleSource entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		RoleSource entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(RoleSource entity) {
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

	public void deleteByRoleId(String roleId) {
		this.getCurrentSession().createSQLQuery("delete from fl_role_source where role_id='"+roleId+"'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<RoleSource> getMyResourceByroleId(String roleId) {
		return this.getCurrentSession().createQuery("from RoleSource where roleId='"+roleId+"'").list();
	}

}
