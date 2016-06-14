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
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>还款管理</title>
</head>
<body>
<h1 align="center" style="color: highlight;">还款初审</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
  <thead>
    <tr>
     <th class="dataTableHeader">合同号</th>
	 <th class="dataTableHeader">产品名</th>
	 <th class="dataTableHeader">客户名称</th>
	 <th class="dataTableHeader">本金</th>
	 <th class="dataTableHeader">利率</th>
	 <th class="dataTableHeader">利息</th>
	 <th class="dataTableHeader">回购金额</th>
	 <th class="dataTableHeader">合同到期日</th>
	 <th class="dataTableHeader">客户经理</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${autoRepays}" var="autoRepay">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" /><a href="paymentNotice.action?no=2&id=${autoRepay.pactId}&redirect=firstPayment">${autoRepay.pactInfo.contractNumber}</a></td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${autoRepay.pactInfo.productInfo.productName }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${autoRepay.pactInfo.custName }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${autoRepay.pactInfo.amount}" pattern="0.00"/></td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${autoRepay.pactInfo.rateFix }" pattern="0.00"/></td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${autoRepay.amount}" pattern="0.00"/></td>
	  <c:if test="${autoRepay.pactInfo.rebuyFlag=='01'}">
	 	 <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="${autoRepay.pactInfo.rebuypactInfo.amount }" pattern="0.00"/></td>
	  </c:if> 
	  <c:if test="${autoRepay.pactInfo.rebuyFlag=='00'}">
	 	 <td><input type="hidden" readonly="readonly" id="checkme1" /><fmt:formatNumber value="0" pattern="0.00"/></td>
	  </c:if> 
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${autoRepay.pactInfo.pactDue }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${autoRepay.pactInfo.managerName }</td>
   </c:forEach>
  </tbody>
</table>
<script type="text/javascript">
</script>
</body>
</html>