package com.hongxin.dao;

import java.util.List;
import com.hongxin.dao.BaseDao;
import com.hongxin.entity.ActionFun;
import com.hongxin.entity.ResourceBak;

public interface ActionFunDao extends BaseDao<ActionFun, String>{

	List<ActionFun> getBySourceId(String sourceId);

	List<ActionFun> getFunctionList(Integer sourceId);

	List<ResourceBak> getSubMenuList(String pID);

	List selectSourceIdByactionIds(String hql);
}
