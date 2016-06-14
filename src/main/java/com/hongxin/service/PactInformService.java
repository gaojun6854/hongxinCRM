package com.hongxin.service;

import java.util.List;
import java.util.Map;

import com.hongxin.entity.PageBean;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInform;

public interface PactInformService 
{
	TPactInform load(String id);

	TPactInform get(String id);

	List<TPactInform> findAll();

	void persist(TPactInform entity);

	String save(TPactInform entity);

	void saveOrUpdate(TPactInform entity);

	void delete(String id);

	void flush();

	PageBean<TPactInform> getPageBean(int i, int page, Map<String, Object> map);

	PageBean<TBackAcct> getPaymentToCustomRecord(int i, int page, Map<String, Object> map);

	TBackAcct getBackAcct(String id);

	void saveOrUpdate4BackAccount(TBackAcct backAcct);

}
