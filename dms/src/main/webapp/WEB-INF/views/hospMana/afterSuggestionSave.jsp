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

function returnList(){
var url = _ctx + "hospMana/list/public";
		window.location = url;
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
	

	
	<input type="hidden" name="suggId" value="${suggestion.id }"> 
		<table class="formTable" align="center">
			<tr>
				<td align="center" colspan="2">
					
					<button class="btn-normal" type="button" onclick="returnList();">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span> <span>返回</span>
					</button>
				</td>
			</tr>
		</table>
	
	<c:url var="attachmentUrl" value="/attachment/${suggestion.id }/TSuggestion/attachmentList" />
<iframe src="${attachmentUrl }" style="width:100%;height:300px;border:0;overflow:auto;" border="0" scrolling="auto"></iframe>
</div>
