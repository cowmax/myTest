<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
 *{margin: 0px; padding: 0px;}
</style>
  </head>
  
  <body>
    <div style="width: 100%; ">
		<p class="user-status">
			<strong>您好：${u.name } ${u.role.name} </strong> 
			<a style="color: #CC33CC;" href="javascript:exit()">注销</a>
		</p>
        <div class="title-pannel">
        	<label class="trade-mark">AMII</label>
        	<h1 class="system-name">BI系统管理后台</h1>
        </div>
		<script type="text/javascript">
        function exit(){
			location.href = 'index.jsp?op=logout'; // 跳转到前面，且注销
			}
		</script>
    </div>
  </body>
</html>
