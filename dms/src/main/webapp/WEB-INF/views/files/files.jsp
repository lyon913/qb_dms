<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<c:url value="/resources/js/dojo-release-1.7.3/dojox/form/resources/UploaderFileList.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/js/dojo-release-1.7.3/dojox/form/resources/BusyButton.css"/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/js/dojo-release-1.7.3/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css'/>" />
<style>
.departCheckLable {
	width: 100px;
	display:inline-block;
	overflow: hidden;
	text-overflow:ellipsis;
    word-wrap:normal;
    white-space: nowrap; 
    max-width:250px;
}
</style>
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
	dojo.require("dijit.form.CheckBox");
	dojo.require("dijit.Toolbar");

	dojo.ready(function() {

				/**
				 **文件列表
				 **/

				//数据结构
				var layout = [ {
					'name' : '#',
					'field' : 'id',
					'width' : '5%',
					formatter : rowIndexFormatter
				}, {
					'name' : '文件名',
					'field' : 'name',
					'width' : '30%'
				}, {
					'name' : '大小(MB)',
					'field' : 'size',
					'width' : '15%',
					formatter : byteSizeFormatter
				}, {
					'name' : '上传者',
					'field' : 'author',
					'width' : '15%px'
				}, {
					'name' : '上传时间',
					'field' : 'createTime',
					'width' : '20%',
					formatter : dateFormatter
				}, {
					'name' : '操作',
					'field' : 'id',
					'width' : '15%',
					formatter : rowOperationFormatter
				} ];
				//grid列表
				var grid = new dojox.grid.EnhancedGrid({
					id : 'grid',
					store : null,
					structure : layout,
					sortInfo:-5,
			        canSort:function(colIndex){
			        	if(colIndex == 1|| colIndex == 6){
			        		return false;
			        	}
			        	return true;
			        },
					rowSelector : '20px',
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
				}, dojo.byId("fileList"));
				grid.startup();

				//树模型定义
				var restModel = new dojo.store.JsonRest(
						{
							target : "folder/list/",
							mayHaveChildren : function(object) {
								return true;
							},
							getChildren : function(object, onComplete, onError) {
								this.get(object.id).then(function(fullNode) {
									object.children = fullNode.children;
									onComplete(fullNode.children);
								}, function(error) {
									showMessage("错误", error);
									onComplete([]);
								});
							},
							getRoot : function(onItem, onError) {
								this.get("-1").then(onItem, onError);
							},
							getLabel : function(object) {
								return object.name;
							},
							put : function(object, options) {
								this.onChildrenChange(object, object.children);
								this.onChange(object);
								return JsonRest.prototype.put.apply(this,
										arguments);
							},
							remove : function(id) {
								this.onDelete({
									id : id
								});
								return JsonRest.prototype.remove.apply(this,
										arguments);
							}
						});

				/**
				 *文件树
				 **/
				var tree = new dijit.Tree({
					model : restModel,
					showRoot : false,
					onMouseDown : function(ev, node) {
						var here = dijit.getEnclosingWidget(ev.target);
						this.set('selectedNode', here);
						if(ev.button != 2){
							loadList(here.item.id);
						}
					}
				}, "folderTree");

				/**
				 *文件树右键菜单
				 **/
				var pMenu = new dijit.Menu({
					targetNodeIds : [ "folderTree" ],
					selector : ".dijitTreeNode"
				});
				
				var isManager = ${isManager};
				
				//管理权限
				if(isManager){
					//新建文件夹菜单按钮
					pMenu.addChild(new dijit.MenuItem({
						label : "新建文件夹",
						iconClass : "dijitCommonIcon dijitFolderClosed",
						onClick : function(item) {
							dijit.byId("cDlg").show();
						}
					}));
	
					//删除文件夹菜单按钮
					pMenu.addChild(new dijit.MenuItem({
						label : "删除文件夹",
						iconClass : "dijitCommonIcon dijitIconDelete",
						onClick : function(item) {
							var xhrArgs = {
								url : "<c:url value='/folder/'/>"
										+ tree.selectedNode.item.id,
								handleAs : "json",
								content : {
									_method : "delete"
								},
								load : function(data) {
									if (data.success) {
										var selected = tree.selectedNode;
										restModel.get(selected.getParent().item.id)
												.then(function(fullNode) {
															fullNode.name = selected.getParent().item.name;
															tree.model.put(fullNode);
													  });
									} else {
										showMessage("错误",data.message);
									}
								},
								error : function(error) {
									showMessage("错误", error);
								}
							};
							dojo.xhrPost(xhrArgs);
						}
					}));
				}
				//上传文件菜单按钮
				pMenu.addChild(new dijit.MenuItem(
								{
									label : "上传文件",
									iconClass : "dijitCommonIcon dijitIconUndo",
									onClick : function(item) {
										dijit.byId("upDlg").show();
										dojo.byId("uploader_parentId").value = tree.selectedNode.item.id;
										dijit.byId("uploader_submit_button").resetTimeout();
									}
								}));

				pMenu.startup();
				pMenu.bindDomNode(dojo.byId("folderTree"));

				//提交创建文件夹表单
				var form = dojo.byId("cForm");
				dojo.connect(
								form,
								"onsubmit",
								function(event) {
									dojo.stopEvent(event);
									dojo.byId("parentId").value = tree.selectedNode.item.id;
									//form.parentId = tree.selectedNode.item.id;
									var xhrArgs = {
										form : form,
										handleAs : "json",
										load : function(data) {
											if (data.success) {
												restModel
														.get(
																tree.selectedNode.item.id)
														.then(
																function(
																		fullNode) {
																	fullNode.name = tree.selectedNode.item.name;
																	restModel
																			.put(fullNode);
																});
											} else {
												showMessage("错误", data.message);
											}
										},
										error : function(error) {
											showMessage("错误", "服务器发生错误。");
										}
									};
									dojo.xhrPost(xhrArgs);
									dijit.byId("cDlg").hide();
								});

				//文件上传完成事件
				dojo.connect(dijit.byId("uploader"), "onComplete", function(
						dataArray) {
					dijit.byId("upDlg").hide();
					if (dataArray.success == true) {
						//刷新grid
						loadList(tree.selectedNode.item.id);

						showMessage("信息", "文件上传成功。");
					} else {
						showMessage("错误", dataArray.message);
					}
				});
				dojo.connect(dojo.byId("upload_form"),"onSubmit",function(e){
					alter(dijit.byId("shareSelect").get("value"));
				});
			});

	//刷新grid
	function loadList(folderId) {
		var store = new dojo.store.JsonRest({
			target : "<c:url value='/files/list/'/>" + folderId,
			sortParam : "sort"
		});
		var grid = dijit.byId("grid");
		grid.setStore(new dojo.data.ObjectStore({
				objectStore : store
			}),null,null
		);
	}

	function rowOperationFormatter(id, index) {
		var rtv = '<button data-dojo-type="dijit.form.Button" onclick="downLoad('
			+ id + ')">下载</button>';
		rtv = rtv + '<button data-dojo-type="dijit.form.Button" onclick="deleteFile('
				+ id + ')">删除</button>';
		return rtv;
	}

	function downLoad(id) {
		window.location.href = "<c:url value='/files/'/>" + id;
	}

	function deleteFile(id) {
		if (confirm("确定要删除吗？")) {
			var xhrArgs = {
				url : "<c:url value='/files/'/>" + id,
				handleAs : "json",
				content : {
					_method : "delete"
				},
				load : function(data) {
					if (data.success) {
						showMessage("信息", "删除成功。");
						//刷新grid
						loadList(dijit.byId("folderTree").selectedNode.item.id);
					} else {
						showMessage("错误", data.message);
					}
				},
				error : function(error) {
					showMessage("错误", error);
				}
			};
			dojo.xhrPost(xhrArgs);
		}
	}

	function search() {
		var form = dijit.byId("search_form");
		if (form.validate()) {
			var key = dijit.byId("key").get('value');
			
			var store = new dojo.store.JsonRest({
				target : "<c:url value='/files/search'/>",
				sortParam : "sort"
			});
			var objStore =  new dojo.data.ObjectStore({objectStore:store});
			var grid = dijit.byId("grid");
			grid.setStore(objStore,{nameKey:key},null);
		}
	}
	
	//全选
	function checkAll(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",true);
		});
	}
	//反选
	function checkReverse(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",!chk.checked);
		});
	}
	//全取消
	function clearCheck(parent){
		var nodes = dojo.query("#" + parent + " input[type='checkbox']:not([disabled])");
		dojo.forEach(nodes,function(chk){
			dijit.byId(chk.id).set("checked",false);
		});
	}
