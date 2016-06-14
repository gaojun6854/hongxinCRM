package com.hongxin.utils;

import java.util.List;

/*
 * 定义查询返回的结果，泛型定义在类上
 */
public class QueryResult<T> {
	private List<T> resultlist;		//记录查询的结果
	private long totalrecord;		//记录查询得到的总条数
	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
