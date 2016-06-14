package com.hongxin.dao;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.TProductInfo;

public interface ProductDao extends BaseDao<TProductInfo, String>{
	int deleteById(String id);
	int saveOrUpdateByEntity(TProductInfo entity);
	TProductInfo getStrId(String productId);
}
