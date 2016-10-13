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
	<link rel="stylesheet" href="../assets/css/amazeui.min.css" />
<link rel="stylesheet" href="../assets/css/admin.css">
<script src="../assets/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/jquery.validate.messages_cn.js"></script>
<script src="../assets/js/app.js"></script>
<style type="text/css">
	.error{color:red;
		font-size:13px;
		margin-bottom:0;
	}
	.am-form-group{
		margin-bottom:0;
		height:64px;
	}
</style>
</head>
<body>
	<header class="am-topbar admin-header">
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		</div>
	</header>
	<div class="am-cf admin-main">
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">添加角色</strong>
				</div>
			</div>
			<div class="am-g" style="padding-left: 500px;">
				<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
				
					<form class="am-form am-form-horizontal" action="addRole.action" id="addRole" method="post">
						<p>${msg}</p> 
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">名称</label>
							<div class="am-u-sm-9">
								<input type="text" id="roleName" placeholder="名称  "	name="role.roleName" value="">
								<span class="roleName"></span>
							</div>
						</div>
						
						<div class="am-form-group">
							<label for="password" class="am-u-sm-3 am-form-label">备注</label>
							<div class="am-u-sm-9">
								<input type="text"   name="role.description"	placeholder="备注  " value="">
								<span class="description"></span>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<button type="submit" class="am-btn am-btn-primary">保存</button>
							</div>
						</div>
					</form>
					
					
				</div>
			</div>
		</div>

	</div>
<script type="text/javascript">
   $(function () {
       $("#addRole").validate(
         {
             /*自定义验证规则*/
             rules: {
            	 roleName:{
           		  required:true,
           		  rangelength:[6,16]
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