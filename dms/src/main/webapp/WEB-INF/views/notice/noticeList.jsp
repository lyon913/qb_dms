<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>" />


<script type="text/javascript">
dojo.require("dijit.form.Form");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dojox.grid.EnhancedGrid");
dojo.require("dojox.grid.enhanced.plugins.Pagination");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojo.store.JsonRest");
dojo.require("dojo.data.ObjectStore");
dojo.require("dijit.form.Select");
dojo.require("dijit.form.DateTextBox");

dojo.ready(function(){
	var store = new dojo.store.JsonRest({
		target : "<c:url value='/notice/list/1'/>",
		sortParam : "sort"
	});
	var objStore =  new dojo.data.ObjectStore({objectStore:store});
    /*set up layout*/
    var layout = [[
      {name: '#', field: '', width:"10%", formatter:rowIndexFormatter},
      {name: '标题', field: 'title', width:"40%"},
      {name: '作者', field: 'author', width:"10%"},
      {name: '通知时间', field: 'noticeDate', width:"10%"},
      {name: '最后更新时间', field: 'publishDate', width:"10%", formatter:dateFormatter},
      {name: '操作', field: 'id', width:"20%", formatter:optionFormatter}
    ]];

    /*create a new grid:*/
    var grid = new dojox.grid.EnhancedGrid({
        id: 'gridDiv',
        store:objStore,
        sortInfo:"-4",
        structure: layout,
        rowSelector: '20px',
        canSort:function(colIndex){
        	if(colIndex == 1|| colIndex == 6){
        		return false;
        	}
			
        	return true;
        },
        plugins: {
           pagination: {
              pageSizes: ["10", "25", "50"],
              description: true,
              sizeSwitch: true,
              pageStepper: true,
              gotoButton: true,
              maxPageStep: 4,
              position: "bottom"
          }
        }
    }, dojo.byId("gridDiv"));

    /*Call startup() to render the grid*/
    grid.startup();
});

function optionFormatter(id){
	//return "<button onClick='readNotice(" + id + ")'>阅读</button>";
	return new dijit.form.Button({label:"阅读",  onClick: function() { 
		readNotice(id);
    } });
}

function readNotice(id){
	var w = parseInt(document.body.clientWidth * 0.9);
	var h = parseInt(document.body.clientHeight * 0.9);
	var url = _ctx + "notice/" + id;
	parent.showFrameDialog("阅读通知",url,w,h);
}

	function search(key) {
		//var form = dijit.byId("search_form");
		if (dijit.byId("notice_search_form").validate()) {
			var key = dijit.byId("notice_search_key").get('value');
			var noticetypeId = dijit.byId("noticetypeId").get("value");
			var noticeDate =dojo.byId("noticeDate").value;
			var departmentId = dojo.byId("departmentId").value;
			/**
			var xhrArgs = {
				url : "<c:url value='/notice/searchAll/1'/>",
				content : {
					key : key,
					noticetypeId:noticetypeId,
					noticeDate:noticeDate,
					published:1,
					departmentId:departmentId
				},
				handleAs : "json",
				load : function(data) {
					var obj = {
						identifier : 'id',
						items : data
					};
					var store = new dojo.data.ItemFileWriteStore({
						data : obj
					});
					//刷新grid
					var grid = dijit.byId("gridDiv");
					grid.setStore(store);
				},
				error : function(error) {
					showMessage("错误", error);
				}
			};
			dojo.xhrPost(xhrArgs);
			**/
			var store = new dojo.store.JsonRest({
				target : "<c:url value='/notice/searchAll/1'/>",
				sortParam : "sort"
			});
			var objStore =  new dojo.data.ObjectStore({objectStore:store});
			var grid = dijit.byId("gridDiv");
			grid.setStore(
					objStore,
					//query params
					{
						key : key,
						noticetypeId:noticetypeId,
						noticeDate:noticeDate,
						published:1,
						departmentId:departmentId
					},null);
		}
	}
</script>
<div data-dojo-type="dijit.layout.BorderContainer"
	style="width: 100%; height: 100%; padding: 0px" >
	<div data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'top', splitter:false"
		style="height: 60px; padding: 5px">
		<form id="notice_search_form" data-dojo-type="dijit.form.Form"
			action="">
			<input type="hidden" id="departmentId" name="departmentId" value="${departmentId }">
			<label for="noticetypeId">类别选择：</label> 
			<select id="noticetypeId" name="noticetypeId" data-dojo-type="dijit.form.Select">
				<option value="0">全部</option>
				<c:forEach items="${noticetypeList}" var="nt">
					<option value="${nt.id }">${nt.noticetype }</option>
				</c:forEach>
			</select> 
		<label for="noticeDate">通知时间</label>
			<input id="noticeDate" name="noticeDate" data-dojo-type="dijit.form.DateTextBox" datePattern="yyyy-MM-dd">
			<label for="notice_search_key">查找关键字：</label> 
			<input
				id="notice_search_key" data-dojo-type="dijit.form.ValidationTextBox">
			
			<button data-dojo-type="dijit.form.Button" type="button"
				onclick="search(dijit.byId('notice_search_key').get('value'))"
				data-dojo-props="iconClass:'dijitCommonIcon dijitIconSearch'">查找</button>
		</form>
	</div>

	<div data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'center'">
		<div id="gridDiv"></div>
	</div>
</div>