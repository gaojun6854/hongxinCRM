package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TErrCode;

public interface ErrorCodeService 
{
	TErrCode load(String id);

	TErrCode get(String id);

	List<TErrCode> findAll();

	void persist(TErrCode entity);

	String save(TErrCode entity);

	void saveOrUpdate(TErrCode entity);

	void delete(String id);

	void flush();

}
