package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.RoleActionFunDao;
import com.hongxin.entity.RoleActionFun;
import com.hongxin.utils.QueryResult;

@Repository("roleActionFunDao")
public class RoleActionFunDaoImpl implements RoleActionFunDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public RoleActionFun load(String id) {
		return (RoleActionFun) this.getCurrentSession().load(RoleActionFun.class, id);
	}

	public RoleActionFun get(String id) {
		return (RoleActionFun) this.getCurrentSession().get(RoleActionFun.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<RoleActionFun> findAll() {
		List<RoleActionFun> roleActionFun = this.getCurrentSession().createQuery("from RoleActionFun").list();
		return roleActionFun;
	}

	public void persist(RoleActionFun entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(RoleActionFun entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(RoleActionFun entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		RoleActionFun entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(RoleActionFun entity) {
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
	public List<RoleActionFun> selectByRoleId(String roleId) {
			return this.getCurrentSession().createQuery("from RoleActionFun where roleId = '"+roleId+"' ").list();
	}

	public void deleteByRoleId(String roleId) {
		this.getCurrentSession().createSQLQuery("delete from fl_role_action_fun where role_id='"+roleId+"' ").executeUpdate();
	}
}
