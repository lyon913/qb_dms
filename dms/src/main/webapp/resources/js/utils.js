function showMessage(title, message) {
	var dialog = new dijit.Dialog({
		title : title,
		content : message
	});
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

