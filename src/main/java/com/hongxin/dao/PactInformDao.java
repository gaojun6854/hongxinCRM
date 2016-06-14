package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInform;

public interface PactInformDao extends BaseDao<TPactInform, String>{

	int findAllPaymentListSize(String hql);

	List<TPactInform> queryByPage(String hql, int offset, int pageSize);

	int getPaymentToCustomRecordSize(String hql);

	List<TBackAcct> getPaymentToCustomRecord(String hql, int offset, int pageSize);

	TBackAcct getBackAcct(String id);

	void saveOrUpdate4BackAccount(TBackAcct backAcct);

}
