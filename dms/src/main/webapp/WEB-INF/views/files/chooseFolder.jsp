<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<div>
		<select id="folderSel"
			style="width: 100%; height: 30px; font-size: 16px;">
			<c:forEach items="${folders }" var="folder">
				<option value="${folder.id }">${folder.name }</option>
			</c:forEach>
		</select>
	</div>
	<br> <br>
	<div style="width: 100%; text-align: center;">
		<button data-dojo-type="dijit.form.Button" type="button"
			data-dojo-props="onClick:function(){onFolderChose(dojo.byId('folderSel').value);},iconClass:'dijitCommonIcon dijitIconSave'">确定</button>
	</div>
	<br> <br>
</div>