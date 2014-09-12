<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<div class="panel">
	<div class="title">给院长写信</div>
	<form:form modelAttribute="s" method="post">

		<table class="formTable">
			<tr>
				<th  width="100px">标题：</th>
				<td><dms:inputText name="suggestionTitle" cssStyle="width:99%;"/></td>
			</tr>
			<tr>
				<th  width="100px">内容：</th>
				<td>
					<dms:textarea name="suggestionContent" cssStyle="width:99%;height:100px;"/>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button class="btn-normal">
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
