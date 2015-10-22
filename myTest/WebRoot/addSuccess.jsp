<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
	
<script type="text/javascript" src="script/common.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css"></link></head>

<body>
 	<div style="width: 100%">
 		<div style="text-align:center; margin-left:150px;margin-top: 100px;float: left;"  >
			<div style="font-size: 16px;padding: 0px 0px 10px 0px;">保存${sessionScope.msg}成功！</div>	
			<div style="margin-top: 10px;" class="toolbar"><input class="l-btn" type="button" onclick="removeCurrentPanel()" value="关闭"/></div>
		</div>
	</div>
</body>
</html>
