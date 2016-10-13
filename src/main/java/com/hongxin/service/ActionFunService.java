package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.ActionFun;

public interface ActionFunService 
{
	ActionFun load(String id);

	ActionFun get(String id);

	List<ActionFun> findAll();

	void persist(ActionFun entity);

	String save(ActionFun entity);

	void saveOrUpdate(ActionFun entity);

	void delete(String id);

	void flush();

	List selectSourceIdByactionIds(List<String> actionIdList);

}
