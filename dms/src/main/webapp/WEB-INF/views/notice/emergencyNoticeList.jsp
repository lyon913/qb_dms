<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${ctx }resources/js/suggestion.js"></script>
<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
});

</script>
<div class="panel">
	<div class="title">
		<span>紧急通知</span>
		
	</div>
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
			<c:forEach items="${emergencyNotices.content }" var="s">
				<tr>
					<td width="70%">${s.title }</td>
					<td width="10%">${s.noticeDate }</td>
					<td width="10%">${s.emergencyState }</td>
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