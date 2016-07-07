<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html,charset=UTF-8 ">
<!-- ZTree树形插件 -->  
<link rel="stylesheet" href="<%=basePath%>zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">  
<script type="text/javascript" src="<%=basePath%>zTree/js/jquery-1.4.4.min.js"></script>
	<link rel="stylesheet" href="<%=basePath%>zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.core.js"></script>
	
<title>Insert title here</title>
</head>
<body> 
<script type="text/javascript">
var setting = {
		async: {
			enable: true,
			url:"<%=basePath%>org/findById.action",
			autoParam:["id=orgId"]
			
		},
		callback: {
			beforeClick: beforeClick,//用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作
			beforeAsync: null,//用于捕获异步加载之前的事件回调函数，zTree 根据返回值确定是否允许进行异步加载
			onAsyncError: onAsyncError,//用于捕获异步加载出现异常错误的事件回调函数
			onAsyncSuccess: onAsyncSuccess//用于捕获异步加载正常结束的事件回调函数
		},
		 data: {
	            simpleData: {
	                enable: true
	            }
	        }
		
	};
var nodes = [{ "id":1000000000, "name":"红歆财富",isParent:true}];
	function beforeClick(treeId, treeNode) {
		if (!treeNode.isParent) {
			$.ajax({  
		         type: 'POST',  
		         dataType : "json",  
		         url: "<%=basePath%>org/findEmpByEmpId.action?empId="+treeNode.id,//请求的action路径  
		         error: function () {//请求失败处理函数  
		             alert('请求失败');  
		         },  
		         success:function(data){ //请求成功后处理函数。    
		        	 alert("fullName:"+data.fullName+"-employeeId:"+data.employeeId); 
		         
		              //把后台封装好的简单Json格式赋给treeNodes  
		         }  
		     });  

		} else {
			return true;
		}
	}
	
	
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		//showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
		alert("加载出错");
	}
	function onAsyncSuccess(event, treeId, treeNode, msg) {
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting,nodes);
		
	});
</SCRIPT>
<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>


