<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增客户</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script src="../js/jquery-1.8.0.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="../css/bootstrap//compiled/form-wizard.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/bootstrap/compiled/signin.css" type="text/css" media="screen" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/lib/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/layout.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/icons.css" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
</head>

<body>
	<div id="pad-wrapper" class="new-user">
		<div id="fuelux-wizard" class="wizard row-fluid">
			<ul class="wizard-steps">
				<li data-target="#step1"><span class="step">1</span> <span
					class="title">创建 <br /> 基本客户信息
				</span></li>
				<li data-target="#step2" class="active"><span class="step">2</span>
					<span class="title">填写 <br /> 开户银行信息
				</span></li>
				<li data-target="#step3"><span class="step">3</span> <span
					class="title">修改 <br /> 身份证有效扫描图片
				</span></li>
			</ul>
		</div>
		<form class="new_user_form inline-input" method="post" action="addCustomInfo!updateCustomAccount.action" >
			<div class="row-fluid form-wrapper">
				<!-- left column -->
				<div class="span9 with-sidebar">
					<div class="container">
						<br />
						<div class="span12 field-box">
							<label>开户行省份:</label>
							<div class="ui-select span5">
								<select name="customAccount.provinceCode" onchange="findCity(this.value);">
									<option>请选择</option>
									<c:forEach items="${areas}" var="area">
										<option value="${area.areaCode}" <c:if test="${customAccount.provinceCode==area.areaCode}"> selected="selected" </c:if> >${area.areaName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
<script type="text/javascript">

	var cityC=${customAccount.areaCode};
	function findCity(areaId){
		$.ajax({ 
	       type: "post", 
	       url: "../custom/findCity.action?areaId="+areaId, 
	       cache:false, 
	       dataType :'json',
	       success: function(data){
	    	   var cityList=eval(data);
	    	   if(cityList==null){return false;}
	    	   var obj=document.getElementById('cityCode');
	    	   obj.options.length=0; 
	    	   for (var i = 0; i < cityList.length; i++) {
				var obj=document.getElementById('cityCode'); 
					obj.options.add(new Option(cityList[i].areaName,cityList[i].areaCode)); //这个兼容IE与firefox 
			}
	       } 
	});
	}
</script>
						<div class="span12 field-box">
							<label>开户区县:</label>
							<div class="ui-select span5">
								<select name="customAccount.areaCode" id="cityCode">
									 <c:forEach items="${cityList}" var="city">
									<option value="${city.areaCode}" <c:if test="${customAccount.areaCode==city.areaCode}"> selected="selected" </c:if> >${city.areaName}</option>
								   </c:forEach>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label>开户行行别:</label>
							<div class="ui-select span5">
								<select name="customAccount.payBank">
								   <c:forEach items="${banks}" var="bank">
									<option value="${bank.bankCode}" <c:if test="${customAccount.bankHead==bank.bankCode}"> selected="selected" </c:if>>${bank.bankName}</option>
								   </c:forEach>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label>开户行支行名称:</label> <input class="span9" type="text" name="customAccount.payBankName" value="${customAccount.payBankName}" />
							<input class="span9" type="hidden" name="customAccount.customId" value="${customAccount.customId}" />
						</div>
						<div class="span12 field-box">
							<label>银行卡号:</label> <input class="span9" type="text" name="customAccount.accountBank" value="${customAccount.accountBank}" />
						</div>
						<div class="span11 field-box actions">
							<input type="submit" class="btn-glow primary" value="下一步" style="float: right;" /> 
							<input type="reset" value="清除" class="btn-glow primary" onClick="gotoFaile()" />
						</div>
					</div>
				</div>

			</div>
		</form>
	</div>
</body>
</html>
