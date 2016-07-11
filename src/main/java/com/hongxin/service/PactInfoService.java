package com.hongxin.service;

import java.util.List;
import java.util.Map;

import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TRebuypactInfo;

public interface PactInfoService 
{
	TPactInfo load(String id);

	TPactInfo get(String id);

	List<TPactInfo> findAll();

	void persist(TPactInfo entity);

	int saveOrUpdateByEntity(TPactInfo entity);
	
	int deleteById(String id);

	String save(TPactInfo entity);
	
	void saveOrUpdate(TPactInfo entity);

	void delete(String id);
	void flush();

	List<TPactInfo> findAllReviews();

	int onLineReviewsYN(String id, String param,String noPassReson);

	List<TPactInfo> findAllPZReviews();

	int offLineReviewsYN(String id, String param,String noPassReson);

	List<TPactInfo> findAllRepayment();

	int repaymentYN(String id, String param);

	List<TPactInfo> findByPactNum(String pactNum,CustomBaseInfo customBaseInfo);//通过合同号查询信息

	List<TPactInfo> findRepaymentToCustomList();//还款到客户账信息

	void pactHG2(TPactInfo pactInfo, TRebuypactInfo rebuy);//回购表回到合同表---回购开始

	List<TPactInfo> findAllToPactRecheck();//查询状态为1复审中的合同信息

	void YNPactRecheck(TPactInfo pactInfo, String param );

	public PageBean<TPactInfo> getPageBean(int pageSize, int page,Map<String, Object> map);

	PageBean<TPactInfo> findFailPact(int i, int page,Map<String, Object>map);//查询失败的合同信息

	void PactRecheck(TPactInfo pactInfo, String param);

	PageBean<TPactInfo> getMoneyPageBean(int fenYeShu, int page, Map<String, Object> map);

	PageBean<TPactInfo> getFirstCheckList(int fenYeShu, int page, Map<String, Object> map);

	PageBean<TPactInfo> getLastCheckList(int fenYeShu, int page, Map<String, Object> map);

}
