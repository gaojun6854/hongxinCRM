package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.ActionFun;

public interface ActionFunDao extends BaseDao<ActionFun, String>{

	List<ActionFun> getBySourceId(String sourceId);
}
