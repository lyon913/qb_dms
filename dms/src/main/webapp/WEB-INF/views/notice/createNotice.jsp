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
	dojo.require("dijit._editor.plugins.Print");
	
	dojo.require("dojox.editor.plugins.Preview");
	dojo.require("dojox.editor.plugins.ToolbarLineBreak");
	dojo.require("dojox.editor.plugins.PasteFromWord");
	
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.TitlePane");
	dojo.require("dojox.editor.plugins.LocalImage");
	
	dojo.require("dojox.form.Uploader");
	dojo.require("dojox.form.uploader.FileList");
	dojo.require("dojox.form.uploader.plugins.IFrame");
	dojo.require("dijit.form.DateTextBox");
	dojo.require("dijit.form.Select");
	
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
		var form = dijit.byId("notice_form");
		var editor = dijit.byId("notice_editor");
		if (form.validate()) {
			var content = editor.get("value");
			if(!content || content.length == 0){
				showMessage("消息","内容不能为空。");
				return;
			}else{
				var checked = dojo.query("input[name='sharedDepartmentId']:checked");
				var checkedId = new Array();
				dojo.forEach(checked,function(chk){
					checkedId.push(chk.value);
				});
				var xhrArgs = {
					  	url: "<c:url value='/notice'/>",
					  	content:{
					  		id:dojo.byId("notice_id").value,
					  		title:form.get("value").title,
					  		published:form.get("value").published[0],
					  		emergencyState:form.get("value").emergencyState[0],
					  		sharedDepartmentId:checkedId,
					  		content:content,
					  		author:dojo.byId("author").value,
					  		noticeDate:dojo.byId("noticeDate").value,
					  		noticetypeId:dijit.byId("noticetypeId").get("value")
					  	},
					  	handleAs: "json",
					  	load: function(data){
					  	    if(data.success){
					  	    	dojo.byId("notice_id").value = data.data;
					  	    	dojo.byId("uplaod_form_notice_id").value = data.data;
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
	
	function goToNoticeList(){
		window.location.href = "<c:url value='/notice/manage/1'/>";
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

		var noticeId = "" +dojo.byId("notice_id").value;
		if(noticeId != ""){
			var dialog = dijit.byId("upDlg");
			dialog.show();
		}else{
			alert("请先保存通知内容，再上传附件。");
		}
		
	}
	
	function loadAttachmentList(){
		var noticeId = dojo.byId("notice_id").value;
		if(noticeId == null || noticeId == ""){
			return
		}
		var xhrArgs = {
			  	url: "<c:url value='/notice/attachmentList'/>",
			  	content:{
			  		noticeId:noticeId
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
				  	url: "<c:url value='/notice/deleteAttach/'/>" + id,
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
<style>
.departmentCheckTitle {
	width: 120px;
	display:inline-block;

	text-overflow:ellipsis;
    word-wrap:normal;
    white-space: nowrap; 

}
</style>
<div data-dojo-type="dijit.TitlePane" title="编辑通知" style="width:100%; height:100%;overflow: auto">
	<form id="notice_form" data-dojo-type="dijit.form.Form" action="">
		<input id="notice_id" name="id" type="hidden" value="${notice.id }">
		<table border="0" width="95%">
		<tr>
		<td>通知类型：</td>
		<td>
		<select id="noticetypeId" name="noticetypeId" data-dojo-type="dijit.form.Select">
				<c:forEach items="${noticetypeList}" var="nt">
					<option value="${nt.id }" ${notice.noticetypeId==nt.id?"selected='selected'":"" }>${nt.noticetype }</option>
				</c:forEach>
			</select> 
		</td>
		
		</tr>
			<tr>
				<td width="60px">标题：</td>
				<td><input id="notice_title" 
					name="title" 
					value="${notice.title}"
					style="width: 400px"
					data-dojo-type="dijit.form.ValidationTextBox"
					data-dojo-props="required:true,
									regExp:'(\\w|[\u4e00-\u9fa5])+',
									invalidMessage:'标题不能包含空格和特殊符号。'"></td>
			</tr>
			<tr>
				<td><font color='#FF0000'>紧急状态：</font></td>
				<td><input id="notice_emergency" name="emergencyState"
					type="checkbox" data-dojo-type="dijit.form.CheckBox"   ${notice.emergencyState?"checked":""}> (选中后通知为<font color='#FF0000'>紧急状态</font>。)</td>
			</tr>
			<tr>
				<td>发布：</td>
				<td><input id="notice_published" name="published"
					type="checkbox" data-dojo-type="dijit.form.CheckBox"   ${notice.published?"checked":""}> (选中后通知为发布状态，可供查阅。否则为编辑状态。)</td>
			</tr>
			
			<tr>
			<td>发布科室：</td><td><input type="text" id="author" name="author" value="${user.department.name }"></td>
			</tr>
			<tr>
			<td>通知日期：</td><td><input id="noticeDate" name="noticeDate" data-dojo-type="dijit.form.DateTextBox" datePattern="yyyy-MM-dd" value="${notice.noticeDate}"></td>
			</tr>
			<tr>
				<td>通知对象：</td>
				<td>
					<div data-dojo-type="dijit.Toolbar">
						<button data-dojo-type="dijit.form.Button" id="toolbar1.cut" onclick="checkAll('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconInsertUnorderedList'">全选</button>
						<button data-dojo-type="dijit.form.Button" id="toolbar1.copy" onclick="checkReverse('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconListNumIndent'">反选</button>
						<button data-dojo-type="dijit.form.Button" id="toolbar1.paste" onclick="clearCheck('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconRemoveFormat'">全取消</button>
					</div>
					<div id="departChecks">
						<c:forEach items="${departList}" var="d" varStatus="status">
							<c:set var="checked" value="false" scope="request"/>
							<c:forEach items="${checkedDepartment}" var="id">
								<c:if test="${id == d.id}"> 
									<c:set var="checked" value="true" scope="request"/>
								</c:if>
							</c:forEach>
							<input id="dpChk_${status.index}" type="checkbox"
								name="sharedDepartmentId" value="${d.id }"
								data-dojo-type="dijit.form.CheckBox"
								${d.id==user.department.id||checked?" checked ":""}
								${d.id==user.department.id?" disabled='disabled' ":""}>
							<label for="dpChk_${status.index}" class="departmentCheckTitle">${d.name}</label>
							<!-- 6个科室一行 -->
							<c:if test="${(status.index +1) %6 ==0}">
								<br>
							</c:if>
						</c:forEach>
					</div>
				</td>
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

	<div id="notice_editor" name="content" 
		style="height:80%"
		data-dojo-type="dijit.Editor"
		data-dojo-props="extraPlugins:[{name:'pastefromword',plainText:true},
									   {name: 'LocalImage', uploadable: true, uploadUrl: '<c:url value="/notice/upload"/>', baseImageUrl: '<c:url value="/upload/images/"/>', fileMask: '*.jpg;*.jpeg;*.gif;*.png;*.bmp'},
										{name:'preview',plainText:true},
									   '||',
									   'foreColor', 
									   'hiliteColor',
										{name: 'fontName', plainText: true}, 
										{name: 'fontSize', plainText: true}, 
										{name: 'formatBlock', plainText: true}
									]">${notice.content}</div>

	<button type="button" data-dojo-type="dijit.form.Button" onclick="submitForm" data-dojo-props="iconClass:'dijitCommonIcon dijitIconSave'">保 存</button>
	<button type="button" data-dojo-type="dijit.form.Button" onclick="goToNoticeList();" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconUndo'">返回列表</button>

</div>
<!-- 上传文件对话框 -->
<div id="upDlg" data-dojo-type="dijit.Dialog" title="上传文件">
	提示：上传文件大小限制为100M，超出此大小可能会导致上传无响应。
	<form id="upload_form" action="<c:url value="/notice/uploadAttachment"/>"
		enctype="multipart/form-data" method="POST">
		<!-- 父文件夹id -->
		<input type="hidden" id="uplaod_form_notice_id"  name="noticeId" value="${notice.id}">

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