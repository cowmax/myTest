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
			<c:choose>
				<c:when test="${pu.userId!=null}">
					<strong><span>您好：${pu.userId}</span></strong> 
				</c:when>
				<c:otherwise>
					<strong><span>未登录用户</span></strong> 
				</c:otherwise>
			</c:choose>
			<b>|</b>
			<c:choose>
				<c:when test="${pu.userId!=null}">
					<span class="easyui-menubutton" data-options="menu:'#mm'">注销</span>
					<div id="mm">
						<div onclick="window.location.href='puserexit.action';">注销</div>
						<div onclick="$('#win').window('open')">修改密码</div>
					</div>
				</c:when>
				<c:otherwise>
					<a id="login" href="index.jsp">登录</a>
				</c:otherwise>
			</c:choose>
			
		</p>
		<div class="title-pannel">
			<label class="trade-mark">AMII</label>
			<h1 class="system-name">BI系统管理后台</h1>
		</div>
		
	</div>
</body>
</html>
