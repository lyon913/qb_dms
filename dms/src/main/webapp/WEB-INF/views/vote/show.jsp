<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="dms"%>
<script type="text/javascript">
function submitVote(){
	var selected = dojo.query("input[name=optSel]:checked");
	if(selected < 1){
		alert("请至少选择一个选项");
		return;
	}
	var f = dojo.byId("voteForm");
	f.submit();
}

dojo.ready(function(){
	dojo.query("input[name=optSel]").connect('onclick',function(e){
		
		//检测投票项数是否超出最大允许值
		var selected = dojo.query("input[name=optSel]:checked");
		console.log(selected);
		var max = ${v.maxVotes};
		if(selected.length > max){
			e.target.checked = false;
			alert("投票不能超过${v.maxVotes}项");
		}
	});
});
</script>

	<div style="color: gray; margin: 10px 0 10px 0; text-align: center;">
		<span style="color: black;font-weight: bold;font-size: 16px;">${result.title}</span><br>
		发起人：${v.authorName}&nbsp;&nbsp; 发起时间：
		<fmt:formatDate value="${v.createTime }" pattern="yyyy-MM-dd" />
		&nbsp;&nbsp; 截止时间：
		<fmt:formatDate value="${v.endDate }" pattern="yyyy-MM-dd" />
		<br> ${v.isOpen?"记名投票":"匿名投票" }&nbsp;&nbsp;
		${v.isMulti?"多选择":"单选择" }&nbsp;&nbsp;
		<c:if test="${v.isMulti}">
			（最多可选${v.maxVotes}项）
		</c:if>
	</div>
	<form id="voteForm" action="${ctx }vote/${v.id}/doVote" method="post">
		<table align="center" border="0">
			<c:forEach items="${result.counts }" var="c" varStatus="s">
				<tr>
					<td>
						<input id="optSel_${c.optionId}" 
							name="optSel" 
							type='${v.isMulti?"checkbox":"radio" }'
							value="${c.optionId }">
						<label for="optSel_${c.optionId}">${c.optionTitle }</label>
					</td>
					<td style="width: 200px;">
						<dms:voteBar index="${s.index }" percent="${c.percent}" />
					</td>
					<td>
						<span>${c.count }</span>
						<span style="color: gray;">(<fmt:formatNumber value="${c.percent}" maxFractionDigits="0" />%)</span>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3" align="center">
					<button class="btn-normal" type="button" onclick="submitVote()">投票</button><br>
					<span class="error">${err }</span>
				</td>
			</tr>
		</table>
	</form>

