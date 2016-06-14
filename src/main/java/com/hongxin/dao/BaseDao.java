package com.hongxin.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.hongxin.utils.QueryResult;
@SuppressWarnings("hiding")
interface BaseDao<T, PK extends Serializable> {
	
T load(PK id);
	
	T get(PK id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	PK save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(PK id);
	
	void flush();
	
	/**
	 * 获得分页数据
	 * QueryResult<T> 泛型定义在类上。因为需要返回查询的数据List,和查询的总条数，所以需要自定义类型返回2个数据
	 * @param <T>	泛型
	 * @param entityClass 实体类
	 * @param firstIndex 开始索引
	 * @param maxResult 需要获取的记录数
	 * @param wherejpql where条件语句
	 * @param queryParams 条件语句参数
	 * @param orderby 排序,LinkedHashMap先进先出，使用这个是因为先进去的放到第一位，order by key1 desc,key2 asc	
	 * @return
	 */
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,	String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,String wherejpql,Object[] queryParams);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);
}
