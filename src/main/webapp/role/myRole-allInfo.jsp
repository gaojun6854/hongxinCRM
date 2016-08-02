<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="<%=basePath %>assets/css/admin.css" />
<link rel="stylesheet" href="<%=basePath %>assets/css/amazeui.min.css" />
<script language="JavaScript" src="<%=basePath%>js/jquery.js"></script>
</head>
<body>
	<c:if test="${empty user }">
		<jsp:forward page="/login.jsp" />
	</c:if>
	${RequestScope.msg}
	<div class="am-cf admin-main">


		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">我的角色列表</strong> / <small>RoleTable</small>
					<span style="color:red;margin-left:30px;">您当前属于普通用户，无权修改用户权限</span>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th width="50px;"> 序号</th>
								<th class="table-author">名称</th>
								<th class="table-type">备注</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>


							<c:forEach var="role" items="${userRoleList}" varStatus="inde">
							<c:forEach var="role1" items="${role.roles}">
								<tr>
									<td>${inde.index+1 }</td>
									<td>${role1.roleName}</td>
									<td>${role1.description}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button data-id="${role1.roleId}" class="am-btn am-btn-default am-btn-xsam-text-danger getLimit">
												菜单权限
												</button>
											</div>
										</div>
									</td>
								</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" >
		//获取菜单列表
		$(function(){
			$(".getLimit").bind("click",function(){
				var roleId = $(this).attr("data-id");
				window.location.href="/role/ResourceBakByroleId?roleId="+roleId;
			});
		});
</script>
</body>
</html>
