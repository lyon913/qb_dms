<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>


<div class="panel">
	<input id="suggestion_id" name="id" type="hidden"
		value="${suggestion.id }">
	<div class="title">
		<span>意见审核</span> <span style="float: right;"> <a href="###">意见簿</a>
			<c:url var="newUrl" value="/suggestion/new" /> <a href="${newUrl }">提出意见</a>
		</span>
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
			<c:forEach items="${replyList }" var="r">
				<tr>
					<td><br />&nbsp;&nbsp;&nbsp;回复者：${r.author }</td>
					<td width="80%">
						<div
							style="line-height: 24px; padding-right: 1em; padding-left: 1em">
							${r.replyContent }</div>
						<div style="float: right; font-style: italic">
							&nbsp;&nbsp;&nbsp;回复时间：${r.replyDate }&nbsp;&nbsp;<a
								href="<c:url value='/suggestion/editSuggestionReply/${r.id }'/>">编辑回复内容</a>
						</div>
					</td>
				</tr>
			</c:forEach>
			<form:form modelAttribute="assForm" method="post">
				<tr>
					<td valign="top">&nbsp;&nbsp;审核：</td>
					<td>
					<form:select path="checked">
						<form:option value="true">审核通过</form:option>
						<form:option value="false">待审核 </form:option>
					</form:select></td>
				</tr>
				<tr>

					<td valign="top">&nbsp;&nbsp;回复内容：</td>
					<td><textarea class="textarea" id="suggestionReply_content"
							name="suggestionReplyContent" cols=50 rows=8></textarea></td>


				</tr>
				<tr>
				<td>
				<button class="btn-normal">
					<span class="dijitIconSave"></span>
					<span>保存</span>
				</button>
				</td>
				</tr>
			</form:form>
		<tr>
			<td colspan="2" align="right">
				
				<button type="button" data-dojo-type="dijit.form.Button"
					onclick="goToSuggestionList();">返回列表</button>

			</td>
		</tr>
	</table>
</div>