<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="dms"%>
<script type="text/javascript">
	
</script>
<div class="panel">
	<div class="title">“${result.title}”--记名投票明细</div>
	<table class="formTable" align="center">
		<c:forEach items="${result.details }" var="entry">
			<tr>
				<th style="width: 1px;white-space: nowrap;padding: 10px;">
					${entry.key.title }
				</th>
				<td>
					&nbsp;
					<c:forEach items="${entry.value }" var="name">
						${name},
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

