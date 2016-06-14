<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-script-type" content="text/javascript">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="content-language" content="en-gb">
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../css/custom/dataTable.css" media="screen">
<style type="text/css">
body, th, td { font-family: Arial, Verdana, sans-serif; font-size: 0.9em; }
a:link, a:visited { color: #59B337; }
a:hover, a:active, a:focus { color: #000000; }
table.dataTable tr.marked { background-color: #FFD900; }
</style>
				<%
				request.setAttribute("picType", 2);
				request.setAttribute("url", "pact/pactInfo!offLineReviewsUpload.action");//上传图片后跳转的地址
				;
			%>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/* reveal-modal */
.reveal-modal-bg{position:fixed;height:100%;width:100%;background-color:#000;z-index:100;display:none;top:0;left:0;}
.reveal-modal{visibility:hidden;top:100px;left:50%;margin-left:-300px;width:420px;background:#eee url(../images/modal-gloss.png) no-repeat -200px -80px;position:absolute;z-index:101;padding:30px 40px 34px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-moz-box-shadow:0 0 10px rgba(0,0,0,.4);-webkit-box-shadow:0 0 10px rgba(0,0,0,.4);-box-shadow:0 0 10px rgba(0,0,0,.4);}
.reveal-modal .close-reveal-modal {
font-size:22px;line-height:.5;position:absolute;top:8px;right:11px;color:#aaa;text-shadow:0 -1px 1px rbga(0,0,0,.6);font-weight:bold;cursor:pointer;} 
.reveal-modal h2{font-size:18px;color:#990000;padding:0 0 20px 0;}
.reveal-modal p{padding:0 0 15px 0;}
</style>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.reveal.js"></script>
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>合约复审</title>
</head>
<body>
<h1 align="center" style="color: highlight;">合约复审</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
  <thead>
    <tr>
    <th class="dataTableHeader">合同号</th>
	 <th class="dataTableHeader">产品名</th>
	  <th class="dataTableHeader">产品融资方银行账号</th>
	 <th class="dataTableHeader">产品融资方账号</th>
	 <th class="dataTableHeader">本金</th>
	 <th class="dataTableHeader">利率</th>
	 <th class="dataTableHeader">利息(预期)</th>
	 <th class="dataTableHeader">客户经理</th>
	 <th class="dataTableHeader">团队</th>
	 <th class="dataTableHeader">状态</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${pactInfos}" var="pactInfo">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" /><a href="pactInfo!getPactInfos.action?no=3&id=${pactInfo.pactId}&redirect=lastCheck">${pactInfo.contractNumber}</a></td>
	 <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.productName }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.bankAccout }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.fuyouAccout }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${pactInfo.amount}" pattern="0.00"/></td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.rateFix }</td>
	  <c:if test="${pactInfo.productInfo.settleWay eq '1'}">
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${pactInfo.amount*pactInfo.purchaseDays/365*pactInfo.rateFix}" pattern="0.00"/> </td>
	 </c:if>
	 <c:if test="${pactInfo.productInfo.settleWay eq '2'}">
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${pactInfo.amount*pactInfo.purchaseDays/12*pactInfo.rateFix*0.01}" pattern="0.00"/> </td>
	 </c:if>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.managerName }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.termName }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />
		<c:if test="${pactInfo.pactFlow=='1'}">初审中</c:if>
		<c:if test="${pactInfo.pactFlow=='2'}">初审失败</c:if>
		<c:if test="${pactInfo.pactFlow=='3'}">复审中</c:if>
		<c:if test="${pactInfo.pactFlow=='4'}">复审失败</c:if>
		<c:if test="${pactInfo.pactFlow=='5'}">还款中</c:if>
		<c:if test="${pactInfo.pactFlow=='6'}">回购还款客户</c:if>
		<c:if test="${pactInfo.pactFlow=='7'}">回购还款客户失败</c:if>
		<c:if test="${pactInfo.pactFlow=='8'}">回购成功</c:if>
		<c:if test="${pactInfo.pactFlow=='9'}">回购失败</c:if>
		<c:if test='${pactInfo.pactFlow=="10"}'>合同正常结束</c:if>
	  
	  </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
</body>
</html>
