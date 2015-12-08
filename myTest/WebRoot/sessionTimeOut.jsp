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

<title>BI 管理后台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>
</head>

<body>
	<div style="width: 100%">
 		<div style="text-align:center; margin-left:150px;margin-top: 100px;float: left;"  >
			<div style="font-size: 16px;padding: 0px 0px 10px 0px;">您的登录已经超时，请重新登录！</div>	
			<div style="margin-top: 10px;" class="toolbar"><input class="l-btn" type="button" onclick="removeCurrentPanel('puserexit.action')" value="重新登录"/></div>
		</div>
	</div>
</body>
</html>
