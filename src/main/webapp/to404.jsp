<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请稍等...</title>
<script src="<%=basePath%>/js/jquery-1.8.0.min.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function () {
		<%-- window.top.location.href="<%=basePath%>/404.jsp"; --%>
	})
</script>
</body>
</html>