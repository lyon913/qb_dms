<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>"/>

<script type="text/javascript">
dojo.require("dijit.form.Form");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dojox.grid.DataGrid");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojo.store.JsonRest");
dojo.require("dojo.data.ObjectStore");

dojo.ready(function(){
	var store = new dojo.store.JsonRest({
		target : "<c:url value='/suggestion/suggestionReplyList/${suggestionId}'/>",
		sortParam : "sort"
	});
	var objStore =  new dojo.data.ObjectStore({objectStore:store});
    /*set up layout*/
    var layout = [[
      {name: '#', field: '', width:"10%", formatter:rowIndexFormatter},
      {name: '回复内容', field: 'replyContent', width:"40%"},
      {name: '作者', field: 'authorId', width:"15%"},
      {name: '发布时间', field: 'replyDate', width:"15%", formatter:dateFormatter},
      {name: '操作', field: 'id', width:"20%", formatter:optionFormatter}
    ]];

    /*create a new grid:*/
    var grid = new dojox.grid.DataGrid({
        id: 'gridDiv',
        store:objStore,
        structure: layout,
        rowSelector: '20px',
        sortInfo:"-4",
        canSort:function(colIndex){
        	if(colIndex == 1|| colIndex == 5){
        		return false;
        	}
			
        	return true;
        }

    }, dojo.byId("gridDiv"));

    /*Call startup() to render the grid*/
    grid.startup();
});

function optionFormatter(id){
	return "<a href='<%=request.getContextPath() %>/suggestionReply/" + id + "'>阅读</a>&nbsp;&nbsp;";
}

function add(){
	window.location.href="<c:url value='/suggestion/createSuggestionReply/'/>";
}

function delSuggestion(id){
	if(confirm("确定要删除吗？")){
		var xhrArgs = {
			  	url: "<c:url value='/suggestion/del/'/>" + id,
			  	handleAs: "json",
			  	load: function(data){
		  			//刷新grid
		  			window.location.href = "<c:url value='/suggestion/suggestionMana'/>";
			  	},
			  	error: function(error){
			  		showMessage("错误",error);
			  	}
		};
		dojo.xhrPost(xhrArgs);
	}
}

function search(){
	var form = dijit.byId("suggestion_search_form");
	if(form.validate()){
		var key = dijit.byId("suggestion_search_key").get('value');
		var xhrArgs = {
			  	url: "<c:url value='/suggestion/searchAll'/>",
			  	content:{
			  		key:key
			  	},
			  	handleAs: "json",
			  	load: function(data){
			  	    var obj = {
			  	          identifier: 'id',
			  	          items: data
			  	        };
			  	        var store = new dojo.data.ItemFileWriteStore({data: obj});
			  			//刷新grid
			  			var grid = dijit.byId("gridDiv");
			  			grid.setStore(store);
			  	},
			  	error: function(error){
			  		showMessage("错误",error);
			  	}
		};
		dojo.xhrPost(xhrArgs);
	}
}

</script>


<div data-dojo-type="dijit.layout.BorderContainer" style="width:100%;height:100%;padding:0px">
	<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'top', splitter:false" style="height: 30px;padding:0px">
    	<form id="suggestion_search_form" data-dojo-type="dijit.form.Form" action="">
    		<button data-dojo-type="dijit.form.Button" 
				onclick="add()" type="button"
				data-dojo-props="iconClass:'dijitCommonIcon dijitIconNewTask'">新建意见</button>
			<label for="suggestion_search_key">查找关键字：</label>
			<input id="suggestion_search_key" 
					data-dojo-type="dijit.form.ValidationTextBox" 
					data-dojo-props="required:true,
									regExp:'(\\w|[\u4e00-\u9fa5])+',
									invalidMessage:'标题不能包含空格和特殊符号。'">
			<button data-dojo-type="dijit.form.Button"  type="button"
					onclick="search()"  
					data-dojo-props="iconClass:'dijitCommonIcon dijitIconSearch'">查找</button>
		</form>
	</div>
	<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'center'">
		<div id="gridDiv"></div>
	</div>
</div>

