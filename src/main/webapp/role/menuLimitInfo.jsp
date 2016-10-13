<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../assets/css/admin.css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.8.0.min.js" type="text/javascript"></script>
<title>无标题文档</title>
<style>
.one{
	height:30px;
	width:100%;
	line-height:30px;
	text-indent:2em;
}
</style>
</head>
<body>
${msg}
	<div class="place">
		<span>菜单管理：</span>
		<ul class="placeul">
			<li><a href="#"></a></li>
		</ul>
	</div>
	<div class="rightinfo">
	<form action="addLimitToRole.action?roleId=${roleId }" method="post">
			<table class="tablelist">
				<thead>
					<tr>
						<th>一级菜单</th>
						<th>二级菜单</th>
						<th></th>
						<th id="cz">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menus}" var="firstData">
						<tr>
							<td>${firstData.sourceName}</td>
			               	<td></td>
			               	<td></td>
							<td class="cz">
							    <c:forEach var="two" items="${firstData.funlist }">
								<input style="margin-left: 50px;" type="checkbox" name="limit" value="${two.actionId }"
								<c:forEach var="myroleLimit" items="${myroleactionList }">
								<c:if test="${myroleLimit.actionId eq two.actionId }">checked</c:if> 
								</c:forEach> 
								/>${two.funcName }
							</c:forEach>
						    </td>
						</tr>
						<c:forEach items="${firstData.childMenus}" var="secondData">
                		<tr>
	                		<td></td>
	                		<td>${secondData.sourceName}</td>
	                		<td></td>
	                		<td class="cz">
	                		<c:forEach var="two" items="${secondData.funlist }">
								<input style="margin-left: 50px;" type="checkbox" name="limit" value="${two.actionId }"
								<c:forEach var="myroleLimit" items="${myroleactionList }">
								<c:if test="${myroleLimit.actionId eq two.actionId }">checked</c:if> 
								</c:forEach> 
								/>${two.funcName }
							</c:forEach>
	                   		 </td>
                		</tr>
                		</c:forEach>
					</c:forEach>
				</tbody>
			</table>
			<input style="width:65px;height:25px;cursor:pointer;" type="submit" value="开始分配" />
			<input style="width:45px;height:20px;cursor:pointer;" type="button" value="返回" onclick="history.go(-1);" />
		</form>
	</div>
<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>