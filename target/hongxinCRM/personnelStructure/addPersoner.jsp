<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加角色</title>
</head>
<body>
	<form class="am-form am-form-horizontal" action="/role/inser-role"
		id="addUser" method="post">
		<p>${requestScope.msg}</p>

		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="姓名 " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label ttt">英文名</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="英文名 " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">身份证</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="身份证" name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">性别</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="性别 " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">生日</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="生日 " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">民族</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="民族" name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">籍贯</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="籍贯 " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">政治面貌</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="政治面貌  "
					name="roleName" value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">手机号码</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="手机号码  "
					name="roleName" value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">现住地址</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="现住地址  "
					name="roleName" value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">所在岗位</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="所在岗位  "
					name="roleName" value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">在职状态</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="在职状态  "
					name="roleName" value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="user-name" class="am-u-sm-3 am-form-label">级别</label>
			<div class="am-u-sm-9">
				<input type="text" id="roleName" placeholder="级别  " name="roleName"
					value="">
			</div>
		</div>
		<div class="am-form-group">
			<label for="password" class="am-u-sm-3 am-form-label">备注</label>
			<div class="am-u-sm-9">
				<input type="text" name="description" placeholder="备注  " value="">
			</div>
		</div>
		<div class="am-form-group">
			<div class="am-u-sm-9 am-u-sm-push-3">
				<button type="submit" class="am-btn am-btn-primary">保存</button>
			</div>
		</div>
	</form>
</body>
</html>