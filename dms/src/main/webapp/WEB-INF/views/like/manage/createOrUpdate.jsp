<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<div class="panel">
	<div class="title">编辑选项</div>
	<form:form modelAttribute="option" method="post" enctype="multipart/form-data">

		<table class="formTable">
			<tr>
				<th width="150px">选项名称：</th>
				<td><dms:inputText name="title" cssStyle="width:99%;"/></td>
			</tr>
			<tr>
				<th width="150px">图标：</th>
				<td>
					<input type="file" name="pic">
					<form:errors path="picture"></form:errors>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button class="btn-normal" type="submit">
						<span class="dijitIconSave"></span>
						<span>保存</span>
					</button>
					<a class="btn-normal" href="###" onclick="history.back();return false;">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span>
						<span>返回</span>
					</a>
				</td>
			</tr>
		</table>
	</form:form>
</div>
