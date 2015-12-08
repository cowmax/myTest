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
	width: 6em;
}
</style>
<script type="text/javascript">
	/**
	 * 修改时判断
	 * @returns {Boolean}
	 */
	$(document).ready(function() {
		var rname;
		var msg;
		$("#roleName").textbox("textbox").blur(function() {
			$("#msg").html("");
			return checkRoleName();
		});

		$("#saveedit").click(function() {
			checkRoleName({
				onComplete : function(data, succ) {
					if (succ) {
						rname = $("#roleName").val();
						roleDesc = $("#roleDesc").val();
						msg = $("#msg").html();
						if (rname.length > 0) {
							if (msg.length <= 0) {
								$.post(
									'prolemergeRole.action',{
										'prole.roleName' : rname,
										'prole.roleDesc' : roleDesc,
									},
									function(data){
									if (data) {
									$.messager.show({
										msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">修改角色信息成功！</div></div>',
										timeout : 600,
										showSpeed : 200,
										showType : 'show',
										style : {
											right : '',
											top : '',
											bottom : ''
										}
									});
									window.location = 'prolegetRolisByOptions';
									return true;
									}
								});
							} else {
								alert(msg);
								return false;
							}
						} else {
							alert("角色名称不能为空！");
							return false;
						}
					} else {
					}
				}
			});

		});
	});

	function checkRoleName(params) {
		rname = $("#roleName").val();
		if (rname == "") {
			$("#msg").html("角色名称不能为空！");
			return false;
		} else if (rname.length >= 2 && rname.length <= 8) {
			$.ajax({
				type : 'POST',
				url : 'prolejudgeName.action',
				data : {
					'choose':'edit',
					'rname' : rname
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							$("#msg").html("角色名称已存在！");
							if (params != null && params.onComplete != null)
								params.onComplete(data, false);
							return false;
						} else {
							$("#msg").html("");
							if (params != null && params.onComplete != null)
								params.onComplete(data, true);
							return true;
						}
					}
				}

			});
		} else {
			$("#msg").html("角色名称长度在2~8之间");
			return false;
		}
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
				<td>角色ID</td>
				<td><input class="easyui-textbox" name="prole.roleId"
					disabled="disabled" value="${prole.roleId}" /></td>
			</tr>
			<tr>
				<td>角色名称</td>
				<td><input class="easyui-textbox" name="prole.roleName"
					id="roleName" value="${prole.roleName} " /> <span
					style="color: red;" id="msg"></span></td>
			</tr>

			<tr>
				<td>角色描述</td>
				<td><input class="easyui-textbox" name="prole.roleDesc" id="roleDesc"
					value="${prole.roleDesc}" />
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
