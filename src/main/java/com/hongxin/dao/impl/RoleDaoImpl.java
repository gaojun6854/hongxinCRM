package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.RoleDao;
import com.hongxin.entity.Role;
import com.hongxin.utils.QueryResult;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Role load(String id) {
		return (Role) this.getCurrentSession().load(Role.class, id);
	}

	public Role get(String id) {
		return (Role) this.getCurrentSession().get(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		List<Role> Role = this.getCurrentSession().createQuery("from Role").list();
		return Role;
	}

	public void persist(Role entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(Role entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Role entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		Role entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(Role entity) {
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
	public List<Role> getByRoleId(String roleId) {
		List<Role> Role = this.getCurrentSession().createQuery("from Role where roleId='"+roleId+"'").list();
		return Role;
	}

	/**
	 * 通过角色名查找是否已经存在
	 */
	@SuppressWarnings("unchecked")
	public int selectByName(String roleName) {
		List<Role> roles = this.getCurrentSession().createQuery("from Role where roleName='"+roleName+"'").list();
		return roles.size();
	}

}
