package com.hongxin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.CheckInfoDao;
import com.hongxin.dao.CheckReceiptsDao;
import com.hongxin.dao.CustomAccountDao;
import com.hongxin.dao.CustomBaseInfoDao;
import com.hongxin.dao.CustomStatusDao;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.entity.User;
import com.hongxin.service.CheckInfoService;
import com.hongxin.utils.Date2String8;
import com.opensymphony.xwork2.ActionContext;

@Service("checkInfoService")
public class CheckInfoServiceImpl implements CheckInfoService {

	@Autowired
	private CheckInfoDao checkInfoDao;
	@Autowired
	private CustomBaseInfoDao customBaseInfoDao;
	@Autowired
	private CheckReceiptsDao checkReceiptsDao;
	@Autowired
	private CustomStatusDao customStatusDao;
	@Autowired
	private CustomAccountDao customAccountDao;
	
	public CheckInfo get(Integer id) {
		return checkInfoDao.get(id);
	}

	public List<CheckInfo> findAll() {
		return checkInfoDao.findAll();
	}

	public void persist(CheckInfo entity) {
		checkInfoDao.persist(entity);
	}

	public Integer save(CheckInfo entity) {
		return checkInfoDao.save(entity);
	}

	public void saveOrUpdate(CheckInfo entity) {
		checkInfoDao.save(entity);
		
	}

	public void delete(Integer id) {
		checkInfoDao.delete(id);
	}

	public void flush() {
		checkInfoDao.flush();
	}

	public CheckInfo load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(CheckInfo customBaseInfo) {
		customBaseInfo.setCheckId(UUID.randomUUID().toString());//主键默认为uuid方式生成
		
		return checkInfoDao.save4StrId(customBaseInfo);//保存customBaseInfo
	}

