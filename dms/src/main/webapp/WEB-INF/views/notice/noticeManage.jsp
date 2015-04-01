<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>"/>


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
		target : "<c:url value='/notice/allList/1'/>",
		sortParam : "sort"
	});
	var objStore =  new dojo.data.ObjectStore({objectStore:store});
    /*set up layout*/
    var layout = [[
      {name: '#', field: '', width:"3%", formatter:rowIndexFormatter},
      {name: '标题', field: 'title', width:"40%"},
      {name: '作者', field: 'author', width:"10%"},
      {name: '发布状态', field: 'published', width:"5%", formatter:publishStateFormatter},
      {name: '最后修改', field: 'publishDate', width:"10%", formatter:dateFormatter},
      {name: '紧急状态', field: 'emergencyState', width:"5%", formatter:emergencyStateFormatter},
      {name: '操作', field: 'id', width:"27%", formatter:optionFormatter}
    ]];

    /*create a new grid:*/
    var grid = new dojox.grid.EnhancedGrid({
        id: 'gridDiv',
        store:objStore,
        sortInfo:"-5",
        structure: layout,
        rowSelector: '20px',
        canSort:function(colIndex){
        	if(colIndex == 1|| colIndex == 7){
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
	return "<a href='<%=request.getContextPath() %>/notice/edit/" + id + "'>【编辑】</a> &nbsp;"
		 + "<a href='#' onclick='switchState(" + id + ")'>【发布/取消】</a>&nbsp;"
		 + "<a href='#' onclick='switchEmergencyState(" + id + ")'>【紧急/取消】</a>&nbsp;"
		 + "<a href='#' onclick='delNotice(" + id + ")'>【删除】</a>&nbsp;";
}

function publishStateFormatter(state){
	return state?"<font color='#33CC00'>已发布</font>":"<font color='#FF0000'>未发布</font>";
}

function emergencyStateFormatter(state){
	return state?"<font color='#FF0000'>紧急</font>":"<font color='#33CC00'>正常</font>";
}

function goCreateNotice(){
	window.location.href = "<c:url value='/notice/create'/>";
}

function switchState(id){
	var xhrArgs = {
		  	url: "<c:url value='/notice/switchState/'/>" + id,
		  	handleAs: "json",
		  	load: function(data){
	  			//刷新grid
	  			window.location.href = "<c:url value='/notice/manage/1'/>";
		  	},
		  	error: function(error){
		  		showMessage("错误",error);
		  	}
	};
	dojo.xhrPost(xhrArgs);
}

function switchEmergencyState(id){
	var xhrArgs = {
		  	url: "<c:url value='/notice/switchEmergencyState/'/>" + id,
		  	handleAs: "json",
		  	load: function(data){
	  			//刷新grid
	  			window.location.href = "<c:url value='/notice/manage/1'/>";
		  	},
		  	error: function(error){
		  		showMessage("错误",error);
		  	}
	};
	dojo.xhrPost(xhrArgs);
}

function delNotice(id){
	if(confirm("确定要删除吗？")){
		var xhrArgs = {
			  	url: "<c:url value='/notice/del/'/>" + id,
			  	handleAs: "json",
			  	load: function(data){
			  		if(data && data.success){
		  			//刷新grid
		  			window.location.href = "<c:url value='/notice/manage/1'/>";
			  		}else{
			  			showMessage("错误",data.message);
			  		}
			  	},
			  	error: function(error){
			  		showMessage("错误",error);
			  	}
		};
		dojo.xhrPost(xhrArgs);
	}
}

function search(key){
	//var form = dijit.byId("search_form");
	if (dijit.byId("notice_search_form").validate()) {
			var key = dijit.byId("notice_search_key").get('value');
			var departId = dijit.byId("departmentId").get("value");
			var noticetypeId = dijit.byId("noticetypeId").get("value");
			var noticeDate =dojo.byId("noticeDate").value;
			/**
			var xhrArgs = {
				url : "<c:url value='/notice/searchAll/1'/>",
				content : {
					key : key,
					departmentId:departId,
					noticetypeId:noticetypeId,
					noticeDate:noticeDate,
					published:2
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
						departmentId:departId,
						noticetypeId:noticetypeId,
						noticeDate:noticeDate,
						published:2
					},null);
		}
}
</script>
<div data-dojo-type="dijit.layout.BorderContainer" style="width:100%;height:100%;padding:0px" >
	<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'top', splitter:false" style="height: 60px;padding:5px">
    <form id="notice_search_form" data-dojo-type="dijit.form.Form"
			action="">
			<button data-dojo-type="dijit.form.Button" 
				onclick="goCreateNotice()" type="button"
				data-dojo-props="iconClass:'dijitCommonIcon dijitIconNewTask'">新建</button>
			<label for="departmentId">科室选择：</label> 
			<select id="departmentId" name="departmentId" size="1"
				value="${u.department.id }" data-dojo-type="dijit.form.Select"  data-dojo-props="maxHeight:300">
				<option value="0">全部</option>
				<c:forEach items="${departmentList}" var="d">
					<option value="${d.id }">${d.name }</option>
				</c:forEach>
			</select> 
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