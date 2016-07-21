<%@page import="com.hongxin.entity.TPactInfo"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<title>初审客户信息</title>
</head>
<body>
<h1 align="center" style="color: highlight;">已签约客户信息</h1>
<span style="color: red; font-size: 15px;">${msg}</span>
<form  action="addCustomInfo!findNeedUpdateCustom.action" method="post" id="submitForm">
	客户名:<input type="text" id="custName" name="custName" value="${custName}" />
	客户手机号:<input type="text" id="phoneNum" name="phoneNum" value="${phoneNum}"  />
	客户身份证:<input type="text" id="paperNum" name="paperNum" value="${paperNum}"   />
	<input type="submit" value="查询" />
</form>
<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
  <thead>
    <tr>
     <th class="dataTableHeader">客户名称</th>
	 <th class="dataTableHeader">客户性别</th>
	 <th class="dataTableHeader">证件类型</th>
	 <th class="dataTableHeader">证件编号</th>
	 <th class="dataTableHeader">联系方式</th>
	 <th class="dataTableHeader">状态</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${pageBean.list}" var="custom">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" /><a href="addCustomInfo!getCustomInfo.action?id=${custom.id}&redirect=auditYN">${custom.custname}</a></td>
        <c:if test="${custom.custgender=='1'}">
      <td><input type="hidden" readonly="readonly" id="checkme1" />身份证</td>
      </c:if>
	 <c:if test="${custom.custgender=='0'}">
	 <td><input type="hidden" readonly="readonly" id="checkme1" />护照</td>
	 </c:if>
	 <c:if test="${custom.papertype=='1'}">
	  <td><input type="hidden" readonly="readonly" id="checkme1" />男</td>
	 </c:if>
	 <c:if test="${custom.papertype=='0'}">
	  <td><input type="hidden" readonly="readonly" id="checkme1" />女</td>
	 </c:if>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.papernum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.phonenum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />
	  	<a href="addCustomInfo!auditYN.action?param=yes&id=${custom.id}">通过</a>
		<a href="addCustomInfo!auditYN.action?param=no&id=${custom.id}">不通过</a></td>
    </tr>
   </c:forEach>
  </tbody>
</table>
<!-- 分页 -->
	 <center>
    	<font size="3">第<font color="red"><s:property value="#request.pageBean.currentPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBean.totalPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBean.allRows"/></font>条记录</font>
        
        <s:if test="#request.pageBean.currentPage == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>
        
        <s:else>
        
        <input type="button" value="首页" onclick="jumpNormalPage(1);"/>
           <%--  <a href="${pageBean.actionUrl}">首页</a> --%>
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a> --%>
            <input type="button" value="上一页"  onclick="jumpNormalPage('<s:property value="#request.pageBean.currentPage - 1"/>');"/>
        </s:else>
        
        <s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a> --%>
            <input type="button" value="下一页" onclick="jumpNormalPage('<s:property value="#request.pageBean.currentPage + 1"/>');" />
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a> --%>
            <input type="button" value="尾页" onclick="jumpNormalPage('<s:property value="#request.pageBean.totalPage"/>');" />
        </s:if>
        
        <s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
    </center>
    
    <center>
        <form action="${pageBean.actionUrl}" onsubmit="return validate();">
            <font size="4">跳转至</font>
            <input type="text" size="2" name="page">页
            <input type="submit" value="跳转">
        </form>
    </center>
	<script type="text/javascript">
		function validate() {
			var page = document.getElementsByName("page")[0].value;
			if(""==page){
				$("#submitForm").attr("action", "${pageBean.actionUrl}").submit();
				return false;
			}
			 if (page > '${pageBean.totalPage}') {
				alert("你输入的页数大于最大页数，页面将跳转到首页！");
				//window.document.location.href = "${pageBean.actionUrl}";
				$("#submitForm").attr("action", "${pageBean.actionUrl}").submit();
				return false;
			}
			return true;
		}
		
		/** 普通跳转 **/
		function jumpNormalPage(page){
			$("#submitForm").attr("action", "${pageBean.actionUrl}?page=" + page).submit();
		}
	</script>
</body>
</html>
