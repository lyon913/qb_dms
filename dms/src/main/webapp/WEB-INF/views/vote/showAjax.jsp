<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="dms"%>
<div>
	<div style="color: gray; margin: 10px 0 10px 0; text-align: center;">
		<span style="color: black; font-weight: bold; font-size: 16px;">${result.title}</span><br>
		发起人：${v.authorName}&nbsp;&nbsp; 发起时间：
		<fmt:formatDate value="${v.createTime }" pattern="yyyy-MM-dd" />
		&nbsp;&nbsp; 截止时间：
		<fmt:formatDate value="${v.endDate }" pattern="yyyy-MM-dd" />
		<br> [${v.isOpen?"记名投票":"匿名投票" }]&nbsp;&nbsp;
		[${v.isMulti?"多选择":"单选择"}]
		<c:if test="${v.isMulti}">
			（最多可选${v.maxVotes}项）
		</c:if>
		<br> 总投票数：${result.total}&nbsp;&nbsp;&nbsp;
		<c:if test="${v.isOpen }">
			<a href="###" onclick="showDetails()" style="font-size: 12px">查看明细</a>
		</c:if>
	</div>
	<table align="center" border="0">
		<c:forEach items="${result.counts }" var="c" varStatus="s">
			<tr>
				<td><input id="optSel_${c.optionId}" name="optSel"
					type='${v.isMulti?"checkbox":"radio" }' value="${c.optionId }">
					<label for="optSel_${c.optionId}">${c.optionTitle }</label></td>
				<td style="width: 200px; padding: 1px 8px 1px 2px;"><dms:voteBar
						index="${s.index }" percent="${c.percent}" /></td>
				<td><span>${c.count }</span> <span style="color: gray;">(<fmt:formatNumber
							value="${c.percent}" maxFractionDigits="0" />%)
				</span></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center"><c:if test="${!isExpired }">
					<button class="btn-normal" type="button" onclick="submitVote()">投票</button>
				</c:if> <c:if test="${isExpired }">
					<span class="error">[本次投票已经截止]</span>
				</c:if> <br> <span class="error">${err }</span></td>
		</tr>
	</table>
</div>
