package com.hongxin.dao;

import java.util.List;
import com.hongxin.dao.BaseDao;
import com.hongxin.entity.ResourceBak;

public interface MenuDao extends BaseDao<ResourceBak, Integer>{

	List<ResourceBak> getSubMenuList(String sourceId);//查询三级菜单

	Integer save4parentSourceId(ResourceBak resourceBak);

	List<ResourceBak> getallMenuList(Integer parentsourceId);

	List<ResourceBak> getMenuList();

}
