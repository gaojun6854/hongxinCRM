<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="../css/menu/css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.8.0.min.js" type="text/javascript"></script>

<style type="text/css">
	.error{color:red}
	.right{color:green}
	.iStyle{
		color:red;
		float:right;
	}
	.forminfo li{
		width:550px;
	}
</style>
</head>
<body>
	<div class="place">
		<span>新增菜单：</span>
	</div>
	<div class="mainbox">
	<div>
		<form id="frmV" action="${method_}.action" method="post" >
			<ul class="forminfo">
				<li><label>菜单编码<b>*</b></label><span class="sourceCode iStyle"></span><input name="resCode" type="text" class="dfinput" value="${resourceBak.sourceCode}" /></li>
				<li><input type="hidden" name="resourceBak.parentSourceId" value="${parentSourceId}"/></li><!-- 增加菜单时，将父级的编码当做子级的父级bianma -->
				 <c:if test="${resourceBak.sourceId != null }"><!-- 更新时，编号可能也会更新，因此这里需要带上 -->
					<input type="hidden" value="${resourceBak.sourceId}" name="resourceBak.sourceId"/>
				 </c:if>
				<li><label>菜单名称<b>*</b></label><span class="sourcoName iStyle"></span><input name="resourceBak.sourceName" type="text" class="dfinput" value="${resourceBak.sourceName}"/></li>
				<li><label>排序<b>*</b></label><span class="seq iStyle"></span><input name="resourceBak.seq" type="text" class="dfinput" value="${resourceBak.seq}" /></li>
				<li><label>菜单地址<b>*</b></label><span class="sourcoUrl iStyle"></span><input name="resourceBak.sourceUrl" type="text" class="dfinput" value="${resourceBak.sourceUrl}"/></li>
			</ul>
			<ul class="toolbar">
		        <li class="click"><input class="btn" type="submit" value="保存" /></li>
        		<li class="click"><input type="reset" class="btn" value="重置" /></li>
        	</ul>
		</form>
	</div>
	</div>
</body>
</html>