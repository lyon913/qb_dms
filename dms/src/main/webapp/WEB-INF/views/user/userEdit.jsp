<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	dojo.require("dijit.TitlePane");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.Select");
	
	dojo.ready(function(){
		var form = dijit.byId("userForm");
		dojo.connect(form,"onSubmit",function(event){
			if(!form.validate()){
				dojo.stopEvent(event);
			}
		});
	});
</script>

<div data-dojo-type="dijit.TitlePane" title="编辑用户信息">
	<div><font color="red"><strong>${error}</strong></font></div>
	<form id="userForm" action="<c:url value='/admin/user/save'/>" 
			method="post"
			data-dojo-type="dijit.form.Form">
		<input type="hidden" name="id" value="${u.id}">
		<table>
			<tr>
				<td>登录名：</td>
				<td>
					<input name="loginName" value="${u.loginName}"
						data-dojo-type="dijit.form.ValidationTextBox"
						data-dojo-props="required:true,
										regExp:'(\\w|\\d|_){1,16}',
										invalidMessage:'用户名只能为英文字母、数字和下划线组成，长度不超过16个字符。'">
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input name="password" 
							type="password"
							value="${u.password}"
							data-dojo-type="dijit.form.ValidationTextBox"
							data-dojo-props="required:true,
										regExp:'(.){4,16}',
										invalidMessage:'长度必须为4-6个字符'">
				</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>
					<input name="name" 
							value="${u.name}"
							data-dojo-type="dijit.form.ValidationTextBox"
							data-dojo-props="required:true,
										regExp:'(.){1,20}',
										invalidMessage:'长度不能超过20'">
				</td>
			</tr>
			
			<tr>
				<td>科室：</td>
				<td>
					<select name="departmentId" value="${u.department.id }" 
							data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight:300">
						<c:forEach items="${departList}" var="d">
							<option value="${d.id }">${d.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type="submit" label="保存"
						data-dojo-type="dijit.form.Button">
					<input type="reset"
						label="重置" data-dojo-type="dijit.form.Button">
				</td>
			</tr>
		</table>
	</form>
</div>