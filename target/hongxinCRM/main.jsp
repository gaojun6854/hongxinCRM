<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="struts" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红歆财富投资管理</title>
</head>
<c:if test="${sessionScope.login_user!=null}">
<frameset rows="3.5%,*">
	<frame   src="<%=basePath%>token.jsp">
	<frameset cols="7.785%,*">
	  <frame src="<%=basePath%>menu.jsp">
	  <frame src="<%=basePath%>hongxin.jsp" name="content">
	</frameset>
</frameset>
</c:if>
<a href="login!logout.action">退出系统</a>
<body>
</body>
</html>