<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>My JSP 'da.jsp' starting page</title>

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
<script type="text/javascript"
	src="js/easyui/themes/easyui-lang-zh_CN.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function sureDel(tny) {
	
		var msg = "确定要删除  ["+tny+"] 吗？";
		if (confirm(msg) == true) {
			$.messager.show({
				msg : '删除成功！',
				showType : 'show',
				style : {
					right : '',
					top : document.body.scrollTop
							+ document.documentElement.scrollTop,
					bottom : ''
				}

			});
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据条件查询
	 */
	function query() {
		var tyna=$("#tyna").combobox("getValue");
		window.location = 'queryParaSysValueP.action?tyna=' + tyna;
	}
</script>
</head>
<%
	if (request.getAttribute("paraSysValuePList") == null) {
		if (request.getParameter("offset") != null) {
			response.sendRedirect("getParaSysValuePAll.action?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("getParaSysValuePAll.action");

		}
	}else{
		
	}
 %>

<body>
	<div class="mydatagrid" style="margin-top:20px;width=100%;">
		<div id="query" style="height: 30px;" class="toolbar">
			<a onclick="addPanel1('addParaSysValueP.jsp','增加计算参数')" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
			<a href="getParaSysValuePAll.action" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
			<span style="margin:0px 5px 0px 0px;">产品类目</span>
			<select id="tyna" class="easyui-combobox" name="paraSysValueP.tyna" style="width:150px;"panelHeight="100">
						<option value="">所有产品类目</option>
						<c:forEach  var="sv" items="${requestScope.tynalist }" >
							<option value="${sv}">${sv}</option>
						</c:forEach>
			</select>
			<input class="easyui-linkbutton"
				type="button" id="query" style="margin-left: 15px;"
				onclick="query()" value="查询">
		</div>
		<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
			<thead>
				<tr>
					<th data-options="field:'code'" width="">序号</th>
					<th data-options="field:'tyna'">产品类目</th>
					<th data-options="field:'off_day '">同款补单延期</th>
					<th data-options="field:'re_num '">预期补单次数</th>
					<th data-options="field:'owner_ratio'">自有数据比例</th>
					<th data-options="field:'sysDt'">数据记录时间</th>
					<th data-options="field:'sysUserId'">操作用户</th>
					<th data-options="field:'修改操作'">修改操作</th>
					<th data-options="field:'删除操作'">删除操作</th>
				</tr>
			</thead>
			<c:forEach items="${paraSysValuePList}" var="paraSysValueP" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td><c:out value="${paraSysValueP.tyna }" />
					</td>
					<td>
					<fmt:parseNumber value="${paraSysValueP.offDay }" type="number"  integerOnly="true"/>
					</td>
					<td>
					<fmt:parseNumber value="${paraSysValueP.reNum }" type="number"  integerOnly="true"/>
					</td>
					<td><c:out value="${paraSysValueP.ownerRatio }" />
					</td>
					<td>
					<fmt:setLocale value="zh_cn" />  
					<fmt:formatDate value="${paraSysValueP.sysDt }" type="both" dateStyle="default" />
					</td>
					<td><c:out value="${paraSysValueP.sysUserId }" />
					</td>
					<td><a
						href="getParaSysValuePId.action?tyna=${paraSysValueP.tyna }">修改</a>
					</td>
					<td><a
						href="delParaSysValueP.action?tyna=${paraSysValueP.tyna }"
						onclick="javascript:return sureDel('${paraSysValueP.tyna }')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pager">
			共${rows }条记录&nbsp;&nbsp; 转到<input 
				value="${offset}" size="2" id="offset" />页/共${page }页<button class="easyui-linkbutton"   onclick="reload()">跳转</button>
				 <a class="easyui-linkbutton"
				href="getParaSysValuePAll.action?offset=1">第一页</a> <a class="easyui-linkbutton"
				href="getParaSysValuePAll.action?offset=${offset-1}">上一页</a> <a class="easyui-linkbutton"
				href="getParaSysValuePAll.action?offset=${offset+1}">下一页</a> <a class="easyui-linkbutton"
				href="getParaSysValuePAll.action?offset=${page}">最后一页</a> 
		</div>
	</div>

</body>
</html>
