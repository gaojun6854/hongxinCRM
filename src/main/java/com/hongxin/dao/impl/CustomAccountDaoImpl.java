package com.hongxin.dao.impl;


import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.CustomAccountDao;
import com.hongxin.entity.CustomAccount;
import com.hongxin.utils.QueryResult;

@Repository("customAccountDao")
public class CustomAccountDaoImpl implements CustomAccountDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public CustomAccount load(Integer id) {
		return (CustomAccount) this.getCurrentSession().load(CustomAccount.class, id);
	}

	public CustomAccount get(Integer id) {
		return (CustomAccount) this.getCurrentSession().get(CustomAccount.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CustomAccount> findAll() {
		List<CustomAccount> customAccount = this.getCurrentSession().createQuery("from CustomAccount").list();
		return customAccount;
	}

	public void persist(CustomAccount entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(CustomAccount entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(CustomAccount entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		CustomAccount entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(CustomAccount entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public CustomAccount getStrId(String id) {
		return  (CustomAccount) this.getCurrentSession().createQuery("from CustomAccount where customId='"+id+"'").list().get(0);
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
