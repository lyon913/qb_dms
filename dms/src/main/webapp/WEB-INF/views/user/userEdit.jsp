<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>

<c:url var="formUrl" value="/admin/user/new" />
<form:form id="userForm" action="${formUrl}" method="post"
	modelAttribute="user">
	<div class="panel">
		<div class="title">新增用户</div>
		<table>
			<tr>
				<td>登录名：</td>
				<td><dms:inputText name="loginName" /></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><dms:inputText name="name" /></td>
			</tr>

			<tr>
				<td>科室：</td>
				<td><form:select path="department" cssClass="select">
						<form:options items="${departList }" itemLabel="name"
							itemValue="id" />
					</form:select></td>
			</tr>
			<c:if test="${user.id == null }">
				<tr>
					<td>密码：</td>
					<td>
						<div class="input-text">新用户默认密码为：1234</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>管理权限：</td>
				<td><form:checkboxes path="roles" items="${roleList }"
						itemLabel="displayName" itemValue="id" cssClass="checkbox" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn-normal">
						<span class="dijitIconSave"></span>保存
					</button>
					
					<c:url var="listUrl" value="/admin/user/list"/>
					<a class="btn-normal" href="${listUrl }">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span>返回
					</a>
				</td>
			</tr>
		</table>
	</div>

</form:form>