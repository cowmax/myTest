<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>top.jsp</title>

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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>
<script type="text/javascript">

	/**
	 *打开修改密码窗口
	 */
	function showEditWin(){
		$("#win").window('open');
	}
	/**
	 *关闭修改密码窗口
	 */
	function closeEditWin(){
		$("#win").window('close');
	}
	/**
	 *注销
	 */
    function exit(){
		location.href = 'index.jsp?op=logout'; // 跳转到前面，且注销
	}
	
</script>
</head>

<body>
	
	<div style="width: 100%; ">
		<p class="user-status">
			<strong>您好：<span>${pu.userId}</span></strong> 
			<span class="easyui-menubutton" data-options="menu:'#mm'">注销</span>
			<div id="mm">
				<div><a href="javascript:void(0)" onclick="location.href = 'index.jsp?op=logout';">注销</a></div>
				<div><a href="javascript:void(0)" onclick="$('#win').window('open')" >修改密码</a></div>
			</div>
		</p>
		<div class="title-pannel">
			<label class="trade-mark">AMII</label>
			<h1 class="system-name">BI系统管理后台</h1>
		</div>
		
	</div>
</body>
</html>
