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
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.excheck.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.exedit.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.exhide.js"></script>
	<script type="text/javascript" src="<%=basePath%>zTree/js/jquery.ztree.exhide.min.js"></script>
	
<title>Insert title here</title>
</head>
<body> 
<script type="text/javascript">
var setting = {
		 isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
		    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
		    treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性  
		    showLine : true,                  //是否显示节点间的连线  
		    checkable : true,

		    
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		
	};


	var zNodes;
	var zTree; 

	$(function(){  
	    $.ajax({  
	        async : false,  
	        cache:false,  
	        type: 'POST',  
	        dataType : "json",  
	        url: "<%=basePath%>org/findById.action",//请求的action路径  
	        error: function () {//请求失败处理函数  
	            alert('请求失败');  
	        },  
	        success:function(data){ //请求成功后处理函数。    
	            zNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
	        }  
	    });  
	}); 
	$(document).ready(function(){
		zTree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</SCRIPT>
<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>


