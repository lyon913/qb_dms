<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/form/resources/CheckedMultiSelect.css'/>">
<script type="text/javascript">
	dojo.require("dijit.TitlePane");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.CheckBox");

	dojo.ready(function() {
		var form = dijit.byId("departForm");
		dojo.connect(form, "onSubmit", function(event) {
			if (!form.validate()) {
				dojo.stopEvent(event);
			}
		});
	});
</script>
<div data-dojo-type="dijit.TitlePane" title="编辑科室信息">
	<form id="departForm" action="<c:url value='/admin/department/save'/>"
		method="post" data-dojo-type="dijit.form.Form">
		<input type="hidden" name="id" value="${d.id}">
		<table>
			<tr>
				<td>名称：</td>
				<td><input name="name" value="${d.name}"
					data-dojo-type="dijit.form.ValidationTextBox"
					data-dojo-props="required:true"></td>
			</tr>
			<tr>
				<td>负责人：</td>
				<td><input name="head" value="${d.head}"
					data-dojo-type="dijit.form.TextBox"></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input name="telephone" value="${d.telephone}"
					data-dojo-type="dijit.form.TextBox"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" label="保存"
					data-dojo-type="dijit.form.Button"> <input type="reset"
					label="重置" data-dojo-type="dijit.form.Button"></td>
			</tr>
		</table>
	</form>
</div>