<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="struts" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>红歆-用户登录</title>
<script src="js/layer/jquery.js"></script>
<script src="js/layer/layer.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/index/style.css" rel='stylesheet' type='text/css' />
</head>

<body>
<c:if test="${msg eq '0'}">
<%session.removeAttribute("msg"); %>
<script type="text/javascript">
	$(function () {
		//信息框-例5
		layer.msg('密码错误', function(){
		//关闭后的操作
		});
	})
</script>
</c:if>
<c:if test="${msg eq '1'}">
<%session.removeAttribute("msg"); %>
<script >
	$(function () {
		//信息框-例5
		layer.msg('账号不存在', function(){
		//关闭后的操作
		});
	})
</script>
</c:if>
<c:if test="${msg eq '3'}">
<%session.removeAttribute("msg"); %>
<script >
	$(function () {
		//信息框-例5
		layer.msg('登录超时', function(){
		//关闭后的操作
		});
	})
</script>
</c:if>
 <h1>红歆财富投资管理有限公司</h1>
		<div class="app-cam">
			<div class="cam"><img src="images/index/cam.png" class="img-responsive" alt="" /></div>
			<form action="index.action" method="post">
				<input type="text" name="id" class="text" value="" onfocus="this.value = '';" placeholder="用户" >
				<input type="password" name="name"  class="text" value="" onfocus="this.value = '';" placeholder="密码">
				<div class="submit"><input type="submit"  value="登陆" ></div>
				<div class="clear"></div>
				<div class="buttons">
				</div>
				<div class="new">
					<p><a href="#">忘记密码 ?</a></p>
					<p class="sign"><a href="#"> 注册</a></p>
					<div class="clear"></div>
				</div>
			</form>
		</div>
	<!--start-copyright-->
   		<div class="copy-right">
				<p>Copyright &copy; 2015.红歆财富 All rights reserved.<a target="_blank" href="#"></a></p>
		</div>
	<!--//end-copyright-->
</body>
</html>