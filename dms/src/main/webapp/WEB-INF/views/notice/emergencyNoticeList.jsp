<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${ctx }resources/js/suggestion.js"></script>
<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
var strCookie = document.cookie;
alert(strCookie);
});

function readNotice(id){
	//获取当前时间 
	var date=new Date(); 
	var expireDays=1; 
	//将date设置为10天以后的时间 
	//date.setTime(date.getTime()+expireDays*24*3600*1000); 
	date.setTime(date.getTime()+expireDays*24*3600*1000); 
	document.cookie = "notice"+id+"="+id+";expire="+date.toGMTString(); 
	strCookie = document.cookie;
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
					<td width="70%">${s.title }<img src="resources/images/new.jpg"/></td>
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