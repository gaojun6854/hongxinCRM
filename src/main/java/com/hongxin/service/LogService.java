package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.OperationDiary;

public interface LogService 
{
	OperationDiary load(Integer id);

	OperationDiary get(Integer id);

	List<OperationDiary> findAll();

	void persist(OperationDiary entity);

	Integer save(OperationDiary entity);

	void saveOrUpdate(OperationDiary entity);

	void delete(Integer id);

	void flush();
}
