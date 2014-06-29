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


	function submitForm(){
		var form = dijit.byId("suggestion_form");
		if (form.validate()) {
			var xhrArgs = {
					  	url: "<c:url value='/suggestion'/>",
					  	content:{
					  		id:dojo.byId("suggestion_id").value,
					  		suggestionTitle:form.get("value").suggestionTitle,
					  		suggestionContent:dojo.byId("suggestion_content").value
					  	},
					  	handleAs: "json",
					  	load: function(data){
					  	    if(data.success){
					  	    	dojo.byId("suggestion_id").value = data.data;
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
	
	function goToSuggestionList(){
		window.location.href = "<c:url value='/suggestion/suggestionMana'/>";
	}
</script>
<div data-dojo-type="dijit.TitlePane" Title="意见簿编辑" style="width:100%;height:100%;overflow:auto">
	<form id="suggestion_form" data-dojo-type="dijit.form.Form" action=""
		method="post">
		<input id="suggestion_id" name="id" type="hidden" value="${suggestion.id }">
		<table class="warp_table">
			<thead>
			<tr>
				<td>标题：</td>
				<td>
				<input id="suggestion_title" name="suggestionTitle" value="${suggestion.suggestionTitle} "
					data-dojo-type="dijit.form.ValidationTextBox"
					data-dojo-props="required:true" style="width: 900px">
				</td>
			</tr>
			</thead>
			<tr>
				<td height="70%">内容：</td>
				<td><div><textarea id="suggestion_content" name="suggestionContent"  class="textarea" cols=100 rows=20>${suggestion.suggestionContent }</textarea></div></td>
			</tr>
		<tr>
		<td align="center" colspan="2">
		<button type="button" data-dojo-type="dijit.form.Button" onclick="submitForm">保 存</button>
	<button type="button" data-dojo-type="dijit.form.Button" onclick="goToSuggestionList();">返回列表</button>
		
		</td>
		</tr>
		</table>
	</form>



	
</div>
