package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.CheckReceipts;

public interface CheckReceiptsDao extends BaseDao<CheckReceipts, String>{
	//保存
	String save4StrId(CheckReceipts checkInfo);
	/**
	 * 通过主键查询讯息
	 * return  list
	 * @param id
	 * @return
	 */
	List<CheckReceipts> getByStrId(String id);
	List<CheckReceipts> getByStrIdType(String id, String no);
	void deleteByStrId(String recId);

}
