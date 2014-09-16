<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel"  style="height: 100%;overflow: scroll;">
	<div class="title">移动文件</div>
	<form action="">
		<c:forEach items="fileIdArray" var="fileId">
			<input type="hidden" name="fileId" value="${fileId }">
		</c:forEach>
		<select name="moveTo">
			<c:forEach items="folders" var="folder">
				<option value="${folder.id }">${folder.name }</option>
			</c:forEach>
		</select>
		<button>移动</button>
	</form>
</div>