<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>

<div class="">
	<form:input path="${name}" cssClass="input-text"
		cssErrorClass="input-text error" />
	<form:errors path="${name}" cssClass="error" />
</div>
