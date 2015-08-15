<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<div class="container">
		<form id="inputForm" role="form" action="${ctx}/register"
			method="post">
			<div class="form-group">
				<label for="loginName" class="col-sm-2 control-label">登录名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control required" id="loginName" minlength="3"
						name="loginName" placeholder="请输入登录名">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label ">用户名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control required" id="name" name="name"
						placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="plainPassword" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control required" id="plainPassword"
						name="plainPassword" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control required"
						id="confirmPassword" equalTo="#plainPassword" placeholder="请确认密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">注册</button>
					&nbsp;
					<button type="button" class="btn btn-default"
						onclick="history.back();">返回</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#loginName").focus();
			$("#inputForm").validate({
				rules : {
					loginName : {
						remote : "${ctx}/register/checkLoginName"
					}
				},

				messages : {
					loginName : {
						remote : "用户登录名已存在"
					}
				}
			});
		});
	</script>

</body>
</html>