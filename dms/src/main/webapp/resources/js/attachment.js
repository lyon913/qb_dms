
/**
 * 删除附件
 */
function deleteAttachment(id){
	var url = _ctx + "attachment/" + id+"/del";
	
	if(confirm("是否确认删除？")){
		window.location = url;
	}
}

/**
 * 下载附件
 */
function downloadAttachment(id){
	var url = _ctx + "attachment/download/" + id;
	
	if(confirm("是否确认下载？")){
		window.location = url;
	}
}