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
			response.sendRedirect("gufindByPageaction?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("gufindByPageaction");
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
	function sureDel(groupId,groupName) {
		var msg = "确定要删除 ["+groupName+"] 用户组吗？";
		$.ajax({
			type : 'POST',
			url : 'gufindByGroupIdaction.action',
			data : {
				'gid' : groupId
			},
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					if (data == true) {
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">用户组已被引用，不允许删除！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : '',
								bottom : ''
							}
						});
					return false;
					} else if (confirm(msg) == true) {
						$.ajax({
						type : 'POST',
						url : 'pgdelGuoupaction.action',
						data : {
							'gname' : groupName
						},
						dataType : 'json',
						success : function(data) {
							if (data != null) {
								if (data == true) {
									$.messager.show({	
										msg : '删除用户组信息成功！',
										timeout : 800,
										showSpeed : 200,
										showType : 'show',
										style : {
											right : '',
											top : '',
											bottom : ''
										}
									});
									window.location = 'pggetPglistaction';
									return false;
								} else {
									$.messager.show({
										msg : "<div style='text-align:center;line-height:200px; height-200px;'>用户组信息被引用，删除用户组信息失败！</div>",
										timeout : 800,
										showSpeed : 600,
										showType : 'show',
										style : {
											right : '',
											top : '',
											bottom : '',
										}
									});
									return true;
								}
							}
						}

					});
					return true;
					} else {
						return false;
					}
				}
			}

		});
	}
	/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		window.location = 'gufindByPageaction?offset=' + offset;
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
		<div id="query" class="toolbar" style="height: 30px;">
			<a onclick="addPanel1('addPgroupUserInfo.jsp','增加用户权限信息')" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
			<a href="gufindByPageaction" class="easyui-linkbutton"
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
			<c:forEach items="${pgulis}" var="gu" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${gu.userId.userId}</td>
					<td>${gu.userId.userName}</td>
					<td>${gu.groupId.groupName}</td>
					<td>
						<a onclick="javascript:return sureDel('${g.groupId}','${g.groupName}')" style="text-decoration: underline;">删除</a>&nbsp;&nbsp;&nbsp; <a
						href="pgeditInfoaction?gname=${g.groupName}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pager" id="pagebar">
			共${totalcount }条记录 转到&nbsp;<input value="${offset}" size="2" id="offset" />&nbsp;页/${totalpage }页
			<button class="easyui-linkbutton" width="20" onclick="reload()">跳转</button>
			<a href="gufindByPageaction?offset=1">第一页</a> <a
				href="gufindByPageaction?offset=${offset-1}">上一页</a> <a
				href="gufindByPageaction?offset=${offset+1}">下一页</a> <a
				href="gufindByPageaction?offset=${totalpage}">最后一页</a>
		</div>
	</div>
</body>
</html>
