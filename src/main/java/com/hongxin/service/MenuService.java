package com.hongxin.service;

import java.util.List;

import com.hongxin.entity.ActionFun;
import com.hongxin.entity.ResourceBak;

public interface MenuService 
{
	ResourceBak load(Integer id);

	ResourceBak get(Integer id);

	List<ResourceBak> findAll();

	void persist(ResourceBak entity);

	Integer save(ResourceBak entity);

	void saveOrUpdate(ResourceBak entity);

	void delete(Integer id);

	void flush();

	Integer save4parentSourceId(ResourceBak resourceBak);

	List<ResourceBak> getallMenuList(Integer parentsourceId);

	List<ActionFun> getFunctionList(String sourceId);

	List<ResourceBak> getMenuListFunlist();

	List<ResourceBak> getSubMenuList(String Id);
}
