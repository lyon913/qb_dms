<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="dms"%>
<div class="panel">
	<div class="title">
		投票主题：${result.title}&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div style="color:gray;margin:10px 0 10px 0;text-align: center;">
		发起人：${v.authorName}&nbsp;&nbsp;
		发起时间：<fmt:formatDate value="${v.createTime }" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
		截止时间：<fmt:formatDate value="${v.endDate }" pattern="yyyy-MM-dd"/><br>
		${v.isOpen?"记名投票":"匿名投票" }&nbsp;&nbsp;
		${v.isMulti?"多选择":"单选择" }&nbsp;&nbsp;
		<c:if test="${v.isMulti}">
			（最多可选${v.maxVotes}项）
		</c:if>
	</div>
	<table align="center" border="0">
	<c:forEach items="${result.counts }" var="c" varStatus="s">
		<tr>
			<td>${c.optionTitle}</td>
			<td style="width:200px;">
					<dms:voteBar index="${s.index }" percent="${c.percent}"/>
			</td>
			<td>
				<span>${c.count }</span>
				<span style="color:gray;">
					(<fmt:formatNumber value="${c.percent}" maxFractionDigits="0"/>%)
				</span>
			</td>
		</tr>
	</c:forEach>
	</table>

</div>

