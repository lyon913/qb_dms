<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
function readSuggestion(id){
	var scrWidth = screen.width * 0.9;
	var scrHeight = screen.height * 0.9;
	
	var scrWidth1 = scrWidth+"px";
	var scrHeight1 = scrHeight+"px";
	var url = "<%=request.getContextPath()%>/suggestion/" + id;
	window.showModalDialog(url,null,"dialogWidth=" + scrWidth1 + ";dialogHeight="+ scrHeight1+";center=yes;");
}

function deleteSuggestion(id){
	var baseUrl = "<c:url value='/'/>";
	var url = baseUrl + "suggestion/" + id + "/del/1";
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}
</script>
<div class="panel"  style="height: 100%;overflow: scroll;">
	<div class="title">
		<span>我的意见</span>
		<div style="float: right;margin: 0;padding: 0">
		
			<c:url var="publicUrl" value="/suggestion/list/public"/>
			<a href="${publicUrl }" >返回意见簿</a>
			<c:url var="newUrl" value="/suggestion/new"/>
			<a href="${newUrl }">提出意见</a>
		</div>
	</div>
	<form id="searchForm" method="post">
		<label for="key">标题：</label>
		<input id="key" name="key" value="${key }" class="input-text"> 
		<input name="page" value="0" type="hidden">
		<input name="size" value="20" type="hidden">
		<button type="submit" class="btn-normal">查询</button>
	</form>
	<table width="100%" class="table">
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
					
						<a href="###" onclick="readSuggestion(${s.id})" >查看</a>
						<c:url var="editUrl" value='/suggestion/${s.id }/edit'/>
						<a href="${editUrl }">编辑</a>
						
						<a href="###" onclick="deleteSuggestion('${s.id}')">删除</a>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td colspan="4"><dms:pagination page="${result}"
						formId="searchForm" /></td>
			</tr>
		</tbody>

	</table>
</div>