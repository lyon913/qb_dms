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
	var url = "<%=request.getContextPath()%>/president/" + id;
	window.showModalDialog(url,null,"dialogWidth=" + scrWidth1 + ";dialogHeight="+ scrHeight1+";center=yes;");
}
function deleteSuggestion(id){
	var baseUrl = "<c:url value='/'/>";
	var url = baseUrl + "president/" + id + "/del/2";
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}
</script>
<div class="panel">
	<div class="title">
		<span>全部信件</span>
		<span style="float: right;">
			<c:url var="privateUrl" value="/president/manage/list/private"/>
			<a href="${privateUrl }">未公开信件</a>
		</span>
	</div>
	<form id="searchForm" method="post">
		<label for="key">标题：</label>
		<input id="key" name="key" value="${key }" class="input-text"> 
		<input name="page" value="0" type="hidden">
		<input name="size" value="10" type="hidden">
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
					<td width="10%">${s.state.pName }</td>
					<td width="10%">
						<a href="###" onclick="readSuggestion(${s.id})">查看</a>
						<a href="###" onclick="deleteSuggestion('${s.id}')">删除</a>
						<c:url var="assessUrl" value="/president/manage/assess/${s.id }"/>
						<a href="${assessUrl }" onclick="">回复</a>
						
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