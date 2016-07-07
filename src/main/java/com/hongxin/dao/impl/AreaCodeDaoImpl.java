package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.AreaCodeDao;
import com.hongxin.entity.TAreaCode;
import com.hongxin.utils.QueryResult;

@Repository("areaCodeDao")
public class AreaCodeDaoImpl implements AreaCodeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TAreaCode load(String id) {
		return (TAreaCode) this.getCurrentSession().load(TAreaCode.class, id);
	}

	public TAreaCode get(String id) {
		return (TAreaCode) this.getCurrentSession().get(TAreaCode.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TAreaCode> findAll() {
		List<TAreaCode> TAreaCode = this.getCurrentSession().createQuery("from TAreaCode").list();
		return TAreaCode;
	}

	public void persist(TAreaCode entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(TAreaCode entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TAreaCode entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TAreaCode entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TAreaCode entity) {
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
	 * 查询省份信息
	 * 列表信息或者单个身份信息
	 */
	@SuppressWarnings("unchecked")
	public List<TAreaCode> getProvinceList(String id) {
		if (id==null)
			return this.getCurrentSession().createSQLQuery("select area.* from t_area_code area where area.province_code is null").addEntity(TAreaCode.class).list();
		else
			return this.getCurrentSession().createSQLQuery("select area.* from t_area_code area where area.province_code='"+id+"'").addEntity(TAreaCode.class).list();
	}
}
