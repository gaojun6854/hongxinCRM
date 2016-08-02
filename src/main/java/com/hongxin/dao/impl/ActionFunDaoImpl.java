package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.ActionFunDao;
import com.hongxin.entity.ActionFun;
import com.hongxin.utils.QueryResult;

@Repository("actionFunDao")
public class ActionFunDaoImpl implements ActionFunDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public ActionFun load(String id) {
		return (ActionFun) this.getCurrentSession().load(ActionFun.class, id);
	}

	public ActionFun get(String id) {
		return (ActionFun) this.getCurrentSession().get(ActionFun.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ActionFun> findAll() {
		List<ActionFun> ActionFun = this.getCurrentSession().createQuery("from ActionFun").list();
		return ActionFun;
	}

	public void persist(ActionFun entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(ActionFun entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(ActionFun entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		ActionFun entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(ActionFun entity) {
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

	/**
	 * 获取菜单的功能点列表信息
	 */
	@SuppressWarnings("unchecked")
	public List<ActionFun> getBySourceId(String sourceId) {
		return this.getCurrentSession().createQuery("from ActionFun where sourceId = '"+sourceId+"' ").list();
	}

}
