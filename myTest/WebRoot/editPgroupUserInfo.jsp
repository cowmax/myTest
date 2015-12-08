<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDel(groupName) {
		var msg = "确定要删除 ["+groupName+"] 用户组吗？";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
	function addGroupUser(userId){
		$("#msg").html("");
		var groupUser=$("#groupUsers").combobox("getValue");
		if(groupUser!="所有用户组"){
			window.location.href= 'groupUseraddGroupUserInfo.action?groupId='+groupUser+'&userId='+userId;
			return true;
		}else{
			$("#msg").html("*请选择要添加的用户组");
			return false;
		}
	}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel">
		<div>
			<h3 class="tab-subtitle">编辑用户 [ ${userId} ] 权限信息</h3>
		</div>
		
		<div style="width: 80%; margin: 20px 0px 20px 80px; float: left;" class="toolbar">
			<div style="margin: 20px 0px 0px 0px; height: 30px;">
				<select id="groupUsers" class="easyui-combobox" editable="false"
					name="pgu.roleId.roleId" style="width:148px;" panelHeight="100">
					<option value="所有用户组">所有用户组</option>
					<c:forEach items="${pglis}" var="group">
						<option value="${group.groupId}">${group.groupName}</option>
					</c:forEach>
				</select>&nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="button" value="添加" id="addGroupUser" onclick="return addGroupUser('${userId}');"  class="easyui-linkbutton">
				<span style="color: red;" id="msg"></span>
			</div>
			<table  class="easyui-datagrid" singleSelect="true" style="width: 300px;">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'groupName'">用户组名称</th>
						<th data-options="field:'roleName'">角色名称</th>
						<th data-options="field:'operation'">操作</th>
					</tr>
				</thead>
				<c:forEach items="${upglis}" var="egroup" varStatus="i">
					<tr>
						<td>${i.index+1 }</td>
						<td>${egroup.groupName}</td>
						<td>${egroup.roleId.roleName}</td>
						<td><a href="groupUserdelGroupUserInfo?userId=${userId}&groupId=${egroup.groupId}" onclick="javascript:return sureDel('${egroup.groupName}')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top:20px;">
				<input type="button" value="完成" onclick="removeCurrentPanel();" class="easyui-linkbutton">
			</div>
		</div>
	</div>
</body>
</html>