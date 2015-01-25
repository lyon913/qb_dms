<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
	dojo.require("dijit.form.DateTextBox");
	
	//初始化投票项数
	var optCount = ${opt==null?3:fn:length(opt)};
	
	/**
	*删除投票选项
	**/
	function removeOpt(item){
		if(optCount <= 2){
			alert("至少需要2个投票选项");
			return;
		}
		var div = item.parentNode;
		dojo.destroy(div);
		optCount--;
	}
	
	/**
	*增加投票选项
	**/
	function addOpt(){
		var html = '<div><input name="opt" class="input-text"/>'+
				   '<span class="dijitEditorIcon dijitEditorIconDelete clickable"'+
				   'onclick="removeOpt(this);"></span></div>';
				   
		var container = dojo.byId("optionsDiv");
		dojo.place(html,container,"last");
		optCount++;
	}
	
	function check(){
		var opts = dojo.query("input[name=opt]");
		for(var i = 0; i<opts.length;i++){
			if(dojo.trim(opts[i].value)==""){
				alert("选项名称不能为空");
				return false;
			}
		}
		return true;
	}
	
	dojo.ready(function(){
		var singleR = dojo.byId("_single");
		var multiR = dojo.byId("_multi");
		var maxI = dojo.byId("_max");
		//单选按钮激活时，禁用“最多可选几项”输入框
		dojo.connect(singleR,"click",function(){
			if(singleR.value){
				dojo.attr(maxI,"disabled","disabled");
			}
		});
		//多选按钮激活时，启用“最多可选几项”输入框
		dojo.connect(multiR,"click",function(){
			if(multiR.value){
				dojo.removeAttr(maxI,"disabled");
			}
		});
		
		//初始化日期输入框
		var d = dojo.byId("_endDateInput");
		new dijit.form.DateTextBox({
			id:"_endDateInput",
			name:"endDate",
			value:d.value,
			height:"40px"
		}, "_endDateInput");
	});
</script>
<style>
html,body{
	height: auto;
	width:auto;

}
.radioDiv{
	display: inline-block;
	width: 100px;
	margin: 5px;
}

</style>
<div class="panel">
	<div class="title">发起投票</div>
	<form:form modelAttribute="vote" method="post" onsubmit="return check();">

		<table class="formTable">
			<tr>
				<th width="140px">投票主题：</th>
				<td><dms:inputText name="title" /></td>
			</tr>

			<tr>
				<td colspan="2">
					<span class="radioDiv">
						<form:radiobutton id="_nm" path="isOpen" value="false"/>
						<label for="_nm">匿名投票</label>
					</span>
					<span  class="radioDiv">
						<form:radiobutton id="_jm" path="isOpen" value="true"/>
						<label for="_jm">记名投票</label>
					</span>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<span class="radioDiv">
						<form:radiobutton id="_single" path="isMulti" value="false"/>
						<label for="_single">单选择</label>
					</span>
					<span class="radioDiv">
						<form:radiobutton id="_multi" path="isMulti" value="true"/>
						<label for="_multi">多选择</label>
					</span>
				</td>
			</tr>
			<tr>
				<th>最多可选几项：</th>
				<td>
					<div>
						<form:input id="_max" path="maxVotes"  cssClass="input-text" 
							cssStyle="width:30px" cssErrorClass="input-text error" disabled="${!vote.isMulti }"/>
						<form:errors path="maxVotes" cssClass="error" />
					</div>
				</td>
			</tr>
			<tr>
				<th height="30px">投票截止日期：</th>
				<td><dms:inputText id="_endDateInput" name="endDate"/></td>
			</tr>

			<tr>
				<th>投票选项：</th>
				<td>
					<div id="optionsDiv">
						<c:if test="${opt == null }">
							<dms:voteOption value=""/>
							<dms:voteOption value=""/>
							<dms:voteOption value=""/>
						</c:if>
						<c:if test="${opt != null}">
							<c:forEach items="${opt}" var="o">
								<dms:voteOption value="${o}"/>
							</c:forEach>
						</c:if>
					</div>
					<button type="button" class="btn-normal" onclick="addOpt()">
						<span class="dijitIconAdd"></span>
					</button>
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button class="btn-normal" type="submit">
						<span class="dijitIconSave"></span> <span>保存</span>
					</button> 
					<a class="btn-normal" href="###"
					onclick="history.back();return false;">
					<span class="dijitEditorIcon dijitEditorIconDelete"></span> <span>取消</span>
				</a>
				</td>
			</tr>
		</table>
	</form:form>
</div>

