/*      */ package com.hongxin.service;
/*      */ 
/*      */ import com.fuiou.data.AppRegReqData;
/*      */ import com.fuiou.data.AppSignCardReqData;
/*      */ import com.fuiou.data.AppTransReqData;
/*      */ import com.fuiou.data.ArtifRegReqData;
/*      */ import com.fuiou.data.CancelUserForPageReqData;
/*      */ import com.fuiou.data.ChangeCard2ReqData;
/*      */ import com.fuiou.data.CommonRspData;
/*      */ import com.fuiou.data.FreezeReqData;
/*      */ import com.fuiou.data.ModifyMobileReqData;
/*      */ import com.fuiou.data.PreAuthCancelReqData;
/*      */ import com.fuiou.data.PreAuthReqData;
/*      */ import com.fuiou.data.PreAuthRspData;
/*      */ import com.fuiou.data.QueryBalanceReqData;
/*      */ import com.fuiou.data.QueryBalanceResultData;
/*      */ import com.fuiou.data.QueryBalanceRspData;
/*      */ import com.fuiou.data.QueryChangeCardReqData;
/*      */ import com.fuiou.data.QueryChangeCardRspData;
/*      */ import com.fuiou.data.QueryCzTxReq;
/*      */ import com.fuiou.data.QueryCzTxRspData;
/*      */ import com.fuiou.data.QueryCzTxRspDetailData;
/*      */ import com.fuiou.data.QueryDetail;
/*      */ import com.fuiou.data.QueryOpResultSet;
/*      */ import com.fuiou.data.QueryReqData;
/*      */ import com.fuiou.data.QueryRspData;
/*      */ import com.fuiou.data.QueryTxnReqData;
/*      */ import com.fuiou.data.QueryTxnRspData;
/*      */ import com.fuiou.data.QueryTxnRspDetailData;
/*      */ import com.fuiou.data.QueryUserInfsReqData;
/*      */ import com.fuiou.data.QueryUserInfsRspData;
/*      */ import com.fuiou.data.QueryUserInfsRspDetailData;
/*      */ import com.fuiou.data.QueryUserInfs_v2ReqData;
/*      */ import com.fuiou.data.QueryUserInfs_v2RspData;
/*      */ import com.fuiou.data.QueryUserInfs_v2RspDetailData;
/*      */ import com.fuiou.data.RegReqData;
/*      */ import com.fuiou.data.ResetPassWordReqData;
/*      */ import com.fuiou.data.ReturnLoginReqData;
/*      */ import com.fuiou.data.TransferBmuAndFreezeReqData;
/*      */ import com.fuiou.data.TransferBmuReqData;
/*      */ import com.fuiou.data.UnFreezeRspData;
/*      */ import com.fuiou.data.WebArtifRegReqData;
/*      */ import com.fuiou.data.WebRegReqData;
/*      */ import com.fuiou.data.Wy500012ReqData;
/*      */ import com.fuiou.util.ConfigReader;
/*      */ import com.fuiou.util.Object2Xml;
/*      */ import com.fuiou.util.SecurityUtils;
/*      */ import com.fuiou.util.StringUtil;
import com.hongxin.utils.Constants;
import com.hongxin.utils.TimeId;
import com.hongxin.utils.WebUtils;
/*      */ import com.thoughtworks.xstream.XStream;
/*      */ import com.thoughtworks.xstream.io.xml.DomDriver;
/*      */ import java.io.OutputStream;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ 
/*      */ public class FuiouService
/*      */ {
/*      */   public static CommonRspData reg(RegReqData reqData)
/*      */     throws Exception
/*      */   {
/*   73 */     if (reqData == null) {
/*   74 */       throw new Exception("请求参数为空");
/*      */     }
/*   76 */     reqData.setSignature(SecurityUtils.sign(reqData.createSignValueForReg()));
/*   77 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/reg.action", reqData);
/*   78 */     if (StringUtils.isEmpty(result)) {
/*   79 */       throw new Exception("返回报文为空");
/*      */     }
/*   81 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*   82 */     String signature = Object2Xml.getByTag(result, "signature");
/*   83 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*   84 */     if (!signVal) {
/*   85 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*   87 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*   88 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static QueryRspData query(QueryReqData queryReqData)
/*      */     throws Exception
/*      */   {
/*   98 */     if (queryReqData == null) {
/*   99 */       throw new Exception("请求参数为空");
/*      */     }
/*  101 */     queryReqData.setSignature(SecurityUtils.sign(queryReqData.createSignValue()));
/*  102 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/query.action", queryReqData);
/*  103 */     if (StringUtils.isEmpty(result)) {
/*  104 */       throw new Exception("返回报文为空");
/*      */     }
/*  106 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  107 */     String signature = Object2Xml.getByTag(result, "signature");
/*  108 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  109 */     XStream xstream = new XStream(new DomDriver());
/*  110 */     xstream.alias("plain", QueryRspData.class);
/*  111 */     xstream.alias("opResult", QueryOpResultSet.class);
/*  112 */     xstream.alias("detail", QueryDetail.class);
/*      */ 
/*  114 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  115 */     if (!signVal) {
/*  116 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  118 */     QueryRspData resultData = (QueryRspData)xstream.fromXML(xml);
/*  119 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static QueryBalanceRspData balanceAction(QueryBalanceReqData queryBalanceReqData)
/*      */     throws Exception
/*      */   {
/*  129 */     if (queryBalanceReqData == null) {
/*  130 */       throw new Exception("请求参数为空");
/*      */     }
/*  132 */     queryBalanceReqData.setSignature(SecurityUtils.sign(queryBalanceReqData.createSignValue()));


/*  133 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/BalanceAction.action", queryBalanceReqData);
/*  134 */     if (StringUtils.isEmpty(result)) {
/*  135 */       throw new Exception("返回报文为空");
/*      */     }
/*  137 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  138 */     String signature = Object2Xml.getByTag(result, "signature");
/*  139 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  140 */     XStream xstream = new XStream(new DomDriver());
/*  141 */     xstream.alias("plain", QueryBalanceRspData.class);
/*  142 */     xstream.alias("result", QueryBalanceResultData.class);
/*      */ 
/*  144 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  145 */     if (!signVal) {
/*  146 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  148 */     QueryBalanceRspData resultData = (QueryBalanceRspData)xstream.fromXML(xml);
/*  149 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static PreAuthRspData preAuth(PreAuthReqData preAuthReqData)
/*      */     throws Exception
/*      */   {
/*  159 */     if (preAuthReqData == null) {
/*  160 */       throw new Exception("请求参数为空");
/*      */     }
/*  162 */     preAuthReqData.setSignature(SecurityUtils.sign(preAuthReqData.createSignValue()));
/*  163 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/preAuth.action", preAuthReqData);
/*  164 */     if (StringUtils.isEmpty(result)) {
/*  165 */       throw new Exception("返回报文为空");
/*      */     }
/*  167 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  168 */     String signature = Object2Xml.getByTag(result, "signature");
/*  169 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  170 */     if (!signVal) {
/*  171 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  173 */     PreAuthRspData rspData = (PreAuthRspData)Object2Xml.xml2object(plain, "plain", PreAuthRspData.class);
/*  174 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData preAuthCancel(PreAuthCancelReqData preAuthCancelReqData)
/*      */     throws Exception
/*      */   {
/*  184 */     if (preAuthCancelReqData == null) {
/*  185 */       throw new Exception("请求参数为空");
/*      */     }
/*  187 */     preAuthCancelReqData.setSignature(SecurityUtils.sign(preAuthCancelReqData.createSignValue()));
/*  188 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/preAuthCancel.action", preAuthCancelReqData);
/*  189 */     if (StringUtils.isEmpty(result)) {
/*  190 */       throw new Exception("返回报文为空");
/*      */     }
/*  192 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  193 */     String signature = Object2Xml.getByTag(result, "signature");
/*  194 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  195 */     if (!signVal) {
/*  196 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  198 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  199 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData transferBmu(TransferBmuReqData transferBmuReqData)
/*      */     throws Exception
/*      */   {
/*  209 */     if (transferBmuReqData == null) {
/*  210 */       throw new Exception("请求参数为空");
/*      */     }
/*  212 */     transferBmuReqData.setSignature(SecurityUtils.sign(transferBmuReqData.createSignValue()));
/*  213 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBmu.action", transferBmuReqData);
/*  214 */     if (StringUtils.isEmpty(result)) {
/*  215 */       throw new Exception("返回报文为空");
/*      */     }
/*  217 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  218 */     String signature = Object2Xml.getByTag(result, "signature");
/*  219 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  220 */     if (!signVal) {
/*  221 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  223 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  224 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData transferBu(TransferBmuReqData transferBmuReqData)
/*      */     throws Exception
/*      */   {
/*  234 */     if (transferBmuReqData == null) {
/*  235 */       throw new Exception("请求参数为空");
/*      */     }
/*  237 */     transferBmuReqData.setSignature(SecurityUtils.sign(transferBmuReqData.createSignValue()));
/*  238 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBu.action", transferBmuReqData);
/*  239 */     if (StringUtils.isEmpty(result)) {
/*  240 */       throw new Exception("返回报文为空");
/*      */     }
/*  242 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  243 */     String signature = Object2Xml.getByTag(result, "signature");
/*  244 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  245 */     if (!signVal) {
/*  246 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  248 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  249 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static QueryUserInfsRspData queryUserInfs(QueryUserInfsReqData queryUserInfsReqData)
/*      */     throws Exception
/*      */   {
/*  259 */     if (queryUserInfsReqData == null) {
/*  260 */       throw new Exception("请求参数为空");
/*      */     }
/*  262 */     queryUserInfsReqData.setSignature(SecurityUtils.sign(queryUserInfsReqData.createSignValue()));
/*  263 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryUserInfs.action", queryUserInfsReqData);
/*  264 */     if (StringUtils.isEmpty(result)) {
/*  265 */       throw new Exception("返回报文为空");
/*      */     }
/*  267 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  268 */     String signature = Object2Xml.getByTag(result, "signature");
/*  269 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  270 */     XStream xstream = new XStream(new DomDriver());
/*  271 */     xstream.alias("plain", QueryUserInfsRspData.class);
/*  272 */     xstream.alias("result", QueryUserInfsRspDetailData.class);
/*      */ 
/*  274 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  275 */     if (!signVal) {
/*  276 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  278 */     QueryUserInfsRspData resultData = (QueryUserInfsRspData)xstream.fromXML(xml);
/*  279 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static void webReg(WebRegReqData webRegReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  312 */     if (webRegReqData == null) {
/*  313 */       throw new Exception("请求参数为空");
/*      */     }
/*  315 */     Map paramMap = new HashMap();
/*  316 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(webRegReqData.getMchnt_cd()) ? "" : webRegReqData.getMchnt_cd().trim());
/*  317 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(webRegReqData.getMchnt_txn_ssn()) ? "" : webRegReqData.getMchnt_txn_ssn().trim());
/*  318 */     paramMap.put("user_id_from", StringUtil.isEmpty(webRegReqData.getUser_id_from()) ? "" : webRegReqData.getUser_id_from().trim());
/*  319 */     paramMap.put("mobile_no", StringUtil.isEmpty(webRegReqData.getMobile_no()) ? "" : webRegReqData.getMobile_no().trim());
/*  320 */     paramMap.put("cust_nm", StringUtil.isEmpty(webRegReqData.getCust_nm()) ? "" : webRegReqData.getCust_nm().trim());
/*  321 */     paramMap.put("certif_tp", StringUtil.isEmpty(webRegReqData.getCertif_tp()) ? "" : webRegReqData.getCertif_tp().trim());
/*  322 */     paramMap.put("certif_id", StringUtil.isEmpty(webRegReqData.getCertif_id()) ? "" : webRegReqData.getCertif_id().trim());
/*  323 */     paramMap.put("email", StringUtil.isEmpty(webRegReqData.getEmail()) ? "" : webRegReqData.getEmail().trim());
/*  324 */     paramMap.put("city_id", StringUtil.isEmpty(webRegReqData.getCity_id()) ? "" : webRegReqData.getCity_id().trim());
/*  325 */     paramMap.put("parent_bank_id", StringUtil.isEmpty(webRegReqData.getParent_bank_id()) ? "" : webRegReqData.getParent_bank_id().trim());
/*  326 */     paramMap.put("bank_nm", StringUtil.isEmpty(webRegReqData.getBank_nm()) ? "" : webRegReqData.getBank_nm().trim());
/*  327 */     paramMap.put("capAcntNo", StringUtil.isEmpty(webRegReqData.getCapAcntNo()) ? "" : webRegReqData.getCapAcntNo().trim());
/*  328 */     paramMap.put("page_notify_url", StringUtil.isEmpty(webRegReqData.getPage_notify_url()) ? "" : webRegReqData.getPage_notify_url().trim());
/*  329 */     paramMap.put("back_notify_url", StringUtil.isEmpty(webRegReqData.getBack_notify_url()) ? "" : webRegReqData.getBack_notify_url().trim());
/*  330 */     paramMap.put("signature", SecurityUtils.sign(webRegReqData.createSignValueForReg()));
/*  331 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/webReg.action", paramMap, "utf-8");
/*  332 */     OutputStream out = response.getOutputStream();
/*  333 */     out.write(result.getBytes("utf-8"));
/*  334 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void webLogin(ReturnLoginReqData returnLoginReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  342 */     if (returnLoginReqData == null) {
/*  343 */       throw new Exception("请求参数为空");
/*      */     }
/*  345 */     Map paramMap = new HashMap();
/*  346 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(returnLoginReqData.getMchnt_cd()) ? "" : returnLoginReqData.getMchnt_cd().trim());
/*  347 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(returnLoginReqData.getMchnt_txn_ssn()) ? "" : returnLoginReqData.getMchnt_txn_ssn().trim());
/*  348 */     paramMap.put("cust_no", StringUtil.isEmpty(returnLoginReqData.getCust_no()) ? "" : returnLoginReqData.getCust_no().trim());
/*  349 */     paramMap.put("location", StringUtil.isEmpty(returnLoginReqData.getLocation()) ? "" : returnLoginReqData.getLocation().trim());
/*  350 */     paramMap.put("amt", StringUtil.isEmpty(returnLoginReqData.getAmt()) ? "" : returnLoginReqData.getAmt().trim());
/*  351 */     paramMap.put("signature", SecurityUtils.sign(returnLoginReqData.createSignValueFor()));
/*  352 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/webLogin.action", paramMap, "utf-8");
/*  353 */     OutputStream out = response.getOutputStream();
/*  354 */     out.write(result.getBytes("utf-8"));
/*  355 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static CommonRspData freeze(FreezeReqData freezeReqData)
/*      */     throws Exception
/*      */   {
/*  366 */     if (freezeReqData == null) {
/*  367 */       throw new Exception("请求参数为空");
/*      */     }
/*  369 */     freezeReqData.setSignature(SecurityUtils.sign(freezeReqData.createSignValue()));
/*  370 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/freeze.action", freezeReqData);
/*  371 */     if (StringUtils.isEmpty(result)) {
/*  372 */       throw new Exception("返回报文为空");
/*      */     }
/*  374 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  375 */     String signature = Object2Xml.getByTag(result, "signature");
/*  376 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  377 */     if (!signVal) {
/*  378 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  380 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  381 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static UnFreezeRspData unFreeze(FreezeReqData freezeReqData)
/*      */     throws Exception
/*      */   {
/*  392 */     if (freezeReqData == null) {
/*  393 */       throw new Exception("请求参数为空");
/*      */     }
/*  395 */     freezeReqData.setSignature(SecurityUtils.sign(freezeReqData.createSignValue()));
/*  396 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/unFreeze.action", freezeReqData);
/*  397 */     if (StringUtils.isEmpty(result)) {
/*  398 */       throw new Exception("返回报文为空");
/*      */     }
/*  400 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  401 */     String signature = Object2Xml.getByTag(result, "signature");
/*  402 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  403 */     if (!signVal) {
/*  404 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  406 */     UnFreezeRspData rspData = (UnFreezeRspData)Object2Xml.xml2object(plain, "plain", UnFreezeRspData.class);
/*  407 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData transferBmuAndFreeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*      */     throws Exception
/*      */   {
/*  417 */     if (bmuAndFreezeReqData == null) {
/*  418 */       throw new Exception("请求参数为空");
/*      */     }
/*  420 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/*  421 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBmuAndFreeze.action", bmuAndFreezeReqData);
/*  422 */     if (StringUtils.isEmpty(result)) {
/*  423 */       throw new Exception("返回报文为空");
/*      */     }
/*  425 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  426 */     String signature = Object2Xml.getByTag(result, "signature");
/*  427 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  428 */     if (!signVal) {
/*  429 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  431 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  432 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData transferBuAndFreeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*      */     throws Exception
/*      */   {
/*  443 */     if (bmuAndFreezeReqData == null) {
/*  444 */       throw new Exception("请求参数为空");
/*      */     }
/*  446 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/*  447 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBuAndFreeze.action", bmuAndFreezeReqData);
/*  448 */     if (StringUtils.isEmpty(result)) {
/*  449 */       throw new Exception("返回报文为空");
/*      */     }
/*  451 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  452 */     String signature = Object2Xml.getByTag(result, "signature");
/*  453 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  454 */     if (!signVal) {
/*  455 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  457 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  458 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static QueryTxnRspData queryTxn(QueryTxnReqData queryTxnReqData)
/*      */     throws Exception
/*      */   {
/*  468 */     if (queryTxnReqData == null) {
/*  469 */       throw new Exception("请求参数为空");
/*      */     }
/*  471 */     queryTxnReqData.setSignature(SecurityUtils.sign(queryTxnReqData.createSignValue()));
/*  472 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryTxn.action", queryTxnReqData);
/*  473 */     if (StringUtils.isEmpty(result)) {
/*  474 */       throw new Exception("返回报文为空");
/*      */     }
/*  476 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  477 */     String signature = Object2Xml.getByTag(result, "signature");
/*  478 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  479 */     XStream xstream = new XStream(new DomDriver());
/*  480 */     xstream.alias("plain", QueryTxnRspData.class);
/*  481 */     xstream.alias("result", QueryTxnRspDetailData.class);
/*      */ 
/*  483 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  484 */     if (!signVal) {
/*  485 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  487 */     QueryTxnRspData resultData = (QueryTxnRspData)xstream.fromXML(xml);
/*  488 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static UnFreezeRspData transferBuAndFreeze2Freeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*      */     throws Exception
/*      */   {
/*  498 */     if (bmuAndFreezeReqData == null) {
/*  499 */       throw new Exception("请求参数为空");
/*      */     }
/*  501 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/*  502 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBuAndFreeze2Freeze.action", bmuAndFreezeReqData);
/*  503 */     if (StringUtils.isEmpty(result)) {
/*  504 */       throw new Exception("返回报文为空");
/*      */     }
/*  506 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  507 */     String signature = Object2Xml.getByTag(result, "signature");
/*  508 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  509 */     if (!signVal) {
/*  510 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  512 */     UnFreezeRspData resultData = (UnFreezeRspData)Object2Xml.xml2object(plain, "plain", UnFreezeRspData.class);
/*  513 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static QueryCzTxRspData querycztx(QueryCzTxReq queryCzTxReq)
/*      */     throws Exception
/*      */   {
/*  523 */     if (queryCzTxReq == null) {
/*  524 */       throw new Exception("请求参数为空");
/*      */     }
			String src="";
			try {
				SecurityUtils.sign(queryCzTxReq.createSignValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
/*  526 */     queryCzTxReq.setSignature(src);
/*  527 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/querycztx.action", queryCzTxReq);
/*  528 */     if (StringUtils.isEmpty(result)) {
/*  529 */       throw new Exception("返回报文为空");
/*      */     }
/*  531 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  532 */     String signature = Object2Xml.getByTag(result, "signature");
/*  533 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  534 */     XStream xstream = new XStream(new DomDriver());
/*  535 */     xstream.alias("plain", QueryCzTxRspData.class);
/*  536 */     xstream.alias("result", QueryCzTxRspDetailData.class);
/*      */ 
/*  538 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  539 */     if (!signVal) {
/*  540 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  542 */     QueryCzTxRspData resultData = (QueryCzTxRspData)xstream.fromXML(xml);
/*  543 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static CommonRspData artifReg(ArtifRegReqData reqData)
/*      */     throws Exception
/*      */   {
/*  552 */     if (reqData == null) {
/*  553 */       throw new Exception("请求参数为空");
/*      */     }
/*  555 */     reqData.setSignature(SecurityUtils.sign(reqData.createSignValueForReg()));
/*  556 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/artifReg.action", reqData);
/*  557 */     if (StringUtils.isEmpty(result)) {
/*  558 */       throw new Exception("返回报文为空");
/*      */     }
/*  560 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  561 */     String signature = Object2Xml.getByTag(result, "signature");
/*  562 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  563 */     if (!signVal) {
/*  564 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  566 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  567 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static QueryUserInfs_v2RspData queryUserInfs_v2(QueryUserInfs_v2ReqData queryUserInfs_v2ReqData)
/*      */     throws Exception
/*      */   {
/*  578 */     if (queryUserInfs_v2ReqData == null) {
/*  579 */       throw new Exception("请求参数为空");
/*      */     }
/*  581 */     queryUserInfs_v2ReqData.setSignature(SecurityUtils.sign(queryUserInfs_v2ReqData.createSearchSigature_v2()));
/*  582 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryUserInfs_v2.action", queryUserInfs_v2ReqData);
/*  583 */     if (StringUtils.isEmpty(result)) {
/*  584 */       throw new Exception("返回报文为空");
/*      */     }
/*  586 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  587 */     String signature = Object2Xml.getByTag(result, "signature");
/*  588 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  589 */     XStream xstream = new XStream(new DomDriver());
/*  590 */     xstream.alias("plain", QueryUserInfs_v2RspData.class);
/*  591 */     xstream.alias("result", QueryUserInfs_v2RspDetailData.class);
/*      */ 
/*  593 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  594 */     if (!signVal) {
/*  595 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  597 */     QueryUserInfs_v2RspData resultData = (QueryUserInfs_v2RspData)xstream.fromXML(xml);
/*  598 */     return resultData;
/*      */   }
/*      */ 
/*      */   public static void appSignCard(AppSignCardReqData appSignCardReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  606 */     if (appSignCardReqData == null) {
/*  607 */       throw new Exception("请求参数为空");
/*      */     }
/*  609 */     Map paramMap = new HashMap();
/*  610 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appSignCardReqData.getMchnt_cd()) ? "" : appSignCardReqData.getMchnt_cd().trim());
/*  611 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appSignCardReqData.getMchnt_txn_ssn()) ? "" : appSignCardReqData.getMchnt_txn_ssn().trim());
/*  612 */     paramMap.put("login_id", StringUtil.isEmpty(appSignCardReqData.getLogin_id()) ? "" : appSignCardReqData.getLogin_id().trim());
/*  613 */     paramMap.put("mobile", StringUtil.isEmpty(appSignCardReqData.getMobile()) ? "" : appSignCardReqData.getMobile().trim());
/*  614 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appSignCardReqData.getPage_notify_url()) ? "" : appSignCardReqData.getPage_notify_url().trim());
/*  615 */     paramMap.put("signature", SecurityUtils.sign(appSignCardReqData.createSignValue()));
/*  616 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/appSign_card.action", paramMap, "utf-8");
/*  617 */     OutputStream out = response.getOutputStream();
/*  618 */     out.write(result.getBytes("utf-8"));
/*  619 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void wy500012(Wy500012ReqData wy500012ReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  626 */     if (wy500012ReqData == null) {
/*  627 */       throw new Exception("请求参数为空");
/*      */     }
/*  629 */     Map paramMap = new HashMap();
/*  630 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(wy500012ReqData.getMchnt_cd()) ? "" : wy500012ReqData.getMchnt_cd().trim());
/*  631 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(wy500012ReqData.getMchnt_txn_ssn()) ? "" : wy500012ReqData.getMchnt_txn_ssn().trim());
/*  632 */     paramMap.put("login_id", StringUtil.isEmpty(wy500012ReqData.getLogin_id()) ? "" : wy500012ReqData.getLogin_id().trim());
/*  633 */     paramMap.put("amt", StringUtil.isEmpty(wy500012ReqData.getAmt()) ? "" : wy500012ReqData.getAmt().trim());
/*  634 */     paramMap.put("order_pay_type", StringUtil.isEmpty(wy500012ReqData.getOrder_pay_type()) ? "" : wy500012ReqData.getOrder_pay_type().trim());
/*  635 */     paramMap.put("iss_ins_cd", StringUtil.isEmpty(wy500012ReqData.getIss_ins_cd()) ? "" : wy500012ReqData.getIss_ins_cd().trim());
/*  636 */     paramMap.put("page_notify_url", StringUtil.isEmpty(wy500012ReqData.getPage_notify_url()) ? "" : wy500012ReqData.getPage_notify_url().trim());
/*  637 */     paramMap.put("back_notify_url", StringUtil.isEmpty(wy500012ReqData.getBack_notify_url()) ? "" : wy500012ReqData.getBack_notify_url().trim());
/*  638 */     paramMap.put("signature", SecurityUtils.sign(wy500012ReqData.createSignValue()));
/*  639 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/500012.action", paramMap, "utf-8");
/*  640 */     OutputStream out = response.getOutputStream();
/*  641 */     out.write(result.getBytes("utf-8"));
/*  642 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void cancelUserForPage(CancelUserForPageReqData cancelUserForPageReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  649 */     if (cancelUserForPageReqData == null) {
/*  650 */       throw new Exception("请求参数为空");
/*      */     }
/*  652 */     Map paramMap = new HashMap();
/*  653 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(cancelUserForPageReqData.getMchnt_cd()) ? "" : cancelUserForPageReqData.getMchnt_cd().trim());
/*  654 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(cancelUserForPageReqData.getMchnt_txn_ssn()) ? "" : cancelUserForPageReqData.getMchnt_txn_ssn().trim());
/*  655 */     paramMap.put("login_id", StringUtil.isEmpty(cancelUserForPageReqData.getLogin_id()) ? "" : cancelUserForPageReqData.getLogin_id().trim());
/*  656 */     paramMap.put("signature", SecurityUtils.sign(cancelUserForPageReqData.createSignValue()));
/*  657 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/cancelUserForPage.action", paramMap, "utf-8");
/*  658 */     OutputStream out = response.getOutputStream();
/*  659 */     out.write(result.getBytes("utf-8"));
/*  660 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void p2p400101(ModifyMobileReqData modifyMobileReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  667 */     if (modifyMobileReqData == null) {
/*  668 */       throw new Exception("请求参数为空");
/*      */     }
/*  670 */     Map paramMap = new HashMap();
/*  671 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(modifyMobileReqData.getMchnt_cd()) ? "" : modifyMobileReqData.getMchnt_cd().trim());
/*  672 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(modifyMobileReqData.getMchnt_txn_ssn()) ? "" : modifyMobileReqData.getMchnt_txn_ssn().trim());
/*  673 */     paramMap.put("login_id", StringUtil.isEmpty(modifyMobileReqData.getLogin_id()) ? "" : modifyMobileReqData.getLogin_id().trim());
/*  674 */     paramMap.put("page_notify_url", StringUtil.isEmpty(modifyMobileReqData.getPage_notify_url()) ? "" : modifyMobileReqData.getPage_notify_url().trim());
/*  675 */     
			   paramMap.put("signature", SecurityUtils.sign(modifyMobileReqData.createSignValue()));

/*  676 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/400101.action", paramMap, "utf-8");
/*  677 */     OutputStream out = response.getOutputStream();
System.out.println(result);
/*  678 */     out.write(result.getBytes("utf-8"));
/*  679 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void app400101(ModifyMobileReqData modifyMobileReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  686 */     if (modifyMobileReqData == null) {
/*  687 */       throw new Exception("请求参数为空");
/*      */     }
/*  689 */     Map paramMap = new HashMap();
/*  690 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(modifyMobileReqData.getMchnt_cd()) ? "" : modifyMobileReqData.getMchnt_cd().trim());
/*  691 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(modifyMobileReqData.getMchnt_txn_ssn()) ? "" : modifyMobileReqData.getMchnt_txn_ssn().trim());
/*  692 */     paramMap.put("login_id", StringUtil.isEmpty(modifyMobileReqData.getLogin_id()) ? "" : modifyMobileReqData.getLogin_id().trim());
/*  693 */     paramMap.put("page_notify_url", StringUtil.isEmpty(modifyMobileReqData.getPage_notify_url()) ? "" : modifyMobileReqData.getPage_notify_url().trim());
/*  694 */     paramMap.put("signature", SecurityUtils.sign(modifyMobileReqData.createSignValue()));
/*  695 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/400101.action", paramMap, "utf-8");
/*  696 */     OutputStream out = response.getOutputStream();
/*  697 */     out.write(result.getBytes("utf-8"));
/*  698 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static QueryChangeCardRspData queryChangeCard(QueryChangeCardReqData queryChangeCardReqData)
/*      */     throws Exception
/*      */   {
/*  706 */     if (queryChangeCardReqData == null) {
/*  707 */       throw new Exception("请求参数为空");
/*      */     }
/*  709 */     queryChangeCardReqData.setSignature(SecurityUtils.sign(queryChangeCardReqData.createSignValue()));
/*  710 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryChangeCard.action", queryChangeCardReqData);
/*  711 */     if (StringUtils.isEmpty(result)) {
/*  712 */       throw new Exception("返回报文为空");
/*      */     }
/*  714 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  715 */     String signature = Object2Xml.getByTag(result, "signature");
/*  716 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  717 */     if (!signVal) {
/*  718 */       throw new Exception("接口返回签名错误!");
/*      */     }
/*  720 */     QueryChangeCardRspData rspData = (QueryChangeCardRspData)Object2Xml.xml2object(plain, "plain", QueryChangeCardRspData.class);
/*  721 */     return rspData;
/*      */   }
/*      */ 
/*      */   public static void changeCard2(ChangeCard2ReqData changeCard2ReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  729 */     if (changeCard2ReqData == null) {
/*  730 */       throw new Exception("请求参数为空");
/*      */     }
/*  732 */     Map paramMap = new HashMap();
/*  733 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(changeCard2ReqData.getMchnt_cd()) ? "" : changeCard2ReqData.getMchnt_cd().trim());
/*  734 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(changeCard2ReqData.getMchnt_txn_ssn()) ? "" : changeCard2ReqData.getMchnt_txn_ssn().trim());
/*  735 */     paramMap.put("login_id", StringUtil.isEmpty(changeCard2ReqData.getLogin_id()) ? "" : changeCard2ReqData.getLogin_id().trim());
/*  736 */     paramMap.put("page_notify_url", StringUtil.isEmpty(changeCard2ReqData.getPage_notify_url()) ? "" : changeCard2ReqData.getPage_notify_url().trim());
/*  737 */     paramMap.put("signature", SecurityUtils.sign(changeCard2ReqData.createSignValue()));
/*  738 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/changeCard2.action", paramMap, "utf-8");
/*  739 */     OutputStream out = response.getOutputStream();
/*  740 */     out.write(result.getBytes("utf-8"));
/*  741 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void appWebReg(AppRegReqData appRegReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  749 */     if (appRegReqData == null) {
/*  750 */       throw new Exception("请求参数为空");
/*      */     }
/*  752 */     Map paramMap = new HashMap();
/*  753 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appRegReqData.getMchnt_cd()) ? "" : appRegReqData.getMchnt_cd().trim());
/*  754 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appRegReqData.getMchnt_txn_ssn()) ? "" : appRegReqData.getMchnt_txn_ssn().trim());
/*  755 */     paramMap.put("user_id_from", StringUtil.isEmpty(appRegReqData.getUser_id_from()) ? "" : appRegReqData.getUser_id_from().trim());
/*  756 */     paramMap.put("mobile_no", StringUtil.isEmpty(appRegReqData.getMobile_no()) ? "" : appRegReqData.getMobile_no().trim());
/*  757 */     paramMap.put("cust_nm", StringUtil.isEmpty(appRegReqData.getCust_nm()) ? "" : appRegReqData.getCust_nm().trim());
/*  758 */     paramMap.put("certif_tp", StringUtil.isEmpty(appRegReqData.getCertif_tp()) ? "" : appRegReqData.getCertif_tp().trim());
/*  759 */     paramMap.put("certif_id", StringUtil.isEmpty(appRegReqData.getCertif_id()) ? "" : appRegReqData.getCertif_id().trim());
/*  760 */     paramMap.put("email", StringUtil.isEmpty(appRegReqData.getEmail()) ? "" : appRegReqData.getEmail().trim());
/*  761 */     paramMap.put("city_id", StringUtil.isEmpty(appRegReqData.getCity_id()) ? "" : appRegReqData.getCity_id().trim());
/*  762 */     paramMap.put("parent_bank_id", StringUtil.isEmpty(appRegReqData.getParent_bank_id()) ? "" : appRegReqData.getParent_bank_id().trim());
/*  763 */     paramMap.put("bank_nm", StringUtil.isEmpty(appRegReqData.getBank_nm()) ? "" : appRegReqData.getBank_nm().trim());
/*  764 */     paramMap.put("capAcntNo", StringUtil.isEmpty(appRegReqData.getCapAcntNo()) ? "" : appRegReqData.getCapAcntNo().trim());
/*  765 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appRegReqData.getBack_notify_url()) ? "" : appRegReqData.getBack_notify_url().trim());
/*  766 */     paramMap.put("signature", SecurityUtils.sign(appRegReqData.createSignValueForReg()));
/*  767 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/appWebReg.action", paramMap, "utf-8");
/*  768 */     OutputStream out = response.getOutputStream();
/*  769 */     out.write(result.getBytes("utf-8"));
/*  770 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void appWebLogin(ReturnLoginReqData returnLoginReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  780 */     if (returnLoginReqData == null) {
/*  781 */       throw new Exception("请求参数为空");
/*      */     }
/*  783 */     Map paramMap = new HashMap();
/*  784 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(returnLoginReqData.getMchnt_cd()) ? "" : returnLoginReqData.getMchnt_cd().trim());
/*  785 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(returnLoginReqData.getMchnt_txn_ssn()) ? "" : returnLoginReqData.getMchnt_txn_ssn().trim());
/*  786 */     paramMap.put("cust_no", StringUtil.isEmpty(returnLoginReqData.getCust_no()) ? "" : returnLoginReqData.getCust_no().trim());
/*  787 */     paramMap.put("location", StringUtil.isEmpty(returnLoginReqData.getLocation()) ? "" : returnLoginReqData.getLocation().trim());
/*  788 */     paramMap.put("amt", StringUtil.isEmpty(returnLoginReqData.getAmt()) ? "" : returnLoginReqData.getAmt().trim());
/*  789 */     paramMap.put("signature", SecurityUtils.sign(returnLoginReqData.createSignValueFor()));
/*  790 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/appWebLogin.action", paramMap, "utf-8");
/*  791 */     OutputStream out = response.getOutputStream();
/*  792 */     out.write(result.getBytes("utf-8"));
/*  793 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void webArtifReg(WebArtifRegReqData webArtifRegReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  801 */     if (webArtifRegReqData == null) {
/*  802 */       throw new Exception("请求参数为空");
/*      */     }
/*  804 */     Map paramMap = new HashMap();
/*  805 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(webArtifRegReqData.getMchnt_cd()) ? "" : webArtifRegReqData.getMchnt_cd().trim());
/*  806 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(webArtifRegReqData.getMchnt_txn_ssn()) ? "" : webArtifRegReqData.getMchnt_txn_ssn().trim());
/*  807 */     paramMap.put("user_id_from", StringUtil.isEmpty(webArtifRegReqData.getUser_id_from()) ? "" : webArtifRegReqData.getUser_id_from().trim());
/*  808 */     paramMap.put("cust_nm", StringUtil.isEmpty(webArtifRegReqData.getCust_nm()) ? "" : webArtifRegReqData.getCust_nm().trim());
/*  809 */     paramMap.put("artif_nm", StringUtil.isEmpty(webArtifRegReqData.getArtif_nm()) ? "" : webArtifRegReqData.getArtif_nm().trim());
/*  810 */     paramMap.put("mobile_no", StringUtil.isEmpty(webArtifRegReqData.getMobile_no()) ? "" : webArtifRegReqData.getMobile_no().trim());
/*  811 */     paramMap.put("certif_id", StringUtil.isEmpty(webArtifRegReqData.getCertif_id()) ? "" : webArtifRegReqData.getCertif_id().trim());
/*  812 */     paramMap.put("email", StringUtil.isEmpty(webArtifRegReqData.getEmail()) ? "" : webArtifRegReqData.getEmail().trim());
/*  813 */     paramMap.put("city_id", StringUtil.isEmpty(webArtifRegReqData.getCity_id()) ? "" : webArtifRegReqData.getCity_id().trim());
/*  814 */     paramMap.put("parent_bank_id", StringUtil.isEmpty(webArtifRegReqData.getParent_bank_id()) ? "" : webArtifRegReqData.getParent_bank_id().trim());
/*  815 */     paramMap.put("bank_nm", StringUtil.isEmpty(webArtifRegReqData.getBank_nm()) ? "" : webArtifRegReqData.getBank_nm().trim());
/*  816 */     paramMap.put("capAcntNo", StringUtil.isEmpty(webArtifRegReqData.getCapAcntNo()) ? "" : webArtifRegReqData.getCapAcntNo().trim());
/*  817 */     paramMap.put("page_notify_url", StringUtil.isEmpty(webArtifRegReqData.getPage_notify_url()) ? "" : webArtifRegReqData.getPage_notify_url().trim());
/*  818 */     paramMap.put("back_notify_url", StringUtil.isEmpty(webArtifRegReqData.getBack_notify_url()) ? "" : webArtifRegReqData.getBack_notify_url().trim());
/*  819 */     paramMap.put("signature", SecurityUtils.sign(webArtifRegReqData.createSignValueForReg()));
/*  820 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/webArtifReg.action", paramMap, "utf-8");
/*  821 */     OutputStream out = response.getOutputStream();
/*  822 */     out.write(result.getBytes("utf-8"));
/*  823 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void app500001(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  831 */     if (appTransReqData == null) {
/*  832 */       throw new Exception("请求参数为空");
/*      */     }
/*  834 */     Map paramMap = new HashMap();
/*  835 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  836 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  837 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  838 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  839 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  840 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  841 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  842 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/500001.action", paramMap, "utf-8");
/*  843 */     OutputStream out = response.getOutputStream();
/*  844 */     out.write(result.getBytes("utf-8"));
/*  845 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void app500002(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  853 */     if (appTransReqData == null) {
/*  854 */       throw new Exception("请求参数为空");
/*      */     }
/*  856 */     Map paramMap = new HashMap();
/*  857 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  858 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  859 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  860 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  861 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  862 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  863 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  864 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/500002.action", paramMap, "utf-8");
/*  865 */     OutputStream out = response.getOutputStream();
/*  866 */     out.write(result.getBytes("utf-8"));
/*  867 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void app500003(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  875 */     if (appTransReqData == null) {
/*  876 */       throw new Exception("请求参数为空");
/*      */     }
/*  878 */     Map paramMap = new HashMap();
/*  879 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  880 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  881 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  882 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  883 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  884 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  885 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  886 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/500003.action", paramMap, "utf-8");
/*  887 */     OutputStream out = response.getOutputStream();
/*  888 */     out.write(result.getBytes("utf-8"));
/*  889 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void p2p500001(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  897 */     if (appTransReqData == null) {
/*  898 */       throw new Exception("请求参数为空");
/*      */     }
/*  900 */     Map paramMap = new HashMap();
/*  901 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  902 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  903 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  904 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  905 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  906 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  907 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  908 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/500001.action", paramMap, "utf-8");
/*  909 */     OutputStream out = response.getOutputStream();
/*  910 */     out.write(result.getBytes("utf-8"));
/*  911 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void p2p500002(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  919 */     if (appTransReqData == null) {
/*  920 */       throw new Exception("请求参数为空");
/*      */     }
/*  922 */     Map paramMap = new HashMap();
/*  923 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  924 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  925 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  926 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  927 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  928 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  929 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  930 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/500002.action", paramMap, "utf-8");
/*  931 */     OutputStream out = response.getOutputStream();
/*  932 */     out.write(result.getBytes("utf-8"));
/*  933 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void p2p500003(AppTransReqData appTransReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  941 */     if (appTransReqData == null) {
/*  942 */       throw new Exception("请求参数为空");
/*      */     }
/*  944 */     Map paramMap = new HashMap();
/*  945 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(appTransReqData.getMchnt_cd()) ? "" : appTransReqData.getMchnt_cd().trim());
/*  946 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(appTransReqData.getMchnt_txn_ssn()) ? "" : appTransReqData.getMchnt_txn_ssn().trim());
/*  947 */     paramMap.put("login_id", StringUtil.isEmpty(appTransReqData.getLogin_id()) ? "" : appTransReqData.getLogin_id().trim());
/*  948 */     paramMap.put("amt", StringUtil.isEmpty(appTransReqData.getAmt()) ? "" : appTransReqData.getAmt().trim());
/*  949 */     paramMap.put("page_notify_url", StringUtil.isEmpty(appTransReqData.getPage_notify_url()) ? "" : appTransReqData.getPage_notify_url().trim());
/*  950 */     paramMap.put("back_notify_url", StringUtil.isEmpty(appTransReqData.getBack_notify_url()) ? "" : appTransReqData.getBack_notify_url().trim());
/*  951 */     paramMap.put("signature", SecurityUtils.sign(appTransReqData.createSignValue()));
/*  952 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/500003.action", paramMap, "utf-8");
/*  953 */     OutputStream out = response.getOutputStream();
/*  954 */     out.write(result.getBytes("utf-8"));
/*  955 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void resetPassWord(ResetPassWordReqData resetPassWordReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  963 */     if (resetPassWordReqData == null) {
/*  964 */       throw new Exception("请求参数为空");
/*      */     }
/*  966 */     Map paramMap = new HashMap();
/*  967 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(resetPassWordReqData.getMchnt_cd()) ? "" : resetPassWordReqData.getMchnt_cd().trim());
/*  968 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(resetPassWordReqData.getMchnt_txn_ssn()) ? "" : resetPassWordReqData.getMchnt_txn_ssn().trim());
/*  969 */     paramMap.put("login_id", StringUtil.isEmpty(resetPassWordReqData.getLogin_id()) ? "" : resetPassWordReqData.getLogin_id().trim());
/*  970 */     paramMap.put("busi_tp", StringUtil.isEmpty(resetPassWordReqData.getBusi_tp()) ? "" : resetPassWordReqData.getBusi_tp().trim());
/*  971 */     paramMap.put("back_url", StringUtil.isEmpty(resetPassWordReqData.getBack_url()) ? "" : resetPassWordReqData.getBack_url().trim());
/*  972 */     paramMap.put("signature", SecurityUtils.sign(resetPassWordReqData.createSignValueFor()));
/*  973 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/resetPassWord.action", paramMap, "utf-8");
/*  974 */     OutputStream out = response.getOutputStream();
/*  975 */     out.write(result.getBytes("utf-8"));
/*  976 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void appResetPassWord(ResetPassWordReqData resetPassWordReqData, HttpServletResponse response)
/*      */     throws Exception
/*      */   {
/*  984 */     if (resetPassWordReqData == null) {
/*  985 */       throw new Exception("请求参数为空");
/*      */     }
/*  987 */     Map paramMap = new HashMap();
/*  988 */     paramMap.put("mchnt_cd", StringUtil.isEmpty(resetPassWordReqData.getMchnt_cd()) ? "" : resetPassWordReqData.getMchnt_cd().trim());
/*  989 */     paramMap.put("mchnt_txn_ssn", StringUtil.isEmpty(resetPassWordReqData.getMchnt_txn_ssn()) ? "" : resetPassWordReqData.getMchnt_txn_ssn().trim());
/*  990 */     paramMap.put("login_id", StringUtil.isEmpty(resetPassWordReqData.getLogin_id()) ? "" : resetPassWordReqData.getLogin_id().trim());
/*  991 */     paramMap.put("busi_tp", StringUtil.isEmpty(resetPassWordReqData.getBusi_tp()) ? "" : resetPassWordReqData.getBusi_tp().trim());
/*  992 */     paramMap.put("back_url", StringUtil.isEmpty(resetPassWordReqData.getBack_url()) ? "" : resetPassWordReqData.getBack_url().trim());
/*  993 */     paramMap.put("signature", SecurityUtils.sign(resetPassWordReqData.createSignValueFor()));
/*  994 */     String result = WebUtils.genForwardHtml(ConfigReader.getConfig("jzhUrl") + "/app/appResetPassWord.action", paramMap, "utf-8");
/*  995 */     OutputStream out = response.getOutputStream();
/*  996 */     out.write(result.getBytes("utf-8"));
/*  997 */     out.flush();
/*      */   }
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/* 1023 */    QueryBalanceReqData qb=new QueryBalanceReqData();
				try {
				qb.setMchnt_cd(Constants.MCHNT_CD);
				qb.setMchnt_txn_ssn(TimeId.generateSequenceNo());
				qb.setMchnt_txn_dt("20160520");
				qb.setCust_no("15385538970");
				QueryBalanceRspData result = balanceAction(qb);
				System.out.println(result.getResults().get(0).getCt_balance());
				} catch (Exception e) {
					e.printStackTrace();
	}
/*      */ }}

/* Location:           D:\download\我的下载\金账户测试材料\金账户测试材料\金账户测试材料\金账户测试材料\测试材料\JAVA金账户SDKv0.04\金账户SDKv0.04\fuiou_goldacnt_sdk-0.04.jar
 * Qualified Name:     com.fuiou.service.FuiouService
 * JD-Core Version:    0.6.2
 */