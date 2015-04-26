<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/Preview.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/editor/plugins/resources/css/PasteFromWord.css'/>">
<script type="text/javascript">
	dojo.require("dijit.form.Button");
	dojo.require("dijit.TitlePane");
	
	dojo.ready(function(){
		loadAttachmentList();
	}
	);
	function goToNoticeList(){
		
			history.back();
	}
	
	function loadAttachmentList(){
		var xhrArgs = {
			  	url: "<c:url value='/notice/attachmentList'/>",
			  	content:{
			  		noticeId:'${notice.id }'
			  	},
			  	handleAs: "json",
			  	load: function(data){

			  	    	if(data != null && data.length > 0){
			  	    		var html = "";
			  	    		for(var i=0;i<data.length;i++){
			  	    			 html +="<li><span><a href=\"<c:url value='/notice/download/"+data[i].id+"'/>\">" + data[i].name + "</a></span></li>";
			  	    			
			  	    		}
			  	    		dojo.byId("attachList").innerHTML = html;
			  	    		
			  	    	}else{
			  	    		dojo.byId("attachList").innerHTML = "无附件";
			  	    	}
			  	},
			  	error: function(error){
			  		showMessage("错误",error);
			  	}
		};
		dojo.xhrPost(xhrArgs);
	}
</script>
<div data-dojo-type="dijit.TitlePane" title="查看通知<font color=red>『${notice.title}』</font> 点击次数：${counts}" style="width:100%; height:100%; overflow: auto">
		<table border="0" width="95%">
			<tr>
				<td>${notice.content}</td>
			</tr>
			<tr>
				<td><div>附件：
						<ul id="attachList">
						</ul>
					</div></td>
			</tr>
			<tr>
				<td align="right">发布科室：${notice.author}&nbsp;&nbsp;&nbsp;&nbsp;最近一次更新时间：${notice.publishDate }</td>
			</tr>
		</table>
		
	<button type="button" data-dojo-type="dijit.form.Button" onclick="goToNoticeList();" data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconUndo'">返回列表</button>
</div>