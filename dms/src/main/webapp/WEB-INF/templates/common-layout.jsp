<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>丘北县人民医院内网信息共享平台</title>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojo/resources/dojo.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dijit/themes/soria/soria.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/resources/soriaGrid.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/public.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/pagination.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/themes/soria/soria.css'/>" />

<script type="text/javascript"
	src="<c:url value='/resources/js/dojo-release-1.7.3/dojo/dojo.js'/>"
	data-dojo-config="parseOnLoad:true"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/utils.js'/>"></script>

<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0px;
	overflow: hidden;
	font-size: 14px;
}
</style>

<script type="text/javascript">
	dojo.require("dijit/layout/ContentPane");
	dojo.require("dijit/layout/BorderContainer");
	dojo.require("dijit/layout/AccordionContainer");
	dojo.require("dijit/form/Button");
	dojo.require("dijit/Menu");
	dojo.require("dijit/Dialog");
	dojo.require("dojo/date/locale");
</script>
</head>
<body class="soria">

	<tiles:insertAttribute name="content" />

</body>
</html>