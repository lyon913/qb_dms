<%@ tag language="java" pageEncoding="UTF-8"%>
<%@tag import="com.whr.dms.utils.Utils"%>
<%@ attribute name="percent" required="true" rtexprvalue="true"
	type="java.lang.Long"%>
<%@ attribute name="index" required="true" rtexprvalue="true"
	type="java.lang.Integer"%>
<div
	style="width: 20px; height: 50px; margin: 0px; padding: 0px; border: 1px;border-style: solid;border-color: #EBEBEB;position: relative;">
	<div style="width:20px;height:${percent}%;background-color:<%=Utils.getColor(index) %>;margin:0px;padding:0px;position: absolute;left: 0;bottom: 0;">
	</div>
</div>