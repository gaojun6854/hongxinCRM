package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.TIndexInfo;

public interface ZhishuDao extends BaseDao<TIndexInfo, String>{

	String save4StrId(TIndexInfo TIndexInfo);

	TIndexInfo getStrId(String id);

	List<TIndexInfo> findByStartEnd(String start, String end);

}
