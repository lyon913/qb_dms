<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>
<%@ attribute name="cssClass"%>
<%@ attribute name="cssStyle"%>

<div class="">
	<form:textarea path="${name}" cssClass="input-text ${cssClass}" cssStyle="${cssStyle }"
		cssErrorClass="input-text error" />
	<form:errors path="${name}" cssClass="error" />
</div>
