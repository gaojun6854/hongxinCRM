package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.CheckReceipts;

public interface CheckReceiptsService 
{
	CheckReceipts load(String id);

	CheckReceipts get(String id);

	List<CheckReceipts> findAll();

	void persist(CheckReceipts entity);

	String save(CheckReceipts entity);

	void saveOrUpdate(CheckReceipts entity);

	void delete(String id);

	void flush();

	String save4StrId(CheckReceipts CheckReceipts);
	List<CheckReceipts>getByStrId(String Id);

	List<CheckReceipts> getByStrIdType(String id, String no);

	void deleteByStrId(String recNum);

}
