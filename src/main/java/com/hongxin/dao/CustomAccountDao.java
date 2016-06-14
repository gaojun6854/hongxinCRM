package com.hongxin.dao;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.CustomAccount;

public interface CustomAccountDao extends BaseDao<CustomAccount, Integer>{

	String save4StrId(CustomAccount customAccount);

	CustomAccount getStrId(String id);

}
