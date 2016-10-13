package com.hongxin.action.OA;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fuiou.data.CommonRspData;
import com.fuiou.data.ModifyMobileReqData;
import com.fuiou.data.ModifyMobileRsqData;
import com.fuiou.data.RegReqData;
import com.fuiou.service.FuiouRspParseService;
import com.fuiou.util.SecurityUtils;
import com.hongxin.entity.CheckInfo;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.CustomAccount;
import com.hongxin.entity.CustomBaseInfo;
import com.hongxin.entity.CustomStatus;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAreaCode;
import com.hongxin.entity.TBankCode;
import com.hongxin.entity.TErrCode;
import com.hongxin.entity.TFuyouTran;
import com.hongxin.service.AreaCodeService;
import com.hongxin.service.BankCodeService;
import com.hongxin.service.CheckInfoService;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.CustomStatusService;
import com.hongxin.service.ErrorCodeService;
import com.hongxin.service.FuiouService;
import com.hongxin.utils.AjaxUtils;
import com.hongxin.utils.Constants;
import com.hongxin.utils.PageView;
import com.hongxin.utils.TimeId;
import com.opensymphony.xwork2.ActionSupport;


public class CustomAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	////////////////注入信息////////////////////
	@Autowired
	private CustomBaseInfoService customBaseInfoService;
	@Autowired
	private CheckInfoService checkInfoService;
	@Autowired
	private CheckReceiptsService checkReceiptsService;
	@Autowired
	private CustomAccountService customAccountService;
	@Autowired
	private CustomStatusService customStatusService;
	@Autowired
	private AreaCodeService areaCodeService;
	@Autowired
	private BankCodeService bankCodeService;
	@Autowired
	private ErrorCodeService errorCodeService;
	//单个用户信息  录入--更新操作注入
	private CustomBaseInfo customBaseInfo;
	private CustomAccount customAccount;
	private CustomStatus customStatus;
	private CheckInfo checkInfo;
	private CheckReceipts checkReceipts;
	//集合信息 ---全部信息--全部初审  复审信息
	private List<CustomBaseInfo> customBaseInfos;
	private PageView<CustomBaseInfo> pageView=new PageView<CustomBaseInfo>();
	private PageBean<CustomBaseInfo> pageBean;
	private int page;
	private TAreaCode areaCode;
	private TBankCode bankCode;
	private List<TBankCode>banks;
	private List<TAreaCode>areas;
	private String msg;
	private String phoneNum;
	private String custName;
	private String paperNum;
	private String redirect;
	private String id;
	private String attr;
	////////////////方法程序体////////////////////
	/**
	 * 程序主方法
	 * 客户信息新增
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		/**
		 * 验证需求-前台js提供验证
		 * -->>赋值
		 */
		customBaseInfo.setCustname(request.getParameter("custname"));
		customBaseInfo.setPhonenum(request.getParameter("phonenum"));
		customBaseInfo.setPapernum(request.getParameter("papernum"));
		customBaseInfo.setManagerId(request.getParameter("managerId"));
		customBaseInfo.setEmail(request.getParameter("email"));
		customBaseInfo.setPostAddr(request.getParameter("postAddr"));
		//通过手机号码或者身份证查找该用户信息是否存在
		int a=customBaseInfoService.getByPhonenumOrPapernum(customBaseInfo.getPhonenum(),customBaseInfo.getPapernum());
		if (a>0) {
			request.setAttribute("flag", "用户信息已经存在,请勿再次录入");
			return INPUT;//已经录入，停止
		}
		//保存刚录取的用户信息置session方便事物
		request.getSession().setAttribute("toAddCustomBaseInfo", customBaseInfo);
		areas=areaCodeService.getProvinceList(null);//所有省份信息
		banks=bankCodeService.findAll();//所有银行代码信息
		return SUCCESS;
	}
	
	/**
	 * 查询全部用户信息
	 * @return
	 */
	public String findAll(){
		Map<String, Object>map=new HashMap<String, Object>();
		if (customBaseInfo==null) {
			map.put("all", 1);
			attr="0";
		}else {
			map.put("attr", attr);
			map.put("custName", customBaseInfo.getCustname());
			map.put("custPhone", customBaseInfo.getPhonenum());
			map.put("custPapernum", customBaseInfo.getPapernum());
			map.put("all", 0);
		}
		pageBean=customBaseInfoService.getPageBean(5, page,map);
		pageBean.setActionUrl("addCustomInfo!findAll.action");
		for (CustomBaseInfo customBaseInfo : pageBean.getList()) {
			customBaseInfo.setCheckInfos(checkInfoService.getByCustomId(customBaseInfo.getId()));
			customBaseInfo.setCheckReceipts(checkReceiptsService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomStatus(customStatusService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomAccount(customAccountService.getStrId(customBaseInfo.getId()));
		}
		return "findAll";
	}
	
	/**
	 * 单个用户信息查询
	 * @return
	 */
	public String getCustomInfo(){
		List<CustomBaseInfo>  customs=customBaseInfoService.getByStrId(id);
		if (customs==null) {
			msg = "系统错误";
		}else{
			customBaseInfo=customs.get(0);
			customBaseInfo.setCheckReceipts(checkReceiptsService.getByStrIdType(customBaseInfo.getId(),"1"));
		}
		//辨别 ---》 1.修改客户信息 2.初审客户信息 3.复审客户信息
		if ("FirstCheckYN".equals(redirect)) {//初审
			redirect = "FirstCheckYN";
		}else if("LastCheckYN".equals(redirect)){//复审
			redirect = "LastCheckYN";
		}else if("updateCustomInfo".equals(redirect)){
			return "updateCustomInfo";
		}else if("findNeedUpdateSuccessCustom".equals(redirect)){
			return "updateSuccessCustom";
		}
		return "getCustomInfo";
	}
	
	/**
	 * 客户信息维护
	 * 查询需要更改的客户信息
	 */
	
	public String findFailInfo(){
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findFailInfo();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findFailInfoList";
	}
	
	/**
	 * 客户信息维护
	 * 修改用户信息
	 * @return
	 */
	public String updateCustomInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		customBaseInfo.setCustname(request.getParameter("custname"));
		customBaseInfo.setPhonenum(request.getParameter("phonenum"));
		customBaseInfo.setPapernum(request.getParameter("papernum"));
		customBaseInfo.setManagerId(request.getParameter("managerId"));
		customBaseInfo.setEmail(request.getParameter("email"));
		customBaseInfo.setPostAddr(request.getParameter("postAddr"));
		
		//客户状态信息
		customStatus=customStatusService.getByStrId(customBaseInfo.getId());
		customStatus.setCustStart('1');
		customStatus.setCustCheckStart('1');
		//通过手机号码或者身份证查找该用户信息是否存在
		
		try {
			customStatusService.saveOrUpdate(customStatus);
			customBaseInfoService.saveOrUpdate(customBaseInfo);;
		} catch (Exception e) {
			msg = "系统故障,稍后再试";
		}
		
		//保存刚录取的用户信息置session方便事物
		areas=areaCodeService.getProvinceList(null);//所有省份信息
		banks=bankCodeService.findAll();//所有银行代码信息
		customAccount=customAccountService.getStrId(customBaseInfo.getId());
		List<TAreaCode>cityList=areaCodeService.getProvinceList(customAccount.getProvinceCode());
		request.setAttribute("cityList", cityList);
		return "updateCustomAccount";
	}
	
	/**
	 * 已签约客户信息维护
	 * 对已签约成功客户信息进行修改
	 */
	public String updateSuccessCustomInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		/**
		 * 验证需求-前台js提供验证
		 * -->>赋值
		 */
		customBaseInfo.setCustname(request.getParameter("custname"));
		customBaseInfo.setPhonenum(request.getParameter("phonenum"));
		customBaseInfo.setPapernum(request.getParameter("papernum"));
		customBaseInfo.setManagerId(request.getParameter("managerId"));
		customBaseInfo.setEmail(request.getParameter("email"));
		customBaseInfo.setPostAddr(request.getParameter("postAddr"));
		
		//客户状态信息
		customStatus=customStatusService.getByStrId(customBaseInfo.getId());
		customStatus.setCustStart('5');
		customStatus.setCustCheckStart('1');
		//通过手机号码或者身份证查找该用户信息是否存在
		
		try {
			customStatusService.saveOrUpdate(customStatus);
			customBaseInfoService.saveOrUpdate(customBaseInfo);;
		} catch (Exception e) {
			msg = "系统故障,稍后再试";
		}
		//保存刚录取的用户信息置session方便事物
		areas=areaCodeService.getProvinceList(null);//所有省份信息
		banks=bankCodeService.findAll();//所有银行代码信息
		customAccount=customAccountService.getStrId(customBaseInfo.getId());
		List<TAreaCode>cityList=areaCodeService.getProvinceList(customAccount.getProvinceCode());
		request.setAttribute("cityList", cityList);
		return "updateSuccessCustomAccount";
	}
	
	
	/**
	 * 客户信息维护
	 * 修改账户信息
	 * @return
	 */
	public String updateCustomAccount(){
		CustomAccount customAccountOld=customAccountService.getStrId(customAccount.getCustomId());
		customAccountOld.setProvinceCode(customAccount.getProvinceCode());
		customAccountOld.setAreaCode(customAccount.getAreaCode());
		customAccountOld.setPayBank(customAccount.getPayBank());
		customAccountOld.setPayBankName(customAccount.getPayBankName());
		customAccountOld.setAccountBank(customAccount.getAccountBank());
		try {
			customAccountService.saveOrUpdate(customAccountOld);
		} catch (Exception e) {
			msg="系统异常,保存失败";
		}
		return findReceiptsInfo();//查询客户信息图片
	}
	
	/**
	 * 查询身份证图片信息
	 */
	public String findReceiptsInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String customId=request.getParameter("customId");
		if (customId==null) {
			customId=customAccount.getCustomId();
		}
		List<CheckReceipts>receipts=checkReceiptsService.getByStrIdType(customId, null);
		request.setAttribute("receipts", receipts);
		return "findReceiptsInfo";
	}
	
	/**
	 * 签约客户信息维护
	 * 签约客户信息修改  账户信息修改
	 */
	public String updateSuccessCustomAccount(){
		CustomAccount customAccountOld=customAccountService.getStrId(customAccount.getCustomId());
		customAccountOld.setProvinceCode(customAccount.getProvinceCode());
		customAccountOld.setAreaCode(customAccount.getAreaCode());
		customAccountOld.setPayBank(customAccount.getPayBank());
		customAccountOld.setPayBankName(customAccount.getPayBankName());
		customAccountOld.setAccountBank(customAccount.getAccountBank());
		try {
			customAccountService.saveOrUpdate(customAccountOld);
		} catch (Exception e) {
			msg="系统异常,保存失败";
		}
		return findNeedUpdateSuccessCustom();
	}
	
	
	/**
	 * 查询全部初审客户信息
	 * @return
	 */
	public String findAllFirstCheck(){
		
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("custName", custName==null?"":custName);
		map.put("phoneNum",phoneNum==null?"":phoneNum );//客户手机号
		map.put("paperNum",paperNum==null?"":paperNum );//客户身份号
		
		///////////----分页查询参数----///////////
		
		pageBean=customBaseInfoService.findAllFirstCheck(Constants.FEN_YE_SHU, page,map);
		pageBean.setActionUrl("addCustomInfo!findAllFirstCheck.action");
		return "findAllFirstCheck";
	}
	
	/**
	 * 初审通过与不通过操作
	 * @return
	 */
	public String FirstCheckYN(){
		int code=1;
		CustomBaseInfo customBaseInfo=customBaseInfoService.getByStrId(id).get(0);
		if (customBaseInfo==null) {
			msg = "非法参数";
			return findAllFirstCheck();
		}
		String param=ServletActionContext.getRequest().getParameter("param");
		if ("yes".equals(param)) 
			code=2;
		else if("no".equals(param))
			code=3;
		else 
			msg = "非法参数";
		
		int a=checkInfoService.auditYN(id,code);
		
		if (a==1)
			msg = "客户:"+customBaseInfo.getCustname()+"操作成功";
		else
			msg = "系统故障,稍后再试";
		
		//再次查询所有未初审通过信息
		return findAllFirstCheck();
	}
	
	/**
	 * 查询复审信息
	 * @return
	 */
	public String findAllLastCheck(){
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("custName", custName==null?"":custName);
		map.put("phoneNum",phoneNum==null?"":phoneNum );//客户手机号
		map.put("paperNum",paperNum==null?"":paperNum );//客户身份号
		
		///////////----分页查询参数----///////////
		pageBean=customBaseInfoService.findAllLastCheck(Constants.FEN_YE_SHU, page,map);
		pageBean.setActionUrl("addCustomInfo!findAllLastCheck.action");
		return "findAllLastCheck";
	}
	
	/**
	 * 已签约客户信息待修改查询LIST
	 * 有权限控制
	 * @return
	 */
	public String findNeedUpdateSuccessCustom(){
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("custName", custName==null?"":custName);
		map.put("phoneNum",phoneNum==null?"":phoneNum );//客户手机号
		map.put("paperNum",paperNum==null?"":paperNum );//客户身份号
		
		///////////----分页查询参数----///////////
		pageBean=customBaseInfoService.findNeedUpdateCustom(Constants.FEN_YE_SHU, page,map);
		pageBean.setActionUrl("addCustomInfo!findNeedUpdateCustom.action");
		return "findNeedUpdateSuccessCustom";
	}
	
	/**
	 * 复审通过与不通过操作
	 * @return
	 */
	public String LastCheckYN(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		int code=1;
		CustomBaseInfo customBaseInfo=customBaseInfoService.getByStrId(id).get(0);
		customStatus=customStatusService.getByStrId(id);//客户状态
		if (customBaseInfo==null) {
			msg =  "非法参数";
			return findAllLastCheck();//回到复审查询界面
		}
		CustomAccount customAccount=customAccountService.getStrId(customBaseInfo.getId());
		String param=ServletActionContext.getRequest().getParameter("param");
		String resCode="";
		if ("yes".equals(param))
		{ //如果是通过条件
			
			if (customBaseInfo.getOpenFyAcount().equals("1")) 
			{//选择富有开户
				
				if (customStatus.getCustStart()=='7') 
				{//如果是已经签约的客户信息进修修改复审
					try {
						resCode=UPDATECUSTOM(customBaseInfo, customAccount, customBaseInfoService,request,response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				else if(customStatus.getCustStart()=='3')
				{//正常客户信息进行复审
					resCode=ORGCustom(customBaseInfo, customAccount, customBaseInfoService);
				}
			}
			
			else if (customBaseInfo.getOpenFyAcount().equals("0"))
			{ //不选择开户
				resCode="0000";
			}
			
			code=2;
			
			///////////////////返回码的中文化处理/////////////////
			///////////////////返回码的中文化处理/////////////////
			if ("0000".equals(resCode))
			{
				try {
					checkInfoService.EditedInfoYN(id,code);
					msg="复审客户:"+customBaseInfo.getCustname()+"成功";
				} catch (Exception e) {
					msg="系统异常";
				}
			}
			else
			{
				TErrCode errorCode=new TErrCode(); 
				errorCode=errorCodeService.get(resCode);
				msg="复审客户:"+customBaseInfo.getCustname()+"失败,返回信息："+errorCode.getErrMsg();
			}
		}
		
		else if("no".equals(param))
		{//如果是不通过条件
			code=3;
			checkInfoService.EditedInfoYN(id,code);
		}
		else{//非法参数条件下 
			msg = "非法参数,请求失败";
		}
		return findAllLastCheck();//回到复审查询界面
	}
	
	private String UPDATECUSTOM(CustomBaseInfo custom, CustomAccount account,
			CustomBaseInfoService customBaseInfoService2,HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//本地流水信息注册用户
				TFuyouTran tran=new TFuyouTran();
				tran.setFuyouTran("400101");
				tran.setMchntCd(Constants.MCHNT_CD);//商户代码
				tran.setMchntTxnSsn("REDFORTUNE"+TimeId.generateSequenceNo());//流水号
				tran.setCustNm(custom.getCustname());
				tran.setMobileNo(custom.getPhonenum());
				tran.setCertifId(custom.getPapernum());
				tran.setEmail(custom.getEmail());
				tran.setCityId(account.getAreaCode());
				tran.setParentBankId(account.getPayBank());
				tran.setBankNm(account.getPayBankName());
				tran.setOutCustNo(account.getAccountBank());
				tran.setRemark1("签约成功用户信息修改");
				
				CommonRspData RspData=new CommonRspData();
				ModifyMobileReqData modifyMobileReqData=new ModifyMobileReqData();
				modifyMobileReqData.setMchnt_cd(Constants.MCHNT_CD);//商户代码
				modifyMobileReqData.setMchnt_txn_ssn(tran.getMchntTxnSsn());//流水号
				modifyMobileReqData.setLogin_id(custom.getPhonenum());
				modifyMobileReqData.setPage_notify_url("https://jzh-test.fuiou.com/jzh/400101.action");
				OutputStream out = response.getOutputStream();
				FuiouService.p2p400101(modifyMobileReqData, response);
				ModifyMobileRsqData modifyMobileRsqData=FuiouRspParseService.modifyMobileRspParse(request);
				
		return null;
	}

	
	/**
	 * 提交fuiou旧手机号码请求
	 * @throws Exception
	 */
	public void UpdatePhoneNum() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		ModifyMobileReqData modifyMobileReqData=new ModifyMobileReqData();
		modifyMobileReqData.setMchnt_cd(Constants.MCHNT_CD);//商户代码
		modifyMobileReqData.setMchnt_txn_ssn(TimeId.generateSequenceNo());//流水号
		modifyMobileReqData.setLogin_id("15385538903");
		modifyMobileReqData.setPage_notify_url("http://gaojun6854.eicp.net:57420/hongxinCRM/custom/returnModifyMobile.action");
		try {
			FuiouService.p2p400101(modifyMobileReqData, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 接受fuiou请求返回新手机号码
	 * @throws Exception
	 */
	public void returnModifyMobile() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setCharacterEncoding("ISO8859-1");
		try {
			ModifyMobileRsqData modifyMobileRsqData=new ModifyMobileRsqData();
			modifyMobileRsqData=FuiouRspParseService.modifyMobileRspParse(request);
			AjaxUtils.ajaxJSONResponse(modifyMobileRsqData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 富有RegAPI
	 * 富有注册信息
	 * @return
	 */
	private static String ORGCustom(CustomBaseInfo custom,CustomAccount account, CustomBaseInfoService customBaseInfoService ){
		//本地流水信息注册用户
		TFuyouTran tran=new TFuyouTran();
		tran.setFuyouTran("reg");
		tran.setMchntCd(Constants.MCHNT_CD);//商户代码
		tran.setMchntTxnSsn("REDFORTUNE"+TimeId.generateSequenceNo());//流水号
		tran.setCustNm(custom.getCustname());
		tran.setMobileNo(custom.getPhonenum());
		tran.setCertifId(custom.getPapernum());
		tran.setEmail(custom.getEmail());
		tran.setCityId(account.getAreaCode());
		tran.setParentBankId(account.getPayBank());
		tran.setBankNm(account.getPayBankName());
		tran.setOutCustNo(account.getAccountBank());
		tran.setRemark1("用户注册");
		
		//富有注册信息
		CommonRspData RspData=new CommonRspData();
		RegReqData regData=new RegReqData();
			regData.setMchnt_cd(tran.getMchntCd());//商户代码
			regData.setMchnt_txn_ssn(tran.getMchntTxnSsn());//流水号
			regData.setCust_nm(tran.getCustNm());//客户姓名
			regData.setCertif_id(tran.getCertifId());//客户身份证
			regData.setMobile_no(tran.getMobileNo());//客户手机号码
			regData.setEmail(tran.getEmail());//邮箱
			regData.setCity_id(tran.getCityId());//开户地区代码--附件
			regData.setParent_bank_id(tran.getParentBankId());//开户行
			regData.setBank_nm(tran.getBankNm());//支行名
			regData.setCapAcntNm(tran.getCustNm());//银行户名
			regData.setCapAcntNo(tran.getOutCustNo());//账号
			regData.setRem(tran.getRemark1());//备注
		
		try {
			RspData=FuiouService.reg(regData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tran.setRespCode(RspData.getResp_code());
		tran.setSendMsg(regData.createSignValueForReg());//发送签名
		tran.setRespCode(RspData.getResp_code());//接受签名
		try {
			customBaseInfoService.ReqFuyouResAPISsn(tran);
		} catch (Exception e) {
			return "5019";//数据校验失败。失败则默认
		}
		
		return RspData.getResp_code();
	}
	
	///////////get--set//////////////
	public CustomBaseInfo getCustomBaseInfo() {
		return customBaseInfo;
	}

	public void setCustomBaseInfo(CustomBaseInfo customBaseInfo) {
		this.customBaseInfo = customBaseInfo;
	}

	public List<CustomBaseInfo> getCustomBaseInfos() {
		return customBaseInfos;
	}

	public void setCustomBaseInfos(List<CustomBaseInfo> customBaseInfos) {
		this.customBaseInfos = customBaseInfos;
	}

	public CustomAccount getCustomAccount() {
		return customAccount;
	}

	public void setCustomAccount(CustomAccount customAccount) {
		this.customAccount = customAccount;
	}

	public CustomStatus getCustomStatus() {
		return customStatus;
	}

	public void setCustomStatus(CustomStatus customStatus) {
		this.customStatus = customStatus;
	}

	public CheckInfo getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}

	public CheckReceipts getCheckReceipts() {
		return checkReceipts;
	}

	public void setCheckReceipts(CheckReceipts checkReceipts) {
		this.checkReceipts = checkReceipts;
	}

	public PageView<CustomBaseInfo> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<CustomBaseInfo> pageView) {
		this.pageView = pageView;
	}

	public PageBean<CustomBaseInfo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<CustomBaseInfo> pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public TAreaCode getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(TAreaCode areaCode) {
		this.areaCode = areaCode;
	}

	public TBankCode getBankCode() {
		return bankCode;
	}

	public void setBankCode(TBankCode bankCode) {
		this.bankCode = bankCode;
	}

	public List<TAreaCode> getAreas() {
		return areas;
	}

	public void setAreas(List<TAreaCode> areas) {
		this.areas = areas;
	}

	public List<TBankCode> getBanks() {
		return banks;
	}

	public void setBanks(List<TBankCode> banks) {
		this.banks = banks;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}
	
}
