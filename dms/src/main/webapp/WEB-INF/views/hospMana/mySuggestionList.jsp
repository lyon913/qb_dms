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
		<span>交流区——我的帖子</span>
		<div style="float: right;margin: 0;padding: 0">
		
			<c:url var="publicUrl" value="/hospMana/list/public"/>
			<a href="${publicUrl }" >返回交流区</a>
			<c:url var="newUrl" value="/hospMana/new"/>
			<a href="${newUrl }">发帖</a>
		</div>
	</div>
	<form id="searchForm" method="post">
		<label for="key">标题：</label>
		<input id="key" name="key" value="${key }" class="input-text"> 
		<input name="page" value="0" type="hidden">
		<input name="size" value="20" type="hidden">
		<button type="submit" class="btn-normal">查询</button>
	</form>
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>标题</th>
				<th>发帖人</th>
				<th>日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${result.content }" var="s">
				<tr>
					<td width="70%">${s.suggestionTitle }</td>
					<td width="10%">${s.author }</td>
					<td width="10%">${s.suggestionDate }</td>
					<td width="10%">
					
						<a href="###" onclick="readSuggestion(${s.id})" >查看</a>
						<c:url var="editUrl" value='/hospMana/${s.id }/edit'/>
						<a href="${editUrl }">编辑</a>
						
						<a href="###" onclick="deleteSuggestion('${s.id}',1)">删除</a>
					</td>
				</tr>

			</c:forEach>
			
		</tbody>

	</table>
	<dms:pagination page="${result}"
						formId="searchForm" />
</div>