package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.RoleSource;

public interface RoleSourceDao extends BaseDao<RoleSource, String>{

	void deleteByRoleId(String roleId);

	List<RoleSource> getMyResourceByroleId(String roleId);

}
