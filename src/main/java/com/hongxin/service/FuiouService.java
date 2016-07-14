/*     */ package com.hongxin.service;
/*     */ 
/*     */ import com.fuiou.data.ArtifRegReqData;
/*     */ import com.fuiou.data.CommonRspData;
/*     */ import com.fuiou.data.FreezeReqData;
/*     */ import com.fuiou.data.ModifyUserInfReqData;
/*     */ import com.fuiou.data.PreAuthCancelReqData;
/*     */ import com.fuiou.data.PreAuthReqData;
/*     */ import com.fuiou.data.PreAuthRspData;
/*     */ import com.fuiou.data.QueryBalanceReqData;
/*     */ import com.fuiou.data.QueryBalanceResultData;
/*     */ import com.fuiou.data.QueryBalanceRspData;
/*     */ import com.fuiou.data.QueryDetail;
/*     */ import com.fuiou.data.QueryOpResultSet;
/*     */ import com.fuiou.data.QueryReqData;
/*     */ import com.fuiou.data.QueryRspData;
/*     */ import com.fuiou.data.QueryTxnReqData;
/*     */ import com.fuiou.data.QueryTxnRspData;
/*     */ import com.fuiou.data.QueryTxnRspDetailData;
/*     */ import com.fuiou.data.QueryUserInfsReqData;
/*     */ import com.fuiou.data.QueryUserInfsRspData;
/*     */ import com.fuiou.data.QueryUserInfsRspDetailData;
/*     */ import com.fuiou.data.RegReqData;
/*     */ import com.fuiou.data.TransferBmuAndFreezeReqData;
/*     */ import com.fuiou.data.TransferBmuReqData;
/*     */ import com.fuiou.data.UnFreezeRspData;
/*     */ import com.fuiou.http.WebUtils;
/*     */ import com.fuiou.util.ConfigReader;
/*     */ import com.fuiou.util.Object2Xml;
/*     */ import com.fuiou.util.SecurityUtils;
import com.hongxin.utils.Constants;
import com.hongxin.utils.TimeId;
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.io.xml.DomDriver;
/*     */ import java.io.PrintStream;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class FuiouService
/*     */ {
/*     */   public static CommonRspData reg(RegReqData reqData)
/*     */     throws Exception
/*     */   {
/*  49 */     if (reqData == null) {
/*  50 */       throw new Exception("请求参数为空");
/*     */     }
/*  52 */     reqData.setSignature(SecurityUtils.sign(reqData.createSignValueForReg()));
/*  53 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/reg.action", reqData);
/*  54 */     if (StringUtils.isEmpty(result)) {
/*  55 */       throw new Exception("返回报文为空");
/*     */     }
/*  57 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  58 */     String signature = Object2Xml.getByTag(result, "signature");
/*  59 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  60 */     if (!signVal) {
/*  61 */       throw new Exception("接口返回签名错误!");
/*     */     }
/*  63 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/*  64 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static QueryRspData query(QueryReqData queryReqData)
/*     */     throws Exception
/*     */   {
/*  74 */     if (queryReqData == null) {
/*  75 */       throw new Exception("请求参数为空");
/*     */     }
/*  77 */     queryReqData.setSignature(SecurityUtils.sign(queryReqData.createSignValue()));
/*  78 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/query.action", queryReqData);
/*  79 */     if (StringUtils.isEmpty(result)) {
/*  80 */       throw new Exception("返回报文为空");
/*     */     }
/*  82 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/*  83 */     String signature = Object2Xml.getByTag(result, "signature");
/*  84 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/*  85 */     XStream xstream = new XStream(new DomDriver());
/*  86 */     xstream.alias("plain", QueryRspData.class);
/*  87 */     xstream.alias("opResult", QueryOpResultSet.class);
/*  88 */     xstream.alias("detail", QueryDetail.class);
/*     */ 
/*  90 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/*  91 */     if (!signVal) {
/*  92 */       throw new Exception("接口返回签名错误!");
/*     */     }
/*  94 */     QueryRspData resultData = (QueryRspData)xstream.fromXML(xml);
/*  95 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static QueryBalanceRspData balanceAction(QueryBalanceReqData queryBalanceReqData)
/*     */     throws Exception
/*     */   {
/* 105 */     if (queryBalanceReqData == null) {
/* 106 */       throw new Exception("请求参数为空");
/*     */     }
/* 108 */     queryBalanceReqData.setSignature(SecurityUtils.sign(queryBalanceReqData.createSignValue()));
/* 109 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/BalanceAction.action", queryBalanceReqData);
/* 110 */     if (StringUtils.isEmpty(result)) {
/* 111 */       throw new Exception("返回报文为空");
/*     */     }
/* 113 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 114 */     String signature = Object2Xml.getByTag(result, "signature");
/* 115 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/* 116 */     XStream xstream = new XStream(new DomDriver());
/* 117 */     xstream.alias("plain", QueryBalanceRspData.class);
/* 118 */     xstream.alias("result", QueryBalanceResultData.class);
/*     */ 
/* 120 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 121 */     if (!signVal) {
/* 122 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 124 */     QueryBalanceRspData resultData = (QueryBalanceRspData)xstream.fromXML(xml);
/* 125 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static PreAuthRspData preAuth(PreAuthReqData preAuthReqData)
/*     */     throws Exception
/*     */   {
/* 135 */     if (preAuthReqData == null) {
/* 136 */       throw new Exception("请求参数为空");
/*     */     }
/* 138 */     preAuthReqData.setSignature(SecurityUtils.sign(preAuthReqData.createSignValue()));
/* 139 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/preAuth.action", preAuthReqData);
/* 140 */     if (StringUtils.isEmpty(result)) {
/* 141 */       throw new Exception("返回报文为空");
/*     */     }
/* 143 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 144 */     String signature = Object2Xml.getByTag(result, "signature");
/* 145 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 146 */     if (!signVal) {
/* 147 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 149 */     PreAuthRspData rspData = (PreAuthRspData)Object2Xml.xml2object(plain, "plain", PreAuthRspData.class);
/* 150 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData preAuthCancel(PreAuthCancelReqData preAuthCancelReqData)
/*     */     throws Exception
/*     */   {
/* 160 */     if (preAuthCancelReqData == null) {
/* 161 */       throw new Exception("请求参数为空");
/*     */     }
/* 163 */     preAuthCancelReqData.setSignature(SecurityUtils.sign(preAuthCancelReqData.createSignValue()));
/* 164 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/preAuthCancel.action", preAuthCancelReqData);
/* 165 */     if (StringUtils.isEmpty(result)) {
/* 166 */       throw new Exception("返回报文为空");
/*     */     }
/* 168 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 169 */     String signature = Object2Xml.getByTag(result, "signature");
/* 170 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 171 */     if (!signVal) {
/* 172 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 174 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 175 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData transferBmu(TransferBmuReqData transferBmuReqData)
/*     */     throws Exception
/*     */   {
/* 185 */     if (transferBmuReqData == null) {
/* 186 */       throw new Exception("请求参数为空");
/*     */     }
/* 188 */     transferBmuReqData.setSignature(SecurityUtils.sign(transferBmuReqData.createSignValue()));
/* 189 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBmu.action", transferBmuReqData);
/* 190 */     if (StringUtils.isEmpty(result)) {
/* 191 */       throw new Exception("返回报文为空");
/*     */     }
/* 193 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 194 */     String signature = Object2Xml.getByTag(result, "signature");
/* 195 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 196 */     if (!signVal) {
/* 197 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 199 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 200 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData transferBu(TransferBmuReqData transferBmuReqData)
/*     */     throws Exception
/*     */   {
/* 210 */     if (transferBmuReqData == null) {
/* 211 */       throw new Exception("请求参数为空");
/*     */     }
/* 213 */     transferBmuReqData.setSignature(SecurityUtils.sign(transferBmuReqData.createSignValue()));
/* 214 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBu.action", transferBmuReqData);
/* 215 */     if (StringUtils.isEmpty(result)) {
/* 216 */       throw new Exception("返回报文为空");
/*     */     }
/* 218 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 219 */     String signature = Object2Xml.getByTag(result, "signature");
/* 220 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 221 */     if (!signVal) {
/* 222 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 224 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 225 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static QueryUserInfsRspData queryUserInfs(QueryUserInfsReqData queryUserInfsReqData)
/*     */     throws Exception
/*     */   {
/* 235 */     if (queryUserInfsReqData == null) {
/* 236 */       throw new Exception("请求参数为空");
/*     */     }
/* 238 */     queryUserInfsReqData.setSignature(SecurityUtils.sign(queryUserInfsReqData.createSignValue()));
/* 239 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryUserInfs.action", queryUserInfsReqData);
/* 240 */     if (StringUtils.isEmpty(result)) {
/* 241 */       throw new Exception("返回报文为空");
/*     */     }
/* 243 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 244 */     String signature = Object2Xml.getByTag(result, "signature");
/* 245 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/* 246 */     XStream xstream = new XStream(new DomDriver());
/* 247 */     xstream.alias("plain", QueryUserInfsRspData.class);
/* 248 */     xstream.alias("result", QueryUserInfsRspDetailData.class);
/*     */ 
/* 250 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 251 */     if (!signVal) {
/* 252 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 254 */     QueryUserInfsRspData resultData = (QueryUserInfsRspData)xstream.fromXML(xml);
/* 255 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData modifyUserInf(ModifyUserInfReqData modifyUserInfReqData)
/*     */     throws Exception
/*     */   {
/* 265 */     if (modifyUserInfReqData == null) {
/* 266 */       throw new Exception("请求参数为空");
/*     */     }
/* 268 */     modifyUserInfReqData.setSignature(SecurityUtils.sign(modifyUserInfReqData.createSignValue()));
/* 269 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/modifyUserInf.action", modifyUserInfReqData);
/* 270 */     if (StringUtils.isEmpty(result)) {
/* 271 */       throw new Exception("返回报文为空");
/*     */     }
/* 273 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 274 */     String signature = Object2Xml.getByTag(result, "signature");
/* 275 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 276 */     if (!signVal) {
/* 277 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 279 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 280 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData freeze(FreezeReqData freezeReqData)
/*     */     throws Exception
/*     */   {
/* 290 */     if (freezeReqData == null) {
/* 291 */       throw new Exception("请求参数为空");
/*     */     }
/* 293 */     freezeReqData.setSignature(SecurityUtils.sign(freezeReqData.createSignValue()));
/* 294 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/freeze.action", freezeReqData);
/* 295 */     if (StringUtils.isEmpty(result)) {
/* 296 */       throw new Exception("返回报文为空");
/*     */     }
/* 298 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 299 */     String signature = Object2Xml.getByTag(result, "signature");
/* 300 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 301 */     if (!signVal) {
/* 302 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 304 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 305 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static UnFreezeRspData unFreeze(FreezeReqData freezeReqData)
/*     */     throws Exception
/*     */   {
/* 316 */     if (freezeReqData == null) {
/* 317 */       throw new Exception("请求参数为空");
/*     */     }
/* 319 */     freezeReqData.setSignature(SecurityUtils.sign(freezeReqData.createSignValue()));
/* 320 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/unFreeze.action", freezeReqData);
/* 321 */     if (StringUtils.isEmpty(result)) {
/* 322 */       throw new Exception("返回报文为空");
/*     */     }
/* 324 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 325 */     String signature = Object2Xml.getByTag(result, "signature");
/* 326 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 327 */     if (!signVal) {
/* 328 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 330 */     UnFreezeRspData rspData = (UnFreezeRspData)Object2Xml.xml2object(plain, "plain", UnFreezeRspData.class);
/* 331 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData transferBmuAndFreeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*     */     throws Exception
/*     */   {
/* 341 */     if (bmuAndFreezeReqData == null) {
/* 342 */       throw new Exception("请求参数为空");
/*     */     }
/* 344 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/* 345 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBmuAndFreeze.action", bmuAndFreezeReqData);
/* 346 */     if (StringUtils.isEmpty(result)) {
/* 347 */       throw new Exception("返回报文为空");
/*     */     }
/* 349 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 350 */     String signature = Object2Xml.getByTag(result, "signature");
/* 351 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 352 */     if (!signVal) {
/* 353 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 355 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 356 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData transferBuAndFreeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*     */     throws Exception
/*     */   {
/* 367 */     if (bmuAndFreezeReqData == null) {
/* 368 */       throw new Exception("请求参数为空");
/*     */     }
/* 370 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/* 371 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBuAndFreeze.action", bmuAndFreezeReqData);
/* 372 */     if (StringUtils.isEmpty(result)) {
/* 373 */       throw new Exception("返回报文为空");
/*     */     }
/* 375 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 376 */     String signature = Object2Xml.getByTag(result, "signature");
/* 377 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 378 */     if (!signVal) {
/* 379 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 381 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 382 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static QueryTxnRspData queryTxn(QueryTxnReqData queryTxnReqData)
/*     */     throws Exception
/*     */   {
/* 392 */     if (queryTxnReqData == null) {
/* 393 */       throw new Exception("请求参数为空");
/*     */     }
/* 395 */     queryTxnReqData.setSignature(SecurityUtils.sign(queryTxnReqData.createSignValue()));
/* 396 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/queryTxn.action", queryTxnReqData);
/* 397 */     if (StringUtils.isEmpty(result)) {
/* 398 */       throw new Exception("返回报文为空");
/*     */     }
/* 400 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 401 */     String signature = Object2Xml.getByTag(result, "signature");
/* 402 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/* 403 */     XStream xstream = new XStream(new DomDriver());
/* 404 */     xstream.alias("plain", QueryTxnRspData.class);
/* 405 */     xstream.alias("result", QueryTxnRspDetailData.class);
/*     */ 
/* 407 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 408 */     if (!signVal) {
/* 409 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 411 */     QueryTxnRspData resultData = (QueryTxnRspData)xstream.fromXML(xml);
/* 412 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static UnFreezeRspData transferBuAndFreeze2Freeze(TransferBmuAndFreezeReqData bmuAndFreezeReqData)
/*     */     throws Exception
/*     */   {
/* 422 */     if (bmuAndFreezeReqData == null) {
/* 423 */       throw new Exception("请求参数为空");
/*     */     }
/* 425 */     bmuAndFreezeReqData.setSignature(SecurityUtils.sign(bmuAndFreezeReqData.createSignValue()));
/* 426 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/transferBuAndFreeze2Freeze.action", bmuAndFreezeReqData);
/* 427 */     if (StringUtils.isEmpty(result)) {
/* 428 */       throw new Exception("返回报文为空");
/*     */     }
/* 430 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 431 */     String signature = Object2Xml.getByTag(result, "signature");
/* 432 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 433 */     if (!signVal) {
/* 434 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 436 */     UnFreezeRspData resultData = (UnFreezeRspData)Object2Xml.xml2object(plain, "plain", UnFreezeRspData.class);
/* 437 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static QueryTxnRspData querycztx(QueryTxnReqData queryTxnReqData)
/*     */     throws Exception
/*     */   {
/* 447 */     if (queryTxnReqData == null) {
/* 448 */       throw new Exception("请求参数为空");
/*     */     }
/* 450 */     queryTxnReqData.setSignature(SecurityUtils.sign(queryTxnReqData.createSignValue()));
/* 451 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/querycztx.action", queryTxnReqData);
/* 452 */     if (StringUtils.isEmpty(result)) {
/* 453 */       throw new Exception("返回报文为空");
/*     */     }
/* 455 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 456 */     String signature = Object2Xml.getByTag(result, "signature");
/* 457 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + plain;
/* 458 */     XStream xstream = new XStream(new DomDriver());
/* 459 */     xstream.alias("plain", QueryTxnRspData.class);
/* 460 */     xstream.alias("result", QueryTxnRspDetailData.class);
/*     */ 
/* 462 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 463 */     if (!signVal) {
/* 464 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 466 */     QueryTxnRspData resultData = (QueryTxnRspData)xstream.fromXML(xml);
/* 467 */     return resultData;
/*     */   }
/*     */ 
/*     */   public static CommonRspData artifReg(ArtifRegReqData reqData)
/*     */     throws Exception
/*     */   {
/* 476 */     if (reqData == null) {
/* 477 */       throw new Exception("请求参数为空");
/*     */     }
/* 479 */     reqData.setSignature(SecurityUtils.sign(reqData.createSignValueForReg()));
/* 480 */     String result = WebUtils.sendHttp(ConfigReader.getConfig("jzhUrl") + "/artifReg.action", reqData);
/* 481 */     if (StringUtils.isEmpty(result)) {
/* 482 */       throw new Exception("返回报文为空");
/*     */     }
/* 484 */     String plain = result.substring(result.indexOf("<plain>"), result.indexOf("<signature>"));
/* 485 */     String signature = Object2Xml.getByTag(result, "signature");
/* 486 */     boolean signVal = SecurityUtils.verifySign(plain, signature);
/* 487 */     if (!signVal) {
/* 488 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 490 */     CommonRspData rspData = (CommonRspData)Object2Xml.xml2object(plain, "plain", CommonRspData.class);
/* 491 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 495 */     ArtifRegReqData reqData = new ArtifRegReqData();
        /* try {
         reqData.setMchnt_cd("0002900F0339996");
         reqData.setMchnt_txn_ssn(TimeId.generateSequenceNo());
         reqData.setCust_nm("swift");
         reqData.setArtif_nm("swift法人");
         reqData.setCertif_id("452545122654785223");
         reqData.setMobile_no("15385538971");
         reqData.setEmail("gaojun6854@126.com");
         reqData.setCity_id("2900");
         reqData.setParent_bank_id("0308");
        reqData.setBank_nm("招行");
         reqData.setCapAcntNm("户名");
         reqData.setCapAcntNo("6217856123456789877");
         reqData.setRem("Test");
      
         CommonRspData result = artifReg(reqData);
         System.out.println(result);
         } catch (Exception e) {
         e.printStackTrace();
          }
          //法人企业注册
          */
		
			///////////////////////////////
		//	/////////////////个人用户注册
		/*CommonRspData comrsd=new CommonRspData();
		RegReqData regData=new RegReqData();
		regData.setMchnt_cd(Constants.MCHNT_CD);
		regData.setMchnt_txn_ssn(TimeId.generateSequenceNo());
		regData.setCust_nm("sinorfc-swift");
		regData.setCertif_id("340222198810022917");
		regData.setMobile_no("15385538972");
		regData.setEmail("gaojun6854@126.com");
		regData.setCity_id("2900");
		regData.setParent_bank_id("0308");
		regData.setBank_nm("招行");
		regData.setCapAcntNm("高军");
		regData.setCapAcntNo("6214831217828899");
		regData.setRem("15385538972-6217856123456789875");
		try {
			comrsd=FuiouService.reg(regData);
			System.out.println(comrsd.getResp_code());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//////////////////////////////////////////////////////
//////////////////////余额查询

		/*QueryBalanceReqData qb=new QueryBalanceReqData();
		try {
			qb.setMchnt_cd(Constants.MCHNT_CD);
			qb.setMchnt_txn_ssn(TimeId.generateSequenceNo());
			qb.setMchnt_txn_dt("20160520");
			qb.setCust_no("15385538972");
			QueryBalanceRspData result = balanceAction(qb);
			System.out.println(result.getResults().get(0).getCt_balance());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
//////////////////////////////////////////////////////////////////

		//预授权
		/*PreAuthReqData preAuthReqData=new PreAuthReqData();
		preAuthReqData.setMchnt_cd(Constants.MCHNT_CD);
		preAuthReqData.setMchnt_txn_ssn(TimeId.generateSequenceNo());
		preAuthReqData.setOut_cust_no("15385538970");
		preAuthReqData.setIn_cust_no("user114");
		preAuthReqData.setAmt("4000");
		//000011328573
		PreAuthRspData result=new PreAuthRspData();
		try {
			result= FuiouService.preAuth(preAuthReqData);
			System.out.println(result.getResp_code());
			System.out.println(result.getContract_no());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}*/

		//个人转账到
		TransferBmuReqData transdata=new TransferBmuReqData();
		CommonRspData condata=new CommonRspData();
		transdata.setMchnt_cd(Constants.MCHNT_CD);
		transdata.setMchnt_txn_ssn(TimeId.generateSequenceNo());
		transdata.setOut_cust_no("15385538970");
		transdata.setIn_cust_no("user114");
		transdata.setAmt("4000");
		transdata.setContract_no("000011329700");
		transdata.setRem("15385538970-15385538972-1000");
		try {
			condata=FuiouService.transferBmu(transdata);
			System.out.println(condata.getResp_code());
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
/*     */   }
/*     */ }

/* Location:           D:\重要文档\我的工作\红歆财富\CRM系统\开发\外围系统\JAVA-金账户对接材料\金账户SDK\金账户SDK\fuiou_goldacnt_sdk.jar
 * Qualified Name:     com.fuiou.service.FuiouService
 * JD-Core Version:    0.6.2
 */