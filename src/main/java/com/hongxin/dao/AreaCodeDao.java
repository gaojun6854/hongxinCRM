package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.TAreaCode;

public interface AreaCodeDao extends BaseDao<TAreaCode, String>{
	//查询所有省份
	List<TAreaCode> getProvinceList(String id);
}
