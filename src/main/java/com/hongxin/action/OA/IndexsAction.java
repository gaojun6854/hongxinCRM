package com.hongxin.action.OA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
/**
 * 指数信息
 */
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.TIndexInfo;
import com.hongxin.service.ZhishuService;
import com.hongxin.utils.Constants;
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
			sendIndexsMessage(Constants.sendMessageURL,"type=1&index="+indexs.getIndexs()+"&date="+indexs.getWorkDate()+"");
			ServletActionContext.getRequest().setAttribute("flag", "指数已经录入");
		} else {
			ServletActionContext.getRequest().setAttribute("flag", "今天的指数已经录入,重复录入");
		}
		return SUCCESS;
	}
	
	/**
	 * 短信邮件发送请求
	 * @return
	 */
	public static String sendIndexsMessage(String url, String param){
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
      //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
		return result;
	}
	
	public static void main(String[] args) {
		String a=sendIndexsMessage(Constants.sendMessageURL,"type=1&index=2098.65&date=20160704");
		System.out.println(a);
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
