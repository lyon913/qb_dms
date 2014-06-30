<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	dojo.require("dijit.TitlePane");
	
	function goEdit(){
		window.location.href = "<c:url value='/admin/user/edit'/>";
	}
</script>
<div data-dojo-type="dijit.TitlePane" title="用户管理" style="width:100%;height:100%;overflow:auto;">
	<div>
		<button onclick="goEdit()" data-dojo-type="dijit.form.Button">添加用户</button>
	</div>
	<table border="0" width="100%">
		<tr>
			<td>ID</td>
			<td>登录名</td>
			<td>姓名</td>
			<td>科室</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${userList}" var="u">
			<tr>
				<td width="10%">${u.id }</td>
				<td width="20%">${u.loginName }</td>
				<td width="30%">${u.name }</td>
				<%TUser u = (TUser)pageContext.getAttribute("u");  %>
				<td width="20%">${u.department.name }</td>
				<td width="20%">
					<a href="<c:url value='/admin/user/edit/'/>${u.id}">编辑</a>
					<a href="<c:url value='/admin/user/del/'/>${u.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>