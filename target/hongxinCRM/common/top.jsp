<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery-1.8.0.min.js" type="text/javascript"></script>
<title>无标题文档</title>
<script type="text/javascript">
$(function(){
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	});	
	
});
</script>
</head>
<body style="background:url(/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <!--  img src="/images/logo.png" title="系统首页" /-->
    </div>
     
    <div class="topright">    
    <ul>
    <li><span><img src="/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="/user/loginout" target="_parent">退出</a></li>
    </ul>
    <div class="user">
     <span>${user.username}</span> 
     <!-- 点击此处显示自己的消息 -->
    <a href="/notice/showMyNotice" target="rightFrame"><i>消息</i><b>${user.noticeLook }</b></a>
    </div>    
    </div>
</body>
</html>
