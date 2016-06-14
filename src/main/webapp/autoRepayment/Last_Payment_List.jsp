<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>还款管理</title>
</head>
<body>
<h1 align="center" style="color: highlight;">还款复审</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
<form action="findlastPaymentList.action" method="post" id="submitForm">
	客户名称:<input type="text" name="custName">
	到期日:<input type="text" name="pactDue">
	<input type="submit" value="查询">
</form>
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
   <c:forEach items="${pageBeanAutoRepay.list}" var="autoRepay">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" /><a href="findPaymentInfomation.action?no=2&id=${autoRepay.pactId}&redirect=lastPayment">${autoRepay.pactInfo.contractNumber}</a></td>
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
    </tr>
   </c:forEach>
  </tbody>
</table>
<!-- 分页 -->
	 <center>
    	<font size="3">第<font color="red"><s:property value="#request.pageBeanAutoRepay.currentPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBeanAutoRepay.totalPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBeanAutoRepay.allRows"/></font>条记录</font>
        
        <s:if test="#request.pageBeanAutoRepay.currentPage == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>
        
        <s:else>
        
        <input type="button" value="首页" onclick="jumpNormalPage(1);"/>
            &nbsp;&nbsp;&nbsp;
            <input type="button" value="上一页"  onclick="jumpNormalPage('<s:property value="#request.pageBeanAutoRepay.currentPage - 1"/>');"/>
        </s:else>
        
        <s:if test="#request.pageBeanAutoRepay.currentPage != #request.pageBeanAutoRepay.totalPage">
            <input type="button" value="下一页" onclick="jumpNormalPage('<s:property value="#request.pageBeanAutoRepay.currentPage + 1"/>');" />
            &nbsp;&nbsp;&nbsp;
            <input type="button" value="尾页" onclick="jumpNormalPage('<s:property value="#request.pageBeanAutoRepay.totalPage"/>');" />
        </s:if>
        
        <s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
    </center>
    
    <center>
        <form action="${pageBeanAutoRepay.actionUrl}" onsubmit="return validate();">
            <font size="4">跳转至</font>
            <input type="text" size="2" name="page">页
            <input type="submit" value="跳转">
        </form>
    </center>
	<script type="text/javascript">
		function validate() {
			var page = document.getElementsByName("page")[0].value;
			if(""==page){
				$("#submitForm").attr("action", "${pageBeanAutoRepay.actionUrl}").submit();
				return false;
			}
			 if (page > '${pageBeanAutoRepay.totalPage}') {
				alert("你输入的页数大于最大页数，页面将跳转到首页！");
				//window.document.location.href = "${pageBeanAutoRepay.actionUrl}";
				$("#submitForm").attr("action", "${pageBeanAutoRepay.actionUrl}").submit();
				return false;
			}
			return true;
		}
		
		/** 普通跳转 **/
		function jumpNormalPage(page){
			$("#submitForm").attr("action", "${pageBeanAutoRepay.actionUrl}?page=" + page).submit();
		}
	</script>
</body>
</html>