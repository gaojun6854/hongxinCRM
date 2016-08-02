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
<link rel="stylesheet" href="../assets/css/admin.css" />
<link rel="stylesheet" href="../assets/css/amazeui.min.css" />
<script language="JavaScript" src="../js/jquery.js"></script>
</head>
<body>
	${requestScope.msg}
	<div class="am-cf admin-main">
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">角色列表</strong> / <small>RoleTable</small>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-md-6 am-cf">
					<div class="am-fl am-cf">
						<div class="am-btn-toolbar am-fl">
							<div class="am-btn-group am-btn-group-xs">
								<button onclick="window.location.href='/jsp/role/add-role.jsp'" type="button" class="am-btn am-btn-default">
									<span class="am-icon-plus"></span>
										新增
								</button>
								<button type="button" class="am-btn am-btn-default"
									id="del-role">
									<span class="am-icon-trash-o"></span> 删除
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-check"> </th>
								<th class="table-author">名称</th>
								<th class="table-type">备注</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${roleList}">
								<tr>
									<td><input type="checkbox" data-id="${role.roleId}" /></td>
									<td>${role.roleName}</td>
									<td>${role.description}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button data-id="${role.roleId}"
													class="am-btn am-btn-default am-btn-xsam-text-danger del">
													<span class="am-icon-trash-o"></span> 删除
												</button>
												<button data-id="${role.roleId}" class="am-btn am-btn-default am-btn-xsam-text-danger getLimit">
												菜单权限
												</button>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$("#del-role").click(function() {
				var ids = new Array();
				var checks = $("table.am-table.am-table-striped.am-table-hover.table-main tbody").find(":checkbox");
				var shan = confirm("确定删除?");
				if (shan) {
					for (var i = 0; i < checks.length; i++) {
						if (checks[i].checked == true) {
							ids.push($(checks[i]).attr("data-id"));
						}
					}
					var url = "/role/delroles"; 
					var param = "ids=" + ids;
					$.post(url,param,function(){
				 	window.location ="/role/allInfo"; 
						
					});
				}
});
		//获取菜单列表
		$(function(){
			$(".getLimit").bind("click",function(){
				var roleId = $(this).attr("data-id");
				window.location.href="resourceBakByroleId?roleId="+roleId;
			});
		});
	$(function() {
		/* 删除单条信息 */
		$(".del").click(
				function() {
					var shan = confirm("确定删除?");
					if (shan) {
						var ids = new Array();
						ids.push($(this).attr("data-id"));
						var url = "deleteRoles.action";
						var param = "ids=" + ids;
						$.post(url, param, function(result) {
							window.location.href = "allInfo.action";
						});
					}
				});
		 
	});
</script>
</body>
</html>
