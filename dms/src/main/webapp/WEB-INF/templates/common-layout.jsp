<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.whr.dms.models.TUser" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet"
	href="<c:url value='/resources/css/public.css'/>" />

<script type="text/javascript"
	src="<c:url value='/resources/js/dojo-release-1.7.3/dojo/dojo.js'/>"
	data-dojo-config="parseOnLoad:true"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/utils.js'/>"
	data-dojo-config="parseOnLoad:true"></script>
<script type="text/javascript">
	dojo.require("dijit.layout.ContentPane");
	dojo.require("dijit.layout.BorderContainer");
	dojo.require("dijit.layout.AccordionContainer");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.Menu");
	dojo.require("dijit.Dialog");
	
	function go(url){
		var basePath = "<%=request.getContextPath() %>";
		window.location.href = basePath + url;
	}
	
	function logout(){
		window.location.href="<c:url value='/j_spring_security_logout'/>";
	}
	
	function changePW(){
		window.location.href="<c:url value='/account/changePWPage'/>";
	}
</script>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0px;
	overflow: hidden;
	font-size: 14px;
}

#layout_main {
	width: 100%;
	height: 100%;
	
}
#layout_center{
	width: 100%;
	height: 100%;
	margin:0px;
	overflow: hidden;
}
#layout_top{
	width: 100%;
	height: 70px;
	background:url("<c:url value='/resources/images/top.jpg'/>");
	background-repeat:repeat;
}


</style>
</head>
<body class="soria">

	<div data-dojo-type="dijit.layout.BorderContainer"
		data-dojo-props="gutters:true, liveSplitters:false" id="layout_main">
		<!-- top -->
		<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'top', splitter:false" id="layout_top">
			<%
			TUser ud = (TUser) SecurityContextHolder.getContext()
		    .getAuthentication()
		    .getPrincipal();
			%>
			<div style="position:absolute;bottom:1px;right:1px">
			<font color="white" size="3px"><b>您好&nbsp;&nbsp; <%=ud.getName() %>,欢迎使用院内信息共享平台!&nbsp;&nbsp;&nbsp;</b></font>
			<button data-dojo-type="dijit.form.Button" 
				onclick="changePW()" type="button"
				data-dojo-props="iconClass:'dijitCommonIcon dijitIconKey'"><font size="3">修改密码</font></button>
			<button data-dojo-type="dijit.form.Button" 
				onclick="logout()" type="button"
				data-dojo-props="iconClass:'dijitCommonIcon dijitIconError'"><font size="3">退出</font></button>&nbsp;&nbsp;&nbsp;</div>
		</div>

		<!-- west -->
		<div data-dojo-type="dijit.layout.AccordionContainer"
			data-dojo-props="minSize:30, region:'leading', splitter:true,persist:true"
			style="width: 200px;font-size:20px;" id="leftAccordion">
			<div data-dojo-type="dijit.layout.AccordionPane" title="功能导航"
				 style="padding: 0px">
				<div id="navMenu" data-dojo-type="dijit.Menu" style="width: 100%">
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/home"); }'>首页</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/about"); }'>丘北县人民医院</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/notice/noticeMana");  }'>院内通知</div>
						<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/publicnews/pubMana");  }'>院务政务公开</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){go("/files");}'>文件下载</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){go("/software/list");}'>软件下载</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/suggestion/suggestionMana"); }'>意见簿</div>
				</div>
			</div>
			<div data-dojo-type="dijit.layout.AccordionPane" title="系统管理" style="padding: 0px">
				<div id="adminMenu" data-dojo-type="dijit.Menu" style="width: 100%">
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/notice/manage/1");  }'>通知管理</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/publicnews/manage/2");  }'>院务政务公开信息管理</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/software/softwareMana"); }'>软件管理</div>
						
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/admin/department/departmentMana");  }'>科室管理</div>
					<div data-dojo-type="dijit.MenuItem"
						data-dojo-props='iconClass:"dijitEditorIcon dijitEditorIconPaste",
						onClick:function(){ go("/admin/user/list");  }'>用户管理</div>
				</div>
			</div>
		</div>
		<!-- center -->
		<div id="layout_center" data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'center'" style="padding:0px">
			<tiles:insertAttribute name="content" />
		</div>
		<!-- end TabContainer -->
	</div>
	<!-- end BorderContainer -->
</body>
</html>