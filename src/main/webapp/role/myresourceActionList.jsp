<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="<%=basePath %>assets/css/admin.css" />
<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>js/scriptbreaker-multiple-accordion-1.js"></script>
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
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="pagin">
		<!--  -->
			<ul class="topnav tablelist">
					<c:forEach var="one" items="${menuOfRole }">
					<c:forEach var="two" items="${one.rolesource }">
					<c:forEach var="three" items="${two.resbak }">
						<li class="one">${three.sourcoName }
							<c:forEach var="forth" items="${three.funlist }">
								<input disabled style="margin-left: 50px;" type="checkbox" name="limit" value="${forth.actionId }"
								<c:forEach var="myroleLimit" items="${mymenuOfRole }">
								<c:if test="${myroleLimit.actionId eq forth.actionId }">checked</c:if> 
								</c:forEach> 
								/>${forth.funcName }
							</c:forEach>
						</li>
					</c:forEach>
					</c:forEach>
					</c:forEach>
			</ul>
			<input style="width:45px;height:20px;cursor:pointer;" type="button" value="返回" onclick="history.go(-1);" />
		</div>
	</div>
	<div>共条记录</div>
			<div style="float: right; padding-right:100px;"><a>首页</a>&nbsp;&nbsp;<a>上一页</a>&nbsp;&nbsp;/&nbsp;&nbsp;<a>下一页</a>&nbsp;&nbsp;<a>末页</a></div>
</body>

</html>
