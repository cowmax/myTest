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
    
    <title>My JSP 'top.jsp' starting page</title>
    
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
    <div style="width: 800px; height:10%; center; margin: auto;">
        <div style="width: 100%;"><h1 style="margin:20px 60px 10px 280px; font-size: 30px;">BI后台数据管理系统</h1></div>
        <p style="float: right; font-size: 14px; position: absolute; margin-top: -10px; margin-left: 580px;"><strong>当前用户：${u.name }(${u.role.name})</strong>&nbsp;&nbsp;<a style="color: #CC33CC;" href="javascript:exit()">退出系统</a></p>
        <script type="text/javascript">
        function exit(){
        	if(confirm("确认退出系统？")){
        		location.href='';
       		}
        }
      </script>
    </div>
  </body>
</html>
