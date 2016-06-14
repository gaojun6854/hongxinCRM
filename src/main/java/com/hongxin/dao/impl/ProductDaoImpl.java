package com.hongxin.dao.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.ProductDao;
import com.hongxin.entity.TProductInfo;
import com.hongxin.utils.Date2String8;
import com.hongxin.utils.QueryResult;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TProductInfo load(String id) {
		return (TProductInfo) this.getCurrentSession().load(TProductInfo.class, id);
	}

	public TProductInfo get(Integer id) {
		return (TProductInfo) this.getCurrentSession().get(TProductInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TProductInfo> findAll() {
		String hql="select * from t_product_info where '"+Date2String8.date2String(new Date())+"' >= take_eff and '"+Date2String8.date2String(new Date())+"' <= lost_eff";
		List<TProductInfo> TProductInfo = this.getCurrentSession().createSQLQuery(hql).addEntity(TProductInfo.class).list();
		return TProductInfo;
	}

	public void persist(TProductInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TProductInfo entity) {
			return (String) this.getCurrentSession().save(entity);
	}

	public int saveOrUpdateByEntity(TProductInfo entity) {
		int a=1;
		try {
			this.getCurrentSession().saveOrUpdate(entity);
		} catch (RuntimeException e) {
			a=0;
		}
		return a;
	}

	public int deleteById(String id) {
		int a=1;
		try {
			TProductInfo entity = this.load(id);
			this.getCurrentSession().delete(entity);
		} catch (RuntimeException e) {
			a=0;
		}
		return a;
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TProductInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public TProductInfo getStrId(String id) {
		return  (TProductInfo) this.getCurrentSession().createQuery("from TProductInfo where productId='"+id+"'").list().get(0);
	}

	public TProductInfo get(String id) {
		return (TProductInfo) this.getCurrentSession().get(TProductInfo.class, id);
	}

	public void saveOrUpdate(TProductInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		this.getCurrentSession().delete(id);
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
