<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>

	<div class="easyui-panel">
		<div>
			<h3 class="tab-subtitle">用户详细信息</h3>
		</div>
		<table class="form-table">
			<tr>
				<td>用户ID</td>
				<td><input class="easyui-textbox" name="uid"
					disabled="disabled" value="${pu.userId}" />
				</td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td><input class="easyui-textbox" name="uname"
					disabled="disabled" value="${pu.userName}" />
				</td>
			</tr>
			<tr>
				<td>用户描述</td>
				<td><input class="easyui-textbox" name="udesc"
					disabled="disabled" value="${pu.userDesc}" />
				</td>
			</tr>
			<tr>
				<td>创建时间</td>
				<td><input class="easyui-textbox" name="ucreatetime"
					disabled="disabled" value="${pu.createDt}" />
				</td>
			</tr>
			<tr>
				<td>最近使用时间</td>
				<td>
				
				<input class="easyui-textbox" name="ulasttime"
					disabled="disabled" value="${pu.lastDt}" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left: 100px;"><input
					class="easyui-linkbutton" onclick="javascript:history.go(-1);"
					type="button" value="返回"/></td>
			</tr>
		</table>
	</div>
</body>
</html>
