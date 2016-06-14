package com.hongxin.action.OA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
/**
 * 还款Action
 */
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.CustomBaseInfo;
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

public class AutoRepayAction extends ActionSupport {
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
	private PageBean<TAutoRepay> pageBeanAutoRepay;
	private TAutoRepay autoRepay;
	private String id;
	private TBackAcct backAcct;
	private String msg;

	@Override
	public String execute() throws Exception {
		return null;
	}

	///////////////////////////////////////////////////////////// 还款管理/////////////////////////////////////////////////////////////////
	/**
	 * 查询还款初审
	 * 
	 * @return
	 */
	public String findFirstPaymentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String custName = request.getParameter("custName");// 客户名
		String pactDue = request.getParameter("pactDue");// 到期日
		request.setAttribute("custName", custName);
		request.setAttribute("pactDue", pactDue);
		Map<String, String> map = new HashMap<String, String>();
		map.put("custName", custName);
		map.put("pactDue", pactDue);
		System.out.println(map.get(pactDue));
		//List<TAutoRepay> autoRepays = autoRepayService.findAllRepayment();// 查找今天要还款的账单
		pageBeanAutoRepay = autoRepayService.findFirstCheck(Constants.FEN_YE_SHU, page, map);
		pageBeanAutoRepay.setActionUrl("findFirstPaymentList.action");
		for (TAutoRepay tAutoRepay : pageBeanAutoRepay.getList()) {
			TPactInfo pact = pactInfoService.get(tAutoRepay.getPactId());
			pact.setRebuypactInfo(reBuyPactService.get(pact.getPactId()));
			tAutoRepay.setPactInfo(pact);
			tAutoRepay.getPactInfo().setProductInfo(productService.get(tAutoRepay.getProductId()));
			tAutoRepay.getPactInfo()
					.setCustomBaseInfo(customBaseInfoService.getByStrId(tAutoRepay.getPactInfo().getCustId()).get(0));
		}
		return "firstPaymentList";
	}

	/**
	 * 查询还款复审
	 */
	public String findlastPaymentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String custName = request.getParameter("custName");// 客户名
		String pactDue = request.getParameter("pactDue");// 到期日
		request.setAttribute("custName", custName);
		request.setAttribute("pactDue", pactDue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custName", custName);
		map.put("pactDue", pactDue);
		
		pageBeanAutoRepay = autoRepayService.findReCheckPact(Constants.FEN_YE_SHU, page,map);
		pageBeanAutoRepay.setActionUrl("findlastPaymentList.action");
		
		for (TAutoRepay tAutoRepay : pageBeanAutoRepay.getList()) {
			TPactInfo pact = pactInfoService.get(tAutoRepay.getPactId());
			pact.setRebuypactInfo(reBuyPactService.get(pact.getPactId()));
			tAutoRepay.setPactInfo(pact);
			tAutoRepay.getPactInfo().setProductInfo(productService.get(tAutoRepay.getProductId()));
			tAutoRepay.getPactInfo()
					.setCustomBaseInfo(customBaseInfoService.getByStrId(tAutoRepay.getPactInfo().getCustId()).get(0));
		}
		return "lastPaymentList";
	}

	/**
	 * 查询需要还款修改
	 */
	public String findFailAutoRepayList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String custName = request.getParameter("custName");// 客户名
		String pactDue = request.getParameter("pactDue");// 到期日
		request.setAttribute("custName", custName);
		request.setAttribute("pactDue", pactDue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custName", custName);
		map.put("pactDue", pactDue);
		pageBeanAutoRepay = autoRepayService.findFailAutoRepay(Constants.FEN_YE_SHU, page,map);// 查找失败还款
		pageBeanAutoRepay.setActionUrl("findFailAutoRepayList.action");
		for (TAutoRepay tAutoRepay : pageBeanAutoRepay.getList()) {
			TPactInfo pact = pactInfoService.get(tAutoRepay.getPactId());
			pact.setRebuypactInfo(reBuyPactService.get(pact.getPactId()));
			tAutoRepay.setPactInfo(pact);
			tAutoRepay.getPactInfo().setProductInfo(productService.get(tAutoRepay.getProductId()));
			tAutoRepay.getPactInfo()
					.setCustomBaseInfo(customBaseInfoService.getByStrId(tAutoRepay.getPactInfo().getCustId()).get(0));
		}
		return "FailAutoRepayList";
	}

	/**
	 * 失败还款信息保存
	 */
	public String failPaymentToFirstPayment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		pactInfo=pactInfoService.get(id);
		String amount=request.getParameter("amount");
		TRebuypactInfo rebuyPact=new TRebuypactInfo();
		if ("01".equals(pactInfo.getRebuyFlag())) {
			rebuyPact= reBuyPactService.get(pactInfo.getPactId());
			rebuyPact.setAmount(Double.parseDouble(amount));
		}
		
		TAutoRepay autor=autoRepayService.get(pactInfo.getPactId());
		try {
			if ("01".equals(pactInfo.getRebuyFlag())) {
				reBuyPactService.saveOrUpdate(rebuyPact);
			}
			autor.setBussStart('0');
			pactInfo.setPactFlow("5");
			autoRepayService.saveOrUpdate(autor);
			pactInfoService.saveOrUpdateByEntity(pactInfo);
			request.setAttribute("rebuyPact", rebuyPact);
		} catch (Exception e) {
			request.setAttribute("flag", "系统繁忙");
		}
		List<TProductInfo>products=new ArrayList<TProductInfo>();
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
			
		products=productService.findAll();
		request.setAttribute("products", products);
		return "updateAutoRepay";
	}
	
	/**
	 * 还款初审通过以及不通过
	 * 
	 * @return
	 */
	public String firstPaymentYN() {
		synchronized(PactInfoAction.class){
		TAutoRepay repay=autoRepayService.get(id);
		if ("2".equals(repay.getBussStart())||"5".equals(repay.getBussStart())) {
			msg="该还款信息已被处理,请勿重复处理!";
			return "firstPaymentList";
		}
		String param = ServletActionContext.getRequest().getParameter("param");
		//String id = ServletActionContext.getRequest().getParameter("id");
		try {
			if ("yes".equals(param)) {
				pactInfoService.repaymentYN(id, param);
				msg="还款初审成功";
			} else if ("no".equals(param)) {
				pactInfoService.repaymentYN(id, param);
				msg="操作成功";
			}
		} catch (Exception e) {
				msg="系统繁忙";
		}
		// 在次查询还款到期信息
		return "firstPaymentList";
		}
	}

	
	/**
	 * 确定还款---还款复审
	 */
	public String lastPaymentYN() {
		HttpServletRequest request = ServletActionContext.getRequest();
		synchronized(PactInfoAction.class){
			TAutoRepay repay=autoRepayService.get(id);
			if ("7".equals(repay.getBussStart())) {
				msg="该还款信息已被处理,请勿重复处理!";
				return "findlastPaymentList";
			}
		
			String param = request.getParameter("param");
			pactInfo = pactInfoService.get(id);
			TAutoRepay auto = autoRepayService.get(pactInfo.getPactId());
			double balance = 0.00;
	
			if ("yes".equals(param)) {
				if ("7".equals(pactInfo.getPactFlow())) {// 确定是复审状态
					if ("01".equals(pactInfo.getRebuyFlag())) {// 回购状态
						TRebuypactInfo rebuy = reBuyPactService.get(pactInfo.getPactId());// 回购表数据
						balance = pactInfo.getAmount() + pactInfo.getBackMoney() - rebuy.getAmount();
						try {
							System.out.println("富友打款-回购金额:" + rebuy.getAmount());
							/**
							 * 调用富有接口操作 打款给客户
							 */
							if (balance == 0.00) {
								pactInfo.setPactFlow("12");//合同状态结束
								
							} else {
								TBackAcct backAccount=new TBackAcct();
								backAccount.setPactId(pactInfo.getPactId());
								backAccount.setRebuyAmount(rebuy.getAmount());
								backAccount.setBackAmount(balance);
								backAccount.setState("00");
								autoRepayService.saveBackAccount(backAccount);
								pactInfo.setPactFlow("10");//合同状态待还款至客户信息
							}
						} catch (Exception e) {
							msg="系统繁忙,稍后再试!";
						}
					} else if ("00".equals(pactInfo.getRebuyFlag())) {// 不回购
	
						try {
							System.out.println("富友打款-全额打款:" + pactInfo.getAmount() + pactInfo.getBackMoney());
							/**
							 * 调用富有接口操作 打款给客户
							 */
							pactInfo.setPactFlow("12");
						} catch (Exception e) {
							msg="系统繁忙,稍后再试!";
						}
					}
				}
				auto.setBussStart('7');
			} else if ("no".equals(param)) {
				pactInfo.setPactFlow("8");
				pactInfo.setCheckStart('2');
				auto.setBussStart('6');
			}
			try {
				autoRepayService.saveOrUpdate(auto);
				pactInfoService.saveOrUpdate(pactInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "findlastPaymentList";
	}
	
	/**
	 * 查询还款详细信息
	 */
	public String findPaymentInfomation() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String redirect = request.getParameter("redirect");
		String no = request.getParameter("no");
		autoRepay = autoRepayService.get(id);
		pactInfo = pactInfoService.get(id);
		pactInfo.setProductInfo(productService.getStrId(pactInfo.getProductId()));
		pactInfo.setCustomBaseInfo(customBaseInfoService.getByStrId(pactInfo.getCustId()).get(0));
		List<CheckReceipts> receipts = checkReceiptsService.getByStrIdType(id, no);
		pactInfo.setReceipts(receipts);
		if ("firstPayment".equals(redirect)) {// 还款初审
			if ("01".equals(pactInfo.getRebuyFlag())) {
				TRebuypactInfo rebuyPact = reBuyPactService.get(pactInfo.getPactId());
				request.setAttribute("rebuyPact", rebuyPact);
			}
			return "firstPaymentInfo";
		} else if ("lastPayment".equals(redirect)) {// 还款复审
			// 是回购合同
			if ("01".equals(pactInfo.getRebuyFlag())) {
				TRebuypactInfo rebuyPact = reBuyPactService.get(pactInfo.getPactId());
				request.setAttribute("rebuyPact", rebuyPact);
			}
			return "lastPaymentInfo";
		} else if ("updateAutoRepay".equals(redirect)) {// 失败还款修改界面
			if ("01".equals(pactInfo.getRebuyFlag())) {
				TRebuypactInfo rebuyPact = reBuyPactService.get(pactInfo.getPactId());
				request.setAttribute("rebuyPact", rebuyPact);
			}
			List<TProductInfo> products = new ArrayList<TProductInfo>();
			products = productService.findAll();
			request.setAttribute("products", products);
			return "updateAutoRepay";
		}

		return "";
	}

	/////////////////////////// get-----set////////////////////////
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
	/////////////////////////// get-----set////////////////////////
}
