/**
 * 对话框方式阅读帖子
 */
function readSuggestion(id){
	
	var w = parseInt(document.body.clientWidth);
	var h = parseInt(document.body.clientHeight);
	var url = _ctx + "hospMana/" + id;
	parent.showFrameDialog("交流区——查看帖子内容",url,w,h);
}

/**
 * 删除帖子
 */
function deleteSuggestion(id,returnType){
	var url = _ctx + "hospMana/" + id + "/del/"+returnType;
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}