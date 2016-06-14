package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TFundInfo;

public interface FundInfoService
{
	TFundInfo load(String id);

	TFundInfo get(String id);

	List<TFundInfo> findAll();

	void persist(TFundInfo entity);

	String save(TFundInfo entity);

	void saveOrUpdate(TFundInfo entity);

	void delete(String id);

	void flush();

	List<TFundInfo> findByStartEnd(String start, String end);
}
