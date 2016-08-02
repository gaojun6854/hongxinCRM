<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><c:if test="${empty user }">
	<jsp:forward page="/login.jsp" />
</c:if>
<title>无标题文档</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.messages_cn.js"></script>
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
		<form:form id="frmV" action="/menu/saveFun" method="POST" modelAttribute="actionFun">
			<ul id="forminfo" class="forminfo">
				<li><label>功能点编码<b>*</b></label><span class="code iStyle"></span>
				<input name="code" type="text" class="dfinput" value="${actionFun.code}" /></li>
				 <li><input type="hidden" name="sourceId" value="${sourceId}"/></li>
				 <c:if test="${actionId != null }">
						<tr>
					     <form:hidden path="actionId"/>
						 <input type="hidden" name="_method" value="PUT"/>
					   </tr>
				 </c:if>
				<li><label>功能点标识<b>*</b></label><span class="func iStyle"></span><input name="func" type="text" class="dfinput" value="${actionFun.func}"  /></li>
				<li><label>功能点名称<b>*</b></label><span class="funcName iStyle"></span><input name="funcName" type="text" class="dfinput" value="${actionFun.funcName}" /></li>
				<li><label>功能点URL<b>*</b></label><span class="funcUrl iStyle"></span><input name="funcUrl" type="text" class="dfinput" value="${actionFun.funcUrl}" /></li>
			</ul>
			<ul class="toolbar">
		        <li class="click"><input class="btn" type="submit" value="保存" /></li>
        		<li class="click"><input type="reset" class="btn" value="重置" /></li>
        	</ul>
		</form:form>
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
                         // alert(element.attr("name"));
                          error.appendTo("."+element.attr("name"));
                      }
                  }
                );
            });
        </script>
</body>
</html>