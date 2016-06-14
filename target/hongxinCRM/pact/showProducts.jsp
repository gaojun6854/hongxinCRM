<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap/bootstrap.css">
<script type="text/javascript" src="../js/jquery-2.2.0.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<title>产品购买页面</title>
		<script type="text/javascript">
		function shopping(id,product_id,product_name){
			$("#custid").val(id);
			$("#productid").val(product_id);
			$("#span1").html(product_name);
		}
		function aa(){
			window.open( '../uploadify/fileupclose.jsp','_blank','width=700,height=500,toolbars=yes,resizable=yes,scrollbars=yes,left=50%,top=50%');
			return false;
		}
		function shabi(a,b,c,d,e,f,g){
			$("#id0").html(a);
			$("#id1").html(a);
			$("#id2").html(b);
			$("#id3").html(c);
			$("#id4").html(d);
			$("#id5").html(e);
			$("#id6").html(f);
			$("#productid").val(g);
		}
</script>
</head>
<body>
	<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
	<div class="container">
		<div>
			<ul id="myTab" class="nav nav-pills">
				<li class="active"><a href="#custom" data-toggle="tab">客户信息</a></li>
				<li><a href="#account" data-toggle="tab">产品信息</a></li>
				<li><a href="#downfile" data-toggle="tab">原始文件</a></li>
				<li><a href="#buy_pack" data-toggle="tab">确认购买</a></li>
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
							<td class="font_table" height="20" style="width: 200px">${cust.custname}</td>
							<c:if test="${cust.custgender=='1'}">
								<td class="font_table" height="20" style="width: 200px">男</td>
							</c:if>
							<c:if test="${cust.custgender=='0'}">
								<td class="font_table" height="20" style="width: 200px">女</td>
							</c:if>
							<c:if test="${cust.papertype=='1'}">
								<td class="font_table" height="20" style="width: 200px">身份证</td>
							</c:if>
							<c:if test="${cust.papertype=='0'}">
								<td class="font_table" height="20" style="width: 200px">护照</td>
							</c:if>
							<td class="font_table" height="20" style="width: 200px">${cust.papernum}</td>
							<td class="font_table" height="20" style="width: 200px">${cust.phonenum}</td>
				</table>
			</div>

			<div class="tab-pane fade in " id="account">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
					</div>
					<div>
						<ul class="nav navbar-nav" id="t_product_info">
							<li class="dropdown" id="chang_product">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="id0" style="color: red">请选择产品类别  <b class="caret"></b></a>
							<ul class="dropdown-menu">
							<c:forEach items="${products}" var="product">
										<li><a href="#" id="chang_product" onclick="javascript:shabi('${product.productName}','${product.productType}','${product.companyName}','${product.companyCode}','${product.fuyouAccout}','${product.bankAccout}','${product.productId}');">${product.productName}</a></li>
							</c:forEach>	
								</ul></li>
						</ul>
					</div>
				</nav>
				<table class="table " id="table_product_info">
					<tr>
						<td>产品名称</td>
						<td id="id1">红银财富定增系列产品6号</td>
					</tr>
					<tr>
						<td>产品类型</td>
						<td id="id2">固定利率</td>
					</tr>
					<tr>
						<td>融资方名称</td>
						<td id="id3">红银财富管理有限公司</td>
					</tr>
					<tr>
						<td>融资方营业代码</td>
						<td id="id4">1010</td>
					</tr>
					<tr>
						<td>融资方指定银行</td>
						<td id="id5">200001</td>
					</tr>
					<tr>
						<td>融资方银行账号</td>
						<td id="id6">123456789</td>
					</tr>
				</table>
			</div>

			<div class="tab-pane fade in " id="downfile">
				<p>开始上传文件</p>
				<input type="button" onclick="javascript:aa()" value="上传图片"><span id="pic" style="color: red"></span><br/>
			</div>

			<div class="tab-pane fade in " id="buy_pack">
				<table class="table table-striped">
					<tr>
							<td>
							<form action="pactInfo!addPactInfo.action" method="post" style="margin-left: 40%;">
							<%String pactId=UUID.randomUUID().toString();
				session.setAttribute("custIDS", pactId);
				session.setAttribute("picType", 2);
				
			%>	
			<div class="col-sm-6">
			<input type="hidden" id="custid" name="pactInfo.custId" value="${cust.id}">
			<input type="hidden" id="productid" name="pactInfo.productId" value="1007">
			<input type="hidden" id="repayFlag" name="repayFlag" value="${repayFlag}">
			<input type="hidden" id="lastPactId" name="lastPactId" value="${lastPactId}">
			起投金额10万,每次增加则1万：<br/>
			请输入再投金额：<input type="text" id="amount" name="pactInfo.amount" class="form-control"><br/>
			请输入合同编号：<input type="text" id="pactNo" name="pactInfo.contractNumber" class="form-control"><br/>
			请输入起息日期：<input type="text" id="countEff" name="pactInfo.countEff" class="form-control">格式:20160317<br/>
			<input type="submit" id="datebox" value="确定购买" class="btn1" />
			</div></form>
							</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</html>