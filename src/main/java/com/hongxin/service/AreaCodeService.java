package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TAreaCode;

public interface AreaCodeService 
{
	TAreaCode load(String id);

	TAreaCode get(String id);

	List<TAreaCode> findAll();

	void persist(TAreaCode entity);

	String save(TAreaCode entity);

	void saveOrUpdate(TAreaCode entity);

	void delete(String id);

	void flush();
	//查询所有省份列表
	List<TAreaCode> getProvinceList(String id);

}
