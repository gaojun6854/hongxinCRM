package com.hongxin.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.CustomBaseInfoDao;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.entity.TFuyouTran;
import com.hongxin.utils.QueryResult;

@Repository("customBaseInfoDao")
public class CustomBaseInfoDaoImpl implements CustomBaseInfoDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public CustomBaseInfo load(Integer id) {
		return (CustomBaseInfo) this.getCurrentSession().load(CustomBaseInfo.class, id);
	}

	public CustomBaseInfo get(Integer id) {
		return (CustomBaseInfo) this.getCurrentSession().get(CustomBaseInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> findAll() {
		List<CustomBaseInfo> customBaseInfos = this.getCurrentSession().createQuery("from CustomBaseInfo").setFirstResult(0).setMaxResults(5).list();
		return customBaseInfos;
	}

	public void persist(CustomBaseInfo entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public Integer save(CustomBaseInfo entity) {
		return (Integer) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(CustomBaseInfo entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		CustomBaseInfo entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public String save4StrId(CustomBaseInfo entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public int getByPhonenumOrPapernum(String phonenum, String Papernum) {
		@SuppressWarnings("unchecked")
		List<CustomBaseInfo> customBaseInfos= this.getCurrentSession().createQuery("from CustomBaseInfo where phonenum='"+phonenum+"' OR papernum='"+Papernum+"'").list();
		return customBaseInfos.size();
	}

	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> getByStrId(String id) {
		return  (List<CustomBaseInfo> )this.getCurrentSession().createQuery("from CustomBaseInfo where id ='"+id+"'").list();
	}

	public int saveOrUpdateByStr(CustomBaseInfo customBaseInfo) {
		this.getCurrentSession().saveOrUpdate(customBaseInfo);
		return 1;
	}

	/**
	 *  查询初审客户信息
	 */
	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> findAllFirstAudit() {
		List<CustomBaseInfo>customBaseInfos=new ArrayList<CustomBaseInfo>();
		String hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1 and sta.cust_start='1'";
		customBaseInfos=this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).list();
		return customBaseInfos;
	}

	/**
	 * 查询复审客户信息
	 */
	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> findAudited() {
		List<CustomBaseInfo>customBaseInfos=new ArrayList<CustomBaseInfo>();
		String hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1 and sta.cust_start='3'";
		customBaseInfos=this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).list();
		return customBaseInfos;
	}

	/**
	 * 查询已编辑了的客户信息的客户
	 * 复审信息
	 */
	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> findEditedInfo() {
		List<CustomBaseInfo>customBaseInfos=new ArrayList<CustomBaseInfo>();
		String hql="select cust.custom_id,cust.cust_name,cust.cust_gender,cust.paper_type,cust.paper_num,cust.phone_num from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1 and sta.cust_start='1'";
		customBaseInfos= this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).list();
		return customBaseInfos;
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

	public int getAllRowCount(String hql) {
		return  this.getCurrentSession().createSQLQuery(hql).list().size();
	}

	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> queryByPage(String hql, int offset, int pageSize) {
		return  this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	public List<CustomStatus> QuerySql(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	public int getAllRowCountByHql(String hql) {
		return  this.getCurrentSession().createSQLQuery(hql).list().size();
	}

	public List<CustomBaseInfo> queryByPageByHql(String hql, int offset, int pageSize) {
		return  this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	public List<CustomBaseInfo> findFailInfo(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(CustomBaseInfo.class).list();
	}
	/**
	 * 富有信息流水信息
	 * SAVE
	 */
	public void ReqFuyouResAPISsn(TFuyouTran tran) {
			this.getCurrentSession().save(tran);
	}
}
