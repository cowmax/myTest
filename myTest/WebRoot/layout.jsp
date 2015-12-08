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
<script type="text/javascript" src="script/editPwd.js"></script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.form-table tr td:first-child {
	width: 4em;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		var t = $("#oldPwd");
		
		$("input", $("#oldPwd").next("span")).blur(function() {
			checkPwd();
		});
		
		$("#newPwd").textbox("textbox").blur(function(){
			$("#msg1").html("");
			
			if (!$("#newPwd").validatebox("isValid")){
			
				$("#msg1").html("用户密码必须在6~16之间！");
				return false;
			}else{
				$("#msg1").html("");
			}
		});
	});
</script>
</head>

<body class="easyui-layout" id="layout_win">
	<div id="win" class="easyui-window" title="修改用户密码" 
		collapsible="false" minimizable="false" maximizable="false" closed="true" style="width:450px;height:260px;">  
	    <div class="easyui-panel" align="center" style="height: 100%;padding-top: 20px;">
			<table class="form-table">
				<tr>
					<td>用户ID</td>
					<td><input class="easyui-textbox" name="pu.userId" id="userId"
						value="${pu.userId} " /></td>
				</tr>
				<tr>
					<td>原密码</td>
					<td><input name="oldPwd" id="oldPwd" type="password" value=""
						class="easyui-textbox"/>
						<span style="color: red;" id="msg"></span>
					</td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input name="newPwd" id="newPwd" type="password" value=""
						class="easyui-validatebox easyui-textbox"
						data-options="required:true, validType:'length[6,16]',missingMessage:'密码为必填！',invalidMessage:'密码长度必须在6~16之间！'" />
						<span style="color: red;" id="msg1"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left: 80px;padding-top: 10px;"><input
						class="easyui-linkbutton" type="button" id="saveedit" value="确认"
						onclick="editPwd()">&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="easyui-linkbutton" type="button" value="取消"
						 onclick="$('#win').window('close')" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left: 100px;">
						<span style="color: green;" id="msg2"></span>
					</td>
				</tr>
			</table>
		</div> 
	</div>  
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
