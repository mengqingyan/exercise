<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<div id="header">
	<div id="title">
		<h1>
			<a href="${ctx }">QuickSample</a> <small>--TodoList应用</small>
			<shiro:user>
				<div class="btn-group pull-right">
					<button type="button" class="btn dropdown-toggle"
						id="dropdownMenu1" data-toggle="dropdown">
						<i class="icon-user"></i>
						<shiro:principal property="name" />
						<span class="caret"></span>
					</button>

					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">

						<shiro:hasRole name="admin">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${ctx }/admin/user">Admin Users</a></li>
							<li role="presentation" class="divider"></li>
						</shiro:hasRole>

						<li><a href="${ctx }/api">Apis</a></li>
						<li><a href="${ctx }/profile">Edit Profile</a></li>
						<li><a href="${ctx }/logout"> logout </a></li>
					</ul>
				</div>
			</shiro:user>
		</h1>
	</div>
</div>