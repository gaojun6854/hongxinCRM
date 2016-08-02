package com.hongxin.utils;
import com.fuiou.http.HttpClientHelper;
/*    */ 
/*    */ import com.fuiou.util.ConfigReader;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class WebUtils
/*    */ {
/*    */   public static String sendHttp(String url, Object parameters)
/*    */     throws Exception
/*    */   {
/* 11 */     String outStr = "";
/*    */     try {
/* 13 */       String charSet = "UTF-8";
/* 14 */       String timeOut = ConfigReader.getConfig("TimeOut");
/* 15 */       outStr = HttpClientHelper.doHttp(url, charSet, parameters, timeOut);
/* 16 */       if (outStr == null) {
/* 17 */         throw new Exception("请求接口失败!");
/*    */       }
/* 19 */       System.out.println("接口返回=" + outStr);
/*    */     } catch (Exception e) {
/* 21 */       e.printStackTrace();
/* 22 */       throw new Exception("请求接口失败!");
/*    */     }
/* 24 */     return outStr;
/*    */   }
/*    */ 
/*    */   public static String sendHttp(String url, Map parameters) throws Exception {
/* 28 */     String outStr = "";
/*    */     try {
/* 30 */       String charSet = "UTF-8";
/* 31 */       String timeOut = ConfigReader.getConfig("TimeOut");
/* 32 */       outStr = HttpClientHelper.doHttp(url, charSet, parameters, timeOut);
/* 33 */       if (outStr == null) {
/* 34 */         throw new Exception("请求接口失败!");
/*    */       }
/* 36 */       System.out.println("接口返回=" + outStr);
/*    */     } catch (Exception e) {
/* 38 */       e.printStackTrace();
/* 39 */       throw new Exception("请求接口失败!");
/*    */     }
/* 41 */     return outStr;
/*    */   }
/*    */ 
/*    */   public static String genForwardHtml(String url, Map<String, String> parameters, String charset)
/*    */   {
/* 47 */     StringBuffer returnHtml = new StringBuffer("");
/* 48 */     if (!"".equals(url))
/*    */     {
/* 50 */       returnHtml.append("<html>");
/* 51 */       String head = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\" pageEncoding=\"" + charset + "\" />";
/* 52 */       returnHtml.append(head);
/* 53 */       returnHtml.append("<title>loading</title>");
/* 54 */       returnHtml.append("<style type=\"text/css\">");
/* 55 */       returnHtml.append("body{margin:200px auto;font-family: \"宋体\", Arial;font-size: 12px;color: #369;text-align: center;}");
/* 56 */       returnHtml.append("#1{height:auto; width:78px; margin:0 auto;}");
/* 57 */       returnHtml.append("#2{height:auto; width:153px; margin:0 auto;}");
/* 58 */       returnHtml.append("vertical-align: bottom;}");
/* 59 */       returnHtml.append("</style>");
/* 60 */       returnHtml.append("</head>");
/* 61 */       returnHtml.append("<body>");
/* 62 */       returnHtml.append("<div id=\"3\">交易处理中...</div>");
/* 63 */       returnHtml.append("<form name=\"forwardForm\" action=\"").append(url).append("\" method=\"POST\">");
/* 64 */       System.out.println("WebUtils genForwardHtml::url=" + url);
/* 65 */       Iterator keyIterator = parameters.keySet().iterator();
/* 66 */       while (keyIterator.hasNext())
/*    */       {
/* 68 */         Object key = keyIterator.next();
/* 69 */         returnHtml.append("  <input type=\"hidden\" name=\"").append(key.toString()).append("\" value=\"").append((String)parameters.get(key)).append("\"/>");
/* 70 */         System.out.println("WebUtils genForwardHtml::" + key.toString() + "=" + (String)parameters.get(key));
/*    */       }
/* 72 */       returnHtml.append("</form>");
/* 73 */       returnHtml.append("<SCRIPT LANGUAGE=\"Javascript\">");
/* 74 */       returnHtml.append("    document.forwardForm.submit();");
/* 75 */       returnHtml.append("</SCRIPT>");
/* 76 */       returnHtml.append("</body>");
/* 77 */       returnHtml.append("</html>");
/*    */     }
/* 79 */     return returnHtml.toString();
/*    */   }
/*    */ }

