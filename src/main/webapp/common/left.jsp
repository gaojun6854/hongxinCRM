﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="struts" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左侧导航</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style type="text/css">
body{margin:0;padding:0;overflow-x:hidden;}
html, body{height:100%;}
img{border:none;}
*{font-family:'微软雅黑';font-size:12px;color:#626262;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

#bg{background-image:url(../images/content/dotted.png);}
.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{width:160px;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#3992d0;}
.line{height:2px;width:100%;background-image:url(../images/left/line_bg.png);background-repeat:repeat-x;}
.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{background-color:#317eb4;padding-left:40px;}
.leftsidebar_box dd a{color:#f5f5f5;line-height:20px;}
.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
.system_log dt{background-image:url(../images/left/system.png)}
.custom dt{background-image:url(../images/left/custom.png)}
.channel dt{background-image:url(../images/left/channel.png)}
.app dt{background-image:url(../images/left/app.png)}
.cloud dt{background-image:url(../images/left/cloud.png)}
.syetem_management dt{background-image:url(../images/left/syetem_management.png)}
.source dt{background-image:url(../images/left/source.png)}
.statistics dt{background-image:url(../images/left/statistics.png)}
.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
</style>

</head>
<c:if test="${sessionScope.login_user==null}">
<jsp:forward page="index.jsp"/>
</c:if>
<body id="bg">
	<c:if test="${sessionScope.login_user.userName=='gaojun' }">
		<c:forEach items="${menuList}" var="firstData">
			<div class="container">
				<div class="leftsidebar_box">
					<div class="line"></div>
					<c:forEach items="${menuList}" var="menu">
					<dl class='${menu.style}'>
						<dt  >${menu.sourceName}<img src="../images/left/${menu.style}.png"></dt>
						<c:forEach items="${menu.childMenus}" var="childMenu">
								<dd><a href="<%=basePath%>${childMenu.sourceUrl}" target="rightFrame">${childMenu.sourceName}</a></dd>
						</c:forEach>
					</dl>
				</c:forEach>	
				</div>
			</div>
		</c:forEach>
	</c:if>
	
	<c:if test="${sessionScope.login_user.userName ne gaojun }">
		<div class="container">
			<div class="leftsidebar_box">
				<div class="line"></div>
				<c:forEach items="${menuList}" var="firstData">
					<dl class='${firstData.style}'>
						<c:if test="${fn:contains(sourceIdList ,firstData.sourceId)}">
							<dt  >${firstData.sourceName}<img src="../images/left/${firstData.style}.png"></dt>
						</c:if>
							<c:forEach items="${firstData.childMenus}" var="secondData">
									<c:if test="${fn:contains(sourceIdList ,secondData.sourceId)}">
										<dd><a href="<%=basePath%>${secondData.sourceUrl}" target="rightFrame">${secondData.sourceName}</a></dd>
									</c:if>
							</c:forEach>
					</dl>
			</c:forEach>	
			</div>
		</div>
	</c:if>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(".leftsidebar_box dt").css({"background-color":"#3992d0"});
$(".leftsidebar_box dt img").attr("src","../images/left/select_xl01.png");
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#3992d0"})
		$(this).css({"background-color": "#317eb4"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","../images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","../images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
})
</script>
</body>
</html>