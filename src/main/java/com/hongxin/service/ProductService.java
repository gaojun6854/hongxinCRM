package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TProductInfo;

public interface ProductService 
{
	TProductInfo load(String id);

	TProductInfo get(String id);

	List<TProductInfo> findAll();

	void persist(TProductInfo entity);

	int saveOrUpdateByEntity(TProductInfo entity);
	
	int deleteById(String id);

	String save(TProductInfo entity);
	
	void saveOrUpdate(TProductInfo entity);

	void delete(String id);
	void flush();

	TProductInfo getStrId(String string);
}
