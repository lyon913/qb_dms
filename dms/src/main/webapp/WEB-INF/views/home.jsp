<%@page import="com.whr.dms.service.FileService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/widget/Portlet/Portlet.css'/>"/>
<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/layout/resources/GridContainer.css'/>"/>
<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/widget/Calendar/Calendar.css'/>"/>
<script type="text/javascript">
	dojo.require("dijit.dijit");
	dojo.require("dojox.widget.Portlet");
	dojo.require("dojox.layout.GridContainer");
	dojo.require("dojox.widget.Calendar");
	
	function downLoad(id){
		window.location.href = "<c:url value='/files/'/>" + id;
	}
	
	function downLoadsoft(id){
		window.location.href = "<c:url value='/software/download/'/>" + id;
	}
	function readNotice(id){
		window.location.href="<c:url value='/notice/'/>"+id;
	}
	function readPubNews(id){
		window.location.href="<c:url value='/publicnews/'/>"+id;
	}
</script>
<style>
.portalTitle {
	width: 250px;
	display:inline-block;
	overflow: hidden;
	text-overflow:ellipsis;
    word-wrap:normal;
    white-space: nowrap; 
    max-width:250px;
}
</style>
<div data-dojo-type="dojox.layout.GridContainer" id="gc1"
		acceptTypes="dojox.widget.Portlet, dojox.widget.FeedPortlet,dojox.widget.ExpandableFeedPortlet"
		hasResizableColumns="true" opacity="0.3" nbZones="2"
		allowAutoScroll="true" withHandles="false"
		handleClasses="dijitTitlePaneTitle"
		minChildWidth="200" minColWidth="40" style="width:100%;height:100%;overflow:auto;">

	<div data-dojo-type="dojox.widget.Portlet" title="通知公告">

			<ul>
				<c:forEach items="${latestNotices }" var="notice">
					<li>
						<a href="#" onclick="readNotice('${notice.id}')" class="portalTitle">
							${notice.title}
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<fmt:formatDate	value="${notice.publishDate }"/>
					</li>
				</c:forEach>
			</ul>

	</div>
		<div data-dojo-type="dojox.widget.Portlet" title="文件下载">

			<ul>
				<c:forEach items="${latestFiles }" var="file">
					<li>
						<a href="#" onclick="downLoad('${file.id}')"  class="portalTitle">
							${file.name}
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<fmt:formatDate value="${file.createTime }"/> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						上传用户:${file.author }
					</li>
				</c:forEach>
			</ul>

	</div>
	
	<div data-dojo-type="dojox.widget.Portlet" title="软件下载">

			<ul>
				<c:forEach items="${latestSoftwares }" var="soft">
					<li>
						<a href="#" onclick="downLoadsoft('${soft.id}')" class="portalTitle">
							${soft.name}
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<fmt:formatDate	value="${soft.createTime }" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						上传用户:${soft.author }
					</li>
				</c:forEach>
			</ul>
	</div>
	
		

	<div data-dojo-type="dojox.widget.Portlet" title="院务政务信息公开">

			<ul>
				<c:forEach items="${latestPubNews }" var="pubnews">
					<li>
						<a href="#" onclick="readPubNews('${pubnews.id}')" class="portalTitle">
							${pubnews.title}
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<fmt:formatDate	value="${pubnews.publishDate }" />
					</li>
				</c:forEach>
			</ul>

	</div>
		


		<div data-dojo-type="dojox.widget.Portlet" title="日历" >
			<div data-dojo-type="dojox.widget.Calendar">
			</div>

		</div>

	</div>
