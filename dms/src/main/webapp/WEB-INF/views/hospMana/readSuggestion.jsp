<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="delUrl" value="/hospMana/reply/"/>
<c:url var="readUrl" value="/hospMana/${suggestion.id }"/>

<script type="text/javascript">
function deleteReply(id){
	if(confirm("是否确定要删除此回复？")){
		var xhrArgs = {
			  	url: "${delUrl}"+id+"/delete",
			  	load: function(data){
			  		console.log(data);
			  	    if('"OK"' == data){
			  	    	window.location = "${readUrl}";
			  	    }
			  	},
			  	error: function(error){
			  		showMessage("错误","只有管理员和本人才能删除");
			  	}
		};
		dojo.xhrGet(xhrArgs);
	}
}

function submitReply(){
	var textArea = document.getElementById('reply');
	var form = document.getElementById('rForm');
	if(/^\s*$/.test(textArea.value)){
		alert("请填写内容。");
		return false;
	}
	form.submit();
}
</script>
<div class="panel">
	<div class="title">
		<span>标题：${suggestion.suggestionTitle}&nbsp;&nbsp;发帖人：${suggestion.author }</span>
	</div>
	<table class="formTable">
		<tr>

			<td colspan="2">
				<div
					style="line-height: 24px; padding-right: 1em; padding-left: 1em">
					${suggestion.suggestionContent }</div> <br />
				<div style="float: right; font-style: italic">
					最后一次更新时间：${suggestion.suggestionDate}</div>
			</td>
		</tr>
	</table>
	<div class="title">
		<span>回复内容</span>
	</div>
	<table class="formTable">
		<c:forEach items="${replyList }" var="r">
			<tr>
				<th width="150px"><br>&nbsp;&nbsp;&nbsp;回复者：${r.author }<br></th>
				<td>
					<div
						style="line-height: 24px; padding-right: 1em; padding-left: 1em">
						${r.replyContent }</div>
						<div style="float: right; font-style: italic">
						&nbsp;&nbsp;&nbsp;回复时间：${r.replyDate }&nbsp;&nbsp;
						<a href="###" onclick="deleteReply('${r.id}')">删除回复</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<c:url var="rUrl" value="/hospMana/${suggestion.id }/reply" />
	<form id="rForm" action="${rUrl }" method="post" target="_self">
	<input type="hidden" name="suggId" value="${suggestion.id }"> 
		<table class="formTable" align="center">
			<tr>
				<th width="20%">回复内容：</th>
				<td width="75%"><textarea id="reply" name="reply" style="width:90%" rows="10"></textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<button class="btn-normal" type="button" onclick="submitReply();">
						<span class="dijitIconSave"></span> <span>保存</span>
					</button>
					<button class="btn-normal" type="button" onclick="parent.closeFrameDialog();">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span> <span>返回</span>
					</button>
				</td>
			</tr>
		</table>
	</form>
</div>