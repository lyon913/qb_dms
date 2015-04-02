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

function readNotice(id){
	window.location.href = "<c:url value='/notice/emergencyNotice/'/>" + id;
}
</script>
<div class="panel">
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>标题</th>
				<th>日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${emergencyNotices.content }" var="s">
				<tr>
					<td width="70%">${s.title }</td>
					<td width="10%">${s.noticeDate }</td>
					<td width="10%">
						<a href="###" onclick="readNotice(${s.id})">阅读</a>
					</td>
				</tr>

			</c:forEach>
			
		</tbody>

	</table>
	<dms:pagination page="${result}"
						formId="searchForm" />
</div>