<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.messages_cn.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
<style type="text/css">
	.error{
		color:red;
		font-size:13px;
	}
	.am-form-group{
		height:62px;
		margin-bottom:0;
	}
</style>
</head>
<body>
	${msg }
	<header class="am-topbar admin-header">
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
	</div>
	</header>
	<div class="am-cf admin-main">
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">添加数据</strong>
				</div>
			</div>
			<div class="am-g" style="padding-left: 500px;">
				<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
					<form class="am-form am-form-horizontal"
						action="/Organzation/insertDempt" id="addUser" method="post">

						<div class="am-form-group">
							<label for="password" class="am-u-sm-3 am-form-label">部门名称</label>
							<div class="am-u-sm-9">
								<input type="text" name="orgName" placeholder="部门名称" value="" id="orgName"  /><span class="orgName"></span>
								<input type="hidden" name="parentOrgId" 
									value="${param.parentOrgId }" />
								<!-- 父级id -->
							</div>
						</div>
						<div class="am-form-group">
							<label for="password" class="am-u-sm-3 am-form-label">部门前缀</label>
							<div class="am-u-sm-9">
								<input type="text" name="orgValue" id="orgValue"
									   value="" />(只能是四位字母或者数字结合)<span class="orgValue"></span>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<button type="submit" class="am-btn am-btn-primary"
									style="margin-left: 70px;">保存</button>
								<button type="button" class="am-btn am-btn-primary"
									style="margin-left: 70px;" onclick="history.go(-1);">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer>
	<hr>
	<p class="am-padding-left" align="right">蜂鸟优服 © 2015</p>
	</footer>

</body>
<script type="text/javascript">

var orgValue=document.getElementById("orgValue");
orgValue.onchange=function() {
	var p = /^([a-z]|[A-Z]|[0-9]){4}$/;
		if (!p.exec(this.value)) {
			alert("部门前缀只能是四位字母与数字组合！");
		}
	}
	
</script>

<script type="text/javascript">
            $(function () {
                $("#addUser").validate({
                      /*自定义验证规则*/
                      rules: {
                    	  orgName:{
                            required:true
                          },orgValue:{
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
</html>