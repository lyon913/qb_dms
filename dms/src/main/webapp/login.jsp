<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录::丘北县人民医院内网信息共享平台</title>
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
	overflow: hidden;
}

.button {
	width: 50px;
	height: 25px;
}

.error {
	font-family: '微软雅黑';
	color: red;
	font-size: 16px;
	text-align: center;
}

.loginDiv {

	width: 100%;
	margin: auto;
	position: absolute;
	top: 50%;
	margin: -150px 0 0 0;
}

#titleDiv {
	height: 50px;
	width: 300px;
	padding: 0;
	margin: 0;
	background: url(resources/images/title.png);
	background-repeat: no-repeat;
	/**IE6**/
	_background: none;
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="resources/images/title.png");
}

.formBgOuter {
	width: 350px;
	position: absolute;
	padding: 0;
	left: 50%;
	margin: 0 0 0 -180px;
	padding: 0;
}

.formBg {
	height: 205px;
	width: 350px;
	
	padding:10px 0 0 0;
	
	background: url(resources/images/bg-login-form-1.png);
	background-repeat: no-repeat;
	/**IE6**/
	_background: none;
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="resources/images/bg-login-form-1.png");
}

.baseinput {
	height: 18px;
	background: none transparent scroll repeat 0% 0%;
	border: none;
	margin-top: -2px;
}

.labdiv {
	font-family: '微软雅黑';
	z-index: 12;
	float: left;
	margin: 0px 2px 0 5px;
}

.inputtd {
	height: 30px;
	padding: 0px 0px 0px 0px;
	margin: 0;
}

.inputDiv {
	height: 30px;
	width: 223px;
	background: url(resources/images/text1.png) no-repeat;
	font-family: '微软雅黑';
	font-size: 14px;
	padding: 4px 0px 4px 0px;
	margin: 0;
	z-index: 12;
}
</style>

<c:set var="_ctx" value="${pageContext.request.contextPath}/" />
<script type="text/javascript"
	src="${_ctx}resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${_ctx}resources/js/jquery.backstretch.min.js"></script>
<script type="text/javascript">
	$(function() {
		//背景图片
		$.backstretch("/dms/resources/images/bg-login.jpg");
		$("#j_username").focus();
		//判断是否在iframe中
		if (self.frameElement && self.frameElement.tagName == "IFRAME") {
			alert("登陆超时，请重新登陆");
			window.parent.location = "${_ctx}";
		}
	});
</script>
</head>
<body>
	<form action="j_spring_security_check" method="post">
		<div class="loginDiv">

			<table style="width: 100%;">
				<tr>
					<td align="center" valign="bottom" style="padding: 0 0 0 0; margin: 0;">
						<div id="titleDiv"></div>
					</td>
				</tr>
				<tr>
					<td  style="padding: 0 0 0 0; margin: 0;">
						<div class="formBgOuter">
							<div class="formBg">

								<table align="center" style="position: relative;">
									<tbody>
										<tr>
											<td class="error" height="40" valign="middle">
												${SPRING_SECURITY_LAST_EXCEPTION.message}</td>
										</tr>
										<tr>
											<td class="inputtd">
												<div class="inputDiv">
													<label class="labdiv" for="j_username">帐号：</label> 
													<input id="j_username" name="j_username" type="text" class="baseinput"
														style="font-size: 14px; width: 165px;" autocomplete="off" >
												</div>
											</td>
										</tr>
										<tr>
											<td class="inputtd">
												<div class="inputDiv">
													<label class="labdiv" for="j_password">密码：</label> <input
														type="password" class="baseinput"
														style="font-size: 14px; width: 165px;" name="j_password"
														id="j_password" value="" autocomplete="off">
												</div>
											</td>
										</tr>
										<tr>
											<td height="40px" align="center" valign="middle">
												<input name="submit" type="submit" value="登陆" class="button" />
												&nbsp;&nbsp;&nbsp;
												<input type="reset" value="重置" class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>

		</div>
	</form>
</body>
</html>
