package com.hongxin.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.AutoRepayDao;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TBackAcct;
import com.hongxin.utils.QueryResult;

@Repository("autoRepayDao")
public class AutoRepayDaoImpl implements AutoRepayDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TAutoRepay load(String id) {
		return (TAutoRepay) this.getCurrentSession().load(TAutoRepay.class, id);
	}

	public TAutoRepay get(String id) {
		return (TAutoRepay) this.getCurrentSession().get(TAutoRepay.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TAutoRepay> findAll() {
		List<TAutoRepay> TAutoRepay = this.getCurrentSession().createQuery("from TAutoRepay").list();
		return TAutoRepay;
	}

	public void persist(TAutoRepay entity) {
		this.getCurrentSession().persist(entity);

	}

	public String save(TAutoRepay entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(TAutoRepay entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		TAutoRepay entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TAutoRepay entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	/**
	 * 通过主键查询该信息 return list
	 */
	@SuppressWarnings("unchecked")
	public List<TAutoRepay> getByStrId(String id) {
		List<TAutoRepay> TAutoRepay = this.getCurrentSession()
				.createQuery("from TAutoRepay where recNum = '" + id + "' ").list();
		return TAutoRepay;
	}

	public List<TAutoRepay> getByStrIdType(String id, String no) {
		@SuppressWarnings("unchecked")
		List<TAutoRepay> TAutoRepay = this.getCurrentSession()
				.createQuery(" from TAutoRepay where recNum = '" + id + "' and recType='" + no + "' ").list();
		return TAutoRepay;
	}

	@SuppressWarnings("unchecked")
	public List<TAutoRepay> findAllRepayment() {
		return this.getCurrentSession().createSQLQuery("select auto.* from t_pact_info pact,t_auto_repay auto where pact.pact_id=auto.pact_id and pact.pact_flow in(5,6) and auto.buss_start in(0,1,2,3,4)").addEntity(TAutoRepay.class).list();
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
	 * 还款复审中分页总条数
	 */
	public int getfindReCheckPactAllRowCount(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	public List<TAutoRepay> queryByPage(String hql, int offset, int pageSize) {
		return  this.getCurrentSession().createSQLQuery(hql).addEntity(TAutoRepay.class).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	public void saveBackAccount(TBackAcct backAccount) {
		this.getCurrentSession().save(backAccount);
	}

	public TBackAcct getBackAccount(String id) {
		return (TBackAcct) this.getCurrentSession().createSQLQuery("select backaccount.* from t_back_acct backaccount where backaccount.pact_id='"+id+"' ").addEntity(TBackAcct.class).list().get(0);
	}

	public void saveOrUpdateForBackAccount(TBackAcct backAcct) {
		this.getCurrentSession().saveOrUpdate(backAcct);
	}

}
