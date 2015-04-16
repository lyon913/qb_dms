<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@tag import="com.whr.dms.utils.Utils"%>
<%@ attribute name="value" required="true" rtexprvalue="true"%>

<div>
	<input name="opt" class="input-text" value="${value}"/><span class="dijitEditorIcon dijitEditorIconDelete clickable"
		onclick="removeOpt(this);"></span>
</div>
