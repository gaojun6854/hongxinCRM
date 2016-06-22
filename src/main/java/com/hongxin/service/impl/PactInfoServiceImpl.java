package com.hongxin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.AutoRepayDao;
import com.hongxin.dao.CustomBaseInfoDao;
import com.hongxin.dao.PactInfoDao;
import com.hongxin.dao.ProductDao;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TProductInfo;
import com.hongxin.entity.TRebuypactInfo;
import com.hongxin.service.PactInfoService;
import com.hongxin.utils.Date2String8;

@Service("pactInfoService")
public class PactInfoServiceImpl implements PactInfoService {

	@Autowired
	private PactInfoDao pactInfoDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CustomBaseInfoDao customBaseInfoDao; 
	@Autowired
	private AutoRepayDao autoRepayDao;
	private byte[] lock = new byte[0];  // 特殊的instance变量
	public TPactInfo load(String id) {
		return pactInfoDao.load(id);
	}

	public TPactInfo get(String id) {
		return pactInfoDao.get(id);
	}

	public List<TPactInfo> findAll() {
		return pactInfoDao.findAll();
		
	}

	public void persist(TPactInfo entity) {
		pactInfoDao.persist(entity);
	}

	public String save(TPactInfo entity) {
		return pactInfoDao.save(entity);
	}

	public int saveOrUpdateByEntity(TPactInfo entity) {
		return pactInfoDao.saveOrUpdateByEntity(entity);
		
	}

	public int deleteById(String id) {
		return pactInfoDao.deleteById(id);
	}

	public void flush() {
		pactInfoDao.flush();
	}

	public void saveOrUpdate(TPactInfo entity) {
		pactInfoDao.saveOrUpdate(entity);
	}

	public void delete(String id) {
		pactInfoDao.delete(id);
	}

	public List<TPactInfo> findAllReviews() {
		return pactInfoDao.findAllReviews();
	}

