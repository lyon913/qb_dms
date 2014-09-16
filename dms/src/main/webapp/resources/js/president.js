/**
 * 对话框方式阅读信件
 */
function readSuggestion(id){
	
	var w = parseInt(document.body.clientWidth);
	var h = parseInt(document.body.clientHeight);
	var url = _ctx + "president/" + id;
	parent.showFrameDialog("查看信件",url,w,h);
}

/**
 * 删除信件
 */
function deleteSuggestion(id,returnType){
	var url = _ctx + "president/" + id + "/del/"+returnType;
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}