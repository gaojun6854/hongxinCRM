package com.hongxin.action.Interceptor;
/**
 * 日志拦截器
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hongxin.entity.OperationDiary;
import com.hongxin.entity.User;
import com.hongxin.service.LogService;
import com.hongxin.service.UserInfoService;
import com.hongxin.utils.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class LogIntercept implements Interceptor {

	private static final long serialVersionUID = 1L;
	//注入Service用于把日志保存数据库    
	private LogService logService;
	private UserInfoService userInfoService;
	//本地异常日志记录对象    
	private  static  final Logger logger = LoggerFactory.getLogger(LogIntercept.class);
	
	public String intercept(ActionInvocation ai) throws Exception {
		ai.addPreResultListener(new PreResultListener() {
			public void beforeResult(ActionInvocation ai, String arg1) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH:MM:SS");
				try {
                      StringBuffer sb = new StringBuffer();
                      sb.append(dateformat.format(new Date()) + ":");
                      Map<String, Object> session = ai.getInvocationContext().getSession();
                     User user = (User) session.get("login_user");
                      if (user != null) {
                          sb.append("操作人：" + user.getUserName());
                      } else {
                          sb.append("操作人：系统未获取");
                      }
                      InetAddress addr = null;
          			try {
          				addr = InetAddress.getLocalHost();
          			} catch (UnknownHostException e) {
          				e.printStackTrace();
          			}
          			sb.append("IP："+addr.getHostAddress().toString());
                      String methodName=ai.getAction().toString();
                      methodName=methodName.substring(0, methodName.toString().lastIndexOf("@"));
                     sb.append("类名：" + methodName + " ");
                      sb.append("方法名：" + ai.getInvocationContext().getName()+ " ");
                      Map<String, Object> map = ai.getInvocationContext().getParameters();
                      Set<String> keys = map.keySet();
                     sb.append("参数：");
                      for (String key : keys) {
                          sb.append(key + "=" + ((Object[]) map.get(key))[0]+ "#");
                      }
                      sb.append(" ");
                     sb.append("执行结果：" + ai.getResultCode());
                      saveLog(Constants.saveLogPath, sb.toString());
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
			}
		});

		return ai.invoke();
	}

	
	public static void main(String[] args) {
		String str="com.hongxin.action.OA.UserLoginAction@2f92dfcf";
		String a=str.substring(0,str.lastIndexOf("@"));
		System.out.println(a);
	}
	
	
	
	public static void saveLog(String dir, String content) {
		
		//SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH:MM:SS");
		//写入文件
		/*try {
			StringBuffer sb = new StringBuffer();
			sb.append(dateformat.format(new Date()) + ":");
			Map<String, Object> session = ai.getInvocationContext().getSession();
			UserInfo user = (UserInfo) session.get("login_user");
			if (user != null) {
				sb.append("操作人：" + user.getName());
			} else {
				sb.append("操作人：系统未获取");
			}
			sb.append("类名：" + ai.getAction() + " ");
			sb.append("方法名：" + ai.getInvocationContext().getName() + " ");
			Map<String, Object> map = ai.getInvocationContext().getParameters();
			Set<String> keys = map.keySet();
			sb.append("参数：");
			for (String key : keys) {
				sb.append(key + "=" + ((Object[]) map.get(key))[0] + "#");
			}
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			sb.append(addr.getHostAddress().toString());
			sb.append("执行结果：" + ai.getResultCode() + " ");
			String appPath = ServletActionContext.getServletContext().getRealPath("/");
			saveLog(appPath + "logs", sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		///////////////////////////////////
		//////////////////////////////
		///////////////////////操作数据库
		/////////////////
		///////////
		//读取session中的用户    
		/*User user = (User) ActionContext.getContext().getSession().get("login_user");   
        InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();
         try {    
            OperationDiary operationDiary = new OperationDiary();   
            if(user != null)
	       	 {
	       		 operationDiary.setOperationUserId(user.getEmployeeId());
	             operationDiary.setOprationName(user.getEmployStaff().getFullName());   
	       	 }else
	       	 {
	       		 operationDiary.setOperationUserId("未知");
	             operationDiary.setOprationName("游客");   
	       	 }
            HttpServletRequest request= ServletActionContext.getRequest();
            operationDiary.setDescription((String)request.getAttribute("logRec")==null?"未知操作":(String)request.getAttribute("logRec")); 
            operationDiary.setExceptionDetail(ai.getResultCode()==null?"":ai.getResultCode());
            operationDiary.setOperationCode(ai.getInvocationContext().getName());
            operationDiary.setOperationSource(ai.getAction().toString());   
            operationDiary.setOprationIp(ip);  
            operationDiary.setOperationDate(dateformat.format(new Date()));
            //保存数据库
            if (!operationDiary.getOperationCode().equals("login")) {
            	logService.save(operationDiary);//返回重新登录则不记录
			}
            request.removeAttribute("logRec");
            logger.info("=====前置通知结束=====");    
        }  catch (Exception e) {    
            //记录本地异常日志    
        	e.printStackTrace();
            logger.error("==前置通知异常==");    
            logger.error("异常信息:{}", e.getMessage());    
        }   */ 

		try {
			File path = new File(dir);
			if (!path.exists()) {
				path.mkdir();
			}
			File LogDir = new File(path + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1));
			if (!LogDir.exists()) {
				LogDir.mkdir();
			}
			File file = new File(LogDir + "/" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ".log");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
			br.write(content);
			br.newLine();
			br.flush();
			br.close();

			File LogDirOld = new File(path + "/"
					+ (Calendar.getInstance().get(Calendar.MONTH) - 2 > 0
							? (Calendar.getInstance().get(Calendar.MONTH) - 2)
							: Calendar.getInstance().get(Calendar.MONTH) + 10));
			if (LogDirOld.exists()) {
				File[] fileOlds = LogDirOld.listFiles();
				for (File f : fileOlds) {
					f.delete();
				}
				LogDirOld.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

	}

	public void init() {

	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
}
