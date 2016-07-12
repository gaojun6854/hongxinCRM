package com.hongxin.action.OA;

/**
 * 通知
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
/**
 * 还款通知Action
 */
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.PageBean;
import com.hongxin.entity.TAutoRepay;
import com.hongxin.entity.TBackAcct;
import com.hongxin.entity.TPactInfo;
import com.hongxin.entity.TPactInform;
import com.hongxin.entity.TProductInfo;
import com.hongxin.entity.TRebuypactInfo;
import com.hongxin.service.AutoRepayService;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomAccountService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.PactInfoService;
import com.hongxin.service.PactInformService;
import com.hongxin.service.ProductService;
import com.hongxin.service.ReBuyPactService;
import com.hongxin.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class PaymentNoticeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
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
	@Autowired
	PactInformService pactInformService;
	private TPactInfo pactInfo;
	private TPactInform PactInform;
	private List<TPactInform> pactInforms;
	private int page;
	private PageBean<TPactInform> pageBean;
	private PageBean<TBackAcct> pageBean4backAcct;
	private PageBean<TAutoRepay>pageBeanAutoRepay;
	private TAutoRepay autoRepay; 
	private String id;
	private TBackAcct backAcct;
	private String msg;
	private TProductInfo productInfo;

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	

	/**
	 * 查询所有的还款通知
	 * 
	 * @return
	 */
	public String findPaymentNoticeList() {
		HttpServletRequest request=ServletActionContext.getRequest();
		///////////----分页查询参数----///////////
		String pactNum=request.getParameter("pactNum");//合同号
		String phoneNum=request.getParameter("phoneNum");
		String custName=request.getParameter("custName");
		String paperNum=request.getParameter("paperNum");
		Map<String, Object>map=new HashMap<String, Object>();
		
		map.put("pactNum", pactNum==null?"":pactNum);//合同号
		map.put("custName", custName==null?"":custName);
		map.put("phoneNum",phoneNum==null?"":phoneNum );//客户手机号
		map.put("paperNum",paperNum==null?"":paperNum );//客户身份号
		request.setAttribute("pactNum", pactNum);
		request.setAttribute("custName", custName);
		request.setAttribute("phoneNum", phoneNum);
		request.setAttribute("paperNum", paperNum);
		
		try {
			pageBean = pactInformService.getPageBean(Constants.FEN_YE_SHU, page, map);
			pageBean.setActionUrl("findPaymentNoticeList.action");
		} catch (Exception e) {
			msg="系统繁忙,稍后再试";
		}
		return "paymentNoticeList";
	}

	/**
	 * 还款到客户账记录
	 * 
	 * @return
	 */
	public String findPaymentToCustomRecord() {
		HttpServletRequest request=ServletActionContext.getRequest();
		///////////----分页查询参数----///////////
		String pactNum=request.getParameter("pactNum");//合同号
		String phoneNum=request.getParameter("phoneNum");
		String custName=request.getParameter("custName");
		String paperNum=request.getParameter("paperNum");
		Map<String, Object>map=new HashMap<String, Object>();
		
		map.put("pactNum", pactNum==null?"":pactNum);//合同号
		map.put("custName", custName==null?"":custName);
		map.put("phoneNum",phoneNum==null?"":phoneNum );//客户手机号
		map.put("paperNum",paperNum==null?"":paperNum );//客户身份号
		request.setAttribute("pactNum", pactNum);
		request.setAttribute("custName", custName);
		request.setAttribute("phoneNum", phoneNum);
		request.setAttribute("paperNum", paperNum);
		///////////----分页查询参数----///////////
		
		try {
			pageBean4backAcct = pactInformService.getPaymentToCustomRecord(Constants.FEN_YE_SHU, page, map);
			pageBean4backAcct.setActionUrl("findPaymentToCustomRecord.action");
		} catch (Exception e) {
			msg="系统繁忙,稍后再试";
		}
		return "paymentToCustomRecord";
	}


	/**
	 * 还款客户账
	 */
	public String paymentToCustom(){
		pactInfo=pactInfoService.get(id);
		TAutoRepay auto = autoRepayService.get(pactInfo.getPactId());
		backAcct=autoRepayService.getBackAccount(id);
		backAcct.setState("01");
		pactInfo.setPactFlow("12");
		pactInfo.setCheckStart('2');
		auto.setBussStart('7');
		try {
			autoRepayService.saveOrUpdateForBackAccount(backAcct);
			autoRepayService.saveOrUpdate(auto);
			pactInfoService.saveOrUpdate(pactInfo);
		} catch (Exception e) {
			msg="系统出错";
		}
		return "findPaymentToCustomRecord";
	}
	
	/**
	 * 还款客户操作
	 * 
	 * @return
	 */
	public String PaymentToCustom() {
		backAcct = pactInformService.getBackAcct(id);
		if (backAcct == null) {
			msg = "系统繁忙";
			return "";
		}
		boolean flag = false;
		/**
		 * 富友环节
		 */
		if (flag) {
			try {
				backAcct.setState("01");// 已还款
				pactInformService.saveOrUpdate4BackAccount(backAcct);
			} catch (Exception e) {
				msg = "系统繁忙";
				return "";
			}
		}
		msg = "操作成功";
		return "";
	}

	/**
	 * 查询还款详细信息
	 */
	public String findPaymentInfomation() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String no = request.getParameter("no");
		autoRepay = autoRepayService.get(id);
		pactInfo = pactInfoService.get(id);
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
		List<CheckReceipts> receipts = checkReceiptsService.getByStrIdType(id, no);
		pactInfo.setReceipts(receipts);
		
		if ("01".equals(pactInfo.getRebuyFlag())) {
			TRebuypactInfo rebuyPact = reBuyPactService.get(pactInfo.getPactId());
			request.setAttribute("rebuyPact", rebuyPact);
		}
		List<TProductInfo> products = new ArrayList<TProductInfo>();
		products = productService.findAll();
		request.setAttribute("products", products);
		return "paymentNoticeInfo";
	
	}
	
	////////////////////////////// get----------------set//////////////////////////////////////////
	public TPactInfo getPactInfo() {
		return pactInfo;
	}

	public void setPactInfo(TPactInfo pactInfo) {
		this.pactInfo = pactInfo;
	}

	public TPactInform getPactInform() {
		return PactInform;
	}

	public void setPactInform(TPactInform pactInform) {
		PactInform = pactInform;
	}

	public List<TPactInform> getPactInforms() {
		return pactInforms;
	}

	public void setPactInforms(List<TPactInform> pactInforms) {
		this.pactInforms = pactInforms;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean<TPactInform> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<TPactInform> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<TBackAcct> getPageBean4backAcct() {
		return pageBean4backAcct;
	}

	public void setPageBean4backAcct(PageBean<TBackAcct> pageBean4backAcct) {
		this.pageBean4backAcct = pageBean4backAcct;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TBackAcct getBackAcct() {
		return backAcct;
	}

	public void setBackAcct(TBackAcct backAcct) {
		this.backAcct = backAcct;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PageBean<TAutoRepay> getPageBeanAutoRepay() {
		return pageBeanAutoRepay;
	}

	public void setPageBeanAutoRepay(PageBean<TAutoRepay> pageBeanAutoRepay) {
		this.pageBeanAutoRepay = pageBeanAutoRepay;
	}

	public TAutoRepay getAutoRepay() {
		return autoRepay;
	}

	public void setAutoRepay(TAutoRepay autoRepay) {
		this.autoRepay = autoRepay;
	}

	public TProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(TProductInfo productInfo) {
		this.productInfo = productInfo;
	}

}
