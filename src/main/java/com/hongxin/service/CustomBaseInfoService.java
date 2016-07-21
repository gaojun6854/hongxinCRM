package com.hongxin.service;

import java.util.List;
import java.util.Map;

import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TFuyouTran;
import com.hongxin.entity.TPactInfo;

public interface CustomBaseInfoService 
{
	CustomBaseInfo load(Integer id);

	CustomBaseInfo get(Integer id);

	List<CustomBaseInfo> findAll();

	void persist(CustomBaseInfo entity);

	Integer save(CustomBaseInfo entity);

	void saveOrUpdate(CustomBaseInfo entity);

	void delete(Integer id);

	void flush();

	String save4StrId(CustomBaseInfo customBaseInfo);
	
	//通过手机号码或者身份证查找该用户信息是否存在
	int getByPhonenumOrPapernum(String phonenum,String papernum);

	List<CustomBaseInfo>  getByStrId(String id);//通过StrId查询用户信息

	int saveOrUpdateByStr(CustomBaseInfo customBaseInfo,int g);//修改用户信息

	List<CustomBaseInfo> findAllFirstAudit();//查询全部初审客户信息

	PageBean<CustomBaseInfo> getPageBean(int i, int page, Map<String, Object> map);

	PageBean<CustomBaseInfo> findBuyCust(int i, int page, Map<String, Object> map);

	List<CustomBaseInfo> findFailInfo();//查询需要更改的客户信息

	void ReqFuyouResAPISsn(TFuyouTran tran);//富有用户信息流水

	PageBean<CustomBaseInfo> findAllFirstCheck(int fenYeShu, int page, Map<String, Object> map);

	PageBean<CustomBaseInfo> findAllLastCheck(int fenYeShu, int page, Map<String, Object> map);

	PageBean<CustomBaseInfo> findNeedUpdateCustom(int fenYeShu, int page, Map<String, Object> map);
}
