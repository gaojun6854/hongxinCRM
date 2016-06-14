package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TBackAcct;

public interface AutoRepayDao extends BaseDao<TAutoRepay, String>{
	//保存
	String save4StrId(TAutoRepay entity);
	/**
	 * 通过主键查询讯息
	 * return  list
	 * @param id
	 * @return
	 */
	List<TAutoRepay> getByStrId(String id);
	List<TAutoRepay> getByStrIdType(String id, String no);
	List<TAutoRepay> findAllRepayment();
	int getfindReCheckPactAllRowCount(String hql);
	List<TAutoRepay> queryByPage(String hql, int offset, int pageSize);
	void saveBackAccount(TBackAcct backAccount);
	TBackAcct getBackAccount(String id);
	void saveOrUpdateForBackAccount(TBackAcct backAcct);

}
