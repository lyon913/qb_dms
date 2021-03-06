<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
});

</script>
<div class="panel"  style="height: 100%;overflow: scroll;">
	<div class="title">用户管理</div>
	<div>
		<c:url var="addUserUrl" value="/admin/user/new" />
		<a href="${addUserUrl }" class="btn-normal"> <span
			class="dijitIconNewTask"></span>新增用户
		</a>
	</div>
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>ID</th>
				<th>登录名</th>
				<th>姓名</th>
				<th>科室</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${userList}" var="u">
			<tr>
				<td width="10%">${u.id }</td>
				<td width="20%">${u.loginName }</td>
				<td width="30%">${u.name }</td>
				<%
					TUser u = (TUser) pageContext.getAttribute("u");
				%>
				<td width="20%">${u.department.name }</td>
				<td width="20%"><c:url var="editUrl"
						value="/admin/user/${u.id }/edit" /> <a href="${editUrl }"
					class="btn-normal"> <span class="dijitIconEdit"></span>编辑
				</a> <c:url var="delUrl" value="/admin/user/${u.id }/delete" /> <a
					href="${delUrl }" class="btn-normal"> <span
						class="dijitIconDelete"></span>删除
				</a>
				
				<c:url var="setUrl" value="/admin/user/${u.id }/setReadDepartments" />
				<a href="${setUrl }" class="btn-normal">
				设置用户阅读范围
				</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>