function showMessage(title, message) {
	var dialog = new dijit.Dialog({
		title : title,
		content : '<div id="_mb_body" style="margin:0px;padding:10px;font-size:1em;">' + message
				+ '<br><br></div>',
		hide : function() {
			this.destroyRecursive();
		}
	});
	var btn = new dijit.form.Button({
		label : "确定",
		onClick : function() {
			dialog.hide();
		}
	});
	var _mb_body = dojo.query("#_mb_body",dialog.domNode)[0];
	
	_mb_body.appendChild(btn.domNode);
	dialog.show();
}

function rowIndexFormatter(data, rowIndex) {
	return rowIndex + 1;
}

function dateFormatter(data, index) {
	var d = new Date(data);
	return dojo.date.locale.format(d, {
		datePattern : "yyyy-MM-dd",
		timePattern : "HH:mm:ss"
	});
}

function byteSizeFormatter(data) {
	var result = new Number(data / 1024 / 1024);
	return result.toFixed(2);
}

function onPageSelected(page, size, formId) {
	var form = document.getElementById(formId);
	form["page"] = page;
	form["size"] = size;
	form.submit();
}
