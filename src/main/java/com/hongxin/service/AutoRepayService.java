package com.hongxin.service;

import java.util.List;
import java.util.Map;

import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInfo;

public interface AutoRepayService 
{
	TAutoRepay load(String id);

	TAutoRepay get(String id);

	List<TAutoRepay> findAll();

	void persist(TAutoRepay entity);

	String save(TAutoRepay entity);

	void saveOrUpdate(TAutoRepay entity);

	void delete(String id);

	void flush();

	String save4StrId(TAutoRepay entiry);
	List<TAutoRepay>getByStrId(String Id);

	List<TAutoRepay> findAllRepayment();

	PageBean<TAutoRepay> findReCheckPact(int i, int page,Map<String, Object> map);

	PageBean<TAutoRepay> findFailAutoRepay(int pageSize, int page,Map<String, Object> map);

	PageBean<TAutoRepay> findFirstCheck(int i, int page,Map<String, String> map);//查询所有初审

	void saveBackAccount(TBackAcct backAccount);//保存还款客户账

	TBackAcct getBackAccount(String id);

	void saveOrUpdateForBackAccount(TBackAcct backAcct);

}
