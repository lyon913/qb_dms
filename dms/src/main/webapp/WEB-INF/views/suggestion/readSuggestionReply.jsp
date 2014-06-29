<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>"/>

<script type="text/javascript">	
dojo.require("dijit.TitlePane");

	function goToSuggestionReplyList(){
		window.location.href = "<c:url value='/suggestion/${suggestionReply.suggestionId}'/>";
	}
</script>
<div  data-dojo-type="dijit.TitlePane" 
Title="回复内容查看"
style="width:100%;height:100%">
		<input id="suggestionReply_id" name="id" type="hidden" value="${suggestionReply.id }">
		<table border="0" cellspacing=1 cellpadding=5 width="100%" height="100%">
			<tr>
				<td height=100px valign="top">${suggestionReply.replyContent }</td>
			</tr>
		</table>

<button type="button" data-dojo-type="dijit.form.Button" onclick="goToSuggestionReplyList();">返回列表</button>
</div>