<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="../css/menu/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery.validate.messages_cn.js"></script>
<style type="text/css">
	.error{color:red}
	.right{color:green}
	.iStyle{
		color:red;
		float:right;
	}
	#forminfo li{
		width:550px;
	}
</style>
</head>
<body>
	<div class="place">
		<span>新增功能点：</span>
	</div>
	<div class="mainbox">
	<div>
		<form id="frmV" action="${method_}.action" method="post">
			<ul id="forminfo" class="forminfo">
				<li><label>功能点编码<b>*</b></label><span class="code iStyle"></span>
				<input name="actionFun.code" type="text" class="dfinput" value="${actionFun.code}" /></li>
				 <li><input type="hidden" name="actionFun.sourceId" value="${sourceId}"/></li>
				 <c:if test="${actionFun.actionId != null }">
					<input type="hidden" name="actionFun.actionId" value="${actionFun.actionId}"/>
				 </c:if>
				<li><label>功能点标识<b>*</b></label><span class="func iStyle"></span><input name="actionFun.func" type="text" class="dfinput" value="${actionFun.func}"  /></li>
				<li><label>功能点名称<b>*</b></label><span class="funcName iStyle"></span><input name="actionFun.funcName" type="text" class="dfinput" value="${actionFun.funcName}" /></li>
				<li><label>功能点URL<b>*</b></label><span class="funcUrl iStyle"></span><input name="actionFun.funcUrl" type="text" class="dfinput" value="${actionFun.funcUrl}" /></li>
			</ul>
			<ul class="toolbar">
		        <li class="click"><input class="btn" type="submit" value="保存" /></li>
        		<li class="click"><input type="reset" class="btn" value="重置" /></li>
        	</ul>
		</form>
	</div>
	</div> <script type="text/javascript">
            $(function () {
                $("#frmV").validate(
                  {
                      /*自定义验证规则*/
                      rules: {
                    	  code:{
                    		  required:true
                    	  },sourceId:{
                            required:true
                          },func:{
                            required:true
                          },funcName:{
                        	required:true
                          },funcUrl:{
                        	required:true
                          }
                      },
                      /*错误提示位置*/
                      errorPlacement: function (error, element) {
                          error.appendTo("."+element.attr("name"));
                      }
                  }
                );
            });
        </script>
</body>
</html>