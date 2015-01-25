<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="suggId" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="suggState" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="voteId" required="false" rtexprvalue="true" type="java.lang.String"%>


<c:if test='${empty voteId && suggState == "Private"}'>
	<c:url var="url" value="/vote/addVotePage?suggId=${suggId }"/>
	<iframe src="${url}" style="width:100%;height:auto;overflow: hidden;" scrolling="no" onload="resizeIframe(this);"></iframe>
</c:if>

<c:if test='${not empty voteId && suggState == "Private"}'>
	<c:url var="url" value="/vote/${voteId }"/>
	<iframe src="${url}" style="width:100%;overflow: hidden;" scrolling="no" onload="resizeIframe(this);"></iframe>
</c:if>


