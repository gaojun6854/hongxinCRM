package com.hongxin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.CheckInfoDao;
import com.hongxin.dao.CheckReceiptsDao;
import com.hongxin.dao.CustomAccountDao;
import com.hongxin.dao.CustomBaseInfoDao;
import com.hongxin.dao.CustomStatusDao;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TFuyouTran;
import com.hongxin.entity.User;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.utils.Date2String8;
import com.opensymphony.xwork2.ActionContext;

@Service("customBaseInfoService")
public class CustomBaseInfoServiceImpl implements CustomBaseInfoService {

	@Autowired
	private CustomBaseInfoDao customBaseInfoDao;
	@Autowired
	private CheckInfoDao checkInfoDao;
	@Autowired
	private CustomStatusDao customStatusDao;
	@Autowired
	private CheckReceiptsDao checkReceiptsDao;
	@Autowired
	private CustomAccountDao customAccountDao;
	public CustomBaseInfo get(Integer id) {
		return customBaseInfoDao.get(id);
	}

	public List<CustomBaseInfo> findAll() {
		List<CustomBaseInfo> customBaseInfos=customBaseInfoDao.findAll();
		return customBaseInfos;
	}

	public void persist(CustomBaseInfo entity) {
		customBaseInfoDao.persist(entity);
	}

	public Integer save(CustomBaseInfo entity) {
		return customBaseInfoDao.save(entity);
	}

	public void saveOrUpdate(CustomBaseInfo entity) {
		customBaseInfoDao.save(entity);
		
	}

	public void delete(Integer id) {
		customBaseInfoDao.delete(id);
	}

	public void flush() {
		customBaseInfoDao.flush();
	}

	public CustomBaseInfo load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(CustomBaseInfo customBaseInfo) {
		return customBaseInfoDao.save4StrId(customBaseInfo);//保存customBaseInfo
	}
	
	//通过手机号码或者身份证查找该用户信息是否存在
	public int getByPhonenumOrPapernum(String phonenum,String papernum) {
		return customBaseInfoDao.getByPhonenumOrPapernum(phonenum,papernum);
	}

	//通过主键查询用户信息
	public List<CustomBaseInfo>  getByStrId(String id) {
		return customBaseInfoDao.getByStrId(id);
	}

