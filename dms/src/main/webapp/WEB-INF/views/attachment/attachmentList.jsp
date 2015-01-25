<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${ctx }resources/js/attachment.js"></script>
<script type="text/javascript">
dojo.ready(function(){
styleTable("tb");
});

</script>
<div class="panel"  style="height: 100%;overflow: scroll;">
	<div class="title">
		
			<form id="upload_form" action="<c:url value="/attachment/${foreignId}/${attachmentType}/addAttachment"/>"
		enctype="multipart/form-data" method="POST">
	<table width="100%" >
	<tr>
				<td >
				<span>公共附件</span>
					<input type="file" name="uploadedfile" >
					<input type="hidden" name="attachmentType" value="${attachmentType }">
					<input class="btn-normal" type="submit" value="上传">
				</td>
				<td >
					
				</td>
				</tr>
	</table>
	
	</form>
	</div>

	<table width="100%" class="table" id="tb">
		<thead>
			<tr>
				<th>附件名称</th>
				<th>上传人</th>
				<th>上传时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${attachmentList }" var="s">
				<tr>
					<td width="70%">${s.name }</td>
					<td width="10%">${s.creatorName }</td>
					<td width="10%">${s.createTime }</td>
					<td width="10%">
						<a href="${ctx}attachment/download/${s.id }"  >下载</a>
						<a href="###" onclick="deleteAttachment('${s.id}')">删除</a>
					</td>
				</tr>

			</c:forEach>
			
		
		</tbody>

	</table>
	


</div>