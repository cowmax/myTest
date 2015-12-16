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
.form-table tr td:first-child {
	width: 5em;
}
</style>
<script type="text/javascript">
	/**
	 * 添加时ID判断
	 * @returns {Boolean}
	 */
	$(document).ready(function() {
		var uid;
		var upwd=$("#upwd").val();
		var msg;
		var reg;
		$("#uid").textbox("textbox").blur(function() {
			return checkId();
		});

		$("#userPwd").textbox("textbox").blur(function(){
			$("#msg1").html("");
			if (!$("#userPwd").validatebox("isValid"))
			{
				$("#msg1").html("用户密码必须在6~16之间！");
				return false;
			}else{
				$("#msg1").html("");
			}
		});
		$("#savepuser").click(function() {
			if (!$("#userPwd").validatebox("isValid"))
			{
				$("#msg1").html("用户密码必须在6~16之间！");
				return false;
			}else{
				$("#msg1").html("");
			}
			
			checkId({
				onComplete : function(data, succ) {
					if (succ) {
						uid = $("#uid").val();
						msg = $("#msg").html();
						if (uid.trim().length > 0) {
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

	function checkId(params) {
		reg = /^[a-zA-Z0-9]+$/;
		uid = $("#uid").val().trim();
		if (uid == "") {
			$("#msg").html("用户ID不能为空！");
			return false;
		} else if (reg.test(uid)) {
			$.ajax({
				type : 'POST',
				url : 'puserjudgeId.action',
				data : {
					'userId' : uid
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							$("#msg").html("用户ID已存在！");
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
			$("#msg").html("用户ID只能由数字和字母组成！");
			return false;
		}
	}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center">
		<form action="puseraddUser" id="saveform" method="post">
			<div>
				<div>
					<h3 class="tab-subtitle">增加用户信息</h3>
				</div>
				<table class="form-table">
					<tr>
						<td>用户ID</td>
						<td>
							<input class="easyui-textbox" id="uid" name="pu.userId"
							value="" /> 
							<span style="color: red;" id="msg"></span>
						</td>
					</tr>
					
					<tr style="margin-bottom: 50px;">
						<td>用户名称</td>
						<td><input name="pu.userName" value="" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td>用户密码</td>
						<td><input name="pu.userPwd" id="userPwd" type="password" value=""
							 class="easyui-validatebox easyui-textbox" data-options="required:true, validType:'length[6,16]',missingMessage:'密码为必填！',invalidMessage:'密码长度必须在6~16之间！'"/>
							 <span style="color: red;" id="msg1"></span>
						</td>
					</tr>
					<tr>
						<td>用户描述</td>
						<td><input name="pu.userDesc" value="" class="easyui-textbox"style="height: 80px;" data-options="multiline:true"  /></td>
					</tr>
					<tr>
						<td colspan="2" style="padding-left: 100px;"><input
							type="button" id="savepuser" value='保存' class="easyui-linkbutton" />&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" value="取消" onclick="removeCurrentPanel();"
							class="easyui-linkbutton">
						</td>
					</tr>

				</table>
			</div>
		</form>
	</div>
</body>
</html>