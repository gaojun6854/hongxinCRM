package com.hongxin.action.OA;

/**
 * 基金信息
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.TFundInfo;
import com.hongxin.service.FundInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class FundAction extends ActionSupport {

	private static final long serialVersionUID = 8051545551005559302L;
	@Autowired
	FundInfoService fundInfoService;
	private TFundInfo fundInfo;
	List<TFundInfo> fundInfos;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String fundInsert() {
		TFundInfo foun = fundInfoService.get(fundInfo.getWorkDate());
		if (foun == null) {
			fundInfoService.save(fundInfo);
			ServletActionContext.getRequest().setAttribute("flag", "基金已经录入");
		} else {
			ServletActionContext.getRequest().setAttribute("flag", "今天的基金已经录入,重复录入");
		}
		return SUCCESS;
	}

	public String fundquery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if (start == null || end == null || start.equals("") || end.equals("")) {
			fundInfos = fundInfoService.findAll();
		} else {
			fundInfos = fundInfoService.findByStartEnd(start, end);
		}
		if (fundInfos == null) {
			request.setAttribute("flag", "查询为空");
		}
		request.setAttribute("fundInfos", fundInfos);
		return "fundquery";
	}

	/**
	 * 指数查询--->返回到指数修改界面
	 * 
	 * @return
	 */
	public String getFundsInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String WorkDate = request.getParameter("WorkDate");
		fundInfo = fundInfoService.get(WorkDate);
		if (fundInfo == null) {
			request.setAttribute("flag", "错误请求");
		}
		return "toUpdateFund";
	}

	/**
	 * 指数修改
	 * 
	 * @return
	 */
	public String fundUpdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			fundInfoService.saveOrUpdate(fundInfo);
			fundInfo = fundInfoService.get(fundInfo.getWorkDate());
			if (fundInfo != null) {
				request.setAttribute("flag", "基金指数修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("flag", "系统异常,稍后重试！");
			return "toUpdateFund";
		}
		return "toUpdateFund";
	}



	//////////////////////////// get----set//////////////////////
	public TFundInfo getFundInfo() {
		return fundInfo;
	}
	
	public void setFundInfo(TFundInfo fundInfo) {
		this.fundInfo = fundInfo;
	}
	
	public List<TFundInfo> getFundInfos() {
		return fundInfos;
	}
	
	public void setFundInfos(List<TFundInfo> fundInfos) {
		this.fundInfos = fundInfos;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
