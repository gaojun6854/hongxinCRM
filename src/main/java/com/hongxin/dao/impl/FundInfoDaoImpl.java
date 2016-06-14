package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hongxin.dao.FundInfoDao;
import com.hongxin.entity.TFundInfo;
import com.hongxin.utils.QueryResult;

@Repository("fundInfoDao")
public class FundInfoDaoImpl implements FundInfoDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TFundInfo load(String id) {
		return (TFundInfo) this.getCurrentSession().load(TFundInfo.class, id);
	}

	public TFundInfo get(String id) {
		return (TFundInfo) this.getCurrentSession().get(TFundInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TFundInfo> findAll() {
		List<TFundInfo> TFundInfo = this.getCurrentSession().createQuery("from TFundInfo").list();
		return TFundInfo;
	}

	public void persist(TFundInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TFundInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TFundInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TFundInfo entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TFundInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	/**
	 * 通过主键查询该信息
	 * return list
	 */
	@SuppressWarnings("unchecked")
	public List<TFundInfo> getByStrId(String id) {
		List<TFundInfo> TFundInfo = this.getCurrentSession().createQuery("from TFundInfo where recNum = '"+id+"' ").list();
		return TFundInfo;
	}

	public List<TFundInfo> getByStrIdType(String id, String no) {
		@SuppressWarnings("unchecked")
		List<TFundInfo> TFundInfo = this.getCurrentSession().createQuery(" from TFundInfo where recNum = '"+id+"' and recType='"+no+"' ").list();
		return TFundInfo;
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
