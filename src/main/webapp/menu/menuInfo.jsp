<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/menu/css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.8.0.min.js" type="text/javascript"></script>
<title>无标题文档</title>
</head>
<body>
${msg }
	<div class="place"> 	
		<span>菜单管理：</span>
	</div>
	<%-- <c:if test="${not fn:contains(mylimits ,'meun_select')}">
	对不起，你无权访问！
	</c:if> 
	<c:if test="${fn:contains(mylimits ,'meun_select')}">  --%>
	<div class="rightinfo">
		<div class="productList" style="cursor:pointer;"><a href="javascript:addMenu('0')" class="more1 btn2" >新增一级菜单</a></div>
			<table class="tablelist">
				<thead>
					<tr>
						<th>一级菜单</th>
						<th>二级菜单</th>
						<th></th>
						<%-- <c:if test="${fn:contains(mylimits ,'meun_update_delete_add')}"> --%>
						<th id="cz">操作</th>
						<%-- </c:if> --%>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuList}" var="firstData">
						<tr>
							<td>${firstData.seq}-${firstData.sourceName}</td>
			               	<td>------------------------------</td>
			               	<td></td>
							<%-- <c:if test="${fn:contains(mylimits ,'meun_update_delete_add')}"> --%>
							<td class="cz">
							    <input id="button" class="btn1" type="button" onclick="javascript:updateMenu('${firstData.sourceId}','0');" value="更新菜单"/>
						    	<input type="button" class="btn1" onclick="javascript:addMenu('${firstData.sourceId}');" value="增加子菜单"/>
						    	<input id="button" class="btn1" type="button" onclick="javascript:deleteMenu('${firstData.sourceId}');" value="删除该菜单"/>
						    	<input type="button" class="btn1" onclick="javascript:functionList('${firstData.sourceId}');" value="功能点"/>
						    </td>
						 <%--  </c:if> --%>
						</tr>
						<c:forEach items="${firstData.childMenus}" var="secondData">
                		<tr>
	                		<td></td>
	                		<td>${firstData.seq}.${secondData.seq}-${secondData.sourceName}</td>
	                		<td></td>
	                		<%-- <c:if test="${fn:contains(mylimits ,'meun_update_delete_add')}"> --%>
	                		<td class="cz">
		                        <input type="button" type="button" class="btn1" onclick="javascript:updateMenu('${secondData.sourceId}','${secondData.parentSourceId}');" value="更新菜单"/>
		                        <input id="button" class="btn1" type="button" onclick="javascript:deleteMenu('${secondData.sourceId}');" value="删除该菜单"/>
		                        <input type="button" class="btn1" onclick="javascript:functionList('${secondData.sourceId}');" value="功能点"/>
	                   		 </td>
	                   		 <%-- </c:if> --%>
	                   		 
	                   		 <tr>
	                			<td></td>
	                			<td></td>
		                		<td></td>
		                		<td  class="cz"> </td>
                   		 	</tr>
	                   		 
                		</tr>
                		</c:forEach>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<%-- </c:if> --%>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
		
		function addMenu(menuid){
			window.location="addMenu.action?menuid="+menuid;
		}
		
		function updateMenu(sourceId,parentSourceId){
			window.location="addMenu.action?sourceId="+sourceId+"&method_=update&menuid="+parentSourceId;
		}
		
		function functionList(sourceId){
			window.location="functionList.action?sourceId="+sourceId;
		}
		function deleteMenu(sourceId){
			window.location="deleteMenu.action?sourceId="+sourceId;
		}
	</script>
</body>
</html>
