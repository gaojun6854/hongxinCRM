package com.hongxin.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongxin.dao.UserInfoDao;
import com.hongxin.entity.EmployStaff;
import com.hongxin.entity.User;
import com.hongxin.utils.QueryResult;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public User load(String id) {
		return (User) this.getCurrentSession().load(User.class, id);
	}

	public User get(String id) {
		String hql="select user.* from t_user user where  user.user_id='"+id+"' ";
		
		User userInfo=new User(); 
		try {
	  		 userInfo = (User) this.getCurrentSession().createSQLQuery(hql).addEntity("user",User.class).list().get(0);
	  		 if (userInfo.getUserId()!=null) {
	  			 userInfo.setEmployStaff((EmployStaff) this.getCurrentSession().createSQLQuery("select emp.* from employ_staff emp where emp.employee_id='"+userInfo.getEmployeeId()+"' ").addEntity(EmployStaff.class).list().get(0));
			}
		} catch (Exception e) {
			return null;
		}
		return userInfo;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		String hql="select user.* from t_user user ";
		List<User> userInfos = this.getCurrentSession().createSQLQuery(hql).addEntity("user",User.class).list();
		return userInfos;
	}

	public void persist(User entity) {
		this.getCurrentSession().persist(entity);
		
	}

	public String save(User entity) {
		return (String) this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(User entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(String id) {
		User entity = this.load(id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
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
