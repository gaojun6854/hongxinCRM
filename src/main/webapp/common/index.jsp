<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Document</title>
	<style type="text/css">
		#right{
			width: 300px;
			height: 400px;
			border: 1px solid gray;
			float: right;
		}
		#right ul{
			margin: 0;
			padding-top:0;
			padding-bottom:0;
		}
		#left{
			width: 500px;
			height: 600px;
			border: 1px solid gray;
		}

	</style>
</head>
<body>
<%-- 	<div id="right">
	<h3>通知历史记录</h3>
		<ul>
			<c:forEach var="list" items="${unlist }">
				<c:if test="${list.lookFlag eq '0' }">
				<li><a style="color:red;" href="/notice/showOneNotice?noticeId=${list.notice.noticeId }&i=0">${list.notice.noticeTitle }</a></li>
				</c:if>
				<c:if test="${list.lookFlag eq '1' }">
				<li style="color:black;"><a href="/notice/showOneNotice?noticeId=${list.notice.noticeId }">${list.notice.noticeTitle }</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
<c:if test="${not empty myOneNotice}">
<div id="left">
	<h3>标题：${myOneNotice.noticeTitle }</h3>
<p>内容：${myOneNotice.noticeContent }</p>
</div>
</c:if> --%>
</body>
</html>
