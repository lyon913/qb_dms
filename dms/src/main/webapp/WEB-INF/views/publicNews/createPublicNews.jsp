<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/Preview.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/PasteFromWord.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/LocalImage.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/form/resources/FileUploader.css'/>">

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
	dojo.require("dojox.editor.plugins.LocalImage");
	dojo.require("dijit.form.Select");
	dojo.require("dojox.form.Uploader");
	dojo.require("dojox.form.uploader.FileList");
	dojo.require("dojox.form.uploader.plugins.IFrame");

	dojo.ready(function(){
		//文件上传完成事件
		dojo.connect(dijit.byId("uploader"), "onComplete", function(dataArray) {
			dijit.byId("upDlg").hide();
			if (dataArray.success == true) {
				//刷新附件列表
				loadAttachmentList();

				showMessage("信息", " 附件上传成功。");
			} else {
				showMessage("错误", dataArray.message);
			}
		});
		loadAttachmentList();
	});
	
	function submitForm(){
		var form = dijit.byId("publicnews_form");
		var editor = dijit.byId("publicnews_editor");
		if (form.validate()) {
			var content = editor.get("value");
			if(!content || content.length == 0){
				showMessage("消息","内容不能为空。");
				return;
			}else{
				var xhrArgs = {
					  	url: "<c:url value='/publicnews'/>",
					  	content:{
					  		id:dojo.byId("publicnews_id").value,
					  		title:form.get("value").title,
					  		published:form.get("value").published[0],
					  		content:content,
					  		author:dojo.byId("author").value,
					  		noticetypeId:dijit.byId("publicnewstypeId").get("value")
					  	},
					  	handleAs: "json",
					  	load: function(data){
					  	    if(data.success){
					  	    	dojo.byId("publicnews_id").value = data.data;
					  	    	dojo.byId("uplaod_form_publicnewsId").value = data.data;
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
	}
	
	function goToPublicNewsList(){
		window.location.href = "<c:url value='/publicnews/manage/2'/>";
	}
	
	//全选
	function checkAll(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",true);
		});
	}
	//反选
	function checkReverse(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",!chk.checked);
		});
	}
	//全取消
	function clearCheck(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",false);
		});
	}
	
	function showUploadDialog(){

		var noticeId = "" +dojo.byId("publicnews_id").value;
		if(noticeId != ""){
			var dialog = dijit.byId("upDlg");
			dialog.show();
		}else{
			alert("请先保存院务政务信息，再上传附件。");
		}
		
	}
	
	function loadAttachmentList(){
		var publicnewsId = dojo.byId("publicnews_id").value;
		if(publicnewsId == null || publicnewsId == ""){
			return
		}
		var xhrArgs = {
			  	url: "<c:url value='/publicnews/attachmentList'/>",
			  	content:{
			  		publicnewsId:publicnewsId
			  	},
			  	handleAs: "json",
			  	load: function(data){
	  	    		var html = "";
	  	    		if(data != null && data.length > 0){
		  	    		for(var i=0;i<data.length;i++){
		  	    			 html +="<li><span>" + data[i].name + "</span>" + "<button type='button' onclick=\"deleteAttach('" + data[i].id + "')\">删除</button></li>";
		  	    			
		  	    		}
		  	    	}
	  	    		dojo.byId("attachList").innerHTML = html;
			  	},
			  	error: function(error){
			  		showMessage("错误",error);
			  	}
		};
		dojo.xhrPost(xhrArgs);
	}
	
	function deleteAttach(id){
		if(confirm("你确定要删除此附件吗？")){
			var xhrArgs = {
				  	url: "<c:url value='/publicnews/deleteAttach/'/>" + id,
				  	handleAs: "json",
				  	load: function(data){
				  		if(data.success){
				  			//删除成功，刷新列表
				  			loadAttachmentList();
				  		}else{
				  			showMessage("错误",data.message);
				  		}
				  	},
				  	error: function(error){
				  		showMessage("错误",error);
				  	}
			};
			dojo.xhrGet(xhrArgs);
		}
	}
