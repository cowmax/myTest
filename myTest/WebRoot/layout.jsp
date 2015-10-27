<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>AMII BI 管理后台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="ico" href=images/logo.png>
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

</style>
</head>

<body class="easyui-layout">
	<!-- 正上方panel -->
	<div region="north" style="height:100px;padding:10px;" href="top.jsp"></div>
	<!-- 正左边panel -->
	<div region="west" title="菜单栏" split="true"
		style="width:280px;padding1:1px;overflow:hidden;list-style: none;" href="tree.jsp">
	</div>
	<!-- 正中间panel -->
	<div region="center" title="功能区" id="right">
		<div class="easyui-tabs" id="tabs" fit="true" border="false">
			<div title="欢迎页" id="welcome" style="padding:20px;overflow:hidden;">
				<div style="margin-top:20px;">
					<h1>欢迎使用！</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- 正下方panel -->
	<div region="south" style="height:50px;" align="center">
	<div class="copy-right">
		<label class="label-text"> AMII All Rights Reserved.<br /> 2015  </label>
	</div>
	</div>
</body>

</html>