	/**
	 * 更新用户信息
	 */
	public int saveOrUpdateByStr(CustomBaseInfo customBaseInfo,int g) {
		User userInfo=(User) ActionContext.getContext().getSession().get("login_user");
		if (g==1) {
			//客户出于初审，直接保存
			return customBaseInfoDao.saveOrUpdateByStr(customBaseInfo);
		}else{
			//不管客户出于审核失败还是出于初审成功,强制为客户信息修改了
			//增加CheckInfo信息
			CheckInfo checkInfo=new CheckInfo();
			checkInfo.setCheckId(UUID.randomUUID().toString());
			checkInfo.setCheckSer(1);
			checkInfo.setCheckSec("客户信息修改");
			checkInfo.setCheckType('1');//客户签约类型
			checkInfo.setOthId(customBaseInfo.getId());
			checkInfo.setCheckFlow('3');//状态——》信息修改
			checkInfo.setCheckManNum(userInfo.getEmployStaff().getEmployeeId());
			checkInfo.setCheckManName(userInfo.getEmployStaff().getFullName());
			checkInfo.setCheckStart('1');
			checkInfo.setCheckOpinion("用户信息修改");
			checkInfo.setCheckDesc("用户信息修改");
			checkInfo.setCehckRecId(null);
			checkInfo.setCreateDate(Date2String8.date2String(new Date()));
			checkInfo.setCreateTime(Date2String8.time2String());
			
			try {
				checkInfoDao.save4StrId(checkInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//客户状态中为客户信息修改
			CustomStatus customStatus=customStatusDao.getByStrId(customBaseInfo.getId());
			customStatus.setCustStart('1');
			customStatus.setCustCheckStart('1');
			customStatusDao.saveOrUpdate(customStatus);
			return customBaseInfoDao.saveOrUpdateByStr(customBaseInfo);
		}
	}

	/**
	 * 查询全部初审客户信息
	 */
	public List<CustomBaseInfo> findAllFirstAudit() {
		return customBaseInfoDao.findAllFirstAudit();
	}

	//初审已完成信息
	public List<CustomBaseInfo> findAudited() {
		return customBaseInfoDao.findAudited();
	}
	//查询已编辑了的客户信息的客户
	public List<CustomBaseInfo> findEditedInfo() {
		return customBaseInfoDao.findAudited();
	}

	public PageBean<CustomBaseInfo> getPageBean(int pageSize, int page, Map<String, Object> map) {
		///////////////////////////////////////////////////////////附带的条件
		String custPhone=(String) map.get("custPhone");
		String custPapernum=(String) map.get("custPapernum");
		String custName=(String) map.get("custName");
		String attr=(String) map.get("attr");
		int all=(Integer) map.get("all");
		
		String hql = "select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1";
		if ("1".equals(attr)) {//初审
			hql=hql+"  AND sta.cust_start='1' ";
		}
		if ("2".equals(attr)) {//初审失败
			hql=hql+"   AND sta.cust_start='2' ";
		}
		if ("3".equals(attr)) {//复审
			hql=hql+"   AND sta.cust_start='3' ";
		}
		if ("4".equals(attr)) {//复审失败
			hql=hql+"   AND sta.cust_start='4' ";
		}
		if ("5".equals(attr)) {//信息修改中
			hql=hql+"   AND sta.cust_start='5' ";
		}
		if ("6".equals(attr)) {//签约成功
			hql=hql+"   AND sta.cust_start='6' ";
		}
		if ("7".equals(attr)) {//解约成功
			hql=hql+"   AND sta.cust_start='7' ";
		}
			
		if ("".equals(custPhone)&&"".equals(custPapernum)&&"".equals(custName)&&"0".equals(attr)||all==1) {
			hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1";
		}else{
		if (!"".equals(custPhone))
			hql=hql+" and cust.phone_num='"+custPhone+"'";
		if (!"".equals(custPapernum)) 
			hql=hql+" and cust.paper_num='"+custPapernum+"'";
		if (!"".equals(custName))
			hql=hql+" and cust.cust_name='"+custName+"'";
		}
		
		///////////////////////////////////////////////////////
		PageBean<CustomBaseInfo> pageBean = new PageBean<CustomBaseInfo>();
		
		int allRows = customBaseInfoDao.getAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<CustomBaseInfo> list = customBaseInfoDao.queryByPage(hql, offset, pageSize);
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	public PageBean<CustomBaseInfo> findBuyCust(int pageSize, int page, Map<String, Object> map) {
		String custPhone=(String) map.get("custPhone");
		String custPapernum=(String) map.get("custPapernum");
		String custName=(String) map.get("custName");
		int all=(Integer) map.get("all");

		String hql = "select cust.* from t_custom_base_info cust INNER JOIN t_custom_status statu on cust.custom_id=statu.custom_id where 1=1 and statu.cust_start='6' ";	
		if ("".equals(custPhone)&&"".equals(custPapernum)&&"".equals(custName)||all==1) {
		hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status statu on cust.custom_id=statu.custom_id where 1=1 and statu.cust_start='6'";
		}else{
		if (!"".equals(custPhone))
		hql=hql+" and cust.phone_num='"+custPhone+"'";
		if (!"".equals(custPapernum)) 
		hql=hql+" and cust.paper_num='"+custPapernum+"'";
		if (!"".equals(custName))
		hql=hql+" and cust.cust_name='"+custName+"'";
		}
		
		///////////////////////////////////////////////////////
		PageBean<CustomBaseInfo> pageBean = new PageBean<CustomBaseInfo>();
		
		
		
		try {
			int allRows = customBaseInfoDao.getAllRowCountByHql(hql);
			
			int totalPage = pageBean.getTotalPages(pageSize, allRows);
			
			int currentPage = pageBean.getCurPage(page);
			
			int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
			List<CustomBaseInfo> list = customBaseInfoDao.queryByPageByHql(hql, offset, pageSize);
			
			pageBean.setList(list);
			pageBean.setAllRows(allRows);
			pageBean.setCurrentPage(currentPage);
			pageBean.setTotalPage(totalPage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		return pageBean;
	}

	public List<CustomBaseInfo> findFailInfo() {
		String hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1  AND sta.cust_start in (2,4) ";
		return customBaseInfoDao.findFailInfo(hql);
	}

	public void ReqFuyouResAPISsn(TFuyouTran tran) {
		customBaseInfoDao.ReqFuyouResAPISsn(tran);
	}

	/**
	 * 分页  所有初审信息
	 */
	public PageBean<CustomBaseInfo> findAllFirstCheck(int pageSize, int page, Map<String, Object> map) {

		String custName=(String) map.get("custName");
		String phoneNum=(String) map.get("phoneNum");
		String paperNum=(String) map.get("paperNum");
		
		String hql = "select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1";
		
		if ("".equals(custName)&&"".equals(phoneNum)&&"".equals(paperNum)) {
		hql="select cust.* from t_custom_base_info cust INNER JOIN t_custom_status sta on cust.custom_id=sta.custom_id where 1=1";
		}else{
		if (!"".equals(phoneNum))
		hql=hql+" and cust.phone_num='"+phoneNum+"'";
		if (!"".equals(paperNum)) 
		hql=hql+" and cust.paper_num='"+paperNum+"'";
		if (!"".equals(custName))
		hql=hql+" and cust.cust_name='"+custName+"'";
		}
		
		///////////////////////////////////////////////////////
		PageBean<CustomBaseInfo> pageBean = new PageBean<CustomBaseInfo>();
		
		int allRows = customBaseInfoDao.getAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<CustomBaseInfo> list = customBaseInfoDao.queryByPage(hql, offset, pageSize);
		for (CustomBaseInfo customBaseInfo :list) {
		customBaseInfo.setCheckInfos(checkInfoDao.getByCustomId(customBaseInfo.getId()));
		customBaseInfo.setCheckReceipts(checkReceiptsDao.getByStrId(customBaseInfo.getId()));
		customBaseInfo.setCustomStatus(customStatusDao.getByStrId(customBaseInfo.getId()));
		customBaseInfo.setCustomAccount(customAccountDao.getStrId(customBaseInfo.getId()));
		}
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
