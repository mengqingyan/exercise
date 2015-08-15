<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<div class="container">
	
		<form role="form" id="loginForm" action="${ctx }/login" method="post">
			
			<%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if(error != null){
			%>
				<div class="alert alert-error input-medium controls">
					<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
				</div>
			<%
			}
			%>
			
			<div class="form-group">
				<label for="username" class="col-md-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control required" id="username"
						name="username" value="${username}" placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-md-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control required" id="password"
						name="password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-10">
					<div class="checkbox">
						<label> <input type="checkbox" name="rememberMe"> 请记住我
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" class="login btn btn-default" data-loading-text="Loading...">登录</button>
					<a class="btn" href="${ctx}/register">注册</a>
					<span class="help-block">(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
				</div>
			</div>
		</form>
	</div>
	
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
			
			$(".login").click(function(){
		         $(this).button('loading').delay(1000).queue(function() {
		            $(this).button('reset');
		         });        
		      });
		});
	</script>
	
</body>
</html>