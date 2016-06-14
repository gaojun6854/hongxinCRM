package com.hongxin.common;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hongxin.entity.OperationDiary;
import com.hongxin.entity.UserInfo;
import com.hongxin.service.LogService;
import com.opensymphony.xwork2.ActionContext;

    
/**  
 * 切点类  
 * @author gaojun  
 * @since 2016年3月27日11:32:08
 * @version 1.0  
 */    
@Aspect    
@Component    
public  class SystemLogAspect {    
    //注入Service用于把日志保存数据库    
	@Autowired    
    private LogService logService;    
    //本地异常日志记录对象    
     private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);    
    
    //Service层切点    
    @Pointcut("@annotation(com.hongxin.common.SystemServiceLog)")    
     public  void serviceAspect() {    
    }    
    
    //Controller层切点    
    @Pointcut("@annotation(com.hongxin.common.SystemControllerLog)")    
     public  void controllerAspect() {    
    }    
    
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
    @Before("controllerAspect()")    
     public  void doBefore(JoinPoint joinPoint) {    
    
        //读取session中的用户    
        UserInfo user = (UserInfo) ActionContext.getContext().getSession().get("login_user");   
        InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();
		
         try {    
            //*========数据库日志=========*//    
            OperationDiary operationDiary = new OperationDiary();   
            if(user != null)
	       	 {
	       		 operationDiary.setOperationUserId(Integer.toString(user.getId()));
	                operationDiary.setOprationName(user.getName());   
	       	 }else
	       	 {
	       		 operationDiary.setOperationUserId(null);
	                operationDiary.setOprationName(null);   
	       	 }
            operationDiary.setDescription(getControllerMethodDescription(joinPoint));    
            operationDiary.setOperationSource((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));   
            operationDiary.setOprationIp(ip);  
            operationDiary.setOperationDate("2010-10-01");
            //保存数据库    
            logService.save(operationDiary);    
            System.out.println("=====前置通知结束=====");    
        }  catch (Exception e) {    
            //记录本地异常日志    
            logger.error("==前置通知异常==");    
            logger.error("异常信息:{}", e.getMessage());    
        }    
    }    
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        //读取session中的用户    
        UserInfo user = (UserInfo) ActionContext.getContext().getSession().get("login_user");   
        //获取请求ip    
        InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();
         try {    
              /*========控制台输出=========*/    
        	 
        	 OperationDiary operationDiary = new OperationDiary();   
        	 if(user != null)
        	 {
        		 operationDiary.setOperationUserId(Integer.toString(user.getId()));
                 operationDiary.setOprationName(user.getName());   
        	 }else
        	 {
        		 operationDiary.setOperationUserId(null);
                 operationDiary.setOprationName(null);   
        	 }
             operationDiary.setDescription(getServiceMthodDescription(joinPoint));    
             operationDiary.setOperationSource((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));   
             operationDiary.setOprationIp(ip);  
             operationDiary.setOperationCode(e.getClass().getName());
             operationDiary.setExceptionDetail(e.getMessage());
             operationDiary.setOperationDate("2020-10-01");
             logService.save(operationDiary);    
            System.out.println("=====异常通知结束=====");    
        }  catch (Exception ex) {    
            //记录本地异常日志    
            logger.error("==异常通知异常==");    
            logger.error("异常信息:{}", ex.getMessage());    
        }    
         /*==========记录本地异常日志==========*/    
    
    }    
    
    
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     @SuppressWarnings("rawtypes")
	public  static String getServiceMthodDescription(JoinPoint joinPoint)    
             throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemServiceLog. class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     @SuppressWarnings("rawtypes")
	public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemControllerLog.class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
}
