package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomStatus;

public interface CheckInfoDao extends BaseDao<CheckInfo, Integer>{

	String save4StrId(CheckInfo checkInfo);
	//通过客户id查询checkInfo信息
	List<CheckInfo> getByCustomId(String id);
	//初审动作
	int auditYN(CheckInfo checkInfo,CustomStatus customStatus);

}
