<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<%@ attribute name="value" required="true" rtexprvalue="true"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="cssClass"%>
<%@ attribute name="cssStyle"%>

<div class="">
	<form:radiobutton path="${name}" value="${value }" 
		cssClass="input-text ${cssClass}" 
		cssStyle="${cssStyle }"
		cssErrorClass="input-text error" />
	<label>${title}</label>
	<form:errors path="${name}" cssClass="error" />
</div>