	public  int  onLineReviewsYN(String id, String param,String noPassReson) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TPactInfo pact=pactInfoDao.get(id);
		if ("yes".equals(param)) {
			pact.setCheckStart('1');
			pact.setPactFlow("3");//复审
		}else if("no".equals(param)){
			pact.setCheckStart('3');//失败
			pact.setPactFlow("2");//仍然是初审
			pact.setNoPassReson(noPassReson);
		}
		pact.setCheckDate(sdf.format(new Date()));
		pact.setCheckTime(Date2String8.time2String());
		return pactInfoDao.onLineReviewsYN(pact);
		
	}

	public List<TPactInfo> findAllPZReviews() {
		return pactInfoDao.findAllPZReviews();
	}

	public int offLineReviewsYN(String id, String param,String noPassReson) {
		TPactInfo pact=pactInfoDao.get(id);
		if ("yes".equals(param)) {
			pact.setCheckStart('1');
			pact.setPactFlow("2");//进入复审
		}else if("no".equals(param)){
			pact.setCheckStart('3');//失败
			pact.setPactFlow("7");
			pact.setLastNoPassReson(noPassReson);
		}
		return pactInfoDao.onLineReviewsYN(pact);
	}

	public List<TPactInfo> findAllRepayment() {
		return pactInfoDao.findAllRepayment();
	}

	public int repaymentYN(String id, String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TPactInfo pact=pactInfoDao.get(id);
		if ("yes".equals(param)) {
			pact.setCheckStart('2');
			pact.setPactFlow("7");//成功进入复审
			TAutoRepay repay=autoRepayDao.get(id);
			repay.setBussStart('5');
			autoRepayDao.saveOrUpdate(repay);
		}else if ("no".equals(param)) {
			pact.setCheckStart('2');
			pact.setPactFlow("6");
			TAutoRepay repay=autoRepayDao.get(id);
			repay.setBussStart('2');
			autoRepayDao.saveOrUpdate(repay);
		}
		pact.setCheckDate(sdf.format(new Date()));
		pact.setCheckTime(Date2String8.time2String());
		return pactInfoDao.onLineReviewsYN(pact);
	}

	public List<TPactInfo> findByPactNum(String pactNum,CustomBaseInfo cust) {
		return pactInfoDao.findByPactNum(pactNum,cust);
	}

	public List<TPactInfo> findReimbursementToCustom() {
		return pactInfoDao.findReimbursementToCustom();
	}

	/**
	 * 回购到新合约中
	 */
	public void pactHG2(TPactInfo pactInfo,TRebuypactInfo rebuy) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TPactInfo entity=new TPactInfo();
		TProductInfo pro=productDao.getStrId(rebuy.getProductId());
		CustomBaseInfo custom=customBaseInfoDao.getByStrId(pactInfo.getCustId()).get(0);
		entity.setPactId(rebuy.getPactId());//合同编号
		entity.setPactDate(rebuy.getPactDate());//创建日期
		entity.setPactTime(rebuy.getPactTime());//时间
		entity.setCustId(custom.getId());//客户编号
		entity.setCustName(custom.getCustname());//客户姓名
		entity.setPaperType(custom.getPapertype().charAt(0));//客户证件类型
		entity.setPaperNum(custom.getPapernum());//客户证件编号
		entity.setPhoneNum(custom.getPhonenum());//客户联系电话
		entity.setInvestType('1');//支付方式
		entity.setProductId(pro.getProductId());//产品编号
		entity.setFuyouAccout(pro.getFuyouAccout());//融资方富有账号
		entity.setPurchaseDays(pro.getProductCycle());//客户购买产品周期
		entity.setRecruitmentDays(pro.getLostEff());
		entity.setAmount(rebuy.getAmount());//金额
		entity.setBackTime("0");//反息次数
		entity.setBackMoney(0.00);//反息金额
		entity.setPactFlow("1");//状态
		entity.setCheckStart('1');//状态
		entity.setPactEff(rebuy.getPactEff()); //合同生效日期--暂时默认当前日期
		entity.setCountEff(rebuy.getCountEff());//合同起息日期--暂时默认当前日期
		entity.setPactDue(rebuy.getPactDue());//合同到期日期--暂时默认当前日期
		entity.setContractNumber(rebuy.getContractNumber());//合同书编号
		entity.setRateFix(pro.getRateFix());//利率
		entity.setRebuyFlag("00");//回购标志-默认00-不回购
		entity.setBackFlag("02");//默认POS打款
		if (pro.getRecruitmentFix()!=null) {
			entity.setRecruitmentDate(pro.getRecruitmentFix());//募集期利率
		}
		pactInfoDao.save(entity);

		TAutoRepay auto=autoRepayDao.get(pactInfo.getPactId());//自动还款表信息
		
		//旧合约数据状态结束
		//全额回购
		if (rebuy.getAmount()==pactInfo.getAmount()+pactInfo.getBackMoney()) {
			pactInfo.setCheckStart('2');
			pactInfo.setPactFlow("10");//成功--合同结束
			auto.setBussStart('0');//表示还款成功了
		}else{
			pactInfo.setCheckStart('1');
			pactInfo.setPactFlow("6");//新的           回购资金 < 旧合约的本金+利息
			auto.setBussStart('0');//表示还款了一部分到客户富有了
		}
		pactInfoDao.saveOrUpdateByEntity(pactInfo);
		
		autoRepayDao.saveOrUpdate(auto);//更新数据
		
	}

	/**
	 * 查询状态为1复审中合同信息
	 */
	public List<TPactInfo> findAllToPactRecheck() {
		return pactInfoDao.findAllToPactRecheck();
	}

	/**
	 * 合同复审
	 */
	public void YNPactRecheck(TPactInfo pactInfo, String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc =new GregorianCalendar();
		TProductInfo product=productDao.get(pactInfo.getProductId());
		
		pactInfo.setCheckDate(sdf.format(new Date()));
		pactInfo.setCheckTime(Date2String8.time2String());
		
		if ("no".equals(param)) {
			pactInfo.setPactFlow("4");
			pactInfo.setCheckStart('3');
		}else if("yes".equals(param)){
			pactInfo.setPactFlow("5");
			pactInfo.setCheckStart('2');
			
			pactInfo.setPactEff(sdf.format(new Date()));//生效日期
			
			gc.setTime(new Date());
			gc.add(5,+1);
			gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
			pactInfo.setCountEff(sdf.format(gc.getTime()));//+1天--起息日期
			
			if (product.getSettleWay().equals("1")) {//天
				gc.add(2,+Integer.parseInt(product.getProductCycle()));//+月
				gc.add(5,+Integer.parseInt(product.getProductFcycle()));//再相加天数
				pactInfo.setPactDue(sdf.format(gc.getTime()));//到期日期
				int  ret[]  =  getDateLength( pactInfo.getCountEff(), pactInfo.getPactDue());
				pactInfo.setPurchaseDays(Integer.toString(ret[2]));
			}else if (product.getSettleWay().equals("2")) {//月
				gc.add(2,+Integer.parseInt(product.getProductCycle()));//+月
				gc.add(5,+Integer.parseInt(product.getProductFcycle()));//再相加天数
				pactInfo.setPactDue(sdf.format(gc.getTime()));//到期日期
				pactInfo.setPurchaseDays(product.getProductCycle());
			}
		}
		pactInfoDao.saveOrUpdate(pactInfo);
	}

	/*public static void main(String[] args) {
		//gc.add(1,-1)表示年份减一.
		//gc.add(2,-1)表示月份减一.
		//gc.add(3.-1)表示周减一.
		//gc.add(5,-1)表示天减一.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar gc =new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(5,+3);
		gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
		System.out.println(sdf.format(gc.getTime()));
		
		
	     String dateStart = "2013-02-19 09:29:58";
	     String dateStop = "2016-05-20 11:31:48";
	    
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	     Date d1 = null;
	     Date d2 = null;
	    
         try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
    
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
    
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
    
            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");
    
         } catch (Exception e) {
            e.printStackTrace();
         }
	    
         System.out.println();
         int  ret[]  =  getDateLength( "20050531 ", "20070101 "); 
         System.out.println(ret[0]  +  ": "  +  ret[1]  +  ": "  +  ret[2]); 
	}*/
	
	//**********时间计算差*******//
	static  int[]  getDateLength(String  fromDate, String  toDate)  { 
	      Calendar  c1  =  getCal(fromDate); 
	      Calendar  c2  =  getCal(toDate); 
	      int[]  p1  =  {  c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)  }; 
	      int[]  p2  =  {  c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)  }; 
	      return  new  int[]  {  p2[0]  -  p1[0], p2[0]  *  12  +  p2[1]  -  p1[0]  *  12  -  p1[1], (int)  ((c2.getTimeInMillis()  -  c1.getTimeInMillis())  /  (24  *  3600  *  1000))  }; 
	   } 
	   static  Calendar  getCal(String  date)  { 
	      Calendar  cal  =  Calendar.getInstance(); 
	      cal.clear(); 
	      cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))  -  1, Integer.parseInt(date.substring(6, 8))); 
	      return  cal; 
	   } 
	//**********时间计算差*******//
	   
	public PageBean<TPactInfo> getPageBean(int pageSize, int page,Map<String, Object> map) {
		///////////////////////////////////////////////////////////附带的条件
		String custPhone=(String) map.get("custPhone");
		String custPapernum=(String) map.get("custPapernum");
		String pactNum=(String) map.get("pactNum");
		int all=(Integer) map.get("all");
		
		String hql = "from TPactInfo where 1=1";	
        if ("".equals(custPhone)&&"".equals(custPapernum)&&"".equals(pactNum)||all==1) {
        	hql="from TPactInfo";
        }else{
        	if (!"".equals(custPhone))
        		hql=hql+" and phoneNum='"+custPhone+"'";
        	if (!"".equals(custPapernum)) 
        		hql=hql+" and paperNum='"+custPapernum+"'";
        	if (!"".equals(pactNum))
        		hql=hql+" and contractNumber='"+pactNum+"'";
        }
        
        ///////////////////////////////////////////////////////
        PageBean<TPactInfo> pageBean = new PageBean<TPactInfo>();
        
        int allRows = pactInfoDao.getAllRowCount(hql);
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
        
        List<TPactInfo> list = pactInfoDao.queryByPage(hql, offset, pageSize);
        for (TPactInfo tPactInfo : list) {
        	tPactInfo.setProductInfo(productDao.get(tPactInfo.getProductId()));
		}
        
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        return pageBean;
	}

	public PageBean<TPactInfo> findFailPact(int pageSize, int page) {
		///////////////////////////////////////////////////////////查询初审与复审失败合同信息
		String hql = "from TPactInfo where pactFlow in(2,4)";	
		///////////////////////////////////////////////////////
		PageBean<TPactInfo> pageBean = new PageBean<TPactInfo>();
		
		int allRows = pactInfoDao.getFailPactAllRowCount(hql);
		
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		
		int currentPage = pageBean.getCurPage(page);
		
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		
		List<TPactInfo> list = pactInfoDao.queryByPage(hql, offset, pageSize);
		for (TPactInfo pact : list) {
			pact.setProductInfo(productDao.get(pact.getProductId()));
			pact.setCustomBaseInfo(customBaseInfoDao.getByStrId(pact.getCustId()).get(0));
		}
		
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
}
