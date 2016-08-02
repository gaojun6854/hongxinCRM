<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath%>js/showPic.js"></script>
<style type="text/css">
.error {
	color: red;
	font-size: 13px;
}
</style>
</head>
<body style="width: 900px;">
	${msg }
	<div style="background-color: #ccc;">新增员工信息</div>
	<form action="/emp/addDirectorInfo" method="post" name="myFomr"
		id="myFomr" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" border="0px"
			style="width: 100%; margin-top: 0px" class="table_blue">
			<tbody>
				<tr>
					<td width="237" rowspan="17" align="center" valign="middle"
						class="td_blue" style="text-align: left; background-color: #fff"><table
							cellspacing="0" cellpadding="0" style="width: 100%">
							<tbody>
								<tr>
									<td colspan="5" class="td_blue" style="text-align: center;"><div
											style="height: 140px; width: 100%;" class="border_blue">
											<table cellspacing="0" cellpadding="0" style="width: 100%">
												<tbody>
													<tr>
														<td height="25" align="center">个人照片</td>
													</tr>
													<tr>
														<td align="center"><span id="preview0"> <img
																id="uploadImage" name="picpath" src="" alt="上传照片"
																style="height: 138px; width: 108px; border-width: 0px;" />
														</span></td>
													</tr>
													<tr>
														<td style="text-align: center; width: auto;"><input
															type="file" name="myfiles"
															onchange="previewImage(this,'preview0','uploadImage')" />
														</td>
													</tr>
												</tbody>
											</table>
										</div></td>
								</tr>
							</tbody>
						</table>
					<td width="18%" class="td_blue"
						style="text-align: left; color: #314571">&nbsp;</td>
					<td width="22%" class="td_blue"
						style="text-align: left; background-color: #fff">&nbsp;</td>
					<td width="20%" class="td_blue"
						style="text-align: left; color: #314571">&nbsp;</td>
					<td width="20%" class="td_blue"
						style="text-align: left; background-color: #fff">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571"><span
								class="td_blue" style="text-align: left; color: #314571">岗位：&nbsp;</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; background-color: #fff">
								<select name="stationCode" id="stationCode" class="dropdown"
								style="width: 144px;">
									<option selected="selected"
										value="364363ed-4b4c-4108-a139-e879605f2887">主管</option>
							</select>
						</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">姓名：&nbsp;<span
							style="color: Red">*</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="fullName" type="text" id="fullName" class="textbox"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">
							身份证号：&nbsp;<span style="color: Red">*</span>
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="idNumber" type="text" id="idNumber" class="textbox"
							style="width: 145px;" /><span class="idNumber"></span>
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">性别：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <select
							name="sex" id="sex" class="dropdown" style="width: 144px;">
								<option value="0" selected="selected">女</option>
								<option value="1">男</option>
						</select>
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571"><span
								class="td_blue" style="text-align: left; color: #314571">所在部门：</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">
								<select name="orgId" id="orgId" class="dropdown"
								style="width: 144px;">
									<c:forEach var="org" items="${nodirectOrg}">
										<option value="${org.orgId }">${org.orgName}</option>
									</c:forEach>
							</select>
						</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">用户名：<span
							class="td_blue" style="text-align: left; color: #314571">&nbsp;<span
								style="color: Red">*</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="englishName" type="text" id="englishName" class="textbox"
							onblur="return IdNumberSubmit();" style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">
							出生日期：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="birthday" type="date" id="birthday" class="textbox"
							style="width: 145px;" />
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">民族：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <select
							name="nationid" id="nationid" class="dropdown"
							style="width: 144px;">
								<c:forEach var="list" items="${nationids }">
									<option value="${list.dataId }">${list.dictdataName }</option>
								</c:forEach>
						</select>
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">
							籍贯：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="natives" type="text" id="natives" class="textbox"
							style="width: 145px;" />
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">政治面貌：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <select
							name="policyid" id="policyid" class="dropdown"
							style="width: 144px;">
								<c:forEach var="list" items="${policyids }">
									<option value="${list.dataId }">${list.dictdataName }</option>
								</c:forEach>
						</select>
					</span></td>
				</tr>

				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; color: #314571"> 身高(CM)：</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"> <input
							name="height" type="text" id="height" class="textbox"
							style="width: 145px;" />
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; color: #314571">体重(KG)：</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="weight" type="text" id="weight" class="textbox"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">
								手机号码：<span style="color: Red">*</span>
						</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"> <input
							name="mobile" id="mobile" type="text" class="textbox"
							style="width: 145px;" /><span class="mobile"></span>
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">现居住地址：</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="address" type="text" id="address" class="textbox"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">
								文化程度：</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"> <select
							name="educationid" id="educationid" class="dropdown"
							style="width: 144px;">
								<c:forEach var="list" items="${educationids }">
									<option value="${list.dataId }">${list.dictdataName }</option>
								</c:forEach>
						</select>
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">专业：</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="major" type="text" id="major" class="textbox"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">
								婚姻状况：</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"> <select
							name="marriageid" id="marriageid" class="dropdown"
							style="width: 144px;">
								<c:forEach var="list" items="${marriageids }">
									<option value="${list.dataId }">${list.dictdataName }</option>
								</c:forEach>
						</select>
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">在职状态：&nbsp;</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <select
							name="stateid" id="stateid" class="dropdown"
							style="width: 144px;">
								<option value="0" selected="selected">在职</option>
								<option value="1">离职</option>
						</select>
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left"><span class="td_blue"
						style="text-align: left; color: #314571; vertical-align: top; padding-top: 5px">
							疾病史：</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">&nbsp;&nbsp;<span
							style="text-align: left"> <input type="radio"
								name="diseaseinfo" class="jbs1" /> 有 <input type="radio"
								name="diseaseinfo" class="jbs2" checked /> 无
						</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">备注：&nbsp;<span
								style="color: Red">*</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="jbs2" type="text" value="no" id="jbs" style="width: 145px;" />
					</span></td>
				</tr>

			</tbody>
		</table>

		<table cellpadding="0" cellspacing="0" border="0px"
			style="width: 100%; margin-top: 0px" class="table_blue">
			<tbody>
				<tr>
					<td width="290" rowspan="17" align="center" valign="middle"
						class="td_blue" style="text-align: left; background-color: #fff"><table
							cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td colspan="5" class="td_blue" style="text-align: center;"><div
											style="height: 140px; width: 100%;" class="border_blue">
											<table cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td height="25" align="center"><span
															class="border_blue" style="height: 140px; width: 100%;">身份证照片</span></td>
													</tr>
													<tr>
														<td align="center"><span id="preview1"
															class="border_blue" style="height: 140px; width: 100%;">
																<img id="uploadImage1" src="" alt="身份证照片" align="middle"
																style="height: 140px; width: 108px;" />
														</span></td>
													</tr>
													<tr>
														<td style="text-align: center; width: auto;"><span
															style="text-align: center"> <input type="file"
																name="myfiles"
																onchange="previewImage(this,'preview1','uploadImage1')" />
														</span></td>
													</tr>
												</tbody>
											</table>
										</div></td>
								</tr>
							</tbody>
						</table>
					<td width="268" class="td_blue"
						style="text-align: left; color: #314571"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571"><span
								style="text-align: left"><span class="td_blue"
									style="text-align: left; color: #314571; vertical-align: top; padding-top: 5px">
										生理缺陷：</span></span></span></span></td>
					<td width="323" class="td_blue"
						style="text-align: left; background-color: #fff"><span
						class="td_blue" style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; color: #314571">&nbsp;&nbsp;<span
								style="text-align: left"> <input type="radio"
									name="disabilityinfo" class="slqx1" /> 有 <input type="radio"
									name="disabilityinfo" class="slqx2" checked="checked" /> 无
							</span></span></span></td>
					<td width="293" class="td_blue"
						style="text-align: left; color: #314571"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; background-color: #fff"><span
								class="td_blue" style="text-align: left; color: #314571">备注：&nbsp;<span
									style="color: Red">*</span></span></span></span></td>
					<td width="293" class="td_blue"
						style="text-align: left; background-color: #fff"><span
						class="td_blue" style="text-align: left; background-color: #fff">
							<input name="slqx" value="no" type="text" id="slqx2"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571"><span
								class="td_blue" style="text-align: left; color: #314571">到岗日期</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"><span
							class="td_blue" style="text-align: left; background-color: #fff">
								<input name="stationDate" type="date" id="stationDate"
								class="textbox" style="width: 145px;" />
						</span></span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">级别：&nbsp;<span
								style="color: Red">*</span></span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <select
							name="gradeCode" id="gradeCode" class="dropdown"
							style="width: 144px;">
								<c:forEach var="list" items="${gradeCodes }">
									<option value="${list.dataId }">${list.dictdataName }</option>
								</c:forEach>
						</select>
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">
								计量工资：</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="calculatewages" type="text" id="calculatewages"
							class="textbox" style="width: 145px;" />
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">工资卡卡号：</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="cardid" type="text" id="cardid" class="textbox"
							style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">
								紧急联系电话：&nbsp;</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="urgenttelephone" type="text" class="textbox"
							style="width: 145px;" />
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">紧急联系人关系：&nbsp;</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="urgentrelation" type="text" id="urgentrelation"
							class="textbox" style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">
								工资形式：</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="wagestype" type="text" id="wagestype" class="textbox"
							style="width: 145px;" />
					</span></td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"><span
							class="td_blue" style="text-align: left; color: #314571">紧急联系人：&nbsp;</span></span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							name="urgentcontact" type="text" id="urgentcontact"
							class="textbox" style="width: 145px;" />
					</span></td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>

				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>

			</tbody>
		</table>
		<table cellpadding="0" cellspacing="0" border="0px"
			style="width: 100%; margin-top: 0px" class="table_blue">
			<tbody>
				<tr>
					<td width="289" rowspan="17" align="center" valign="middle"
						class="td_blue" style="text-align: left; background-color: #fff"><table
							cellspacing="0" cellpadding="0" style="width: 100%">
							<tbody>
								<tr>
									<td colspan="5" class="td_blue" style="text-align: center;"><div
											style="height: 140px; width: 100%;" class="border_blue">
											<table cellspacing="0" cellpadding="0" style="width: 100%">
												<tbody>
													<tr>
														<td height="25" align="center">银行卡照片</td>
													</tr>
													<tr>
														<td align="center"><span id="preview2"
															class="border_blue" style="height: 140px; width: 100%;">
																<img id="uploadImage2" src="" alt="银行卡" align="middle"
																style="height: 138px; width: 108px;" />
														</span></td>
													</tr>
													<tr>
														<td style="text-align: center; width: auto;"><span
															style="text-align: center"> <input type="file"
																name="myfiles"
																onchange="previewImage(this,'preview2','uploadImage2')" />
														</span></td>
													</tr>
												</tbody>
											</table>
										</div></td>
								</tr>
							</tbody>
						</table>
					<td width="270" class="td_blue"
						style="text-align: left; color: #314571">&nbsp;</td>
					<td width="199" class="td_blue"
						style="text-align: left; background-color: #fff">&nbsp;</td>
					<td width="271" class="td_blue"
						style="text-align: left; color: #314571">&nbsp;</td>
					<td width="270" class="td_blue"
						style="text-align: left; background-color: #fff">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571">&nbsp;&nbsp;备注：</span></td>
					<td colspan="2" rowspan="4" class="td_blue"
						style="text-align: left; background-color: #fff"><textarea
							name="remark" cols="38" rows="5"></textarea></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>

				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td width="168" class="td_blue"
						style="text-align: left; display: none; background-color: #fff">&nbsp;<input
						type="text" name="creator" value="${userName }">
					</td>
				</tr>
				<tr>
					<td style="text-align: left">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue"><span
						class="td_blue" style="text-align: left; color: #314571"> <input
							type="submit" id="save" value="保存" />
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue"><span class="td_blue"
						style="text-align: left; background-color: #fff"> <input
							type="button" id="cancel" value="取消" />
					</span></td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: left">&nbsp;</td>
					<td style="text-align: left; color: #314571" class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
					<td style="text-align: left; background-color: #fff"
						class="td_blue">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
	$("#cancel").click(function() {
		window.location.href = "/emp/allinfo";
	});
	$(function() {
		$(".jbs1").bind("click", function() {
			$("#jbs2").val("暂时保密").removeAttr("readonly");
		});
		$(".jbs2").bind("click", function() {
			$("#jbs2").val("no").attr("readonly", "readonly");
		});
	});
	$(function() {
		$(".slqx1").bind("click", function() {
			$("#slqx2").val("暂时保密").removeAttr("readonly");
		});
		$(".slqx2").bind("click", function() {
			$("#slqx2").val("no").attr("readonly", "readonly");

		});
	});

	$(function() {
		$("#myFomr").validate({
			rules : {
				mobile : { //验证手机号
					mobile : true,
					required : true
				},
				idNumber : {
					idNumber : true
				}
			}, /*错误提示位置*/
			errorPlacement : function(error, element) {
				// alert(element.attr("name"));
				error.appendTo("." + element.attr("name"));
			}
		});
	});
</script>
</html>