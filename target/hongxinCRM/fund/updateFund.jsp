<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<script type="text/javascript" src="../js/jquery-date.js"></script>
<link rel="stylesheet" type="text/css" href="../css/dateTime/jquery.datetimepicker.css"/>
<script src="../js/jquery.datetimepicker.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指数修改界面</title>
</head>
<body>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br/></span>
<form action="fundInfo!fundUpdate.action" method="post">
日期<input type="text" readonly="readonly" name="fundInfo.workDate" value="${fundInfo.workDate}"><br/>
指  数 值  <input type="text" name="fundInfo.funds" value="${fundInfo.funds}"><br/>
<input type="submit" value="保存">
</form>
</body>
</html>