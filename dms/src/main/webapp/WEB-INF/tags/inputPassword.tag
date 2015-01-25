<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>

<div class="">
	<form:password path="${name}" cssClass="input-text"
		cssErrorClass="input-text error" />
	<form:errors path="${name}" cssClass="error" />
</div>
