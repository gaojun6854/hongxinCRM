<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还款修改</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/layout.css" />
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/icons.css" />
<link rel="stylesheet" href="../css/bootstrap//compiled/form-wizard.css"
	type="text/css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/lib/font-awesome.css" />
<link rel="stylesheet" href="../css/bootstrap/compiled/signin.css"
	type="text/css" media="screen" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />
<link href="../css/custom/bootstrap.css" rel="stylesheet">
<link href="../css/custom/index.css" rel="stylesheet">
<script src="../js/layer/jquery.js"></script>
<script src="../js/layer/layer.js"></script>
</head>
<body>
	<div class="settings-wrapper" id="pad-wrapper">
		<button class="btn-glow primary" style="float: right;" id="pzxx">凭证信息</button>
		<script>
			$('#pzxx')
					.on(
							'click',
							function() {
								//此处插件采用贤心的layui
								//官网地址:http://www.layui.com/
								var index = layer
										.open({
											type : 2,
											content : '../pact/pactInfo!findCheckReceipt.action?pactId=${pactInfo.pactId}',
											area : [ '330px', '210px' ],
											maxmin : true
										});
								layer.full(index);
							});
		</script>
		<div id="pad-wrapper" class="new-user">

			<span style="color: red; font-size: 15px;">${requestScope.flag}<br />
			</span>
			<form class="new_user_form inline-input" method="post" action="failPaymentToFirstPayment.action">
				<div class="row-fluid form-wrapper">
					<!-- left column -->
					<input type="hidden" value="${pactInfo.pactId}"
						name="id" />
					<div class="span9 with-sidebar">
						<div class="container">
							<br />
							<div class="span12 field-box">
								<label>客户名称:</label> <input class="span9" type="text"
									value="${pactInfo.customBaseInfo.custname}"
									name="pactInfo.customBaseInfo.custname" />
							</div>
							<div class="span12 field-box">
								<label>客户电话:</label> <input class="span9" type="text"
									value="${pactInfo.customBaseInfo.phonenum}"
									name="pactInfo.customBaseInfo.phonenum" />
							</div>
							<div class="span12 field-box">
								<label>证件类型:</label>
								<div class="ui-select span5">
									<select name="pactInfo.customBaseInfo.papertype">
										<option value="${pactInfo.customBaseInfo.papertype}"
											<c:if test="${pactInfo.customBaseInfo.papertype=='0'}">selected="selected"</c:if>>身份证</option>
										<option value="${pactInfo.customBaseInfo.papertype}"
											<c:if test="${pactInfo.customBaseInfo.papertype=='1'}">selected="selected"</c:if>>护照</option>
									</select>
								</div>
							</div>
							<div class="span12 field-box">
								<label>证件号码:</label> <input class="span9" type="text"
									value="${pactInfo.customBaseInfo.papernum}"
									name="pactInfo.customBaseInfo.papernum" />
							</div>
							<div class="span12 field-box">
								<label>性别:</label>
								<div class="ui-select span5">
									<select name="pactInfo.customBaseInfo.custgender">
										<option
											<c:if test="${pactInfo.customBaseInfo.custgender=='0'}">selected="selected"</c:if>
											value="${pactInfo.customBaseInfo.custgender}">男</option>
										<option
											<c:if test="${pactInfo.customBaseInfo.custgender=='1'}">selected="selected"</c:if>
											value="${pactInfo.customBaseInfo.custgender}"
											selected="selected">女</option>
									</select>
								</div>
							</div>
							<div class="field-box">
								
								<label style="color: red">购买产品名称</label>
								<select name="pactInfo.productId">
								<c:forEach items="${requestScope.products}" var="product">
									<option
										<c:if test="${pactInfo.productInfo.productId eq product.productId}">selected="selected"</c:if> value="${product.productId}">${product.productName}
									</option>
								</c:forEach> 
								</select>
							</div>
							<div class="span12 field-box">
								<label>产品类型:</label>
								<div class="ui-select span5">
									<select name="pactInfo.productInfo.productType">
										<option
											<c:if test="${pactInfo.productInfo.productType=='1'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}">固定利率型</option>
										<option
											<c:if test="${pactInfo.productInfo.productType=='2'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}"
											selected="selected">固定利率每月返息型</option>
										<option
											<c:if test="${pactInfo.productInfo.productType=='3'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}"
											selected="selected">私募基金型</option>
										<option
											<c:if test="${pactInfo.productInfo.productType=='4'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}"
											selected="selected">指数基金型</option>
									</select>
								</div>
							</div>
							<div class="field-box">
								<label>融资方公司名</label> <input class="span5 inline-input"
									name='pactInfo.productInfo.companyName' type="text"
									value="${pactInfo.productInfo.companyName}" />
							</div>
							<div class="field-box">
								<label>融资方账号</label> <input class="span5 inline-input"
									name='pactInfo.productInfo.bankNo' type="text"
									value="${pactInfo.productInfo.bankNo}" />
							</div>
							<div class="field-box">
								<label>融资方富有账号</label> <input class="span5 inline-input"
									name='pactInfo.productInfo.fuyouAccout' type="text"
									value="${pactInfo.productInfo.fuyouAccout}" />
							</div>
							<div class="field-box">
								<label style="color: red">客户购买金额*</label> <input
									class="span5 inline-input" name='pactInfo.amount' type="text"
									value="${pactInfo.amount}" />
							</div>
							<div class="field-box">
								<label>最低收益</label> <input class="span5 inline-input" name=''
									type="text"
									value="${pactInfo.amount*(1+pactInfo.recruitmentDate)}" />
							</div>
							<div class="field-box">
								<label style="color: red">合同书编号*</label> <input
									class="span5 inline-input" name='pactInfo.contractNumber'
									type="text" value="${pactInfo.contractNumber}"
									style="color: red" />
							</div>
							<div class="field-box">
								<label>合同起息日</label> <input class="span5 inline-input"
									name='pactInfo.countEff' type="text"
									value="${pactInfo.countEff}" />
							</div>
							<div class="span11 field-box actions">
								<input type="submit" class="btn-glow primary" value="保存并退回还款初审" />
								<c:if test="${pactInfo.rebuyFlag eq '00'}">	
									<a class="btn-glow primary" href="../pact/pactInfo!pactHG.action?id=${pactInfo.pactId}">合同回购</a>
								</c:if>
								<c:if test="${pactInfo.rebuyFlag eq '01'}">	
									<input type="button" class="btn-glow primary" value="已回购成功" />
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>