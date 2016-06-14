package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TIndexInfo;

public interface ZhishuService 
{
	TIndexInfo load(String id);

	TIndexInfo get(String id);

	List<TIndexInfo> findAll();

	void persist(TIndexInfo entity);

	String save(TIndexInfo entity);

	void saveOrUpdate(TIndexInfo entity);

	void delete(String id);

	void flush();

	String save4StrId(TIndexInfo TIndexInfo);
	
	TIndexInfo getByStrId(String id);

	List<TIndexInfo> findByStartEnd(String start, String end);
}
