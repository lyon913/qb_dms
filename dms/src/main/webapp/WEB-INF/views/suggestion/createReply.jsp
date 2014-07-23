<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/Preview.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/PasteFromWord.css'/>">
<script type="text/javascript">
	dojo.require("dijit.Editor");
	dojo.require("dijit._editor.plugins.FontChoice");
	dojo.require("dijit._editor.plugins.TextColor");
	dojo.require("dojox.editor.plugins.Preview");
	dojo.require("dojox.editor.plugins.ToolbarLineBreak");
	dojo.require("dojox.editor.plugins.PasteFromWord");
	dojo.require("dijit._editor.plugins.Print");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.TitlePane");

	function submitForm1(){
		var form = dijit.byId("suggestionReplyform");
		if (form.validate()) {
			var xhrArgs = {
					  	url: "<c:url value='/suggestion/suggestionReply'/>",
					  	content:{
					  		id:dojo.byId("suggestionReply_id").value,
					  		replyContent:dojo.byId("suggestionReply_content").value,
					  		suggestionId:dojo.byId("suggestion_id").value
					  	},
					  	handleAs: "json",
					  	load: function(data){
					  	    if(data.success){
					  	    	dojo.byId("suggestionReply_id").value = data.data;
					  	    	showMessage("消息","保存成功。");
					  	    }else{
					  	    	showMessage("错误",data.message);
					  	    }
					  	},
					  	error: function(error){
					  		showMessage("错误",error);
					  	}
				};
				dojo.xhrPost(xhrArgs);
			
		}
	}
	
	function goToSuggestionReplyList(){
		window.location.href = "<c:url value='/suggestion/${suggestionId}'/>";
	}
</script>
<div data-dojo-type="dijit.TitlePane" 
Title="意见簿回复编辑"
style="width:100%;height:100%">
	<form id="suggestionReplyform" data-dojo-type="dijit.form.Form" action="" method="post">
		<input id="suggestionReply_id" name="id" type="hidden" value="${suggestionReply.id }">
		<table class="warp_table">
		<thead>
			<tr>
				<th width="200px">意见簿标题：</th>
				<th>${suggestion.suggestionTitle }</th>
			</tr>
		</thead>
			<tr>
				<td valign="top">&nbsp;&nbsp;回复内容：</td>
				<td><textarea class="textarea" id="suggestionReply_content" name="suggestionReplyContent" cols=50 rows=8>${suggestionReply.replyContent }</textarea></td>
			</tr>

		</table>
		<input type="hidden" id="suggestion_id" name="suggestionId" value="${suggestionId }">
	</form>



	<button type="button" data-dojo-type="dijit.form.Button" onclick="submitForm1();">保 存</button>
	<button type="button" data-dojo-type="dijit.form.Button" onclick="goToSuggestionReplyList();">返回列表</button>

</div>