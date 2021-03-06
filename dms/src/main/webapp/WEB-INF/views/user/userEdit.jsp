<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
<!--
function resetPwd(userId){
	if(confirm('是否将该用户密码重置为1234')){
		var url = '${ctx}admin/user/'+userId+'/resetPwd';
		var xhrArgs = {
				url : url,
				handleAs : "json",
				load : function(data) {
					if(data == 'success')
						showMessage("", "密码已重置");
					else
						showMessage("错误", "系统处理错误，请重试。");
				},
				error : function(error) {
					showMessage("错误", error);
				}
			};
		dojo.xhrPost(xhrArgs);
	}
}
//-->
</script>
<c:url var="formUrl" value="/admin/user/new" />
<form:form id="userForm" action="${formUrl}" method="post"
	modelAttribute="user">
	<div class="panel">
		<div class="title">新增用户</div>
		<table class="formTable">
			<tr>
				<th>登录名：</th>
				<td><dms:inputText name="loginName" /></td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td><dms:inputText name="name" /></td>
			</tr>

			<tr>
				<th>科室：</th>
				<td><form:select path="department" cssClass="select">
						<form:options items="${departList }" itemLabel="name"
							itemValue="id" />
					</form:select></td>
			</tr>
			<c:if test="${user.id == null }">
				<tr>
					<td colspan="2">
						<div>新用户默认密码为：1234</div>
					</td>
				</tr>
			</c:if>
			<c:if test="${user.id != null }">
				<tr>
					<td colspan="2">
						<button type="button" class="btn-normal" onclick="resetPwd('${user.id}');">
							重置密码为：1234
						</button>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>管理权限：</td>
				<td>
					<form:checkboxes path="roles" items="${roleList }"
						itemLabel="displayName" itemValue="name" cssClass="checkbox" />
				</td>
			</tr>
			
			<tr>
				<td>阅读范围：</td>
				<td>
					<form:checkboxes path="udepartments" items="${departList }"
						itemLabel="name" itemValue="id" cssClass="checkbox" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<button type="submit" class="btn-normal">
						<span class="dijitIconSave"></span>
						保存
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