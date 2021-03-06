package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;

public interface CheckInfoService 
{
	CheckInfo load(Integer id);

	CheckInfo get(Integer id);

	List<CheckInfo> findAll();

	void persist(CheckInfo entity);

	Integer save(CheckInfo entity);

	void saveOrUpdate(CheckInfo entity);

	void delete(Integer id);

	void flush();

	String save4StrId(CheckInfo CheckInfo);
/**
 * 创建用户所有信息
 * CustomBaseInfo
 * customAccount
 * Checkinfo
 * customStatus
 * checkReceipts
 * @param toAddCustomBaseInfo
 * @param toAddCustomAccount
 * @param picList
 * @return
 */
	int createCustomInfos(CustomBaseInfo toAddCustomBaseInfo, CustomAccount toAddCustomAccount);

	/**
	 * 通过custom_id查询checkInfo信息
	 * @param id
	 * @return
	 */
    List<CheckInfo> getByCustomId(String id);

	int auditYN(String id, int code);//c初审审核信息

	int EditedInfoYN(String id, int code);//复审审核信息
}
