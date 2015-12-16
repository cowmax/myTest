<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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

.form-table tr td:first-child {
	width: 7em;
}
</style>
<script type="text/javascript">
	/**
	 * 保存修改数据
	 */
	$(function() {
		$("#userPwd").textbox("textbox").blur(function() {
			$("#msg1").html("");
			if (!$("#userPwd").validatebox("isValid")) {
				$("#msg1").html("用户密码必须在6~16之间！");
				return false;
			} else {
				$("#msg1").html("");
			}
		});
		$("#saveedit").click(function() {
			if (!$("#userPwd").validatebox("isValid")) {
				$("#msg1").html("用户密码必须在6~16之间！");
				return false;
			} else {
				$("#msg1").html("");
			}
			save();
		});

	});
	function save() {
		var uid = $("#uid").val().trim();
		var uname = $("#uname").val().trim();
		var upwd = $("#userPwd").val().trim();
		var udesc = $("#udesc").val();
		var ucreatetime = $("#ucreatetime").val();
		var ulasttime = $("#ulasttime").val();
		
		$.post("pusermergeUser", {
			'pu.userId' : uid,
			'pu.userName' : uname,
			'uPwd' : upwd,
			'pu.userDesc' : udesc,
			'pu.createDt' : ucreatetime,
			'pu.lastDt' : ulasttime
		}, function(data) {
			if (data) {
				$.messager.show({
					msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">修改用户信息成功！</div></div>',
					timeout : 600,
					showSpeed : 200,
					showType : 'show',
					style : {
						right : '',
						top : '',
						bottom : ''
					}
				});
				window.location = 'pusergetByOptions';
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-panel" align="center">
		<div>
			<h3 class="tab-subtitle">修改用户信息</h3>
		</div>
		<table class="form-table">
			<tr>
				<td>用户ID</td>
				<td><input class="easyui-textbox" name="uid" id="uid"
					value="${pu.userId} " disabled="disabled" /></td>
			</tr>
			<tr>
				<td>用户名称</td>
				<td><input class="easyui-textbox" name="uname" id="uname"
					value="${pu.userName}" /></td>
			</tr>
			<tr>
				<td>用户密码</td>
				<td><input name="pu.userPwd" id="userPwd" type="password" value="******"
					class="easyui-validatebox easyui-textbox"
					data-options="required:true, validType:'length[6,16]',missingMessage:'密码为必填！',invalidMessage:'密码长度必须在6~16之间！'" />
					<span style="color: red;" id="msg1"></span>
				</td>
			</tr>
			<tr>
				<td>用户描述</td>
				<td><input class="easyui-textbox" name="udesc" id="udesc"style="height: 80px;" data-options="multiline:true" 
					value="${pu.userDesc}" />
				</td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td><input class="easyui-textbox" name="ucreatetime"
					id="ucreatetime" readonly="readonly" value="${pu.createDt}" /></td>
			</tr>
			<tr>
				<td>最近使用时间：</td>
				<td><input class="easyui-textbox" name="ulasttime"
					id="ulasttime" readonly="readonly" value="${pu.lastDt}" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left: 100px;"><input
					class="easyui-linkbutton" type="button" id="saveedit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="easyui-linkbutton" type="button" value="取消"
					onclick="javascript:history.go(-1);" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
