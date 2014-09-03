<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>


<div class="panel">
	<input id="suggestion_id" name="id" type="hidden"
		value="${suggestion.id }">
	<div class="title">
		<span>信件审核及回复</span>

	</div>
	<table class="formTable">
		<tr>
			<th colspan="2">
				&nbsp;&nbsp;&nbsp;标题：${suggestion.suggestionTitle}</th>
		</tr>
		<tr>
			<td width="80%" colspan="2">
				<div
					style="line-height: 24px; padding-right: 1em; padding-left: 1em">
					${suggestion.suggestionContent }</div> <br />
				<div style="float: right; font-style: italic">
					最后一次更新时间：${suggestion.suggestionDate}</div>
			</td>
		</tr>
	</table>
	<div class="title">
		<span>回复内容</span>
	</div>
	<table class="formTable">
		<c:forEach items="${replyList }" var="r">
			<tr>
				<th width="150px"><br>&nbsp;&nbsp;&nbsp;回复者：${r.author }<br></th>
				<td>
					<div
						style="line-height: 24px; padding-right: 1em; padding-left: 1em">
						${r.replyContent }</div>
						<c:url var="delUrl" value="/president/manage/reply/${r.id }/delete"></c:url>
					<div style="float: right; font-style: italic">
						&nbsp;&nbsp;&nbsp;回复时间：${r.replyDate }&nbsp;&nbsp;<a href="${delUrl }">删除回复</a></div>
						
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form:form modelAttribute="assForm" method="post">
		<table class="formTable" align="center">
			<tr>
				<th style="width: 150px">审核：</th>
				<td><form:radiobutton path="checked" value="true" label="审核通过" />
					<form:radiobutton path="checked" value="false" label="待审核" /></td>
			</tr>
			<tr>

				<th>回复内容：</th>
				<td><form:textarea path="reply" cssStyle="width:90%" rows="10" /></td>


			</tr>
			<tr>
				<td align="center" colspan="2">
					<button class="btn-normal">
						<span class="dijitIconSave"></span> <span>保存</span>
					</button> <a class="btn-normal" href="###"
					onclick="javascript:window.close();history.back();return false;">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span> <span>返回</span>
				</a>
				</td>
			</tr>
		</table>
	</form:form>
</div>