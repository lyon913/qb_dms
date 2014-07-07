<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="userForm" action="<c:url value='/admin/user/new'/>"
	method="post" modelAttribute="user">
	<div class="panel">
		<div class="panel-title">新增用户</div>
		<div>
		<font color="red"><strong><form:errors path="*" /></strong></font>
		</div>
		<table>
		<tr>
			<td>登录名：</td>
			<td><form:input path="loginName" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><form:input path="name" /></td>
		</tr>

		<tr>
			<td>科室：</td>
			<td><form:select path="department" items="${departList}"
					itemLabel="name" itemValue="id" /></td>
		</tr>

		<tr>
			<td>权限</td>
			<td><form:checkboxes path="roles" items="${roleList }"
					itemLabel="displayName" itemValue="id" cssClass="checkbox"/></td>
		</tr>

		<tr>
			<td colspan="2" align="center">
				<button type="submit" class="btn-normal">
					<span class="dijitIconSave"></span>保存
				</button>
				<button type="reset" class="btn-normal">
					<span class="dijitIconDelete"></span>重置
				</button>
			</td>
		</tr>
	</table>
	</div>
	
</form:form>