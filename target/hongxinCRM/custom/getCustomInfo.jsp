<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单个用户信息查询</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- bootstrap -->
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/layout.css" />
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/elements.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap/icons.css" />
<link rel="stylesheet" href="../css/bootstrap//compiled/form-wizard.css"
	type="text/css" media="screen" />

<!-- libraries -->
<link rel="stylesheet" type="text/css"
	href="../css/bootstrap/lib/font-awesome.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="../css/bootstrap/compiled/signin.css"
	type="text/css" media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<link href="../css/custom/bootstrap.css" rel="stylesheet">
<link href="../css/custom/index.css" rel="stylesheet">
</head>
<body>
	<div class="settings-wrapper" id="pad-wrapper">
		<div class="span3 avatar-box" style="float: right;">
			<div class="personal-image">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-4 col-md-3 fancybox img-responsive">
							<a class="fancy" href="../uploads/${customBaseInfo.checkReceipts[0].recFile}" 
								data-fancybox-group="gallery" title="客户合同信息">
								<img src="../uploads/${customBaseInfo.checkReceipts[0].recFile}" />
							</a>
						</div>
						
  				 <c:forEach items="${customBaseInfo.checkReceipts}" var="receipt">
						<div class="col-xs-12 col-sm-4 col-md-3 fancybox img-responsive" style="display:none;">
							<a class="fancy" href="../uploads/${receipt.recFile}" 
								data-fancybox-group="gallery" title="${receipt.recFile}">
								<img src="../uploads/${receipt.recFile}" />
							</a>
						</div>
				</c:forEach>
					</div>
				</div>
				<script src="../js/custom/bootstrap/jquery-1.11.1.min.js"></script>
				<script src="../js/custom/bootstrap/bootstrap.min.js"></script>
				<script src="../js/custom/bootstrap/ie10-viewport-bug-workaround.js"></script>
				<script src="../js/custom/bootstrap/ie-emulation-modes-warning.js"></script>
				<script type="text/javascript"
					src="../js/custom/jquery.fancybox.js "></script>
				<!--缩略图样式-->
				<script type="text/javascript"
					src="../js/custom/jquery.fancybox-thumbs.js"></script>
				<script type="text/javascript">
					$(document).ready(function() {
						$('.fancy').fancybox();
						$('.fancybox-thumbs').fancybox({
							prevEffect : 'none',
							nextEffect : 'none',
							closeBtn : false,
							arrows : false,
							nextClick : true,
							helpers : {
								thumbs : {
									width : 50,
									height : 50
								}
							}
						});
					});
				</script>
			</div>
		</div>

		<div id="pad-wrapper" class="new-user">
			<span style="color: red; font-size: 15px;">${requestScope.flag}<br />
				</span>
			<form class="new_user_form inline-input" method="post"
				action="addCustomInfo!updateCustomInfo.action">
				<div class="row-fluid form-wrapper">
					<!-- left column -->
					<input type="hidden" value="${customBaseInfo.id}" name="customBaseInfo.id" />
					<div class="span9 with-sidebar">
						<div class="container">
							<br />
							<div class="span12 field-box">
								<label>客户名称:</label> <input class="span9" type="text"
									value="${customBaseInfo.custname}" name="customBaseInfo.custname" />
							</div>
							<div class="span12 field-box">
								<label>客户电话:</label> <input class="span9" type="text"
									value="${customBaseInfo.phonenum}" name="customBaseInfo.phonenum" />
							</div>
							<div class="span12 field-box">
								<label>证件类型:</label>
								<div class="ui-select span5">
									<select name="customBaseInfo.papertype">
										<option value="${customBaseInfo.papertype}"
											<c:if test="${customBaseInfo.papertype=='0'}">selected="selected"</c:if>>身份证</option>
										<option value="${customBaseInfo.papertype}"
											<c:if test="${customBaseInfo.papertype=='1'}">selected="selected"</c:if>>护照</option>
											<option value="${customBaseInfo.papertype}"
											<c:if test="${customBaseInfo.papertype=='2'}">selected="selected"</c:if>>其他</option>
									</select>
								</div>
							</div>
							<div class="span12 field-box">
								<label>证件号码:</label> <input class="span9" type="text"
									value="${customBaseInfo.papernum}" name="customBaseInfo.papernum" />
							</div>
							<div class="span12 field-box">
								<label>性别:</label>
								<div class="ui-select span5">
									<select name="customBaseInfo.custgender">
										<option
											<c:if test="${customBaseInfo.custgender=='0'}">selected="selected"</c:if>
											value="${customBaseInfo.custgender}">男</option>
										<option
											<c:if test="${customBaseInfo.custgender=='1'}">selected="selected"</c:if>
											value="${customBaseInfo.custgender}" selected="selected">女</option>
									</select>
								</div>
							</div>
							<div class="span12 field-box">
								<label>邮箱:</label> <input class="span9" value="${customBaseInfo.email}"
									type="text" name="customBaseInfo.email" />
							</div>
							<div class="span12 field-box">
								<label>客户经理工号:</label> <input class="span9" type="text"
									value="${customBaseInfo.managerId}" name="customBaseInfo.managerId" />
							</div>
							<div class="span12 field-box">
								<label>QQ:</label> <input class="span9" value="${customBaseInfo.qqnum}"
									type="text" name="customBaseInfo.qqnum" />
							</div>
							<div class="span12 field-box">
								<label>微信:</label> <input class="span9" value="${customBaseInfo.weixin}"
									type="text" name="customBaseInfo.weixin" />
							</div>
							<div class="span12 field-box">
								<label>客户当前地址:</label> <input class="span9" type="text"
									value="${customBaseInfo.curAddress}" name="customBaseInfo.curAddress" />
							</div>
							<div class="span12 field-box">
								<label>当前地址邮编:</label> <input class="span9" type="text"
									value="${customBaseInfo.curAddrPost}" name="customBaseInfo.curAddrPost" />
							</div>
							<div class="span12 field-box">
								<label>邮寄地址:</label> <input class="span9" type="text"
									value="${customBaseInfo.postAddr}" name="customBaseInfo.postAddr" />
							</div>
							<div class="span12 field-box">
								<label>是否邮寄地址:</label>
								<div class="ui-select span5">
									<select name="customBaseInfo.selectPost">
										<option
											<c:if test="${customBaseInfo.selectPost=='1'}">selected="selected"</c:if>
											value="${customBaseInfo.selectPost}" selected="selected">选择邮寄</option>
										<option
											<c:if test="${customBaseInfo.selectPost=='0'}">selected="selected"</c:if>
											value="${customBaseInfo.selectPost}">暂时不用邮寄</option>
									</select>
								</div>
							</div>
							<div class="span12 field-box">
								<label>是否开通富有账号:</label>
								<div class="ui-select span5">
									<select name="customBaseInfo.openFyAcount">
										<option
											<c:if test="${customBaseInfo.openFyAcount=='1'}">selected="selected"</c:if>
											value="${customBaseInfo.openFyAcount}" selected="selected">开通</option>
										<option
											<c:if test="${customBaseInfo.openFyAcount=='0'}">selected="selected"</c:if>
											value="${customBaseInfo.openFyAcount}">不开通</option>
									</select>
								</div>
							</div>
							<div class="span12 field-box">
								<label>邮寄地址邮编:</label>
								<div class="address-fields">
									<input class="span9" value="${customBaseInfo.pastEmail}" type="text"
										name="customBaseInfo.pastEmail" />
								</div>
							</div>
							<div class="span11 field-box actions">
								<c:if test="${requestScope.redirect == 'FirstCheckYN'}">
									<a class="btn-glow primary"
										href="addCustomInfo!FirstCheckYN.action?param=yes&id=${customBaseInfo.id}">通过</a>
									<a class="btn-glow primary"
										href="addCustomInfo!FirstCheckYN.action?param=no&id=${customBaseInfo.id}">不通过</a>
								</c:if>
								<c:if test="${requestScope.redirect == 'LastCheckYN'}">
									<a class="btn-glow primary"
										href="addCustomInfo!LastCheckYN.action?param=yes&id=${customBaseInfo.id}">通过</a>
									<a class="btn-glow primary"
										href="addCustomInfo!LastCheckYN.action?param=no&id=${customBaseInfo.id}">不通过</a>
								</c:if>
								<c:if test="${requestScope.redirect == 'updateCustomInfo'}">
									<input type="submit" class="btn-glow primary" value="保存" />
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