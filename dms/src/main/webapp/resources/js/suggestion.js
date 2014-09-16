/**
 * 对话框方式阅读意见簿
 */
function readSuggestion(id){
	
	var w = parseInt(document.body.clientWidth);
	var h = parseInt(document.body.clientHeight);
	var url = _ctx + "suggestion/" + id;
	parent.showFrameDialog("查看意见内容",url,w,h);
}

/**
 * 删除意见簿
 */
function deleteSuggestion(id,returnType){
	var url = _ctx + "suggestion/" + id + "/del/"+returnType;
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}