<%@page import="com.hongxin.entity.TPactInfo"%>
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-script-type" content="text/javascript">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="content-language" content="en-gb">
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../css/custom/dataTable.css" media="screen">
<style type="text/css">
body, th, td { font-family: Arial, Verdana, sans-serif; font-size: 0.9em; }
a:link, a:visited { color: #59B337; }
a:hover, a:active, a:focus { color: #000000; }
table.dataTable tr.marked { background-color: #FFD900; }
</style>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/* reveal-modal */
.reveal-modal-bg{position:fixed;height:100%;width:100%;background-color:#000;z-index:100;display:none;top:0;left:0;}
.reveal-modal{visibility:hidden;top:100px;left:50%;margin-left:-300px;width:420px;background:#eee url(../images/modal-gloss.png) no-repeat -200px -80px;position:absolute;z-index:101;padding:30px 40px 34px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-moz-box-shadow:0 0 10px rgba(0,0,0,.4);-webkit-box-shadow:0 0 10px rgba(0,0,0,.4);-box-shadow:0 0 10px rgba(0,0,0,.4);}
.reveal-modal .close-reveal-modal {
font-size:22px;line-height:.5;position:absolute;top:8px;right:11px;color:#aaa;text-shadow:0 -1px 1px rbga(0,0,0,.6);font-weight:bold;cursor:pointer;} 
.reveal-modal h2{font-size:18px;color:#990000;padding:0 0 20px 0;}
.reveal-modal p{padding:0 0 15px 0;}
</style>
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.reveal.js"></script>
<title>合约初审</title>
</head>
<body>
<h1 align="center" style="color: highlight;">合同维护</h1>
<span style="color: red; font-size: 15px;">${msg}<br /></span>
<form  action="pactInfo!findFailPact.action" method="post" id="submitForm">
	合同号:<input type="text" id="pactNum" name="pactNum" value="${pactNum}" />
	客户名:<input type="text" id="custName" name="custName" value="${custName}" />
	客户手机号:<input type="text" id="phoneNum" name="phoneNum" value="${phoneNum}"  />
	客户身份证:<input type="text" id="paperNum" name="paperNum" value="${paperNum}"   />
	<input type="submit" value="查询" />
</form>
	<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
		<thead>
			<tr>
				<th class="dataTableHeader">合同号</th>
				<th class="dataTableHeader">产品名</th>
				<th class="dataTableHeader">产品融资方银行账号</th>
				<th class="dataTableHeader">产品融资方账号</th>
				<th class="dataTableHeader">本金</th>
				<th class="dataTableHeader">利率</th>
				<th class="dataTableHeader">利息</th>
				<th class="dataTableHeader">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.list}" var="pactInfo">
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" /><a
						href="pactInfo!getPactInfos.action?no=2&redirect=failPact&id=${pactInfo.pactId}">${pactInfo.contractNumber}</a></td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.productName }</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.bankAccout }</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.productInfo.fuyouAccout }</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.amount}</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${pactInfo.recruitmentDate }</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />
					<fmt:formatNumber
							value="${pactInfo.amount*(1+pactInfo.recruitmentDate)}"
							pattern="0.00" /></td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />
						<c:if test="${pactInfo.pactFlow=='1'}">合同初审中</c:if>
						<c:if test="${pactInfo.pactFlow=='2'}">合同初审失败</c:if>
						<c:if test="${pactInfo.pactFlow=='3'}">合同复审中</c:if>
						<c:if test="${pactInfo.pactFlow=='4'}">合同复审失败</c:if>
						<c:if test="${pactInfo.pactFlow=='5'}">还款初审中</c:if>
						<c:if test="${pactInfo.pactFlow=='6'}">还款初审失败</c:if>
						<c:if test="${pactInfo.pactFlow=='7'}">还款复审中</c:if>
						<c:if test="${pactInfo.pactFlow=='8'}">还款复审失败</c:if>
						<c:if test="${pactInfo.pactFlow=='10'}">回购还款客户</c:if>
						<c:if test='${pactInfo.pactFlow=="11"}'>回购还款客户失败</c:if>
						<c:if test='${pactInfo.pactFlow=="12"}'>合同正常结束</c:if>
						<c:if test='${pactInfo.pactFlow=="13"}'>合同已作废</c:if>
						<c:if test='${pactInfo.pactFlow=="14"}'>到款确认中</c:if>		
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	 
	 <!-- 分页 -->
	 <center>
    	<font size="3">第<font color="red"><s:property value="#request.pageBean.currentPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBean.totalPage"/></font>页 </font>&nbsp;&nbsp;
        <font size="3">共<font color="red"><s:property value="#request.pageBean.allRows"/></font>条记录</font>
        
        <s:if test="#request.pageBean.currentPage == 1">
            首页&nbsp;&nbsp;&nbsp;上一页
        </s:if>
        
        <s:else>
        
        <input type="button" value="首页" onclick="jumpNormalPage(1);"/>
           <%--  <a href="${pageBean.actionUrl}">首页</a> --%>
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a> --%>
            <input type="button" value="上一页"  onclick="jumpNormalPage('<s:property value="#request.pageBean.currentPage - 1"/>');"/>
        </s:else>
        
        <s:if test="#request.pageBean.currentPage != #request.pageBean.totalPage">
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a> --%>
            <input type="button" value="下一页" onclick="jumpNormalPage('<s:property value="#request.pageBean.currentPage + 1"/>');" />
            &nbsp;&nbsp;&nbsp;
            <%-- <a href="${pageBean.actionUrl}?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a> --%>
            <input type="button" value="尾页" onclick="jumpNormalPage('<s:property value="#request.pageBean.totalPage"/>');" />
        </s:if>
        
        <s:else>
            下一页&nbsp;&nbsp;&nbsp;尾页
        </s:else>
    </center>
    
    <center>
        <form action="${pageBean.actionUrl}" onsubmit="return validate();">
            <font size="4">跳转至</font>
            <input type="text" size="2" name="page">页
            <input type="submit" value="跳转">
        </form>
    </center>
	<script type="text/javascript">
		function validate() {
			var page = document.getElementsByName("page")[0].value;
			if(""==page){
				$("#submitForm").attr("action", "${pageBean.actionUrl}").submit();
				return false;
			}
			 if (page > '${pageBean.totalPage}') {
				alert("你输入的页数大于最大页数，页面将跳转到首页！");
				//window.document.location.href = "${pageBean.actionUrl}";
				$("#submitForm").attr("action", "${pageBean.actionUrl}").submit();
				return false;
			}
			return true;
		}
		
		/** 普通跳转 **/
		function jumpNormalPage(page){
			$("#submitForm").attr("action", "${pageBean.actionUrl}?page=" + page).submit();
		}
	</script>
</body>
</html>
