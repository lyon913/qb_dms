<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<c:url value="/resources/js/dojo-release-1.7.3/dojox/form/resources/UploaderFileList.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/js/dojo-release-1.7.3/dojox/form/resources/BusyButton.css"/>"/>
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>" />

<script type="text/javascript">
	dojo.require("dojo.store.JsonRest");
	dojo.require("dijit.Tree");
	dojo.require("dijit.Menu");
	dojo.require("dijit.MenuItem");
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.Textarea");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojox.form.Uploader");
    dojo.require("dojox.form.uploader.FileList");
    dojo.require("dojox.form.uploader.plugins.IFrame");
    dojo.require("dojox.form.BusyButton");
	dojo.require("dojox.grid.EnhancedGrid");
	dojo.require("dojox.grid.enhanced.plugins.Pagination");
    dojo.require("dojo.data.ObjectStore");
    dojo.require("dijit.TitlePane");
    dojo.require("dijit.form.Form");
    
    
	dojo.ready(function() {
		
		/**
		**文件列表
		**/
	
		//数据结构
		var layout = [
		               {'name': '#', 'field': 'id', 'width': '5%',formatter:rowIndexFormatter},
		               {'name': '标题', 'field': 'name', 'width': '30%'},
		               {'name': '大小(MB)', 'field': 'size', 'width': '15%',formatter:byteSizeFormatter},
		               {'name': '上传时间', 'field': 'createTime', 'width': '20%',formatter:dateFormatter},
		               {'name': '操作', 'field': 'id','width':'15%',formatter:rowOperationFormatter}
		             ];
		//grid列表
	    var grid = new dojox.grid.EnhancedGrid(
	    		{
			        id: 'grid',
			        store: null,
			        structure: layout,
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
				    },
			        rowSelector: '20px',
			        height:'99%'
			    },
	      	dojo.byId("fileList"));
	    grid.startup();
	    
	    
		
		//树模型定义
		var restModel = new dojo.store.JsonRest({
			target : "<c:url value='/softwarefolder/list/'/>",
			mayHaveChildren : function(object) {
				return true;
			},
			getChildren : function(object, onComplete, onError) {
				this.get(object.id).then(function(fullNode) {
					object.children = fullNode.children;
					onComplete(fullNode.children);
				}, function(error) {
					showMessage("错误",error);
					onComplete([]);
				});
			},
			getRoot : function(onItem, onError) {
				this.get("-2").then(onItem, onError);
			},
			getLabel : function(object) {
				return object.name;
			},
			put : function(object, options) {
				this.onChildrenChange(object, object.children);
				this.onChange(object);
				return JsonRest.prototype.put.apply(this, arguments);
			},
			remove : function(id) {
				this.onDelete({
					id : id
				});
				return JsonRest.prototype.remove.apply(this, arguments);
			}
		});

		/**
		*文件树
		**/
		var tree = new dijit.Tree({
			model : restModel,
			showRoot : false,
			onMouseDown:function(ev,node){
		        var here=dijit.getEnclosingWidget(ev.target);
		        this.set('selectedNode',here);
		        
		        //刷新grid
		        loadList(here.item.id);

		    }
		}, "folderTree");

		
		
		//提交创建文件夹表单
		var form = dojo.byId("cForm");
		dojo.connect(form, "onsubmit", function(event){
			dojo.stopEvent(event);
			dojo.byId("parentId").value = tree.selectedNode.item.id;
			//form.parentId = tree.selectedNode.item.id;
			var xhrArgs = {
			  	form: form,
			  	handleAs: "json",
			  	load: function(data){
			  		if(data.success){
			  			restModel.get(tree.selectedNode.item.id).then(function(fullNode){
			  				fullNode.name = tree.selectedNode.item.name;
			  				restModel.put(fullNode);
						});
			  		}else{
			  			showMessage("错误",data.message);
			  		}
			  	},
			  	error: function(error){
			  		showMessage("错误","服务器发生错误。");
			  	}
			};
			dojo.xhrPost(xhrArgs);
			dijit.byId("cDlg").hide();
		});
		
		//文件上传完成事件
		dojo.connect(dijit.byId("uploader"), "onComplete", function(dataArray){
				dijit.byId("upDlg").hide();
		        if(dataArray.success == true){
		        	//刷新grid
			        loadList(tree.selectedNode.item.id);
		        	
		        	showMessage("信息","文件上传成功。");
		        }else{
		        	showMessage("错误",dataArray.message);
		        }
		});
	});
	
	//刷新grid
	function loadList(folderId){
		var store = new dojo.store.JsonRest({
			target:"<c:url value='/software/softwareList/'/>" + folderId
		});
		var grid = dijit.byId("grid");
		grid.setStore(new dojo.data.ObjectStore({objectStore:store}),null,null);
	}
	
	function rowOperationFormatter(id,index){
		return '<button data-dojo-type="dijit.form.Button" onclick="downLoad(' + id + ')">下载</button>';
	}
	
	function downLoad(id){
		window.location.href = "<c:url value='/software/download/'/>" + id;
	}
	
	function deleteFile(id){
		if(confirm("确定要删除吗？")){
		var xhrArgs = {
			  	url: "<c:url value='/software/delete/'/>"+id,
			  	handleAs: "json",
			  	content:{
			  		_method : "delete"
			  	},
			  	load: function(data){
			  		if(data.success){
			  			showMessage("信息","删除成功。");
			  			//刷新grid
			  			loadList(dijit.byId("folderTree").selectedNode.item.id);
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
	
	function search(){
		var form = dijit.byId("search_form");
		if(form.validate()){
			var key = dijit.byId("key").get('value');
			/**
			var xhrArgs = {
				  	url: "<c:url value='/software/searchAll'/>",
				  	content:{
				  		nameKey:key
				  	},
				  	handleAs: "json",
				  	load: function(data){
				  	    var obj = {
				  	          identifier: 'id',
				  	          items: data
				  	        };
				  	        var store = new dojo.data.ItemFileWriteStore({data: obj});
				  			//刷新grid
				  			var grid = dijit.byId("grid");
				  			grid.setStore(store);
				  	},
				  	error: function(error){
				  		showMessage("错误",error);
				  	}
			};
			dojo.xhrPost(xhrArgs);
			**/
			var store = new dojo.store.JsonRest({
				target : "<c:url value='/software/searchAll'/>",
				sortParam : "sort"
			});
			var objStore =  new dojo.data.ObjectStore({objectStore:store});
			var grid = dijit.byId("grid");
			grid.setStore(objStore,{nameKey:key},null);
		}
	}
	
</script>

<!-- main -->
<div id="file_main" data-dojo-type="dijit.layout.BorderContainer" style="width:100%;height:100%;margin:0;overflow: hidden;">

	<!-- 文件夹树 -->
	<div id="file_left" data-dojo-type="dijit.layout.ContentPane" 
		data-dojo-props="minSize:20, region:'leading', splitter:true"
		style="width: 250px;">
		<div id="folderTree"></div>
	</div>

	<!-- 文件列表 -->
	<div id="file_right" data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'center'">
		<div data-dojo-type="dijit.layout.BorderContainer"
			style="width: 100%; height: 100%; padding: 0px" >
			<div data-dojo-type="dijit.layout.ContentPane"
				data-dojo-props="region:'top', splitter:false"
				style="height: 70px; padding: 0px">
				<div id="fileSearch" data-dojo-type="dijit.TitlePane" data-dojo-props="title:'软件查找'"
					style="width:100%">
					<form id="search_form" 
						data-dojo-type="dijit.form.Form" 
						action="<c:url value='/software/search'/>" 
						method="post">
						<label for="key">关键字：</label>
						<input id="key" name="nameKey" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="required:true">
						<button type="button" 
								data-dojo-type="dijit.form.Button" 
								data-dojo-props="onClick:function(){search();},iconClass:'dijitCommonIcon dijitIconSearch'">查找</button>
					</form>
				</div>
			</div>
			<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'center'">
				<div  id="fileList"></div>
			</div>
		</div>
	</div>
</div>