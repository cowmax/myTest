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

<title>My JSP 'index.jsp' starting page</title>
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

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>
<script type="text/javascript">
	/**
	 * 添加时ID判断
	 * @returns {Boolean}
	 */
	$(document).ready(function() {
		var rname;
		var msg;

		$("#roleName").textbox("textbox").blur(function() {
			$("#msg").html("");
			return checkRoleName();
		});
		
		$("#saveprole").click(function() {
			checkRoleName({
				onComplete : function(data, succ) {
					if (succ) {
						rname = $("#roleName").val();
						msg = $("#msg").html();
						if (rname.length > 0) {
							if (msg.length <= 0) {
								$("#saveform").submit();
								$.messager.show({
									msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">保存用户信息成功！</div></div>',
									timeout : 600,
									showSpeed : 200,
									showType : 'show',
									style : {
										right : '',
										top : '',
										bottom : ''
									}
								});
								return true;
							} else {
								alert(msg);
								return false;
							}
						} else {
							alert("用户ID不能为空！");
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
		} else if (rname.length>=2&&rname.length<=8) {
			$.ajax({
				type : 'POST',
				url : 'prjudgeNameaction.action',
				data : {
					'choose':'add',
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center">
		<form action="praddRoleaction" id="saveform" method="post">
			<div>
				<div>
					<h3 class="tab-subtitle">增加角色信息</h3>
				</div>
				<table class="form-table">
					<tr>
						<td>角色名称</td>
						<td><input class="easyui-textbox" id="roleName"
							name="prole.roleName" value=""/>
							<span style="color: red;" id="msg"></span>
						</td>
					</tr>

					<tr style="margin-bottom: 50px;">
						<td>角色描述</td>
						<td><input name="prole.roleDesc" value=""
							class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 100px;"><input
							type="button" id="saveprole" value='保存' class="easyui-linkbutton" />&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" value="取消" onclick="javascript:history.go(-1);"
							class="easyui-linkbutton">
						</td>
					</tr>

				</table>
			</div>
		</form>
	</div>
</body>
</html>