package com.hongxin.action.OA;
/**
 * 合约信息
 */
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.service.CustomBaseInfoService;
import com.hongxin.service.PactInfoService;
import com.hongxin.service.ProductService;
import com.hongxin.service.ZhishuService;
import com.opensymphony.xwork2.ActionSupport;

public class RepayAction extends ActionSupport{

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
	ZhishuService zhishuService;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	
}
