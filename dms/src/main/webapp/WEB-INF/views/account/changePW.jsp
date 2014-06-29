<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	dojo.require("dijit.TitlePane");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dojox.form.PasswordValidator");
</script>
<div data-dojo-type="dijit.TitlePane" title="修改密码">
	<form id="pwForm" 
		action="<c:url value='/account/changePW'/>"  method="POST"
		data-dojo-type="dijit.form.Form"
		onsubmit="return dijit.byId('pwForm').validate()">
		<div><font color="red"><strong>${error}</strong></font></div>
		<div data-dojo-type="dojox.form.PasswordValidator" name="newPW" oldName="oldPW">
			<script type="dojo/method" data-dojo-event="pwCheck"
				data-dojo-args="password">
				var checkPassed = false;
				var xhrArgs = {
					url : "<c:url value='/account/checkPassword'/>",
					handleAs : "json",
					content : {
						passwordCheck : password
					},
					load : function(data) {
						if (data.success) {
							checkPassed =  true;
						}
					},
					sync:true
				};
				dojo.xhrPost(xhrArgs);
        		return checkPassed;
    		</script>
			<label for="oldPassword">原密码：</label><input id="oldPassword" name="oldPassword" pwType="old" type="password" ><br>
			<label for="newPassword">新密码：</label><input id="newPassword" name="newPassword" pwType="new" type="password"><br>
			<label for="verify">新密码确认：</label><input id="verify" name="verify" pwType="verify" type="password"><br>
			<button data-dojo-type="dijit.form.Button" type="submit">保存</button>
		</div>
	</form>
</div>