<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	if (request.getAttribute("ulis") == null) {
		if (request.getParameter("offset") != null) {
			response.sendRedirect("pufindbypageaction?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("pufindbypageaction");
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
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDel(userName) {
		var msg = "确定要删除 ["+userName+"] 用户吗？";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		window.location = 'pufindbypageaction?offset=' + offset;
	}
	
	/**
	 * 根据条件查询
	 */
	function query() {
			var quid = $("#uid").val();
			var quname = $("#uname").val();
			window.location='pugetByOptionsaction?quid='+ quid + '&quname='
					+ quname ;
	}
</script>
</head>

<body>
	<div style="margin-top:20px;width=100%;">
		<div id="query" class="toolbar" style="height: 30px;">
			<a onclick="addPanel1('addPuserInfo.jsp','增加用户信息')"
				class="easyui-linkbutton" data-options="iconCls:'icon-add'"
				style="margin-right: 15px;">新增</a> <a href="pufindbypageaction"
				class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
				style="margin-right: 15px;">刷新</a> <span
				style="margin:0px 5px 0px 0px;">用户ID</span><input
				class="easyui-textbox" type="text" name="userId" id="uid"
				data-options="height:26" value=""> <span
				style="margin:0px 5px 0px 15px;">用户名</span><input
				class="easyui-textbox" type="text" name="userName" id="uname"
				data-options="height:26" value=""> <input type="button"
				id="query" class="easyui-linkbutton" onclick="query()" value="查询"
				style="margin-left: 15px;">
		</div>

		<table class="easyui-datagrid" style="width:100%" singleSelect="true">
			<thead>
				<tr>
					<th data-options="field:'code'" width="">序号</th>
					<th data-options="field:'userId'">用户ID</th>
					<th data-options="field:'userName'">用户名</th>
					<th data-options="field:'createDt'">创建时间</th>
					<th data-options="field:'operation'">操作</th>
				</tr>
			</thead>
			<c:forEach items="${ulis}" var="u" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${u.userId}</td>
					<td>${u.userName}</td>
					
					
					<td>
						<fmt:setLocale value="zh_cn" />  
						<fmt:formatDate value="${u.createDt}" type="both" dateStyle="default" />
					</td>
					<td><a href="pugetDetailaction?userId=${u.userId}">详情</a>&nbsp;&nbsp;&nbsp;
						<a href="pudelUseraction?userId=${u.userId}"
						onclick="javascript:return sureDel('${u.userName}')">删除</a>&nbsp;&nbsp;&nbsp; <a
						href="pueditInfoaction?userId=${u.userId}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pager" id="pagebar">
			共${totalcount }条记录 转到&nbsp;<input value="${offset}" size="2"
				id="offset" />&nbsp;页/${totalpage }页
			<button class="easyui-linkbutton" width="20" onclick="reload()">跳转</button>
			<a href="pufindbypageaction?offset=1">第一页</a> <a
				href="pufindbypageaction?offset=${offset-1}">上一页</a> <a
				href="pufindbypageaction?offset=${offset+1}">下一页</a> <a
				href="pufindbypageaction?offset=${totalpage}">最后一页</a>
		</div>
	</div>
</body>
</html>
