package com.hongxin.action.OA;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * 合约信息
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.fuiou.data.AppTransRspData;
import com.fuiou.data.CommonRspData;
import com.fuiou.data.PreAuthReqData;
import com.fuiou.data.PreAuthRspData;
import com.fuiou.data.TransferBmuReqData;
import com.fuiou.service.FuiouRspParseService;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TProductInfo;
import com.hongxin.entity.TRebuypactInfo;
import com.hongxin.entity.User;
import com.hongxin.service.AutoRepayService;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.FuiouService;
import com.hongxin.service.PactInfoService;
import com.hongxin.service.ProductService;
import com.hongxin.service.ReBuyPactService;
import com.hongxin.utils.AjaxUtils;
import com.hongxin.utils.Constants;
import com.hongxin.utils.Date2String8;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PactInfoAction extends ActionSupport{

	private static final long serialVersionUID = 8051545551005559302L;
	@Autowired
	PactInfoService pactInfoService;
	@Autowired
	CustomBaseInfoService customBaseInfoService;
	@Autowired
	ProductService productService;
	@Autowired
	CheckReceiptsService checkReceiptsService;
	@Autowired
	CustomAccountService customAccountService; 
	@Autowired
	AutoRepayService autoRepayService;
	@Autowired
	ReBuyPactService reBuyPactService;
	private TPactInfo pactInfo;
	private TRebuypactInfo rebuypactInfo;
	private CustomBaseInfo customBaseInfo;
	private int page;
	private PageBean<TPactInfo> pageBean;
	private PageBean<TAutoRepay> pageBeanAutoRepay ;
	private PageBean<CustomBaseInfo> pageBeanBycustom;
	
	@Override
	public String execute() throws Exception {
		//查询所有可以购买产品的客户
		Map<String, Object>map=new HashMap<String, Object>();
		if (customBaseInfo==null) {
			map.put("all", 1);
		}else {
			map.put("custName", customBaseInfo.getCustname());
			map.put("custPhone", customBaseInfo.getPhonenum());
			map.put("custPapernum", customBaseInfo.getPapernum());
			map.put("all", 0);
		}
		try {
			pageBeanBycustom=customBaseInfoService.findBuyCust(Constants.FEN_YE_SHU, page,map);
			pageBeanBycustom.setActionUrl("pactInfo.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 查询所有商品信息
	 * @return
	 */
	public String findAllProducts(){
		String id=(String) ServletActionContext.getRequest().getParameter("id");//用户id
		List<TProductInfo>products=new ArrayList<TProductInfo>();
		try {
			products=productService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustomBaseInfo cust=customBaseInfoService.getByStrId(id).get(0);
		ServletActionContext.getRequest().setAttribute("products", products);
		ServletActionContext.getRequest().setAttribute("cust", cust);
		ServletActionContext.getRequest().setAttribute("pactId", UUID.randomUUID().toString());
		return "addPactInfo";
	}

	/**
	 * 查询商品信息
	 */
	public void findProductInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String productId=(String) request.getParameter("productId");//产品Id
		TProductInfo pro=productService.get(productId);
		AjaxUtils.ajaxJSONResponse(pro);
	}
	
	/**
	 * 购买产品----回购
	 * @return
	 */
	public void addPactInfo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		HttpServletRequest request=ServletActionContext.getRequest();
		User user = (User) ActionContext.getContext().getSession().get("login_user");//获取登录用户信息
		
		String productId=request.getParameter("productId");
		double amount=Double.parseDouble(request.getParameter("amount"));
		String custId=request.getParameter("custId");
		String contractNumber=request.getParameter("contractNumber");
		String pactId=request.getParameter("pactId");
		String backFlag=request.getParameter("backFlag");
		String isRebuy=request.getParameter("isRebuy");//回购标志

		customBaseInfo=customBaseInfoService.getByStrId(custId).get(0);//customInfo
		TPactInfo pact=new TPactInfo();
		pact.setManagerNum(user.getEmployStaff().getEmployeeId());
		pact.setManagerName(user.getEmployStaff().getFullName());
		//回购表信息
		if ("yes".equals(isRebuy)) {
			TPactInfo oldPact=pactInfoService.get(pactId);
			//TAutoRepay autoRepay=new TAutoRepay();
			//autoRepay=autoRepayService.get(pactId);
			synchronized(PactInfoAction.class){
			try {
				
				//autoRepay.setRebuyFlag("01");
				//autoRepay.setBussStart('3');//回购成功
				//旧合同
				TProductInfo productInfo=productService.get(oldPact.getProductId());//productInfo
				oldPact.setRebuyFlag("01");
				//oldPact.setPactFlow("5");
				
				//回购信息
				TRebuypactInfo rebuyPact=new TRebuypactInfo();
				rebuyPact.setPactId(pactId);
				rebuyPact.setPactDate(sdf.format(new Date()));
				rebuyPact.setPactTime(Date2String8.time2String());
				rebuyPact.setCustId(custId);
				rebuyPact.setCustName(customBaseInfo.getCustname());
				rebuyPact.setPaperType(customBaseInfo.getPapertype().charAt(0));
				rebuyPact.setPaperNum(customBaseInfo.getPapernum());
				rebuyPact.setInvestType('1');//支付方式
				rebuyPact.setProductId(productInfo.getProductId());//产品编号
				rebuyPact.setFuyouAccout(productInfo.getFuyouAccout());//融资方富有账号
				rebuyPact.setPurchaseDays(productInfo.getProductCycle());//客户购买产品周期
				rebuyPact.setAmount(amount);//金额
				rebuyPact.setManagerNum(user.getEmployStaff().getEmployeeId());
				rebuyPact.setManagerName(user.getEmployStaff().getFullName());
				rebuyPact.setPactEff(sdf.format(new Date()));
				rebuyPact.setCountEff(sdf.format(new Date()));
				rebuyPact.setPactDue(sdf.format(new Date()));
				rebuyPact.setContractNumber(oldPact.getContractNumber());
				rebuyPact.setRateFix(productInfo.getRateFix());//利率
				if (productInfo.getRecruitmentFix()!=null) {
					rebuyPact.setRecruitmentDate(productInfo.getRecruitmentFix());//募集期利率
				}
				//autoRepayService.saveOrUpdate(autoRepay);
				reBuyPactService.saveNewOld(rebuyPact,oldPact);
				AjaxUtils.ajaxResponse("回购成功");
			} catch (Exception e) {
				//autoRepay.setBussStart('4');//回购失败
				oldPact.setRebuyFlag("00");
				oldPact.setPactFlow("6");
				try {
					pactInfoService.saveOrUpdate(oldPact);
					//autoRepayService.saveOrUpdate(autoRepay);
				} catch (Exception e2) {
					AjaxUtils.ajaxResponse("系统繁忙");
				}
				AjaxUtils.ajaxResponse("系统繁忙");
			}
			}
		}else{
			//购买产品
			TProductInfo productInfo=productService.get(productId);//productInfo
			pact.setPactId(pactId);//合同编号
			pact.setPactDate(sdf.format(new java.util.Date()));//创建日期
			pact.setPactTime(Date2String8.time2String());//时间
			pact.setCustId(customBaseInfo.getId());//客户编号
			pact.setCustName(customBaseInfo.getCustname());//客户姓名
			pact.setPaperType(customBaseInfo.getPapertype().charAt(0));//客户证件类型
			pact.setPaperNum(customBaseInfo.getPapernum());//客户证件编号
			pact.setPhoneNum(customBaseInfo.getPhonenum());//客户联系电话
			pact.setInvestType('1');//支付方式
			pact.setProductId(productInfo.getProductId());//产品编号
			pact.setFuyouAccout(productInfo.getFuyouAccout());//融资方富有账号
			pact.setPurchaseDays(productInfo.getProductCycle());//客户购买产品周期
			pact.setRecruitmentDays(productInfo.getLostEff());
			pact.setAmount(amount);//金额
			pact.setBackTime("0");//反息次数
			pact.setBackMoney(0.00);//反息金额
			pact.setPactFlow("1");//状态
			pact.setCheckStart('1');//状态
			pact.setBackFlag(backFlag);
			pact.setPactEff(sdf.format(new java.util.Date())); //合同生效日期--暂时默认当前日期
			pact.setCountEff(sdf.format(new java.util.Date()));//合同起息日期--暂时默认当前日期
			pact.setPactDue(sdf.format(new java.util.Date()));//合同到期日期--暂时默认当前日期
			pact.setContractNumber(contractNumber);//合同书编号
			pact.setRateFix(productInfo.getRateFix());//利率
			pact.setRebuyFlag("00");//回购标志-默认00-不回购
			pact.setBackFlag("02");//默认POS打款
			if (productInfo.getRecruitmentFix()!=null) {
				pact.setRecruitmentDate(productInfo.getRecruitmentFix());//募集期利率
			}
			try {
				pactInfoService.save(pact);
				AjaxUtils.ajaxResponse("合约购买成功!\n客户:"+customBaseInfo.getCustname()+"\n产品:"+productInfo.getProductName());
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtils.ajaxResponse("操作不合法,请稍后重试!");
			}
		}
	}
	
	

	//线下凭证上传后下一步处理
	public String offLineReviewsUpload(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String custIDS=(String) request.getParameter("param");
		//处理
		TPactInfo pact=pactInfoService.get(custIDS);
		pact.setInvestType('2');
		pact.setPactFlow("7");
		pact.setCheckStart('2');//图片上传成功
		pactInfoService.saveOrUpdateByEntity(pact);
		request.setAttribute("flag","图片凭证信息上传成功");
		List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		ServletActionContext.getRequest().setAttribute("away","offline");
		return "FirstCheck";
	}
	
	/**
	 * 图片上传跳转
	 * 
	 */
	public String redirectURL(){
		String pactId=(String) ServletActionContext.getRequest().getParameter("pactId");
		ServletActionContext.getRequest().setAttribute("url", "pact/pactInfo!offLineReviewsUpload.action");//上传图片后跳转的地址
		ServletActionContext.getRequest().setAttribute("custIDS", pactId);//上传图片后跳转的地址
		ServletActionContext.getRequest().getSession().setAttribute("picType", "3");
		return "redirectPicUpload";
	}
	
	
	/**
	 * 初审信息列表--全部
	 * @return
	 */
	public String firstCheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String pactNum=request.getParameter("pactNum");//合同号
		Map<String, Object>map=new HashMap<String, Object>();
		if (customBaseInfo==null) {
			map.put("all", 1);
		}else {
			map.put("pactNum", pactNum);//合同号
			map.put("custPhone", customBaseInfo.getPhonenum());//客户手机号
			map.put("custPapernum", customBaseInfo.getPapernum());//客户身份号
			request.setAttribute("pactNum", pactNum);
		map.put("all", 0);
		}
		try {
			//List<TPactInfo>pactInfos=pactInfoService.findByPactNum(pactNum,customBaseInfo);
			//查询结果及分页
			pageBean=pactInfoService.getFirstCheckList(Constants.FEN_YE_SHU, page,map);
			pageBean.setActionUrl("pactInfo!firstCheck.action");
		} catch (Exception e) {
			request.setAttribute("flag", "系統繁忙,稍后再试!");
		}
		
		
		List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		ServletActionContext.getRequest().setAttribute("away","firstCheck");
		return "FirstCheck";
	}
	/**
	 * 线上审核通过----改为初审
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String onLineReviewsYN(){
		HttpServletRequest request=ServletActionContext.getRequest();
		synchronized(PactInfoAction.class){
			String param=request.getParameter("param");
			String id=request.getParameter("id");
			String noPassReson=request.getParameter("noPassReson");
			pactInfo=pactInfoService.get(id);
			if (!"1".equals(pactInfo.getPactFlow())) {
				List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
				for (TPactInfo tPactInfo : pactInfos) {
					tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
					tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
				}
				ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
				ServletActionContext.getRequest().setAttribute("away","firstCheck");
				ServletActionContext.getRequest().setAttribute("flag", "合约已经被审核");
				return "FirstCheck";
			}
			int a=pactInfoService.onLineReviewsYN(id,param,noPassReson);
			if (a==1) {
				ServletActionContext.getRequest().setAttribute("flag", "合约审核成功");
			}else if (a==3) {
				ServletActionContext.getRequest().setAttribute("flag", "合约已经被审核");
			}else{
				ServletActionContext.getRequest().setAttribute("flag", "异常,请稍后再试");
			}
			//再次查询所有的合同线上审核信息
			List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
			for (TPactInfo tPactInfo : pactInfos) {
				tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
				tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
			}
			ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
			ServletActionContext.getRequest().setAttribute("away","firstCheck");
			return "FirstCheck";
		}
	}
	
	/**
	 * 查询all合同复审
	 */
	public String findAllToPactRecheck(){
		//查询需要上层领导审核信息
		List<TPactInfo>pactInfos=pactInfoService.findAllToPactRecheck();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		return "toPactRecheck";
	}
	
	/**
	 * 合同复审通过与不通过
	 */
	
	public String pactRecheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		synchronized(PactInfoAction.class){
			String pactId=request.getParameter("pactId");
			String param=request.getParameter("param");
			String noPassReson=request.getParameter("noPassReson");
			pactInfo=pactInfoService.get(pactId);
			if (!"3".equals(pactInfo.getPactFlow())) {
				request.setAttribute("flag", "该合同已经被审核");
				//再次查询需要合同复审审核信息
				return toPactRecheck();
			}
			pactInfo.setLastNoPassReson(noPassReson);//不通过理由
			
			//CustomAccount custaccount=customAccountService.getStrId(pactInfo.getCustId());
			
			/*//返回参数
			CommonRspData commonRspData=new CommonRspData();
			//欲授权编号
			try {
				PreAuthRspData result=getPreAuth(timeId,custaccount.getAccountBank(),Constants.IN_CUST_NO,pactInfo.getAmount());
				if (result==null) {
					request.setAttribute("flag", "请求富友出错");
				}
				TransferBmuReqData transferBmuReqData=new TransferBmuReqData();
				transferBmuReqData.setMchnt_cd(Constants.MCHNT_CD);
				transferBmuReqData.setMchnt_txn_ssn(TimeId.generateSequenceNo());
				transferBmuReqData.setOut_cust_no(pactInfo.getPhoneNum());
				transferBmuReqData.setIn_cust_no(Constants.IN_CUST_NO);
				transferBmuReqData.setAmt(Double.toString(pactInfo.getAmount()*100));
				transferBmuReqData.setContract_no(result.getContract_no());
				commonRspData=FuiouService.transferBmu(transferBmuReqData);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//富友返回码为0000
			if ("0000".equals(commonRspData.getResp_code())) {}*/
			
			try {
				pactInfoService.PactRecheck(pactInfo,param);
			} catch (Exception e) {
				request.setAttribute("flag", "合同复审失败");
				e.printStackTrace();
			}
	
		//再次查询需要合同复审审核信息
		List<TPactInfo>pactInfos=pactInfoService.findAllToPactRecheck();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		return toPactRecheck();
	}
	}
	
	/**
	 * 查询所有到款确定合同
	 */
	public String findAllgetMoney(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Map<String, Object>map=new HashMap<String, Object>();
		try {
			//List<TPactInfo>pactInfos=pactInfoService.findByPactNum(pactNum,customBaseInfo);
			//查询结果及分页
			pageBean=pactInfoService.getMoneyPageBean(Constants.FEN_YE_SHU, page,map);
			pageBean.setActionUrl("pactInfo!findAllgetMoney.action");
		} catch (Exception e) {
			request.setAttribute("flag", "系統繁忙,稍后再试!");
		}
		return "AllgetMoneyList";
	}
	
	/**
	 * 到款确认
	 * @return
	 */
	public String YNpactRecheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		synchronized(PactInfoAction.class){
			String pactId=request.getParameter("pactId");
			String param=request.getParameter("param");
			String noPassReson=request.getParameter("noPassReson");
			pactInfo=pactInfoService.get(pactId);
			if (!"14".equals(pactInfo.getPactFlow())) {
				request.setAttribute("flag", "该合同已经被审核");
				//再次查询需要合同复审审核信息
				return pactRecheck();
			}
			pactInfo.setLastNoPassReson(noPassReson);//不通过理由
			try {
				pactInfoService.YNPactRecheck(pactInfo,param);
				if ("yes".equals(param)) {
					//InsertRepay(pactInfoService.get(pactId));//模拟熊健程序
					//request.setAttribute("flag", "转账成功,富友返回码:"+commonRspData.getResp_code());
				}
			} catch (Exception e) {
				request.setAttribute("flag", "到款确认失败,稍后再试");
				e.printStackTrace();
			}
			request.setAttribute("flag", "到款确认成功");
			return toPactRecheck();
		}
	}
	
	//再次查询需要合同复审审核信息
	private String toPactRecheck(){
		List<TPactInfo>pactInfos=pactInfoService.findAllToPactRecheck();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		return "toPactRecheck";
	}
	/**
	 * 合同查询
	 */
	public String findPactNum(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String pactNum=request.getParameter("pactNum");//合同号
		Map<String, Object>map=new HashMap<String, Object>();
		if (customBaseInfo==null) {
			map.put("all", 1);
		}else {
			map.put("pactNum", pactNum);//合同号
			map.put("custPhone", customBaseInfo.getPhonenum());//客户手机号
			map.put("custPapernum", customBaseInfo.getPapernum());//客户身份号
			request.setAttribute("pactNum", pactNum);
		map.put("all", 0);
		}
		try {
			//List<TPactInfo>pactInfos=pactInfoService.findByPactNum(pactNum,customBaseInfo);
			//查询结果及分页
			pageBean=pactInfoService.getPageBean(Constants.FEN_YE_SHU, page,map);
			pageBean.setActionUrl("pactInfo!findPactNum.action");
		} catch (Exception e) {
			request.setAttribute("flag", "系統繁忙,稍后再试!");
		}
		return "findPactInfoList";
	}
	
	/**
	 * 失败合同修改
	 * @return
	 */
	public String updatePactInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		TProductInfo product=productService.get(pactInfo.getProductId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TPactInfo pact=pactInfoService.get(pactInfo.getPactId());
		pact.setContractNumber(pactInfo.getContractNumber());//合同书标号
		pact.setAmount(pactInfo.getAmount());
		pact.setProductId(pactInfo.getProductId());
		pact.setFuyouAccout(product.getFuyouAccout());//融资方富有账号
		pact.setPurchaseDays(product.getProductCycle());//客户购买产品周期
		pact.setRecruitmentDays(product.getLostEff());
		pact.setBackTime("0");//反息次数
		pact.setBackMoney(0.00);//反息金额
		pact.setPactFlow("1");//状态
		pact.setCheckStart('1');//状态
		pact.setPactEff(sdf.format(new java.util.Date())); //合同生效日期--暂时默认当前日期
		pact.setCountEff(sdf.format(new java.util.Date()));//合同起息日期--暂时默认当前日期
		pact.setPactDue(sdf.format(new java.util.Date()));//合同到期日期--暂时默认当前日期
		pact.setRateFix(product.getRateFix());//利率
		pact.setRebuyFlag("00");//回购标志-默认00-不回购
		pact.setBackFlag("02");//默认POS打款
		try {
			pactInfoService.saveOrUpdateByEntity(pact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pactInfo=pactInfoService.get(pact.getPactId());
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType(pactInfo.getPactId(),"2");
		
		List<TProductInfo>products=new ArrayList<TProductInfo>();
		products=productService.findAll();
		
		pactInfo.setReceipts(receipts);
		request.setAttribute("products", products);
		request.setAttribute("flag", "合同编号修改成功");
		return "updateFailPactInfo";
	}
	
	
	/////////////////////////////////////////////////////////////还款管理/////////////////////////////////////////////////////////////////
	
	
	
	
///////////////////////////////////////////回购产品//////////////////////////////////////////////////////////////
	/**
	 * 回购-----1阶段的回购，，，输入金额 上传凭证
	 */
	public String pactHG(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		pactInfo=pactInfoService.get(id);
		CustomBaseInfo cust=customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0);
		ServletActionContext.getRequest().setAttribute("cust", cust);
		ServletActionContext.getRequest().setAttribute("pactInfo", pactInfo);//回购标志
		return "HGPactInfo";
	}
	
	
	public String pactHGForPaymentNotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		pactInfo=pactInfoService.get(id);
		CustomBaseInfo cust=customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0);
		ServletActionContext.getRequest().setAttribute("cust", cust);
		ServletActionContext.getRequest().setAttribute("pactInfo", pactInfo);//回购标志
		return "pactHGForPaymentNotice";
	}
	
