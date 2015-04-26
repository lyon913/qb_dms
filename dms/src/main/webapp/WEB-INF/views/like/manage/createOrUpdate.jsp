<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/form/resources/FileUploader.css'/>">
<script type="text/javascript">
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
	
	function showUploadDialog(){

		var optionId = "" +dojo.byId("option_id").value;
		if(optionId != ""){
			var dialog = dijit.byId("upDlg");
			dialog.show();
		}else{
			alert("请先保存选项名字，再上传附件。");
		}
		
	}
	
	function loadAttachmentList(){
		var optionId = dojo.byId("option_id").value;
		if(optionId == null || optionId == ""){
			return
		}
		var xhrArgs = {
			  	url: "<c:url value='/like/optionAttachmentList'/>",
			  	content:{
			  		optionId:optionId
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
		if(confirm("你确定要删除此图片吗？")){
			var xhrArgs = {
				  	url: "<c:url value='/like/deleteOptionAttach/'/>" + id,
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
<div class="panel">
	<div class="title">编辑点赞选项</div>
	<form:form modelAttribute="like" method="post">
<input id="option_id" name="id" type="hidden" value="${option.id }">
		<table class="formTable">
			<tr>
				<th width="150px">选项名称：</th>
				<td><dms:inputText name="title" cssStyle="width:99%;"/></td>
			</tr>
			<tr>
				<th width="150px">图片：</th>
				
				<td>附件：<button data-dojo-type="dijit.form.Button" onclick="showUploadDialog()">上传附件</button></td>
				<td>
					
					<div>
						<ul id="attachList">
						</ul>
					</div>
		
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button class="btn-normal" type="submit">
						<span class="dijitIconSave"></span>
						<span>保存</span>
					</button>
					<a class="btn-normal" href="###" onclick="history.back();return false;">
						<span class="dijitEditorIcon dijitEditorIconUndo"></span>
						<span>返回</span>
					</a>
				</td>
			</tr>
		</table>
	</form:form>
</div>

<!-- 上传文件对话框 -->
<div id="upDlg" data-dojo-type="dijit.Dialog" title="上传文件">
	提示：上传文件大小限制为100M，超出此大小可能会导致上传无响应。
	<form id="upload_form" action="<c:url value="/like/uploadOptionAttachment"/>"
		enctype="multipart/form-data" method="POST">
		<!-- 父文件夹id -->
		<input type="hidden" id="uplaod_form_option_id"  name="optionId" value="${option.id}">

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