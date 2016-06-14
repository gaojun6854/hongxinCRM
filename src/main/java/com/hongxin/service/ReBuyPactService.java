package com.hongxin.service;

import java.util.List;

import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TRebuypactInfo;

public interface ReBuyPactService 
{
	TRebuypactInfo load(String id);

	TRebuypactInfo get(String id);

	List<TRebuypactInfo> findAll();

	void persist(TRebuypactInfo entity);

	String save(TRebuypactInfo entity);

	void saveOrUpdate(TRebuypactInfo entity);

	void delete(String id);

	void flush();

	void saveNewOld(TRebuypactInfo rebuyPact, TPactInfo oldPact);
}
