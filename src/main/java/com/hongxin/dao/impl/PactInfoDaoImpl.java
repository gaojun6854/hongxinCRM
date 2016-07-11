package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.PactInfoDao;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.TPactInfo;
import com.hongxin.utils.QueryResult;

@Repository("pactInfoDao")
public class PactInfoDaoImpl implements PactInfoDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public TPactInfo load(String id) {
		return (TPactInfo) this.getCurrentSession().load(TPactInfo.class, id);
	}

	public TPactInfo get(Integer id) {
		return (TPactInfo) this.getCurrentSession().get(TPactInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> findAll() {
		List<TPactInfo> TPactInfo = this.getCurrentSession().createQuery("from TPactInfo").list();
		return TPactInfo;
	}

	public void persist(TPactInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(TPactInfo entity) {
			return (String) this.getCurrentSession().save(entity);
	}

	public int saveOrUpdateByEntity(TPactInfo entity) {
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
			TPactInfo entity = this.load(id);
			this.getCurrentSession().delete(entity);
		} catch (RuntimeException e) {
			a=0;
		}
		return a;
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(TPactInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public TPactInfo getStrId(String id) {
		return  (TPactInfo) this.getCurrentSession().createQuery("from TPactInfo where customId='"+id+"'").list().get(0);
	}

	public TPactInfo get(String id) {
		return (TPactInfo) this.getCurrentSession().get(TPactInfo.class, id);
	}

	public void saveOrUpdate(TPactInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		this.getCurrentSession().delete(id);
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> findAllReviews() {
		return  (List<TPactInfo>) this.getCurrentSession().createQuery("from TPactInfo where pactFlow='1' ").list();
	}

	/**
	 * 线上审核
	 */
	public int onLineReviewsYN(TPactInfo pact) {
		int a=1;
		try {
			this.getCurrentSession().saveOrUpdate(pact);
		} catch (RuntimeException e) {
			a=0;
		}
		return a;
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> findAllPZReviews() {
		return  (List<TPactInfo>) this.getCurrentSession().createQuery("from TPactInfo where pactFlow='7' and checkStart='2' ").list();
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> findAllRepayment() {
		return  (List<TPactInfo>) this.getCurrentSession().createQuery("from TPactInfo where pactFlow='3' and checkStart='2' or pactFlow='6' and checkStart='3' ").list();
	}

	/**
	 * 通过合同号查询
	 * 通过用户手机号查询
	 * 通过用户身份证号查询
	 */
	@SuppressWarnings("unchecked")
	public List<TPactInfo> findByPactNum(String pactNum,CustomBaseInfo cust){
		String sql4cust="from CustomBaseInfo where 1=1";
		if (!cust.getPhonenum().equals("")) {
			sql4cust=sql4cust+"and phonenum='"+cust.getPhonenum()+"'";
		}if (!cust.getPapernum().equals("")) {
			sql4cust=sql4cust+"and papernum='"+cust.getPapernum()+"'";
		}
		CustomBaseInfo custInfo=null;
		if (cust.getPhonenum().equals("")&&cust.getPapernum().equals("")) {
		}else {
			try {
				custInfo=new CustomBaseInfo();
				custInfo=(CustomBaseInfo) this.getCurrentSession().createQuery(sql4cust).list().get(0);
			} catch (Exception e) {
				custInfo=null;
			}
		}
		String sql4Pact="from TPactInfo where 1=1";
		if (!pactNum.equals("")) {
			sql4Pact=sql4Pact + "and contractNumber='"+pactNum+"'";
		}
		if (custInfo!=null) {
			sql4Pact=sql4Pact+" and custId='"+custInfo.getId()+"'";
		}
		try {
			List<TPactInfo>pacts=this.getCurrentSession().createQuery(sql4Pact).list();	
			return pacts;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> findRepaymentToCustomList() {
		return  this.getCurrentSession().createQuery("from TPactInfo where pactFlow in(6,7) ").list();
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
	public List<TPactInfo> findAllToPactRecheck() {
		return  this.getCurrentSession().createQuery("from TPactInfo where pactFlow in (3) ").list();
	}

	public void T1() {
		//T+1时间的修改
	}

	public int getAllRowCount(String hql) {
		return  this.getCurrentSession().createSQLQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	public List<TPactInfo> queryByPage(String sql, int offset, int pageSize) {
		return  this.getCurrentSession().createSQLQuery(sql).addEntity(TPactInfo.class).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	public void excuteSql(String sql) {
		this.getCurrentSession().createSQLQuery(sql);
		
	}

	/**
	 * 查询失败合同信息条数
	 */
	public int getPactAllRowCount(String hql) {
		return  this.getCurrentSession().createSQLQuery(hql).list().size();
	}

}
