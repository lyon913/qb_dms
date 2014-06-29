<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>" />


<script type="text/javascript">
	dojo.require("dijit.TitlePane");

	function goToSuggestionList() {
		window.location.href = "<c:url value='/suggestion/suggestionMana'/>";
	}

	function addReply() {
		window.location.href = "<c:url value='/suggestion/createSuggestionReply/${suggestion.id }'/>";
	}
</script>
<div data-dojo-type="dijit.TitlePane" Title="意见簿"
	style="width: 100%; height: 100%; overflow: scroll">
	<input id="suggestion_id" name="id" type="hidden"
		value="${suggestion.id }">
	<table class="warp_table">
		<thead>
			<tr>
				<th colspan="2">
					&nbsp;&nbsp;&nbsp;标题：${suggestion.suggestionTitle}</th>
			</tr>
		</thead>
		<tr>
			<!-- <td width="20%" valign="top"><br />
				&nbsp;&nbsp;&nbsp;作者：${suggestion.author}</td>
			 -->
			<td width="80%" colspan="2">
				<div
					style="line-height: 24px; padding-right: 1em; padding-left: 1em">
					${suggestion.suggestionContent }</div> <br />
			<div style="float: right; font-style: italic">
					最后一次更新时间：${suggestion.suggestionDate}&nbsp;&nbsp;<a
						href="<c:url value='/suggestion/edit/${suggestion.id }'/>">编辑意见</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<c:url value='/suggestion/createSuggestionReply/${suggestion.id }'/>">添加回复</a>
				</div>
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

				<button type="button" data-dojo-type="dijit.form.Button"
					onclick="goToSuggestionList();">返回列表</button>

			</td>
		</tr>
	</table>
</div>