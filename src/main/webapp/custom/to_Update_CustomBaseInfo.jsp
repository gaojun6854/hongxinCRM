<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红歆财富投资有限公司-新增客户</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/bootstrap/layout.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/icons.css" />
<link rel="stylesheet" href="../css/bootstrap//compiled/form-wizard.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/lib/font-awesome.css" />
<link rel="stylesheet" href="../css/bootstrap/compiled/signin.css" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="pad-wrapper" class="new-user">
		<div id="fuelux-wizard" class="wizard row-fluid">
			<ul class="wizard-steps">
				<li data-target="#step1" class="active"><span class="step">1</span>
					<span class="title">修改 <br /> 基本客户信息
				</span></li>
				<li data-target="#step2"><span class="step">2</span> <span
					class="title">修改<br /> 开户银行信息
				</span></li>
				<li data-target="#step3"><span class="step">3</span> <span
					class="title">上传 <br /> 身份证有效扫描图片
				</span></li>
			</ul>
		</div>
		<span style="color: red;font-size: 15px;">${requestScope.msg}</span>
		<form class="new_user_form inline-input" method="post" action="addCustomInfo!updateCustomInfo.action" onsubmit="return validate_form(this)">
			<div class="row-fluid form-wrapper">
				<!-- left column -->
				<div class="span9 with-sidebar">
					<div class="container">
						<br />
						<span style="color: red;">*为必填项</span>
						<div class="span12 field-box">
							<label style="color: red; ">*客户姓名:</label> <input class="span9" type="text" value="${customBaseInfo.custname}"
								name="custname" />
								<input class="span9" type="hidden" value="${customBaseInfo.id}"
								name="customBaseInfo.id" />
						</div>
						<div class="span12 field-box">
							<label style="color: red; ">*联系方式:</label> <input class="span9" type="text" value="${customBaseInfo.phonenum}"
								name="phonenum" />
						</div>
						<div class="span12 field-box">
							<label style="color: red; ">*证件类型:</label>
							<div class="ui-select span5">
								<select name="customBaseInfo.papertype">
								
									<option value="0" <c:if test="${customBaseInfo.papertype=='0'}"> selected="selected" </c:if> >身份证-(默认)</option>
									<option value="1" <c:if test="${customBaseInfo.papertype=='1'}"> selected="selected" </c:if> >护照</option>
									<option value="2" <c:if test="${customBaseInfo.papertype=='2'}"> selected="selected" </c:if> >其他</option>
								
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label style="color: red; ">*证件号码:</label> <input class="span9" type="text" value="${customBaseInfo.papernum}"
								name="papernum" />
						</div>
						<div class="span12 field-box">
							<label>性别:</label>
							<div class="ui-select span5">
								<select name="customBaseInfo.custgender">
								<option value="0" <c:if test="${customBaseInfo.custgender=='0'}"> selected="selected" </c:if> >男</option>
									<option value="1" <c:if test="${customBaseInfo.custgender=='1'}"> selected="selected" </c:if> >女</option>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label style="color: red; ">*邮箱:</label> <input class="span9" type="text" name="email" value="${customBaseInfo.email}" /> 
						</div>
						<div class="span12 field-box">
							<label style="color: red; ">*客户经理工号:</label> <input class="span9" type="text" value="${customBaseInfo.managerId}"
								name="managerId" />
						</div>
						<div class="span12 field-box">
							<label>QQ:</label> <input class="span9" type="text" name="customBaseInfo.qqnum" value="${customBaseInfo.qqnum}" />
						</div>
						<div class="span12 field-box">
							<label>微信:</label> <input class="span9" type="text" name="customBaseInfo.weixin" value="${customBaseInfo.weixin}" />
						</div>
						<div class="span12 field-box">
							<label>客户当前地址:</label> <input class="span9" type="text" value="${customBaseInfo.curAddress}"
								name="customBaseInfo.curAddress" />
						</div>
						<div class="span12 field-box">
							<label>当前地址邮编:</label> <input class="span9" type="text" value="${customBaseInfo.curAddrPost}"
								name="customBaseInfo.curAddrPost" />
						</div>
						<div class="span12 field-box">
							<label>邮寄地址邮编::</label> <input class="span9" type="text" value="${customBaseInfo.selectPost}"
								name="customBaseInfo.pastEmail" />
						</div>
						<div class="span12 field-box">
							<label>是否邮寄地址:</label>
							<div class="ui-select span5">
								<select name="customBaseInfo.selectPost" id="selectPost">
									<option value="0" <c:if test="${customBaseInfo.selectPost=='0'}"> selected="selected" </c:if> >暂时不用邮寄-(默认)</option>
									<option value="1" <c:if test="${customBaseInfo.selectPost=='1'}"> selected="selected" </c:if> >选择邮寄</option>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label>是否开通富有账号:</label>
							<div class="ui-select span5">
								<select name="customBaseInfo.openFyAcount">
									<option value="0" <c:if test="${customBaseInfo.openFyAcount=='0'}"> selected="selected" </c:if> >不开通</option>
									<option value="1" <c:if test="${customBaseInfo.openFyAcount=='1'}"> selected="selected" </c:if> >开通-(默认)</option>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label>邮寄地址</label>
							<div class="address-fields">
								<input class="span9" type="text" name="postAddr" value="${customBaseInfo.postAddr}" />
							</div>
						</div>
						<div class="span11 field-box actions">
							<input type="submit" class="btn-glow primary" value="下一步"
								style="float: right;" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
<script type="text/javascript">
/* 红歆财富-By-swift*/
	function validate_required(field,alerttxt)
	{
	with (field)
	  {
	  if (value==null||value=="")
	    {alert(alerttxt);return false}
	  else {return true}
	  }
	}
	/* 
		判断邮寄地址js
	*/
	function validate_required4postEmail(field,alerttxt)
	{
		var selectPost=$("#selectPost").val();
	with (field)
	  {
	  if (value==null&&selectPost==1||value==""&&selectPost==1)
	    {alert(alerttxt);return false}
	  else {return true}
	  }
	}
	
	function validate_form(thisform)
	{
		with (thisform)
		  {
			if (validate_required(custname,"客户姓名不能空!")==false){
				custname.focus();return false;
			}else if (validate_required(phonenum,"联系方式不能为空!")==false){
				phonenum.focus();return false;
		  	}else if (validate_required(papernum,"证件号不能为空!")==false){
		  		papernum.focus();return false;
		  	}else if (validate_required(email,"邮箱不能为空!")==false){
			  email.focus();return false;
		  	}else if (validate_required(managerId,"客户经理工号不能为空!")==false){
		  		managerId.focus();return false;
		  	}else if (validate_required4postEmail(postAddr,"您选择邮寄,邮寄地址不能为空不能为空!")==false){
		  		postAddr.focus();return false;
		  	}
		}
	}
</script>
</body>
</html>