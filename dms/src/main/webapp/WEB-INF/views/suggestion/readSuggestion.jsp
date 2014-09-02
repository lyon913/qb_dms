<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel">
	<div class="title">
		<span>标题：${suggestion.suggestionTitle}</span>
	</div>
	<table class="formTable">
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
		<tr>
			<td colspan="2" align="right">

				<button type="button" onclick="javascript:window.close();window.history.back();return false;" class="btn-normal">返回列表</button>

			</td>
		</tr>
	</table>
</div>