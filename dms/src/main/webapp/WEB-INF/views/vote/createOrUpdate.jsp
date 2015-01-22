<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="dms" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
var optCount = 3;
	function removeOpt(item){
		if(optCount <= 2){
			alert("至少需要2个投票选项");
			return;
		}
		var div = item.parentNode;
		dojo.destroy(div);
		optCount--;
	}
	
	function addOpt(){
		var html = '<div><input name="opt" class="input-text"/>'+
				   '<span class="dijitEditorIcon dijitEditorIconDelete clickable"'+
				   'onclick="removeOpt(this);"></span></div>';
				   
		var container = dojo.byId("optionsDiv");
		dojo.place(html,container,"last");
		optCount++;
	}
	
	dojo.ready(function(){
		var singleR = dojo.byId("_single");
		var multiR = dojo.byId("_multi");
		
		var maxI = dojo.byId("_max");
		dojo.connect(singleR,"change",function(){
			if(singleR.value){
				dojo.attr(maxI,"disabled","disabled");
			}
		});
		dojo.connect(multiR,"change",function(){
			if(multiR.value){
				dojo.removeAttr(maxI,"disabled");
			}
		});
	});
</script>
<div class="panel">
	<div class="title">发起投票</div>
	<form:form modelAttribute="vote" method="post">

		<table class="formTable">
			<tr>
				<th width="150px">投票主题：</th>
				<td><dms:inputText name="title" /></td>
			</tr>

			<tr>
				<td colspan="2">
					<div class="radioDiv">
						<form:radiobutton id="_nm" path="isOpen" value="false"/>
						<label for="_nm">匿名投票</label>
					</div>
					<div class="radioDiv">
						<form:radiobutton id="_jm" path="isOpen" value="true"/>
						<label for="_jm">记名投票</label>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="radioDiv">
						<form:radiobutton id="_single" path="isMulti" value="false"/>
						<label for="_single">单选择</label>
					</div>
					<div class="radioDiv">
						<form:radiobutton id="_multi" path="isMulti" value="true"/>
						<label for="_multi">多选择</label>
					</div>
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
				<th>投票截止日期：</th>
				<td><dms:inputText name="endDate" /></td>
			</tr>

			<tr>
				<th width="150px">投票选项：</th>
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

