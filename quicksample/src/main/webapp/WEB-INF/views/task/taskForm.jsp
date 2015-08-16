<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加任务</title>
</head>
<body>
	<form id="inputForm" role="form" action="${ctx}/task/create"
			method="post">
			<div class="form-group">
				<label for="task_title" class="col-sm-2 control-label">任务名称：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control required" id="task_title" minlength="3"
						name="title" placeholder="请输入任务名称" value="${task.title}">
				</div>
			</div>
			<div class="form-group">
				<label for="task_description" class="col-sm-2 control-label ">任务描述：</label>
				<div class="col-sm-10">
				<textarea rows="4" cols="20" class="form-control required" id="task_description" name="description"
						placeholder="任务描述" >${task.description}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">提交</button>
					&nbsp;
					<button type="button" class="btn btn-default"
						onclick="history.back();">返回</button>
				</div>
			</div>
		</form>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("#task_title").focus();
				$("#inputForm").validate();
			});
		</script>
</body>
</html>