<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<div style="width: 100%; height: 100%; overflow: auto" class="panel">
	<div class="title">提出意见或建议</div>
	<form:form modelAttribute="s" method="post">

		<table class="formTable">
			<tr>
				<th>标题：</th>
				<td><dms:inputText name="suggestionTitle" /></td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<dms:textarea name="suggestionContent" cssStyle="width:100%;height:100px;"/>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button class="btn-normal">
						<span class="dijitIconSave"></span>
						<span>保存</span>
					</button>
					<c:url var="listUrl" value="/suggesion/list"/>
					<a class="btn-normal" href="###" onclick="javascript:window.close();history.back();return false;">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span>
						<span>返回</span>
					</a>
				</td>
			</tr>
		</table>
	</form:form>
</div>
