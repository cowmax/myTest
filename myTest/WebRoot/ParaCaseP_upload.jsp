<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ParaCaseP_daoru.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>
<script type="text/javascript"
	src="js/easyui/themes/easyui-lang-zh_CN.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<style>
* {
	font-family: "宋体";
	font-size: 14px
}
</style>
<body>
  <div  class="easyui-panel"  style="width:500px;height:150px;padding:10px;background:#fafafa;"  
        data-options="iconCls:'icon-save',closable:true,   
                collapsible:true,minimizable:true,maximizable:true">
	<p align="center">请您选择需要上传的文件</p>
	<form id="form1" name="form1" method="post"
		action="servlet/fileServlet" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td>上传文件：</td>
				<td><input name="file" type="file" size="20"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="提交"> <input
					type="reset" name="reset" value="重置"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
