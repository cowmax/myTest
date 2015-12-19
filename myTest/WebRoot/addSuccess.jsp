<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
	
<script type="text/javascript" src="script/common.js"></script>
<style>
.info-box{
	border:1px solid #95B8E7;
	display:block;
	top:8em;
	left:16em;
	position:relative;

}
.info-box .title{
	display:block;
	height:1.5em;
	padding:0.3em 0.5em;
	line-height: 1.5em;
	background:linear-gradient(to bottom,#EFF5FF 0,#E0ECFF 100%);
	border-bottom:1px solid #95B8E7;
	font-weight:bold;
}
.info-box .content{
	text-align:center;
	padding:3em 1em 1em 1em;
}
</style>
<script type="text/javascript">
function confirm(){
	window.location = 'pusergetByOptions.action';
}
</script>
<link rel="stylesheet" href="css/style.css" type="text/css"></link></head>

<body>
 	<div style="width: 100%">
		<div class="info-box" style="width:25em">
			<div class="title">操作结果</div>
			<div class="content">
				<div>保存${sessionScope.msg}成功！</div>
				<div style="margin-top: 2em;" class="toolbar">
					<input class="l-btn" type="button" onclick="confirm()" value="确定" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
