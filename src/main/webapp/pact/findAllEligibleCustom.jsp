<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>可以购买产品用户</title>
</head>
<body>
<h1 align="center" style="color: highlight;">可购买产品用户</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br/></span>
<form  action="pactInfo.action" method="post" id="submitForm">
	客户名:<input type="text" id="custname" name="customBaseInfo.custname" value="${customBaseInfo.custname}" />
	客户手机号:<input type="text" id="phonenum" name="customBaseInfo.phonenum" value="${customBaseInfo.phonenum}"  />
	客户身份证:<input type="text" id="papernum" name="customBaseInfo.papernum" value="${customBaseInfo.papernum}"   />
	
	<input type="submit" value="查询" />
	</form>
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
   <c:forEach items="${pageBeanBycustom.list}" var="custom">
    <tr class="odd_row">
      <td><input type="hidden"  readonly="readonly" id="checkme1" />${custom.custname}</td>
      <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.custgender }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.papertype }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.papernum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${custom.phonenum }</td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />
	  	<a href="pactInfo!findAllProducts.action?id=${custom.id}">选择产品</a>
    </tr>
   </c:forEach>
  </tbody>
</table>
 
	 <!-- 分页 -->
	 <center>
    	<font size="3">第<font color="red"><s:property value="#request.pageBeanBycustom.currentPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBeanBycustom.totalPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBeanBycustom.allRows"/></font>条记录</font>
        
        <s:if test="#request.pageBeanBycustom.currentPage == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>
        
        <s:else>
        
        <input type="button" value="首页" onclick="jumpNormalPage(1);"/>
           <%--  <a href="${pageBeanBycustom.actionUrl}">首页</a> --%>
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBeanBycustom.actionUrl}?page=<s:property value="#request.pageBeanBycustom.currentPage - 1"/>">上一页</a> --%>
            <input type="button" value="上一页"  onclick="jumpNormalPage('<s:property value="#request.pageBeanBycustom.currentPage - 1"/>');"/>
        </s:else>
        
        <s:if test="#request.pageBeanBycustom.currentPage != #request.pageBeanBycustom.totalPage">
            <%-- <a href="${pageBeanBycustom.actionUrl}?page=<s:property value="#request.pageBeanBycustom.currentPage + 1"/>">下一页</a> --%>
            <input type="button" value="下一页" onclick="jumpNormalPage('<s:property value="#request.pageBeanBycustom.currentPage + 1"/>');" />
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBeanBycustom.actionUrl}?page=<s:property value="#request.pageBeanBycustom.totalPage"/>">尾页</a> --%>
            <input type="button" value="尾页" onclick="jumpNormalPage('<s:property value="#request.pageBeanBycustom.totalPage"/>');" />
        </s:if>
        
        <s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
    </center>
    
    <center>
        <form action="${pageBeanBycustom.actionUrl}" onsubmit="return validate();">
            <font size="4">跳转至</font>
            <input type="text" size="2" name="page">页
            <input type="submit" value="跳转">
        </form>
    </center>
	<script type="text/javascript">
		function validate() {
			var page = document.getElementsByName("page")[0].value;
			if(""==page){
				$("#submitForm").attr("action", "${pageBeanBycustom.actionUrl}").submit();
				return false;
			}
			 if (page > '${pageBeanBycustom.totalPage}') {
				alert("你输入的页数大于最大页数，页面将跳转到首页！");
				//window.document.location.href = "${pageBeanBycustom.actionUrl}";
				$("#submitForm").attr("action", "${pageBeanBycustom.actionUrl}").submit();
				return false;
			}
			return true;
		}
		
		/** 普通跳转 **/
		function jumpNormalPage(page){
			$("#submitForm").attr("action", "${pageBeanBycustom.actionUrl}?page=" + page).submit();
		}
	</script>
</body>
</html>
