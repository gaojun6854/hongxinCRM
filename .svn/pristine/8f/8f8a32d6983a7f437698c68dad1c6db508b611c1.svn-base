package com.hongxin.action.OA;

/**
 * 指数信息
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.TIndexInfo;
import com.hongxin.service.ZhishuService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexsAction extends ActionSupport {

	private static final long serialVersionUID = 8051545551005559302L;
	@Autowired
	ZhishuService zhishuService;
	private TIndexInfo indexs;
	List<TIndexInfo> indexList;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String zhishuInsert() {
		TIndexInfo inde = zhishuService.get(indexs.getWorkDate());
		if (inde == null) {
			zhishuService.save(indexs);
			ServletActionContext.getRequest().setAttribute("flag", "指数已经录入");
		} else {
			ServletActionContext.getRequest().setAttribute("flag", "今天的指数已经录入,重复录入");
		}
		return SUCCESS;
	}

	public String zhishuquery() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if (start == null || end == null || start.equals("") || end.equals("")) {
			indexList = zhishuService.findAll();
		} else {
			indexList = zhishuService.findByStartEnd(start, end);
		}
		if (indexList == null) {
			request.setAttribute("flag", "查询为空");
		}
		request.setAttribute("indexList", indexList);
		return "zhishuquery";
	}

	/**
	 * 指数查询--->返回到指数修改界面
	 * 
	 * @return
	 */
	public String getIndexsInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String WorkDate = request.getParameter("WorkDate");
		indexs = zhishuService.get(WorkDate);
		if (indexs == null) {
			request.setAttribute("flag", "错误请求");
		}
		return "toUpdateIndex";
	}

	/**
	 * 指数修改
	 * 
	 * @return
	 */
	public String zhishuUpdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			zhishuService.saveOrUpdate(indexs);
			indexs = zhishuService.get(indexs.getWorkDate());
			if (indexs != null) {
				request.setAttribute("flag", "指数修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("flag", "系统异常,稍后重试！");
			return "toUpdateIndex";
		}
		return "toUpdateIndex";
	}

	//////////////////////////// get----set//////////////////////

	public TIndexInfo getIndexs() {
		return indexs;
	}

	public void setIndexs(TIndexInfo indexs) {
		this.indexs = indexs;
	}

	public List<TIndexInfo> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<TIndexInfo> indexList) {
		this.indexList = indexList;
	}

}
