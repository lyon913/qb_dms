<%@page import="com.whr.dms.service.FileService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/widget/Portlet/Portlet.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/layout/resources/GridContainer.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/widget/Calendar/Calendar.css'/>" />
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dojox.widget.Portlet");
	dojo.require("dojox.layout.GridContainer");
	dojo.require("dojox.widget.Calendar");

	function downLoad(id) {
		window.location.href = "<c:url value='/files/'/>" + id;
	}

	function downLoadsoft(id) {
		window.location.href = "<c:url value='/software/download/'/>" + id;
	}
	function readNotice(id) {
		window.location.href = "<c:url value='/notice/'/>" + id;
	}
	function readPubNews(id) {
		window.location.href = "<c:url value='/publicnews/'/>" + id;
	}
</script>
<style>

.portalTitle{
	height:25px;
	padding: 0 20px 0 20px;
}
.portalTitleLeft {
	width: 65%;
	display: inline;
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: normal;
	white-space: nowrap;
	max-width: 65%;
	float: left;
}

.portalTitleRight {
	float: right;
	display:inline;
}
</style>
<div data-dojo-type="dojox.layout.GridContainer" id="gc1"
	acceptTypes="dojox.widget.Portlet, dojox.widget.FeedPortlet,dojox.widget.ExpandableFeedPortlet"
	hasResizableColumns="false"
	nbZones="2"	
	allowAutoScroll="true" 
	withHandles="true"
	handleClasses="dijitTitlePaneTitle" 
	style="width: 100%; height: 100%; overflow: auto;">

	<div data-dojo-type="dojox.widget.Portlet" title="通知公告">
		<c:forEach items="${latestNotices }" var="notice">
			<div class="portalTitle">
				<a href="#" onclick="readNotice('${notice.id}')" class="portalTitleLeft">${notice.title}</a> 
				<span class="portalTitleRight">
					<fmt:formatDate value="${notice.publishDate }"  pattern="yyyy-MM-dd"/>
				</span>
			</div>
		</c:forEach>
	</div>
	<div data-dojo-type="dojox.widget.Portlet" title="文件下载">
		<c:forEach items="${latestFiles }" var="file">
			<div class="portalTitle">
				<a href="#" onclick="downLoad('${file.id}')" class="portalTitleLeft" style="width: 40%">
					${file.name}
				</a>
				<span class="portalTitleRight">
					${file.authorDepart }&nbsp;&nbsp;
					<fmt:formatDate	value="${file.createTime }"  pattern="yyyy-MM-dd"/>
				</span>
			</div>
		</c:forEach>
	</div>

	<div data-dojo-type="dojox.widget.Portlet" title="软件下载">
		<c:forEach items="${latestSoftwares }" var="soft">
			<div class="portalTitle">
				<a href="#" onclick="downLoadsoft('${soft.id}')"
				class="portalTitleLeft"> ${soft.name} </a>
				<span class="portalTitleRight">
					${soft.author }&nbsp;&nbsp;
					<fmt:formatDate value="${soft.createTime }"  pattern="yyyy-MM-dd"/>
				</span>
			</div>
		</c:forEach>
	</div>



	<div data-dojo-type="dojox.widget.Portlet" title="院务政务信息公开">
		<c:forEach items="${latestPubNews }" var="pubnews">
			<div class="portalTitle">
				<a href="#" onclick="readPubNews('${pubnews.id}')"
				class="portalTitleLeft"> ${pubnews.title} </a>
				<span class="portalTitleRight">
					<fmt:formatDate value="${pubnews.publishDate }" pattern="yyyy-MM-dd"/>
				</span>
			</div>
		</c:forEach>
	</div>



	<div data-dojo-type="dojox.widget.Portlet" title="日历">
		<div style="height: 200px;">
			<div data-dojo-type="dojox.widget.Calendar"
				style="float:left;display: inline;"></div>
			<div style="display: inline;float: left;">
				<ul>
					<li>网站总登录量（次）：${totalCounts }</li>
					<li>当天登录用户量（次）：${curDayCounts }</li>
				</ul>
			</div>
		</div>
	</div>

</div>
