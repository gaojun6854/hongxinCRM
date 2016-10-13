package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.RoleActionFun;

public interface RoleActionFunDao extends BaseDao<RoleActionFun, String>{

	List<RoleActionFun> selectByRoleId(String roleId);

	void deleteByRoleId(String roleId);
}
