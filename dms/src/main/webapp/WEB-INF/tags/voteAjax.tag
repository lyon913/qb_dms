<%@ tag language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="suggId" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="suggState" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="voteId" required="false" rtexprvalue="true" type="java.lang.String"%>


<script type="text/javascript">
function submitVote(){
	var selected = dojo.query("input[name=optSel]:checked");
	if(selected.length < 1){
		alert("请至少选择一个选项");
		return;
	}
	var f = dojo.byId("voteForm");
	f.submit();
}

function showDetails(){
	var url = "${ctx}vote/${v.id}/details";
	window.open(url);
}

dojo.ready(function(){
	dojo.query("input[name=optSel]").connect('onclick',function(e){
		
		//检测投票项数是否超出最大允许值
		var selected = dojo.query("input[name=optSel]:checked");
		console.log(selected);
		var max = ${v.maxVotes};
		if(selected.length > max){
			e.target.checked = false;
			alert("投票不能超过${v.maxVotes}项");
		}
	});
});
</script>

<c:import url="${ctx }/vote/${voteId}"></c:import>


