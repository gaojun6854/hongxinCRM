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
<script src="<%=basePath%>js/jquery-1.8.0.min.js" type="text/javascript"></script>
<title>信息管理系统界面</title>
</head>

<frameset rows="84,*,31" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=basePath %>common/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="167,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=basePath %>common/left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="<%=basePath %>common/index.jsp" name="rightFrame" scrolling="yes" id="rightFrame" title="rightFrame" />
  </frameset>
  <frame src="<%=basePath %>common/footer.jsp" name="bottomFrame" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
</html>
