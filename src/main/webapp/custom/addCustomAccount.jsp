<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- bootstrap -->
<link href="../css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="../css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
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
					class="title">上传 <br /> 身份证有效扫描图片
				</span></li>
			</ul>
		</div>
		<form class="new_user_form inline-input" method="post"
			action="addCustomAccount.action" >
			<div class="row-fluid form-wrapper">
				<!-- left column -->
				<div class="span9 with-sidebar">
					<div class="container">
						<br />
						<div class="span12 field-box">
							<label>开户行:</label>
							<div class="ui-select span5">
								<select name="customAccount.bankHead">
									<option value="0">国家开发银行</option>
									<option value="1" selected="selected">中国进出口银行</option>
									<option value="2">中国农业发展银行</option>
									<option value="3">中国工商银行</option>
									<option value="4">中国农业银行</option>
									<option value="5">中国银行</option>
									<option value="6">中国建设银行</option>
									<option value="7">交通银行</option>
									<option value="8">中信银行</option>
									<option value="9">中国光大银行</option>
									<option value="10">华夏银行</option>
									<option value="11">中国民生银行</option>
									<option value="12">招商银行</option>
									<option value="13">兴业银行</option>
									<option value="14">广发银行</option>
									<option value="15">平安银行</option>
									<option value="16">上海浦东发展银行</option>
									<option value="17">杭州银行</option>
									<option value="18">浙商银行</option>
									<option value="19">渤海银行</option>
								</select>
							</div>
						</div>
						<div class="span12 field-box">
							<label>银行卡号:</label> <input class="span9" type="text"
								name="customAccount.accountBank" />
						</div>

						<div class="span11 field-box actions">
							<input type="submit" class="btn-glow primary" value="下一步"
								style="float: right;" /> 
							<input
								type="reset" value="清除" class="btn-glow primary"
								onClick="gotoFaile()" />
						</div>
					</div>
				</div>

			</div>
		</form>
	</div>
<script type="text/javascript">
</script>
</body>
</html>
