<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/bootstrap.css">
<script type="text/javascript" src="../js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<title>产品回购页面</title>
</head>
<body>
	<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
	<div class="container">
		<div>
			<ul id="myTab" class="nav nav-pills">
				<li class="active"><a href="#custom" data-toggle="tab">客户信息</a></li>
				<li><a href="#account" data-toggle="tab">原合同信息</a></li>
				<li><a href="#downfile" data-toggle="tab">产品回购信息</a></li>
				<li><a href="#buy_pack" data-toggle="tab">确认回购</a></li>
			</ul>
		</div>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="custom">
				<table>
					<tr>
						<td class="font_table" height="20" style="width: 200px">客户名称</td>
						<td class="font_table" height="20" style="width: 100px">客户性别</td>
						<td class="font_table" height="20" style="width: 100px">证件类型</td>
						<td class="font_table" height="20" style="width: 150px">证件编号</td>
						<td class="font_table" height="20" style="width: 150px">联系方式</td>
					</tr>
					<tr>
							<td class="font_table" height="20" style="width: 200px">${pactInfo.customBaseInfo.custname}</td>
							<c:if test="${pactInfo.customBaseInfo.custgender=='1'}">
								<td class="font_table" height="20" style="width: 200px">男</td>
							</c:if>
							<c:if test="${pactInfo.customBaseInfo.custgender=='0'}">
								<td class="font_table" height="20" style="width: 200px">女</td>
							</c:if>
							<c:if test="${pactInfo.customBaseInfo.papertype=='1'}">
								<td class="font_table" height="20" style="width: 200px">身份证</td>
							</c:if>
							<c:if test="${pactInfo.customBaseInfo.papertype=='0'}">
								<td class="font_table" height="20" style="width: 200px">护照</td>
							</c:if>
							<td class="font_table" height="20" style="width: 200px">${pactInfo.customBaseInfo.papernum}</td>
							<td class="font_table" height="20" style="width: 200px">${pactInfo.customBaseInfo.phonenum}</td>
				</table>
			</div>

			<div class="tab-pane fade in " id="account">
				<table>
					<tr>
						<td class="font_table" height="20" style="width: 200px">合同号</td>
						<td class="font_table" height="20" style="width: 100px">产品名</td>
						<td class="font_table" height="20" style="width: 100px">产品融资方银行账号</td>
						<td class="font_table" height="20" style="width: 150px">产品融资方富有账号</td>
						<td class="font_table" height="20" style="width: 150px">本金</td>
						<td class="font_table" height="20" style="width: 150px">利率</td>
						<td class="font_table" height="20" style="width: 150px">利息</td>
					</tr>
					<tr>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.contractNumber}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.productInfo.productName}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.productInfo.companyName}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.productInfo.bankAccout}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.amount}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.rateFix}</td>
						<td class="font_table" height="20" style="width: 200px">${pactInfo.amount*pactInfo.rateFix}</td>
				</table>
			</div>

			<div class="tab-pane fade in " id="downfile">
				<table>
					<tr>
						<td class="font_table" height="20" style="width: 200px">合同号</td>
						<td class="font_table" height="20" style="width: 100px">产品名</td>
						<td class="font_table" height="20" style="width: 100px">产品融资方银行账号</td>
						<td class="font_table" height="20" style="width: 150px">产品融资方富有账号</td>
						<td class="font_table" height="20" style="width: 150px">本金</td>
						<td class="font_table" height="20" style="width: 150px">利率</td>
						<td class="font_table" height="20" style="width: 150px">利息</td>
					</tr>
					<tr>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.contractNumber}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.productInfo.productName}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.productInfo.companyName}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.productInfo.bankAccout}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.amount}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.rateFix}</td>
						<td class="font_table" height="20" style="width: 200px">${rebuypactInfo.amount*rebuypactInfo.rateFix}</td>
				</table>
			</div>

			<div class="tab-pane fade in " id="buy_pack">
				回购说明：这是回购产品,请仔细查看<br/>
				原合同金额为:${pactInfo.amount}<br/>
				原合同利息为:${pactInfo.backMoney}<br/>
				回购合同金额为:${rebuypactInfo.amount}<br/>
				还款客户金额为:${pactInfo.amount+pactInfo.backMoney-rebuypactInfo.amount}<br/>
				<a href="pactInfo!pactHG2.action?id=${pactInfo.pactId}">确定回购</a>
			</div>
		</div>
	</div>
</html>