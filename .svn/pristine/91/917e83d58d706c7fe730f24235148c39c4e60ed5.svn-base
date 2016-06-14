package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.MenuDao;
import com.hongxin.entity.ResourceBak;
import com.hongxin.utils.QueryResult;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public ResourceBak load(Integer id) {
		return (ResourceBak) this.getCurrentSession().load(ResourceBak.class, id);
	}

	public ResourceBak get(Integer id) {
		return (ResourceBak) this.getCurrentSession().get(ResourceBak.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ResourceBak> findAll() {
		List<ResourceBak> resourceBak = this.getCurrentSession().createQuery("from ResourceBak order by seq asc").list();
		return resourceBak;
	}

	public void persist(ResourceBak entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(ResourceBak entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(ResourceBak entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		ResourceBak entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	/**
	 * 查询三级菜单
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceBak> getSubMenuList(String sourceId) {
		return this.getCurrentSession().createQuery("from Resource_Bak where parentSourceId='"+sourceId+"' order by seq asc").list();
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
