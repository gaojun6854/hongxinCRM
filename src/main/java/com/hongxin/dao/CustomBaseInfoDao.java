package com.hongxin.dao;

import java.util.List;

import com.hongxin.dao.BaseDao;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.entity.TFuyouTran;
import com.hongxin.entity.TPactInfo;

public interface CustomBaseInfoDao extends BaseDao<CustomBaseInfo, Integer>{

	String save4StrId(CustomBaseInfo customBaseInfo);

	//通过手机号码或者身份证查找该用户信息是否存在
	int getByPhonenumOrPapernum(String phonenum,String Papernum);

	List<CustomBaseInfo>  getByStrId(String id);

	int saveOrUpdateByStr(CustomBaseInfo customBaseInfo);

	List<CustomBaseInfo> findAllFirstAudit();// 查询全部初审客户信息

	List<CustomBaseInfo> findAudited();//初审已完成信息

	List<CustomBaseInfo> findEditedInfo();//查询已编辑了的客户信息的客户

	int getAllRowCount(String hql);

	List<CustomBaseInfo> queryByPage(String hql, int offset, int pageSize);

	List<CustomStatus> QuerySql(String string);

	int getAllRowCountByHql(String hql);

	List<CustomBaseInfo> queryByPageByHql(String hql, int offset, int pageSize);

	List<CustomBaseInfo> findFailInfo(String hql);

	void ReqFuyouResAPISsn(TFuyouTran tran);

}
