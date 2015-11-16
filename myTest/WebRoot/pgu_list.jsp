<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%
	if (request.getAttribute("pgulis") == null) {
		if (request.getParameter("offset") != null) {
			response.sendRedirect("gufindByOptionsaction?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("gufindByOptionsaction");
		}
	}
%>


<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="<.css">
	-->
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

</style>
<script type="text/javascript">
	/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'gufindByOptionsaction?offset=' + idx;
	}
	/**
	 * 根据条件查询
	 */
	function query() {
			var userId = $("#userId").val();
			var userName = $("#userName").val();
			groupInfo=$("#groupInfo").combobox("getValue");
			window.location='gufindByOptionsaction?userId='+ userId + '&userName='
					+ userName +'&groupId='+groupInfo;
	}
</script>
</head>

<body>
	<div style="margin-top:20px;width=100%;">
		<div id="options" class="toolbar" style="height: 30px;">
			<a href="gufindByOptionsaction" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
			<span style="margin:0px 5px 0px 0px;">用户ID</span><input
				class="easyui-textbox" type="text" name="userId" id="userId"
				data-options="height:26" value=""> <span
				style="margin:0px 5px 0px 15px;">用户名称</span><input
				class="easyui-textbox" type="text" name="userName" id="userName"
				data-options="height:26" value="">
				<span style="margin:0px 5px 0px 15px;">用户组名称</span>
				<select id="groupInfo" class="easyui-combobox" editable="false" name="pgu.groupId.groupId" style="width:150px; margin:0px 5px 0px 15px;" panelHeight="100">
					<option value="-1">全部</option>
						<c:forEach  items="${pglis}" var="group">
								<option value="${group.groupId}">${group.groupName}</option>
						</c:forEach>
				</select> 
				 <input type="button"
				id="query" class="easyui-linkbutton" onclick="query()" value="查询"
				style="margin-left: 15px;">
		</div>

		<table class="easyui-datagrid" style="width:100%" singleSelect="true">
			<thead>
				<tr>
					<th data-options="field:'code'" width="">序号</th>
					<th data-options="field:'userId'">用户ID</th>
					<th data-options="field:'userName'">用户名称</th>
					<th data-options="field:'groupName'">用户组名称</th>
					<th data-options="field:'operation'">操作</th>
				</tr>
			</thead>
			<c:choose>
				<c:when test="${pgulis.size()<=0}">
					<tr>
						<td colspan="5" style="text-align: center;">
							<span style="color: grey;">没有找到相关数据！</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${pgulis}" var="gu" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${gu.userId.userId}</td>
							<td>${gu.userId.userName}</td>
							<td>${gu.groupId.groupName}</td>
							<td>
								<a onclick="addPanel1('pggetPguInfoaction?userId=${gu.userId.userId}','设置权限')"  style="text-decoration: underline;">设置权限</a>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="pager" id="pagebar">
			共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
			<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
			<a href="gufindByOptionsaction?offset=0">&lt;&lt; 第一页</a> <a
				href="gufindByOptionsaction?offset=${offset-1}">&lt; 上一页</a> <a
				href="gufindByOptionsaction?offset=${offset+1}">下一页 &gt;</a> <a
				href="gufindByOptionsaction?offset=${totalpage-1}">最后一页 &gt;&gt;</a>
		</div>
	</div>
</body>
</html>
