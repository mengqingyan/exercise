<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务列表</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/r/dt/dt-1.10.8/datatables.min.css" />
</head>
<body>
	<input type="hidden" value="${ctx}" id="ctx">
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" id="form-search" onsubmit="return false">
				<label>名称：</label> <input type="text" name="search_like_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<!-- <button type="submit" class="btn" id="search_btn">Search</button> -->
				<button class="btn" id="search_btn">Search</button>
		    </form>
		    
	    </div>
	    <div class="">
	    	<button class="btn pull-right" id="weather_btn">Weather</button>
	    </div>
	</div>

	<table id="taskList" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>任务</th>
				<th>描述</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div><a class="btn" href="${ctx}/task/create">新建任务</a></div>
	<div id="container" style="min-width:700px;height:400px"></div>

	<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>


	<script type="text/javascript" src="${ctx}/resources/js/task/list.js?time=<%=new Date().getTime() %>"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/r/dt/dt-1.10.8/datatables.min.js"></script>

</body>
</html>