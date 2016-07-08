<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合约复审详细</title>
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
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.reveal.js"></script>
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
							<a class="fancy" href="../uploads/${pactInfo.receipts[0].recFile}" 
								data-fancybox-group="gallery" title="客户合同信息">
								<img src="../uploads/${pactInfo.receipts[0].recFile}" />
							</a>
						</div>
						
  				 <c:forEach items="${pactInfo.receipts}" var="receipt">
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
			<form class="new_user_form inline-input" method="post" action="pactInfo!updatePactInfo.action">
				<div class="row-fluid form-wrapper">
					<!-- left column -->
					<input type="hidden" value="${pactInfo.pactId}" name="pactInfo.pactId" />
					<div class="span9 with-sidebar">
						<div class="container">
							<br />
							<div class="span12 field-box">
								<label>客户名称:</label> <input class="span9" type="text"
									value="${pactInfo.customBaseInfo.custname}" name="pactInfo.customBaseInfo.custname" />
							</div>
							<div class="span12 field-box">
								<label>客户电话:</label> <input class="span9" type="text"
									value="${pactInfo.customBaseInfo.phonenum}" name="pactInfo.customBaseInfo.phonenum" />
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
									value="${pactInfo.customBaseInfo.papernum}" name="pactInfo.customBaseInfo.papernum" />
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
											value="${pactInfo.customBaseInfo.custgender}" selected="selected">女</option>
									</select>
								</div>
							</div>
							<div class="field-box">
				<label>购买产品名称</label> <input class="span5 inline-input" name='pactInfo.productInfo.productName' type="text" value="${pactInfo.productInfo.productName}" />
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
											value="${pactInfo.productInfo.productType}" selected="selected">固定利率每月返息型</option>
										<option
											<c:if test="${pactInfo.productInfo.productType=='3'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}" selected="selected">私募基金型</option>
										<option
											<c:if test="${pactInfo.productInfo.productType=='4'}">selected="selected"</c:if>
											value="${pactInfo.productInfo.productType}" selected="selected">指数基金型</option>
									</select>
								</div>
							</div>
			<div class="field-box">
				<label>融资方公司名</label> <input class="span5 inline-input" name='pactInfo.productInfo.companyName' type="text" value="${pactInfo.productInfo.companyName}" />
			</div>
			<div class="field-box">
				<label>融资方账号</label> <input class="span5 inline-input" name='pactInfo.productInfo.bankNo' type="text" value="${pactInfo.productInfo.bankNo}" />
			</div>
			<div class="field-box">
				<label>融资方富有账号</label> <input class="span5 inline-input" name='pactInfo.productInfo.fuyouAccout' type="text" value="${pactInfo.productInfo.fuyouAccout}" />
			</div>
			<div class="field-box">
				<label>客户购买金额</label> <input class="span5 inline-input" name='pactInfo.amount' type="text" value="${pactInfo.amount}" />
			</div>
			<div class="field-box">
				<label>最低收益</label> <input class="span5 inline-input" name='' type="text" value="${pactInfo.amount*(1+pactInfo.recruitmentDate)}" />
			</div>
			<div class="field-box">
				<label>合同书编号</label> <input class="span5 inline-input" name='pactInfo.contractNumber' type="text" value="${pactInfo.contractNumber}" style="color: red"/>
			</div>
			<div class="field-box">
				<label>合同起息日</label> <input class="span5 inline-input" name='pactInfo.count_eff' type="text" value="${pactInfo.countEff}" />
			</div>
							<div class="span11 field-box actions">
								<a  href="javascript:void(0);"  class="btn-glow primary" data-reveal-id="myModal" >确认到账</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="myModal" class="reveal-modal">
		<form action="pactInfo!YNpactRecheck.action" method="post" id="yespass">
			<h2>谨慎操作</h2>
			<input type="hidden" name="pactId" value="${pactInfo.pactId}">
			<input type="hidden" name="param" value="yes">
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="sub" type="submit" value="提交"  /></p>
		</form>
	</div>
<script type="text/javascript">
	function aa(){
		var reson=$("#Reson").val();
		$("#noPassReson").val(reson);
		$("#nopass").submit();
	}
</script>
</body>
</html>