	/**
	 * 创建用户所有信息
	 * CustomBaseInfo
	 * customAccount
	 * Checkinfo
	 * customStatus
	 * @param toAddCustomBaseInfo
	 * @param toAddCustomAccount
	 * @param picList
	 * @return
	 */
	public int createCustomInfos(CustomBaseInfo toAddCustomBaseInfo, CustomAccount toAddCustomAccount) {
		//保存基础用户信息
		String strId=toAddCustomBaseInfo.getId();//客户编号
		String checkId=UUID.randomUUID().toString();//审批编号
		String checkReceiptsId=UUID.randomUUID().toString();//审批编号
		toAddCustomBaseInfo.setId(strId);
		
		try {
			customBaseInfoDao.save4StrId(toAddCustomBaseInfo);
		} catch (Exception e) {
			return 0;
		}
		
		//增加Account类的参数信息
		toAddCustomAccount.setCustomId(strId);
		toAddCustomAccount.setPhoneNum(toAddCustomBaseInfo.getPhonenum());
		toAddCustomAccount.setPaperType(toAddCustomBaseInfo.getPapertype().charAt(0));
		toAddCustomAccount.setAccountStart('1');//账户正常
		toAddCustomAccount.setAccountFuyou(toAddCustomBaseInfo.getPhonenum());
		User userInfo=(User) ActionContext.getContext().getSession().get("login_user");
		toAddCustomAccount.setStaffNum(userInfo.getEmployStaff().getEmployeeId());
		toAddCustomAccount.setCheckId(checkId);
		toAddCustomAccount.setCheckIdDesc("用户创建账户");
		toAddCustomAccount.setCheckIdSer(1);
		
		try {
			customAccountDao.save4StrId(toAddCustomAccount);
		} catch (Exception e) {
			return 0;
		}
		
		//增加CheckInfo信息
		CheckInfo checkInfo=new CheckInfo();
		checkInfo.setCheckId(checkId);
		checkInfo.setCheckSer(1);
		checkInfo.setCheckSec("客户信息添加");
		checkInfo.setCheckType('1');//客户签约类型
		checkInfo.setOthId(strId);
		checkInfo.setCheckFlow('0');//状态——》录入信息
		checkInfo.setCheckManNum(userInfo.getEmployStaff().getEmployeeId());
		checkInfo.setCheckManName(userInfo.getEmployStaff().getFullName());
		checkInfo.setCheckStart('1');
		checkInfo.setCheckOpinion("用户信息注册");
		checkInfo.setCheckDesc("用户信息注册");
		checkInfo.setCehckRecId(checkReceiptsId);
		checkInfo.setCreateDate(Date2String8.date2String(new Date()));
		checkInfo.setCreateTime(Date2String8.time2String());
		
		try {
			checkInfoDao.save4StrId(checkInfo);
		} catch (Exception e) {
			return 0;
		}

				
		//保存用户状态信息
		CustomStatus customStatus=new CustomStatus();
		customStatus.setCustomId(strId);
		customStatus.setCustStart('1');
		customStatus.setCustCheckStart('1');
		customStatus.setCheckId(checkId);
		customStatus.setCheckIdSer(1);
		customStatus.setSignDate(Date2String8.date2String(new Date()));
		customStatus.setSignTime(Date2String8.time2String());
		customStatus.setManageId(userInfo.getEmployStaff().getEmployeeId());
		customStatus.setMamageName(userInfo.getEmployStaff().getFullName());
		
		try {
			customStatusDao.save4StrId(customStatus);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * 通过custom——id查询checkInfo信息
	 */
	public List<CheckInfo> getByCustomId(String id) {
		return checkInfoDao.getByCustomId(id);
	}

	/**
	 * 审核初审
	 */
	public int auditYN(String id, int code) {
		User userInfo=(User) ActionContext.getContext().getSession().get("login_user");//登录用户信息
		CheckInfo checkInfo=new CheckInfo();
		checkInfo.setCheckId(UUID.randomUUID().toString());
		checkInfo.setCheckSer(1);
		checkInfo.setCheckSec("初审");
		checkInfo.setCheckType('1');//客户签约类型
		checkInfo.setOthId(id);
		checkInfo.setCheckFlow('1');//状态——》初审
		checkInfo.setCheckManNum(userInfo.getEmployStaff().getEmployeeId());
		checkInfo.setCheckManName(userInfo.getEmployStaff().getFullName());
		
		checkInfo.setCheckOpinion("初审");
		checkInfo.setCheckDesc("初审");
		checkInfo.setCehckRecId(null);
		checkInfo.setCreateDate(Date2String8.date2String(new Date()));
		checkInfo.setCreateTime(Date2String8.time2String());
		
		CustomStatus customStatus=customStatusDao.getByStrId(id);//客户状态
		if (code==2) {
			checkInfo.setCheckStart('1');//成功
			customStatus.setCustStart('3');//进入复审阶段
			customStatus.setCustCheckStart('2');
		}else{
			checkInfo.setCheckStart('2');//失败
			customStatus.setCustStart('2');
			customStatus.setCustCheckStart('3');
		}
		return checkInfoDao.auditYN(checkInfo,customStatus);
	}

	/**
	 * 复审审核信息
	 */
	public int EditedInfoYN(String id, int code) {
		User userInfo=(User) ActionContext.getContext().getSession().get("login_user");//登录用户信息
		CheckInfo checkInfo=new CheckInfo();
		checkInfo.setCheckId(UUID.randomUUID().toString());
		checkInfo.setCheckSer(1);
		checkInfo.setCheckSec("复审");
		checkInfo.setCheckType('1');//客户签约类型
		checkInfo.setOthId(id);
		checkInfo.setCheckFlow('2');//状态——》初审
		checkInfo.setCheckManNum(userInfo.getEmployStaff().getEmployeeId());
		checkInfo.setCheckManName(userInfo.getEmployStaff().getFullName());
		
		checkInfo.setCheckOpinion("复审");
		checkInfo.setCheckDesc("复审");
		checkInfo.setCehckRecId(null);
		checkInfo.setCreateDate(Date2String8.date2String(new Date()));
		checkInfo.setCreateTime(Date2String8.time2String());
		
		CustomStatus customStatus=customStatusDao.getByStrId(id);//客户状态
		if (code==2) {
			checkInfo.setCheckStart('1');//成功
			customStatus.setCustStart('6');//进入复审阶段
			customStatus.setCustCheckStart('2');
		}else{
			checkInfo.setCheckStart('2');//失败
			customStatus.setCustStart('4');
			customStatus.setCustCheckStart('3');
		}
		return checkInfoDao.auditYN(checkInfo,customStatus);
	}

}
