<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>还款到客户账</title>
</head>
<body>
<h1 align="center" style="color: highlight;">还款到客户账</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br/></span>
<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
  <thead>
    <tr>
     <th class="dataTableHeader">合同号</th>
	 <th class="dataTableHeader">客户性别</th>
	 <th class="dataTableHeader">证件类型</th>
	 <th class="dataTableHeader">证件编号</th>
	 <th class="dataTableHeader">联系方式</th>
	 <th class="dataTableHeader">购买操作</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${pactInfos}" var="pactInfo">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" />${pactInfo.contractNumber}</td>
      <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.customBaseInfo.custgender }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.customBaseInfo.papertype }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.customBaseInfo.papernum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.customBaseInfo.phonenum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />
	  	<a href="pactInfo!determineRepay.action?id=${pactInfo.pactId}">还款客户</a>
    </tr>
   </c:forEach>
  </tbody>
</table>
</body>
</html>
