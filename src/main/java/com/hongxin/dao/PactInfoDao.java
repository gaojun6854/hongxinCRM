package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.TPactInfo;

public interface PactInfoDao extends BaseDao<TPactInfo, String>{
	int deleteById(String id);
	int saveOrUpdateByEntity(TPactInfo entity);
	List<TPactInfo> findAllReviews();
	int onLineReviewsYN(TPactInfo pact);
	List<TPactInfo> findAllPZReviews();
	List<TPactInfo> findAllRepayment();
	List<TPactInfo> findByPactNum(String pactNum,CustomBaseInfo cust);
	List<TPactInfo> findRepaymentToCustomList();
	List<TPactInfo> findAllToPactRecheck();
	void T1();
	int getAllRowCount(String sql);
	List<TPactInfo> queryByPage(String sql, int offset, int pageSize);
	void excuteSql(String sql);
	int getPactAllRowCount(String hql);
}
