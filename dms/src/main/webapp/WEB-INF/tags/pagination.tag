<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="formId" required="true"%>
<%@ attribute name="page" required="true" rtexprvalue="true"
	type="org.springframework.data.domain.Page"%>

<script type="text/javascript">
function _onPageSelected(page,size,formId){
	var form = document.getElementById(formId);
	form.elements["page"].value=page;
	form.elements["size"].value=size;
	form.submit();
}
</script>
<div class="pagination" align="center">
	<br>
	<c:if test="${page.totalElements <=0 || page.number <= 0}">
			<a class="current prev">第一页</a>
			<a class="current prev">上一页</a>
	</c:if>
	
	<c:if test="${page.totalElements >0 && page.number > 0}">
			<a href="###" onclick="_onPageSelected(0,${page.size},'${formId}')">第一页</a>
			<a href="###" onclick="_onPageSelected(${page.number - 1},${page.size},'${formId}')">上一页</a>
	</c:if>
		<span class="info">${page.size*page.number+1}-${page.size*page.number+page.numberOfElements}[共${page.totalElements }条记录]&nbsp;&nbsp;[第${page.number + 1}页/共${page.totalPages}页]</span>
	<c:if
		test="${page.totalElements == 0 || page.number + 1 >= page.totalPages}">
		<a class="current next">下一页</a>
		<a class="current next">最末页</a>
	</c:if>

	<c:if test="${page.number + 1 < page.totalPages}">
		<a href="###"
			onclick="_onPageSelected(${page.number + 1},${page.size},'${formId}')">下一页</a>
		<a href="###"
			onclick="_onPageSelected(${page.totalPages - 1},${page.size},'${formId}')">最末页</a>
	</c:if>

</div>