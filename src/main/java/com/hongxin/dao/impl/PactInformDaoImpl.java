package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.PactInformDao;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInform;
import com.hongxin.utils.QueryResult;

@Repository("pactInformDao")
public class PactInformDaoImpl implements PactInformDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TPactInform load(String id) {
		return (TPactInform) this.getCurrentSession().load(TPactInform.class, id);
	}

	public TPactInform get(String id) {
		return (TPactInform) this.getCurrentSession().get(TPactInform.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TPactInform> findAll() {
		List<TPactInform> TPactInforms = this.getCurrentSession().createQuery("from TPactInform").list();
		return TPactInforms;
	}

	public void persist(TPactInform entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TPactInform entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TPactInform entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TPactInform entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TPactInform entity) {
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
	 * 还款通知
	 * 分页查询条数
	 */	
	public int findAllPaymentListSize(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(TPactInform.class).list().size();
	}
	/**
	 * 还款通知
	 * 分页查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List<TPactInform> queryByPage(String hql, int offset, int pageSize) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(TPactInform.class).list();
	}
	/**
	 * 还款客户账
	 * 分页查询条数
	 */	
	public int getPaymentToCustomRecordSize(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(TBackAcct.class).list().size();
	}
	/**
	 * 还款客户账
	 * 分页查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List<TBackAcct> getPaymentToCustomRecord(String hql, int offset, int pageSize) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(TBackAcct.class).list();
	}
	
	/**
	 * 查询还款到客户账信息
	 */
	public TBackAcct getBackAcct(String id) {
		return (TBackAcct) this.getCurrentSession().createSQLQuery("select backAccount.* from t_back_acct backAccount where backAccount.state='00' and backAccount.pact_id='"+id+"'").addEntity(TBackAcct.class).list().get(0);
	}

	/**
	 * 还款客户账后更新数据
	 */
	public void saveOrUpdate4BackAccount(TBackAcct backAcct) {
		this.getCurrentSession().save(backAcct);
	}

}