</script>
<div data-dojo-type="dijit.TitlePane" title="编辑院务政务公开信息" style="width:100%; height:100%;overflow: auto">
	<form id="publicnews_form" data-dojo-type="dijit.form.Form" action="">
		<input id="publicnews_id" name="id" type="hidden" value="${publicnews.id }">
		<table border="0" width="95%">
<tr>
		<td>信息类型：</td>
		<td>
		<select id="publicnewstypeId" name="publicnewstypeId" data-dojo-type="dijit.form.Select">
				<c:forEach items="${publicnewstypeList}" var="nt">
					<option value="${nt.id }" ${publicnews.noticetypeId==nt.id?"selected='selected'":"" }>${nt.noticetype }</option>
				</c:forEach>
			</select> 
		</td>
		
		</tr>
			<tr>
				<td width="60px">标题：</td>
				<td><input id="publicnews_title" 
					name="title" 
					value="${publicnews.title}"
					style="width: 400px"
					data-dojo-type="dijit.form.ValidationTextBox"
					data-dojo-props="required:true,
									regExp:'(\\w|[\u4e00-\u9fa5])+',
									invalidMessage:'标题不能包含空格和特殊符号。'"></td>
			</tr>
			<tr>
				<td>发布：</td>
				<td><input id="publicnews_published" name="published"
					type="checkbox" data-dojo-type="dijit.form.CheckBox"   ${publicnews.published?"checked":""}> (选中后院务政务公开信息为发布状态，可供查阅。否则为编辑状态。)</td>
			</tr>
			<tr>
			<td>发布科室：</td><td><input type="text" id="author" name="author" value="${user.department.name }"></td>
			</tr>
			<tr>
				<td>附件：<button data-dojo-type="dijit.form.Button" onclick="showUploadDialog()">上传附件</button></td>
				<td>
					
					<div>
						<ul id="attachList">
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">内容：</td>
				<td></td>
			</tr>

		</table>
	</form>

	<div id="publicnews_editor" name="content" 
		style="height:80%"
		data-dojo-type="dijit.Editor"
		data-dojo-props="extraPlugins:[{name:'pastefromword',plainText:true},
									   {name: 'LocalImage', uploadable: true, uploadUrl: '<c:url value="/publicnews/upload"/>', baseImageUrl: '<c:url value="/upload/images/"/>', fileMask: '*.jpg;*.jpeg;*.gif;*.png;*.bmp'},
										{name:'preview',plainText:true},
									   '||',
									   'foreColor', 
									   'hiliteColor',
										{name: 'fontName', plainText: true}, 
										{name: 'fontSize', plainText: true}, 
										{name: 'formatBlock', plainText: true}
									]">
		${publicnews.content}
	</div>

	<button type="button" data-dojo-type="dijit.form.Button" onclick="submitForm" data-dojo-props="iconClass:'dijitCommonIcon dijitIconSave'">保 存</button>
	<button type="button" data-dojo-type="dijit.form.Button" onclick="goToPublicNewsList();" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconUndo'">返回列表</button>

</div>

<!-- 上传文件对话框 -->
<div id="upDlg" data-dojo-type="dijit.Dialog" title="上传文件">
	提示：上传文件大小限制为100M，超出此大小可能会导致上传无响应。
	<form id="upload_form" action="<c:url value="/publicnews/uploadAttachment"/>"
		enctype="multipart/form-data" method="POST">
		<!-- 父文件夹id -->
		<input type="hidden" id="uplaod_form_publicnewsId"  name="publicnewsId" value="${publicnews.id}">

		<!-- 上传文件选择器 -->
		<input name="uploadedfile" multiple="false" type="file" force="iframe"
			data-dojo-type="dojox.form.Uploader" label="选择文件" id="uploader"
			data-dojo-props="iconClass:'dijitCommonIcon dijitIconFolderOpen'">

		<!-- 上传提交按钮 -->
		<input id="uploader_submit_button" type="submit" label="上传"
			data-dojo-type="dijit.form.Button" />
		<!-- 上传文件列表 -->
		<div data-dojo-type="dojox.form.uploader.FileList"
			uploaderId="uploader"></div>

	</form>
</div>