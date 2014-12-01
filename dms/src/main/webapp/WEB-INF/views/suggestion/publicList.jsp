<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${ctx }resources/js/hospMana.js"></script>
<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
});

</script>
<div class="panel">
	<div class="title">
		<span>意见簿</span>
		<span style="float: right;">
			<c:url var="myUrl" value="/suggestion/list/my"/>
			<a href="${myUrl }">我的意见</a>
			<c:url var="newUrl" value="/suggestion/new"/>
			<a href="${newUrl }">提出意见</a>
		</span>
	</div>
	<form id="searchForm" method="post">
		<label for="key">标题：</label>
		<input id="key" name="key" value="${key }" class="input-text"> 
		<input name="page" value="0" type="hidden">
		<input name="size" value="10" type="hidden">
		<button type="submit" class="btn-normal">查询</button>
	</form>
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>标题</th>
				<th>日期</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${result.content }" var="s">
				<tr>
					<td width="70%">${s.suggestionTitle }</td>
					<td width="10%">${s.suggestionDate }</td>
					<td width="10%">${s.state.displayName }</td>
					<td width="10%">
						<a href="###" onclick="readSuggestion(${s.id})">查看</a>
					</td>
				</tr>

			</c:forEach>
			
		</tbody>

	</table>
	<dms:pagination page="${result}"
						formId="searchForm" />
</div>