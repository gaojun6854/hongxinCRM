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
<link rel="stylesheet" href="<%=basePath %>assets/css/admin.css" />
<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/funInfo.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>js/scriptbreaker-multiple-accordion-1.js"></script>
<style>
.one .pp{
	position:none;
	float:left;
	margin-left:10%;
}
</style>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
		</form>
		<div class="pagin">
					<ul class="topnav tablelist">
					<c:forEach var="first" items="${menuOfRole }">
					<c:forEach var="second" items="${first.rolesource }">
					<c:forEach var="third" items="${second.resbak }">
						<li class="one"><a href="#">${third.sourcoName }</a>
						<c:forEach var="forth" items="${third.funlist }">
						<p class="pp">${forth.funcName }</p><input class="pp" type="checkbox" name=""/>
						</c:forEach>
						</li>
						</c:forEach>
						</c:forEach>
						</c:forEach>
					</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>
	</div>
	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$(document).ready(function(){
		  $(".click").click(function(){
			location.href = '<%=basePath %>menu/addFun/${sourceId}';
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
<script language="JavaScript">
	$('.tablelist li:odd').addClass('odd');
		$(document).ready(function() {
			$(".topnav").accordion({
				accordion:false,
				speed: 500,
				closedSign: '[+]',
				openedSign: '[-]'
			});
		});
	$(function(){
		//全选和不全选
		$(".ee").bind("click",function(){
			if($(this).attr("checked"))
			{
				$(this).parent().children("input").attr("checked",true);
			}else{
				$(this).parent().children("input").attr("checked",false);
			}
		});
		//有一个不选则为不全选
		$(".a").bind("click",function(){
			if($(this).attr("checked")){
				$(this).attr("checked",true);
			}else{
				$(this).attr("checked",false);
			}
			/* var n=0;
			//var checkednum = $(this).parent().children(".a").attr("checked",false).length;
			var inputs = $(".a");
			for(int i=0;i<4;i++)
			{
				if(inputs[i].checked)
				{
					n++;
				}
			}
			if(n==4){
				$(this).parent().children(".ee").attr("checked",true);
			}else{
				$(this).parent().children(".ee").attr("checked",false);
			} */
		});
	});
</script>
</body>

</html>
