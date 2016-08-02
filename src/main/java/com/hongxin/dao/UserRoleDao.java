package com.hongxin.dao;


import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.UsrRole;

public interface UserRoleDao extends BaseDao<UsrRole, String>{

	List<UsrRole> selectByUserId(String userId);
}
