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
<link href="../css/menu/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="<%=basePath%>menu/menuList.action">菜单管理</a></li>
			<li><a href="#">功能点</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<form action="" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
		</form>
		<div class="tools">
			<ul class="toolbar1">
				<li class="click" style="cursor:pointer;"><span><img src="../css/menu/images/t05.png" /></span>新增功能点</li>
			</ul>

		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="${actionFun.actionId }" checked="checked" /></th>
					<th>功能点编码</th>
					<th>功能点名称</th>
					<th>功能点URL</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${funList}" var="actionFun">
					<tr>
						<td><input name="" type="checkbox" value="" /></td>
						<td>${actionFun.code }</td>
						<td>${actionFun.funcName }</td>
						<td>${actionFun.funcUrl }</td>
						<td><a href="editFun.action?actionId=${actionFun.actionId}&method_=updateFun" class="tablelink">修改</a> <a href="deleteFun.action?actionId=${actionFun.actionId}&sourceId=${sourceId}"
							class="tablelink2"> 删除</a>
							
							</td>
					</tr>
				</c:forEach> 
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$(document).ready(function(){
		  $(".click").click(function(){
			location.href = 'addFun.action?sourceId=${sourceId}&method_=saveFun';
		  });
	});
	
	$(function(){
		$(".tablelink2").click(function(){
			if(window.confirm('您确定删除吗')){
				var href = $(this).attr("href");
				$("form").attr("action", href).submit();			
				return false;
			}else{
				return false;
				}
		});
	});
	</script>

</body>

</html>
