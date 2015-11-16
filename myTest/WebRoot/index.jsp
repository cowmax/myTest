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
<script type="text/javascript">
	function onfocu(n) {
		n.value = "";
	}
	/**
	 * 点击看不清，随机获取验证码
	 */
	function select() {
		var code = document.getElementById("code");
		code.setAttribute("src", "code.jsp?number=" + Math.random());
	}
	/**
	 * 验证码异步验证
	 */
	function checkcode(code) {
		var codeVal = $("#changeCode").val();//取值
		if (codeVal.length < 4) {
			// $("#codemsg").html("请输入验证码！");
			$("#submi").removeAttr("disabled");
			$("#submi").css("background-color", "gray");
		} else if(codeVal.length==4) {
			$("#codemsg").html("");
			$.ajax({
				type : 'POST',
				url : 'pucheckCodeaction.action',
				data : {
					'code' : codeVal
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							$("#submi").attr("disabled", false);
							$("#submi").css("background-color", "#1E82CC");
							return false;
						} else {
							$("#codemsg").html("验证码错误！");
							$("#submi").removeAttr("disabled");
							$("#submi").css("background-color", "gray");
							return true;
						}
					}
				}

			});
		}else{
			$("#codemsg").html("验证码错误！");
			$("#submi").removeAttr("disabled");
			$("#submi").css("background-color", "gray");
		}
	}
	
	/**
	 * 用户账号验证
	 */
	function checkUserId(userId){
		$("#msg").html("");
		var userId = $("#userId").val();
		if(userId.length <= 0){
			$("#useridmsg").html("用户账号不能为空!");
			return false;
		}else{
			$("#useridmsg").html("");
		}
	}

	/**
	 * 密码验证
	 */
	function checkUserPwd(pwd){
		$("#msg").html("");
		var pwd = $("#userPwd").val();
		if(pwd.length <= 0){
			$("#pwdmsg").html("密码不能为空!");
			return false;
		}else{
			$("#pwdmsg").html("");
		}
	}
	/**
	 * 登录验证
	 */
	function checkLogin() {
		var userId = $("#userId").val();
		var pwd = $("#userPwd").val();
		$("#msg").html("");
		$.ajax({
				type : 'POST',
				url : 'puloginCheckaction.action',
				data : {
					'userId' : userId,
					'userPwd':pwd
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							//window.location = "menugetNodes.action?userId"+userId;
							location.href='layout.jsp';
						} else {
							$("#msg").html("用户名或密码错误，请重新登录！");
						}
					}
				}

			});
	}
	
	// 页面初始化代码
	$(function(){
		$("#changeCode").keyup(function(){
			checkcode(null);
		});
	});
</script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

span {
	color: red;
}

dd {
	line-height: 50px;
	height: 50px;
	margin-bottom:5px;
}

dd.msg-bar{
	height:20px;
	line-height:20px;
	margin:0px;
}

dd.btn-bar{
	height:20px;
	line-height:20px;
	margin:0px;
}

dd input {
	padding-left: 5px;
	line-height: 35px;
	height: 35px;
	width: 200px;
	font-size: 15px;
}

#changeCode {
	width: 80px;
}

#submi {
	background-color:gray;
	width: 80px;
	height: 37px;
	border: none;
	cursor: pointer;
	text-align: center;
	font-weight: bold;
	color: #fff;
	line-height: 37px;
	margin: 0 5px 0 0;
	font-size: 14px;
	float: left
}
.msg{
	color: red;
	text-align: left;
	display:inline-block;

}
.inline-div{
	float:left;
	padding-top:5px;
	padding-right:5px;
}

.single-line-div{
	clear:both;
	
}

.login-panel {
    width: 70%;
    margin-top: 200px;
    margin-left: 0;
}

.title-text{
	font-size:2em;
	line-height:2.5em;
}

</style>

</head>

<body>
	<div class="easyui-panel panel-box" align="center">
		<div class="left-panel"></div>
		<div style="width:40%;height:600px; float: left;">
			<form id="loginform" method="post" style="height: 60%">
				<div class="login-panel" align="left">
					<dl>
						<dt>
							<h1 class="title-text">BI 管理后台</h1>
						</dt>
						<dd>
							<input type="text" style="color: gray;" value="用户账号" name="userId" id="userId"
								onfocus="if(this.value==''||this.value=='用户账号'){onfocu(this)};this.style.color='black';"
								onblur="checkUserId(this);if(this.value==''||this.value=='用户账号'){this.value='用户账号';this.style.color='gray';}">
								<div class="msg" id="useridmsg"></div>
						</dd>
						<dd>
							<input style="color: gray;" value="密码" name="userPwd" id="userPwd"
								onfocus="if(this.value==''||this.value=='密码'){onfocu(this)};this.style.color='black';this.type='password';"
								onblur="checkUserPwd(this);if(this.value==''||this.value=='密码'){this.value='密码';this.style.color='gray';this.type='text';};">
								<div class="msg" id="pwdmsg"></div>
								
						</dd>
						<dd>
							<div class="inline-div">
								<input type="text" style="color: gray;" value="验证码"
									id="changeCode"
									onfocus="if(this.value==''||this.value=='验证码'){onfocu(this);};this.style.color='black';"
									onblur="if(this.value==''||this.value=='验证码'){this.value='验证码';this.style.color='gray';}">
							</div>
							<div class="inline-div">
								<img src="code.jsp?number=" +<%Math.random();%>" id="code"
									width="96px;" height="34px;" alt="" onclick="select()" />
							</div>
							<div class="inline-div">
								<a style="cursor: pointer; color: blue; text-decoration: underline;"
									onclick="select()" title="">看不清</a>
							</div>
						</dd>
						<dd class="msg-bar">
							<div class="msg single-line-div" id="codemsg"></div>
							<div class="msg single-line-div" id="msg"></div>
						</dd>
						
						<dd>
							<div class="inline-div" style="clear:both">
								<input disabled="disabled" id="submi" type="button" onclick="checkLogin();" value="登录" />
							</div>
							
						</dd>
						<dd class="btn-bar">
							<a href="#" style="cursor: pointer; color: blue;">修改密码</a>
						</dd>
					</dl>
				</div>
			</form>
		</div>
	</div>
			<div class="copy-right">
				<label class="label-text"> AMII All Rights Reserved.<br /> 2015  </label>
			</div>
</body>
</html>
