<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录-丘北县人民医院内网信息共享平台</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/js/dojo-release-1.7.3/dojo/resources/dojo.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/js/dojo-release-1.7.3/dijit/themes/claro/claro.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/dojo-release-1.7.3/dojo/dojo.js"
	data-dojo-config="parseOnLoad:true"></script>
<script type="text/javascript">
	dojo.require("dijit.TitlePane");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ValidationTextBox");
</script>

<style type="text/css">
<!--

.qb-nerong{
width:581px;
height:439px;
height:439px!important;
font: normal normal 12px  Arial, "宋体" ,Helvetica,sans-serif;
background:url(resources/images/denglu.jpg);
background-repeat:no-repeat;
}
.STYLE1 {
	font-size: 12px;
	color: #1C4060;
}
-->
</style>
</head>
<body>
   
  <table width="1002" height="749" border="0" align="center" cellspacing="0">
    <tr>
      <td width="1000" height="749" align="center" bgcolor="#FFFFFF"><div class="qb-nerong"><table width="580" height="436" border="0" align="center" cellspacing="0">
        <tr>
          <td width="569" height="429" align="left" valign="top" ><p>&nbsp;&nbsp;</p>
              <p>&nbsp;</p>
            <p>&nbsp;</p>              
              <table width="458" border="0" align="center" cellspacing="0">
                <tr>
                  <td width="140" height="253" rowspan="2">&nbsp;</td>
                  <td width="314" height="102">&nbsp;</td>
                </tr>
                <tr>
                  <td height="141"><table width="382" height="153" border="0" 
            align="center" cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr>
                        <td height="153">
<form action="j_spring_security_check" method="post" data-dojo-type="dijit.form.Form" onsubmit="return this.validate()">
                            <table height="146" align="center">
                              <tbody>
                                <tr>
                                  <td height="28"><span class="STYLE1">用户名：</span> </td>
                                  <td><input name="j_username" 
						data-dojo-type="dijit.form.ValidationTextBox"
						data-dojo-props="required:true">
                                  </td>
                                </tr>
                                <tr>
                                  <td height="28" class="STYLE1">密 码： </td>
                                  <td><input name="j_password" 
						type="password"
						data-dojo-type="dijit.form.ValidationTextBox"
						data-dojo-props="required:true">
                                  </td>
                                </tr>
                                <tr>
                                  <td height="47" colspan="2" align="center"><input name="submit" type="submit" class="boxttx" value="提交" />
                                    &nbsp;&nbsp;
                                    <input name="reset" type="reset" class="boxttx" value="重置" />
                                  &nbsp;&nbsp;</td>
                                </tr>
                              </tbody>
                            </table>
                        </form></td>
                      </tr>
                    </tbody>
                  </table></td>
                </tr>
              </table>            
          <p>&nbsp;</p></td>
        </tr>
        
      </table></div></td>
    </tr>
</table>
</body>
</html>