</script>

<!-- main -->
<div id="file_main" data-dojo-type="dijit.layout.BorderContainer"
	style="width: 100%; height: 100%; margin: 0; overflow: hidden;">

	<!-- 文件夹树 -->
	<div id="file_left" data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="minSize:20, region:'leading', splitter:true"
		style="width: 250px;">
		<div id="folderTree"></div>

		<!-- 新建文件夹对话框 -->
		<div id="cDlg" data-dojo-type="dijit.Dialog" title="新建文件夹">
			<form action="<c:url value="/folder"/> " id="cForm">
				<input type="hidden" name="parentId" id="parentId" value="">
				<table width="100%">
					<tr>
						<td width="50px">
							<label for="name">名称: </label>
						</td>
						<td>
							<input name="name" id="name" type="text" style="width:270px"
								data-dojo-type="dijit.form.ValidationTextBox"
								data-dojo-props="regExp:'(\\w|[\u4e00-\u9fa5])+', 
											required:true, 
											invalidMessage:'文件夹名称不能包含空格和特殊符号。'">
						</td>
					</tr>
					<tr>
						<td>
							<label for="loc">描述: </label>
						</td>
						<td>
							<input data-dojo-type="dijit.form.Textarea" 
								type="text"
								name="description" id="description">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<button data-dojo-type="dijit.form.Button" type="submit"
								data-dojo-props="onClick:function(){return dijit.byId('cDlg').isValid();},iconClass:'dijitCommonIcon dijitIconSave'">保存</button>
							<button data-dojo-type="dijit.form.Button" type="button"
								data-dojo-props="onClick:function(){dijit.byId('cDlg').hide();},iconClass:'dijitEditorIcon dijitEditorIconCancel'">取消</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 上传文件对话框 -->
		<div id="upDlg" data-dojo-type="dijit.Dialog" title="上传文件">
			提示：上传文件大小限制为100M，超出此大小可能会导致上传无响应。
			<form id="upload_form" action="<c:url value="/files/upload"/>"
				enctype="multipart/form-data" method="POST">
				<!-- 父文件夹id -->
				<input type="hidden" id="uploader_parentId" name="parentId">

				<!-- 上传文件选择器 -->
				<input name="file" multiple="false" type="file" force="iframe"
					data-dojo-type="dojox.form.Uploader" label="选择文件" id="uploader"
					data-dojo-props="iconClass:'dijitCommonIcon dijitIconFolderOpen'">

				<!-- 上传提交按钮 -->
				<input id="uploader_submit_button" type="submit" label="上传"
					data-dojo-type="dojox.form.BusyButton"
					data-dojo-props="busyLabel:'正在上传...',timeout:3000000,iconClass:'dijitCommonIcon dijitIconUndo'" />
				<!-- 上传文件列表 -->
				<div data-dojo-type="dojox.form.uploader.FileList"
					uploaderId="uploader" style="width:300px"></div>
				<div data-dojo-type="dijit.TitlePane" title="选择共享科室">
					<div data-dojo-type="dijit.Toolbar">
						<button data-dojo-type="dijit.form.Button" id="toolbar1.cut" onclick="checkAll('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconInsertUnorderedList'">全选</button>
						<button data-dojo-type="dijit.form.Button" id="toolbar1.copy" onclick="checkReverse('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconListNumIndent'">反选</button>
						<button data-dojo-type="dijit.form.Button" id="toolbar1.paste" onclick="clearCheck('departChecks');"
							data-dojo-props="iconClass:'dijitEditorIcon dijitEditorIconRemoveFormat'">全取消</button>
					</div>
					<div id="departChecks">
						<!-- 默认本科室共享，不能取消 -->
						<input type="hidden" name="sharedDepartmentId"
							value="${user.department.id}">
						<c:forEach items="${departList}" var="d" varStatus="status">
							<input id="dpChk_${status.index}" type="checkbox"
								name="sharedDepartmentId" value="${d.id }"
								data-dojo-type="dijit.form.CheckBox"
								${d.id==user.department.id?"checked disabled":""}>
							<label for="dpChk_${status.index}" class="departCheckLable">${d.name}</label>
							<!-- 5个科室一行 -->
							<c:if test="${(status.index +1) %5 ==0}">
								<br>
							</c:if>
						</c:forEach>
					</div>
				</div>

			</form>
		</div>
	</div>

	<!-- 文件列表 -->
	<div id="file_right" data-dojo-type="dijit.layout.ContentPane"
		data-dojo-props="region:'center'">
		<div data-dojo-type="dijit.layout.BorderContainer"
			style="width: 100%; height: 100%; padding: 0px" >
			<div data-dojo-type="dijit.layout.ContentPane"
				data-dojo-props="region:'top', splitter:false"
				style="height: 70px; padding: 0px">
					<div id="fileSearch" data-dojo-type="dijit.TitlePane"
						data-dojo-props="title:'文件查找'" style="width: 100%">
						<form id="search_form" data-dojo-type="dijit.form.Form"
							action="<c:url value='/files/search'/>" method="post">
							<label for="key">关键字：</label> <input id="key" name="nameKey"
								data-dojo-type="dijit.form.ValidationTextBox"
								data-dojo-props="required:true">
							<button type="button" data-dojo-type="dijit.form.Button"
								data-dojo-props="onClick:function(){search();},iconClass:'dijitCommonIcon dijitIconSearch'">查找</button>
						</form>
					</div>
			</div>
			<div data-dojo-type="dijit.layout.ContentPane"
				data-dojo-props="region:'center'">
				<div id="fileList"></div>
			</div>
		</div>
	</div>
</div>