///////////////////////////////////////////回购产品//////////////////////////////////////////////////////////////

	/**
	 * 查询还款到客户账(4-2-----5-2)
	 */
	public String findReimbursementToCustom(){
		List<TPactInfo> pactInfos=pactInfoService.findReimbursementToCustom();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		return "findReimbursementToCustom";
	}
	/**
	 * 合同失败后修正维护中心
	 */
	public String findFailPact(){
		//查询失败了的合同信息
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			//List<TPactInfo>pactInfos=pactInfoService.findByPactNum(pactNum,customBaseInfo);
			//查询结果及分页
			pageBean=pactInfoService.findFailPact(Constants.FEN_YE_SHU, page);
			pageBean.setActionUrl("pactInfo!findFailPact.action");
		} catch (Exception e) {
			request.setAttribute("flag", "维护中...!");
			return "findFailPact";
		}
		return "findFailPact";
	}
	

	
	/**
	 * 合同明细查询
	 * @return
	 */
	public String getPactInfos(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String no=request.getParameter("no");
		String id=request.getParameter("id");
		String redirect=request.getParameter("redirect");
		//get Pact Infomation
		pactInfo=pactInfoService.get(id);
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType(id,no);
		pactInfo.setReceipts(receipts);
		request.setAttribute("pactInfo",pactInfo);
		if ("01".equals(pactInfo.getRebuyFlag())) {
			TRebuypactInfo rebuyPact = reBuyPactService.get(pactInfo.getPactId());
			request.setAttribute("rebuyPact", rebuyPact);
		}
		//select Redirect URL
		if ("firstCheck".equals(redirect)) {//合同初审
			return "getPactInfo4online";
		}else if ("lastCheck".equals(redirect)) {//合同复审
			return "pactRecheck";
		}else if ("failPact".equals(redirect)) {//
			List<TProductInfo>products=productService.findAll();
			request.setAttribute("products", products);
			return "updateFailPactInfo";
		}else if ("getMoney".equals(redirect)) {//
			List<TProductInfo>products=productService.findAll();
			request.setAttribute("products", products);
			return "getMoney";
		}else{
			List<TProductInfo>products=productService.findAll();
			request.setAttribute("products", products);
			return "findPactInfo";
		}
	}
	
	
	
	/**
	 * 合同作废
	 * @return
	 */
	public String pactZF(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		TPactInfo pact=pactInfoService.get(id);
		if (!pact.getPactFlow().equals("1")||pact.getPactFlow().equals("2")||pact.getPactFlow().equals("3")||pact.getPactFlow().equals("4")) {
			List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
			for (TPactInfo tPactInfo : pactInfos) {
				tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
				tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
			}
			ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
			ServletActionContext.getRequest().setAttribute("flag", "合同作废操作成功");
			ServletActionContext.getRequest().setAttribute("away","online");
			return "FirstCheck";
		}
		pact.setPactFlow("11");
		pact.setCheckStart('2');
		pactInfoService.saveOrUpdateByEntity(pact);
		List<TPactInfo>pactInfos=pactInfoService.findAllReviews();
		for (TPactInfo tPactInfo : pactInfos) {
			tPactInfo.setProductInfo(productService.getStrId(tPactInfo.getProductId()));
			tPactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(tPactInfo.getCustId()).get(0));
		}
		ServletActionContext.getRequest().setAttribute("pactInfos", pactInfos);
		ServletActionContext.getRequest().setAttribute("flag", "合同作废操作成功");
		ServletActionContext.getRequest().setAttribute("away","online");
		return "FirstCheck";
	}

	/**
	 * 查询checkReceip凭证照片等信息
	 */
	public String findCheckReceipt(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String pactId=request.getParameter("pactId");
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType(pactId, null);
		request.setAttribute("receipts", receipts);
		request.setAttribute("pactId", pactId);
		return "findCheckReceipt";
	}
	
	/**
	 * 模拟熊健写的后台程序
	 * @param tPactInfo
	 * @return
	 */
	public int InsertRepay(TPactInfo tPactInfo){
		int a=1;
		TProductInfo pro=productService.get(tPactInfo.getProductId());
		CustomAccount account=customAccountService.getStrId(tPactInfo.getCustId());
		TAutoRepay repay=new TAutoRepay();
		repay.setPactId(tPactInfo.getPactId());
		repay.setProductId(tPactInfo.getProductId());
		repay.setWorkDate(Date2String8.date2String(new Date()));
		repay.setProductName(pro.getProductName());
		repay.setTakeEff(pro.getTakeEff());
		repay.setLostEff(pro.getLostEff());
		repay.setRepayAccout(account.getAccountFuyou());
		repay.setPayerAccout(pro.getFuyouAccout());
		repay.setCapital(tPactInfo.getAmount());
		repay.setAmount(tPactInfo.getBackMoney());
		repay.setBussDate(Date2String8.date2String(new Date()));
		repay.setBussTime(Date2String8.time2String());
		repay.setBussStart('0');
		repay.setState("01");
		repay.setBackTime(tPactInfo.getBackTime());
		repay.setRebuyFlag(tPactInfo.getRebuyFlag());
		repay.setRebuyPactid(tPactInfo.getRebuyPactid());
		String saveStr=autoRepayService.save(repay);
		if (saveStr==null) {
			a=0;
		}
		return a;
	}
	
	////////////////////////////get----set//////////////////////
	public TPactInfo getPactInfo() {
		return pactInfo;
	}
	
	public void setPactInfo(TPactInfo pactInfo1) {
		pactInfo = pactInfo1;
	}

	public TRebuypactInfo getRebuypactInfo() {
		return rebuypactInfo;
	}

	public void setRebuypactInfo(TRebuypactInfo rebuypactInfo) {
		this.rebuypactInfo = rebuypactInfo;
	}

	public CustomBaseInfo getCustomBaseInfo() {
		return customBaseInfo;
	}

	public void setCustomBaseInfo(CustomBaseInfo customBaseInfo) {
		this.customBaseInfo = customBaseInfo;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean<TPactInfo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<TPactInfo> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<CustomBaseInfo> getPageBeanBycustom() {
		return pageBeanBycustom;
	}

	public void setPageBeanBycustom(PageBean<CustomBaseInfo> pageBeanBycustom) {
		this.pageBeanBycustom = pageBeanBycustom;
	}

	public PageBean<TAutoRepay> getPageBeanAutoRepay() {
		return pageBeanAutoRepay;
	}

	public void setPageBeanAutoRepay(PageBean<TAutoRepay> pageBeanAutoRepay) {
		this.pageBeanAutoRepay = pageBeanAutoRepay;
	}
	
}
