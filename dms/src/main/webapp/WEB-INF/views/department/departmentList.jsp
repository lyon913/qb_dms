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
	dojo.require("dojo.store.JsonRest");
	dojo.require("dojo.data.ObjectStore");
	dojo.require("dijit.form.Select");
	dojo.require("dijit.form.DateTextBox");

	dojo.ready(function() {
		var store = new dojo.store.JsonRest({
			target : "<c:url value='/admin/department/list'/>",
			sortParam : "sort"
		});
		var objStore = new dojo.data.ObjectStore({
			objectStore : store
		});
		/*set up layout*/
		var layout = [ [ {
			name : '#',
			field : 'id',
			width : "10%",
			formatter : rowIndexFormatter
		}, {
			name : '科室名称',
			field : 'name',
			width : "40%"
		}, {
			name : '负责人',
			field : 'head',
			width : "10%"
		}, {
			name : '电话',
			field : 'telephone',
			width : "10%"
		}, {
			name : '操作',
			field : 'id',
			width : "20%",
			formatter : optionFormatter
		} ] ];

		/*create a new grid:*/
		var grid = new dojox.grid.EnhancedGrid({
			id : 'gridDiv',
			store : objStore,
			structure : layout,
			rowSelector : '20px',
			canSort : function(colIndex) {
				if (colIndex == 1 || colIndex == 5) {
					return false;
				}
				return true;
			}
		}, dojo.byId("gridDiv"));

		/*Call startup() to render the grid*/
		grid.startup();
	});
	function optionFormatter(id) {
		return "<a href=\"<c:url value='/admin/department/edit/'/>" + id
				+ "\">编辑</a>&nbsp;&nbsp;<a href='#' onclick='del(" + id
				+ ")' >删除</a>";
	}

	function del(id) {
		if (confirm("删除前请确认该部门下没有用户！")) {
			window.location.href = "<c:url value='/admin/department/del/'/>"
					+ id;
		}
	}

	function goEdit() {
		window.location.href = "<c:url value='/admin/department/edit'/>";
	}
</script>
<div data-dojo-type="dijit.layout.BorderContainer"
	style="width: 100%; height: 100%; padding: 0px">
	<div data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'top'" style="height: 30px">
		<button onclick="goEdit()" data-dojo-type="dijit.form.Button">增加科室</button>
	</div>

	<div data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'center'">
		<div id="gridDiv"></div>
	</div>
</div>