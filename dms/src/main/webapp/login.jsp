<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录-丘北县人民医院内网信息共享平台</title>
<style type="text/css">

html,body{
	width: 98%;
	height: 98%;
}
.qb-nerong {
	width: 581px;
	height: 439px;
	height: 439px !important;
	font: normal normal 12px Arial, "宋体", Helvetica, sans-serif;
	background: url(resources/images/denglu.jpg);
	background-repeat: no-repeat;
}

.STYLE1 {
	font-size: 16px;
	color: #1C4060;
	text-align: right;
	width: 
}

.inputField{
	width: 130px;
	height: 20px;
	font-size: 16px;
}

.button{
	width: 65px;
	height: 30px;
}

.error{
	color: red;
	font-size: 16px;
	font-weight: bold;
	text-align: center;
}

.label{
	
}
</style>
</head>
<body>

	<table width="90%" height="90%" border="0" align="center"
		cellspacing="0">
		<tr>
			<td width="100%" height="100%" align="center" bgcolor="#FFFFFF"><div
					class="qb-nerong">
					<table width="580" height="436" border="0" align="center"
						cellspacing="0">
						<tr>
							<td width="569" height="429" align="left" valign="top"><p>&nbsp;&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<table width="458" border="0" align="center" cellspacing="0">
									<tr>
										<td width="140" height="253" rowspan="2">&nbsp;</td>
										<td width="314" height="102">&nbsp;</td>
									</tr>
									<tr>
										<td height="141"><table width="382" height="153"
												border="0" align="center" cellpadding="0" cellspacing="0">
												<tbody>
													<tr>
														<td height="153">
															<form action="j_spring_security_check" method="post">
																<table height="146" align="center">
																	<tbody>
																		<tr>
																			<td colspan="2"  class="error">
																				${SPRING_SECURITY_LAST_EXCEPTION.message}
																			</td>
																		</tr>
																		<tr>
																			<td height="28"  class="STYLE1">
																				用户名：
																			</td>
																			<td><input name="j_username" class="inputField"></td>
																		</tr>
																		<tr>
																			<td height="28" class="STYLE1">密 码：</td>
																			<td><input name="j_password" type="password"  class="inputField"></td>
																		</tr>
																		<tr>
																			<td height="47" colspan="2" align="right">
																				<input name="submit" type="submit" class="button" value="登陆" /> 
																			</td>
																		</tr>
																	</tbody>
																</table>
															</form>
														</td>
													</tr>
												</tbody>
											</table></td>
									</tr>
								</table>
								<p>&nbsp;</p></td>
						</tr>

					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>
