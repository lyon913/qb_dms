<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@tag import="com.whr.dms.utils.Utils"%>
<%@ attribute name="percent" required="true" rtexprvalue="true" type="java.lang.Long"%>
<%@ attribute name="index" required="true" rtexprvalue="true"  type="java.lang.Integer"%>

<div style="width:${percent<1?1:percent}%;height:20px;background-color:<%=Utils.getColor(index) %>;margin:5px 0 5px 5px">
</div>
