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
	if (request.getAttribute("prlist") == null) {
		if (request.getParameter("offset") != null) {
			response.sendRedirect("prolegetRolisByOptions?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("prolegetRolisByOptions");
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
	function sureDel(roleId,roleName) {
		var msg = "确定要删除 ["+roleName+"] 角色吗？";
		$.ajax({
				type : 'POST',
				url : 'pgroupfindByRoleId.action',
				data : {
					'roleId' : roleId
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							$.messager.show({
									msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">角色已被引用，不允许删除！</div></div>',
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
							url : 'proledelRole.action',
							data : {
								'rname' : roleName
							},
							dataType : 'json',
							success : function(data) {
								if (data != null) {
									if (data == true) {
										$.messager.show({	
											msg : '删除角色信息成功！',
											timeout : 800,
											showSpeed : 200,
											showType : 'show',
											style : {
												right : '',
												top : '',
												bottom : ''
											}
										});
										window.location = 'prolegetRolisByOptions';
										return false;
									} else {
										$.messager.show({
											msg : '角色信息被引用，删除角色信息失败！',
											timeout : 800,
											showSpeed : 200,
											showType : 'show',
											style : {
												right : '',
												top : '',
												bottom : ''
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
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'prolegetRolisByOptions?offset=' + idx;
	}
	
	/**
	 * 根据条件查询
	 */
	function query() {
			var rname = $("#rname").val();
			var rdesc = $("#rdesc").val();
			window.location='prolegetRolisByOptions?rname='+ rname + '&rdesc='
					+ rdesc ;
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var rname = $("#rname").val();
		var rdesc = $("#rdesc").val();
		
		window.location='prolegetRolisByOptions?rname='+ rname + '&rdesc='+rdesc + '&offset=' + offset;
	}
</script>
</head>

<body>
	<div style="margin-top:20px;width=100%;">
		<div id="options" class="toolbar" style="height: 30px;">
			<a onclick="addPanel1('addProleInfo.jsp','增加角色信息')" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
			<a href="prolegetRolisByOptions" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
			<span style="margin:0px 5px 0px 0px;">角色名称</span><input
				class="easyui-textbox" type="text" name="rname" id="rname"
				data-options="height:26" value="${rname}"> <span
				style="margin:0px 5px 0px 15px;">角色描述</span><input
				class="easyui-textbox" type="text" name="rdesc" id="rdesc"
				data-options="height:26" value="${rdesc}"> <input type="button"
				id="query" class="easyui-linkbutton" onclick="query()" value="查询"
				style="margin-left: 15px;">
		</div>

		<table class="easyui-datagrid" style="width:100%" singleSelect="true">
			<thead>
				<tr>
					<th data-options="field:'code'" width="">序号</th>
					<th data-options="field:'roleId'">角色ID</th>
					<th data-options="field:'roleName'">角色名称</th>
					<th data-options="field:'roleDesc'">角色描述</th>
					<th data-options="field:'operation'">操作</th>
				</tr>
			</thead>
			<c:forEach items="${prlist}" var="r" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${r.roleId}</td>
					<td>${r.roleName}</td>
					<td>${r.roleDesc}</td>
					<td>
						<a onclick="javascript:return sureDel('${r.roleId}','${r.roleName}')"  style="text-decoration: underline;">删除</a>&nbsp;&nbsp;&nbsp; <a
						href="proleeditInfo?rname=${r.roleName}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pager" id="pagebar">
			共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
			<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
			<a onclick="turnPage(0)">&lt;&lt; 第一页</a> <a
				onclick="turnPage(${offset-1})">&lt; 上一页</a> <a
				onclick="turnPage(${offset+1})">下一页 &gt;</a> <a
				onclick="turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
		</div>
	</div>
</body>
</html>
