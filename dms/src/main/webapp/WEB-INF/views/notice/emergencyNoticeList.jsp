<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%
				TUser ud = (TUser) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
			%>
<script type="text/javascript" src="${ctx }resources/js/suggestion.js"></script>
<script type="text/javascript">
var strCookie = document.cookie;
var arr = strCookie.split(";");
var s = "";
for(var i=0;i<arr.length;i++){
	a = arr[i].split("=");
	s = s+a[0]+",";
}
dojo.ready(function(){
styleTable("tb");

});

function readNotice(id){
	//获取当前时间 
	var date=new Date(); 
	var expireDays=3; 
	//将date设置为10天以后的时间 
	//date.setTime(date.getTime()+expireDays*24*3600*1000); 
	date.setTime(date.getTime()+expireDays*24*3600*1000); 
	document.cookie = "u"+<%=ud.getId()%>+"n"+id+"="+id+";expire="+date.toGMTString(); 
	strCookie = document.cookie;
	window.location.href = "<c:url value='/notice/emergencyNotice/'/>" + id;
}

function getReaded(id){
	var str = "u"+<%=ud.getId()%>+"n"+id+",";
	if(s.indexOf(str)>=0){
		
	}else{
		document.write("<img src='resources/images/new.jpg'/>");
	}
}
</script>
<div class="panel">
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>标题</th>
				<th>日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${result.content }" var="s">
				<tr>
					<td width="90%"><a href="###" onclick="readNotice(${s.id})">${s.title }</a>
					<script type="text/javascript">
					var i = ${s.id};
					getReaded(i);
					</script>
					
					</td>
					<td width="10%">${s.noticeDate }</td>
				</tr>

			</c:forEach>
			
		</tbody>

	</table>
	<dms:pagination page="${result}"
						formId="searchForm" />
</div>