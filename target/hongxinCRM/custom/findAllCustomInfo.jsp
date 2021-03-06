<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<link href="../css/custom/basic_layout.css" rel="stylesheet" />
<script src="../js/custom/artDialog.js"></script>
<style type="text/css">
body, th, td {
	font-family: Arial, Verdana, sans-serif;
	font-size: 0.9em;
}

a:link, a:visited {
	color: #59B337;
}

a:hover, a:active, a:focus {
	color: #000000;
}

table.dataTable tr.marked {
	background-color: #FFD900;
}
</style>
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>全部客户信息</title>
</head>
<body>
	<h1 align="center" style="color: highlight;">所有客户信息</h1>
	
	<form  action="addCustomInfo!findAll.action" method="post" id="submitForm">
	客户名:<input type="text" id="custname" name="customBaseInfo.custname" value="${customBaseInfo.custname}" />
	客户手机号:<input type="text" id="phonenum" name="customBaseInfo.phonenum" value="${customBaseInfo.phonenum}"  />
	客户身份证:<input type="text" id="papernum" name="customBaseInfo.papernum" value="${customBaseInfo.papernum}"   />
	状态:<select name="attr">
			<option <c:if test="${requestScope.attr eq '0'}">selected="selected"</c:if> value="0">请选择</option>
			<option <c:if test="${requestScope.attr eq '1'}">selected="selected"</c:if> value="1">初审中</option>
			<option <c:if test="${requestScope.attr eq '2'}">selected="selected"</c:if> value="2">初审失败</option>
			<option <c:if test="${requestScope.attr eq '3'}">selected="selected"</c:if> value="3">复审中</option>
			<option <c:if test="${requestScope.attr eq '4'}">selected="selected"</c:if> value="4">复审失败</option>
			<option <c:if test="${requestScope.attr eq '5'}">selected="selected"</c:if> value="5">客户信息修改中</option>
			<option <c:if test="${requestScope.attr eq '6'}">selected="selected"</c:if> value="6">签约成功</option>
			
		</select>
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
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.list}" var="custom">
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" /><a
						href="addCustomInfo!getCustomInfo.action?id=${custom.id}">${custom.custname}</a></td>
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