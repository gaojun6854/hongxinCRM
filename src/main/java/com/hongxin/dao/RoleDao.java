package com.hongxin.dao;


import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.Role;

public interface RoleDao extends BaseDao<Role, String>{

	List<Role> getByRoleId(String roleId);

	int selectByName(String roleName);
}
