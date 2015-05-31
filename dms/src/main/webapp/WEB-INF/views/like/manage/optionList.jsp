<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>

<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
});

</script>
<div class="panel">
	<div class="title">
		<span>点赞选项管理</span>
		<span style="float: right;">
			<c:url var="myUrl" value="/like/option/new"/>
			<a href="${myUrl }">添加点赞选项</a>
			
		</span>
	</div>
	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>点赞选项标题</th>
				<th>图片</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${opt }" var="s">
				<tr>
					<td width="70%">${s.title }</td>
					<td width="10%"><img src="${ctx}upload/images/like/${s.picture }"  width="41" height="41"/></td>
					<td width="10%">
						<a href="${ctx}like/option/${s.id}/edit">修改</a>
						<a href="${ctx}like/option/${s.id}/delete">删除</a>
					</td>
				</tr>

			</c:forEach>
			
		</tbody>

	</table>
	
</div>