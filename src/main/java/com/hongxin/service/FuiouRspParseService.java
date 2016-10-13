/*     */ package com.hongxin.service;
/*     */ 
/*     */ import com.fuiou.data.AppSignCardRspData;
/*     */ import com.fuiou.data.AppTransRspData;
/*     */ import com.fuiou.data.ChangeCard2RspData;
/*     */ import com.fuiou.data.ModifyMobileRsqData;
/*     */ import com.fuiou.data.RechargeAndWithdrawalRspData;
/*     */ import com.fuiou.data.TransRspData;
/*     */ import com.fuiou.data.TxTpBackMchntRspData;
/*     */ import com.fuiou.data.UserModNotifyRsp;
/*     */ import com.fuiou.data.WebArtifRegRspData;
/*     */ import com.fuiou.data.WebRegRspData;
/*     */ import com.fuiou.data.Wy500012RspData;
/*     */ import com.fuiou.data.ZxRspData;
/*     */ import com.fuiou.util.SecurityUtils;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class FuiouRspParseService
/*     */ {
/*     */   public static WebRegRspData webRegRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/*  39 */     WebRegRspData regRspData = new WebRegRspData();
/*  40 */     String signature = req.getParameter("signature");
/*  41 */     BeanUtils.populate(regRspData, req.getParameterMap());
/*  42 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/*  43 */     if (!signVal) {
/*  44 */       throw new Exception("接口返回签名错误!");
/*     */     }
/*  46 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static RechargeAndWithdrawalRspData rechargeAndWithdrawalNotifyParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/*  57 */     RechargeAndWithdrawalRspData rspData = new RechargeAndWithdrawalRspData();
/*  58 */     String signature = req.getParameter("signature");
/*  59 */     BeanUtils.populate(rspData, req.getParameterMap());
/*  60 */     boolean signVal = SecurityUtils.verifySign(rspData.createSignValue(), signature);
/*  61 */     if (!signVal) {
/*  62 */       throw new Exception("接口返回签名错误!");
/*     */     }
/*  64 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static WebRegRspData appWebRegRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/*  75 */     WebRegRspData regRspData = new WebRegRspData();
/*  76 */     String signature = req.getParameter("signature");
/*  77 */     BeanUtils.populate(regRspData, req.getParameterMap());
/*  78 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/*  79 */     if (!signVal) {
/*  80 */       throw new Exception("接口返回签名错误!");
/*     */     }
/*  82 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static TransRspData transRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/*  93 */     TransRspData rspData = new TransRspData();
/*  94 */     String signature = req.getParameter("signature");
/*  95 */     BeanUtils.populate(rspData, req.getParameterMap());
/*  96 */     boolean signVal = SecurityUtils.verifySign(rspData.createSignValue(), signature);
/*  97 */     if (!signVal) {
/*  98 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 100 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static WebArtifRegRspData webArtifRegRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 113 */     WebArtifRegRspData regRspData = new WebArtifRegRspData();
/* 114 */     String signature = req.getParameter("signature");
/* 115 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 116 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/* 117 */     if (!signVal) {
/* 118 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 120 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static AppTransRspData appWebRechargeRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 131 */     AppTransRspData regRspData = new AppTransRspData();
/* 132 */     String signature = req.getParameter("signature");
/* 133 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 134 */     boolean signVal = SecurityUtils.verifySign(regRspData.createWebRechargeSignValue(), signature);
/* 135 */     if (!signVal) {
/* 136 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 138 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static AppTransRspData appTransRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 150 */     AppTransRspData regRspData = new AppTransRspData();
/* 151 */     String signature = req.getParameter("signature");
/* 152 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 153 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/* 154 */     if (!signVal) {
/* 155 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 157 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static UserModNotifyRsp userModNotifyParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 168 */     UserModNotifyRsp regRspData = new UserModNotifyRsp();
/* 169 */     String signature = req.getParameter("signature");
/* 170 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 171 */     boolean grSignVal = SecurityUtils.verifySign(regRspData.createSignValueForWebRegBack(), signature);
/* 172 */     boolean frSignVal = SecurityUtils.verifySign(regRspData.createSignValueForWebArtifRegBack(), signature);
/* 173 */     if ((!grSignVal) && (!frSignVal)) {
/* 174 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 176 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static ZxRspData zxRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 185 */     ZxRspData rspData = new ZxRspData();
/* 186 */     String signature = req.getParameter("signature");
/* 187 */     BeanUtils.populate(rspData, req.getParameterMap());
/* 188 */     boolean signVal = SecurityUtils.verifySign(rspData.createSignValue(), signature);
/* 189 */     if (!signVal) {
/* 190 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 192 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static Wy500012RspData wy500012RspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 208 */     Wy500012RspData regRspData = new Wy500012RspData();
/* 209 */     String signature = req.getParameter("signature");
/* 210 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 211 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/* 212 */     if (!signVal) {
/* 213 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 215 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static ModifyMobileRsqData modifyMobileRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 222 */     ModifyMobileRsqData regRspData = new ModifyMobileRsqData();
/* 223 */     String signature = req.getParameter("signature");
/* 224 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 225 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/* 226 */     if (!signVal) {
/* 227 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 229 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static TxTpBackMchntRspData txTpBackMchntRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 236 */     TxTpBackMchntRspData rspData = new TxTpBackMchntRspData();
/* 237 */     String signature = req.getParameter("signature");
/* 238 */     BeanUtils.populate(rspData, req.getParameterMap());
/* 239 */     boolean signVal = SecurityUtils.verifySign(rspData.createSignValue(), signature);
/* 240 */     if (!signVal) {
/* 241 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 243 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static ChangeCard2RspData changeCard2RspDataRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 252 */     ChangeCard2RspData regRspData = new ChangeCard2RspData();
/* 253 */     String signature = req.getParameter("signature");
/* 254 */     BeanUtils.populate(regRspData, req.getParameterMap());
/* 255 */     boolean signVal = SecurityUtils.verifySign(regRspData.createSignValue(), signature);
/* 256 */     if (!signVal) {
/* 257 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 259 */     return regRspData;
/*     */   }
/*     */ 
/*     */   public static AppSignCardRspData appSignCardRspParse(HttpServletRequest req)
/*     */     throws Exception
/*     */   {
/* 267 */     AppSignCardRspData rspData = new AppSignCardRspData();
/* 268 */     String signature = req.getParameter("signature");
/* 269 */     BeanUtils.populate(rspData, req.getParameterMap());
/* 270 */     boolean signVal = SecurityUtils.verifySign(rspData.createSignValue(), signature);
/* 271 */     if (!signVal) {
/* 272 */       throw new Exception("接口返回签名错误!");
/*     */     }
/* 274 */     return rspData;
/*     */   }
/*     */ 
/*     */   public static String notifyRspXml(String resp_code, String mchnt_cd, String mchnt_txn_ssn)
/*     */     throws Exception
/*     */   {
/* 288 */     if ((StringUtils.isEmpty(resp_code)) || (StringUtils.isEmpty(mchnt_cd)) || (StringUtils.isEmpty(mchnt_txn_ssn))) {
/* 289 */       throw new Exception("参数为空");
/*     */     }
/* 291 */     String plain = "<plain>";
/* 292 */     plain = plain + "<resp_code>" + resp_code + "</resp_code>";
/* 293 */     plain = plain + "<mchnt_cd>" + mchnt_cd + "</mchnt_cd>";
/* 294 */     plain = plain + "<mchnt_txn_ssn>" + mchnt_txn_ssn + "</mchnt_txn_ssn>";
/* 295 */     plain = plain + "</plain>";
/* 296 */     StringBuffer sb = new StringBuffer();
/* 297 */     sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
/* 298 */     sb.append("<ap>");
/* 299 */     sb.append(plain);
/* 300 */     sb.append("<signature>" + SecurityUtils.sign(plain) + "</signature>");
/* 301 */     sb.append("</ap>");
/* 302 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           D:\download\我的下载\金账户测试材料\金账户测试材料\金账户测试材料\金账户测试材料\测试材料\JAVA金账户SDKv0.04\金账户SDKv0.04\fuiou_goldacnt_sdk-0.04.jar
 * Qualified Name:     com.fuiou.service.FuiouRspParseService
 * JD-Core Version:    0.6.2
 */