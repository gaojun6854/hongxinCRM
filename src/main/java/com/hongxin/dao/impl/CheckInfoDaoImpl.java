package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.CheckInfoDao;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.utils.QueryResult;

@Repository("checkInfoDao")
public class CheckInfoDaoImpl implements CheckInfoDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public CheckInfo load(Integer id) {
		return (CheckInfo) this.getCurrentSession().load(CheckInfo.class, id);
	}

	public CheckInfo get(Integer id) {
		return (CheckInfo) this.getCurrentSession().get(CheckInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CheckInfo> findAll() {
		List<CheckInfo> CheckInfos = this.getCurrentSession().createQuery("from CheckInfo").list();
		return CheckInfos;
	}

	public void persist(CheckInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(CheckInfo entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(CheckInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		CheckInfo entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(CheckInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	//通过客户编号查询checkInfo信息
	public List<CheckInfo> getByCustomId(String id) {
		@SuppressWarnings("unchecked")
		List<CheckInfo> CheckInfos = this.getCurrentSession().createQuery("from CheckInfo where othId='"+id+"' ").list();
		return CheckInfos;
	}

	public int auditYN(CheckInfo checkInfo,CustomStatus customStatus) {
		this.getCurrentSession().saveOrUpdate(checkInfo);
		this.getCurrentSession().saveOrUpdate(customStatus);
		return 1;
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
