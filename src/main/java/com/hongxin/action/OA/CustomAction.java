package com.hongxin.action.OA;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户信息
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fuiou.data.CommonRspData;
import com.fuiou.data.RegReqData;
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
		HttpServletRequest request=ServletActionContext.getRequest();
		String attr=request.getParameter("attr");
		Map<String, Object>map=new HashMap<String, Object>();
		if (customBaseInfo==null) {
			map.put("all", 1);
			request.setAttribute("attr", "0");
		}else {
			map.put("attr", attr);
			map.put("custName", customBaseInfo.getCustname());
			map.put("custPhone", customBaseInfo.getPhonenum());
			map.put("custPapernum", customBaseInfo.getPapernum());
			map.put("all", 0);
		}
		pageBean=customBaseInfoService.getPageBean(5, page,map);
		pageBean.setActionUrl("addCustomInfo!findAll.action");
		request.setAttribute("attr", attr);
		for (CustomBaseInfo customBaseInfo : pageBean.getList()) {
			customBaseInfo.setCheckInfos(checkInfoService.getByCustomId(customBaseInfo.getId()));
			customBaseInfo.setCheckReceipts(checkReceiptsService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomStatus(customStatusService.getByStrId(customBaseInfo.getId()));
			customBaseInfo.setCustomAccount(customAccountService.getStrId(customBaseInfo.getId()));
		}
		return "findAll";
	}
	
	
	/**
	 * 查询需要更改的客户信息
	 */
	
	public String findFailInfo(){
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findFailInfo();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findFailInfoList";
	}
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateCustomInfo(){
		//如果客户是出于未初审信息，则直接修改客户信息
		//CheckInfo cheCkInfo=checkInfoService.getByCustomId(customBaseInfo.getId());
		CustomStatus customStatus=customStatusService.getByStrId(customBaseInfo.getId());
		int a=0;
		if ("1".equals(customStatus.getCustStart())||"2".equals(customStatus.getCustStart())) {//如果是初审或者初审失败则修改信西
			int g=1;
			a=customBaseInfoService.saveOrUpdateByStr(customBaseInfo,g);
		}else{
			int g=2;
			a=customBaseInfoService.saveOrUpdateByStr(customBaseInfo,g);
		}
		List<CustomBaseInfo>  customs=customBaseInfoService.getByStrId(customBaseInfo.getId());
		ServletActionContext.getRequest().setAttribute("custom", customs.get(0));
		if (a==1) {//更新成功--更新参数信息
			ServletActionContext.getRequest().setAttribute("flag", "用户信息修改成功");
		}else{//失败--回到原参数信息
			ServletActionContext.getRequest().setAttribute("flag", "系统错误");
		}
		ServletActionContext.getRequest().setAttribute("redirect","updateCustomInfo");
		return "getCustomInfo";
	}
	
	/**
	 * 单个用户信息查询
	 * @return
	 */
	public String getCustomInfo(){
		String redirect=ServletActionContext.getRequest().getParameter("redirect");//从哪里来的审核
		String id=ServletActionContext.getRequest().getParameter("id");//客户编号
		List<CustomBaseInfo>  customs=customBaseInfoService.getByStrId(id);
		if (customs==null) {
			ServletActionContext.getRequest().setAttribute("flag", "系统错误");
		}else{
			CustomBaseInfo cust=customs.get(0);
			cust.setCheckReceipts(checkReceiptsService.getByStrIdType(cust.getId(),"1"));
			ServletActionContext.getRequest().setAttribute("custom", cust);
		}
		//辨别 ---》 1.修改客户信息 2.初审客户信息 3.复审客户信息
		if ("auditYN".equals(redirect)) {//初审
			ServletActionContext.getRequest().setAttribute("redirect","auditYN");
		}else if("EditedInfoYN".equals(redirect)){//复审
			ServletActionContext.getRequest().setAttribute("redirect","EditedInfoYN");
		}else{
			ServletActionContext.getRequest().setAttribute("redirect","updateCustomInfo");
		}
		return "getCustomInfo";
	}
	
	/**
	 * 查询全部初审客户信息
	 * @return
	 */
	public String findAllFirstAudit(){
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findAllFirstAudit();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findAllFirstAudit";
	}
	
	/**
	 * 初审通过与不通过操作
	 * @return
	 */
	public String auditYN(){
		int code=1;
		String id=ServletActionContext.getRequest().getParameter("id");
		CustomBaseInfo customBaseInfo=customBaseInfoService.getByStrId(id).get(0);
		if (customBaseInfo==null) {
			ServletActionContext.getRequest().setAttribute("flag", "非法参数");
		}
		String param=ServletActionContext.getRequest().getParameter("param");
		if ("yes".equals(param)) {
			code=2;
		}else if("no".equals(param)){
			code=3;
		}else {
			ServletActionContext.getRequest().setAttribute("flag", "非法参数");
		}
		int a=checkInfoService.auditYN(id,code);
		if (a==1) {
			ServletActionContext.getRequest().setAttribute("flag", "客户:"+customBaseInfo.getCustname()+"操作成功");
		}
		//再次查询所有未初审通过信息
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findAllFirstAudit();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findAllFirstAudit";
	}
	
	/**
	 * 查询复审信息
	 * @return
	 */
	public String findAllAudited(){
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findAudited();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findAll";
	}
	
	/**
	 * 查询所有需要复审客户信息
	 * @return
	 */
	public String findEditedInfo(){
		List<CustomBaseInfo>customBaseInfos=customBaseInfoService.findEditedInfo();
		ServletActionContext.getRequest().setAttribute("customBaseInfos", customBaseInfos);
		return "findEditedInfo";
	}
	
	/**
	 * 复审通过与不通过操作
	 * @return
	 */
	public String EditedInfoYN(){
		int code=1;
		String id=ServletActionContext.getRequest().getParameter("id");
		CustomBaseInfo customBaseInfo=customBaseInfoService.getByStrId(id).get(0);
		if (customBaseInfo==null) {
			ServletActionContext.getRequest().setAttribute("flag", "非法参数");
			return findAllAudited();//回到复审查询界面
		}
		CustomAccount customAccount=customAccountService.getStrId(customBaseInfo.getId());
		String param=ServletActionContext.getRequest().getParameter("param");
		String resCode="";
		if ("yes".equals(param)){ 
			resCode=ORGCustom(customBaseInfo, customAccount, customBaseInfoService);
			
			code=2;
			if ("0000".equals(resCode)){
				try {
					checkInfoService.EditedInfoYN(id,code);
				} catch (Exception e) {
					msg="系统异常";
				}
			}else{
				TErrCode errorCode=new TErrCode(); 
				errorCode=errorCodeService.get(resCode);
				msg="创建客户:"+customBaseInfo.getCustname()+"失败,返回信息："+errorCode.getErrMsg();
			}
		}else if("no".equals(param)){
			code=3;
			checkInfoService.EditedInfoYN(id,code);
		}else{ 
			ServletActionContext.getRequest().setAttribute("flag", "非法参数");
		}
		return findAllAudited();//回到复审查询界面
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
		tran.setCityId(account.getRemark2());
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
		tran.setRespCode(RspData.toString());//接受签名
		customBaseInfoService.ReqFuyouResAPISsn(tran);
		
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
	
}
