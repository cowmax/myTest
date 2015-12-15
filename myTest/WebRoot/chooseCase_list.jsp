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
<link href="css/style.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript">
	
</script>
</head>
<%
	if (request.getAttribute("refactorParaDtList") == null){
		if (request.getParameter("offset") != null) {
			response.sendRedirect("paraCaseDtchooseParaDt.action?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("paraCaseDtchooseParaDt.action");

		}
	}
 %>
<body>
	<div class="mydatagrid" style="margin-top:20px;padding: 0px 10px;">
		<div id="query" style="height: 30px;" class="toolbar">
			<div style="float: left; margin-bottom: 10px;">
				<span style="margin-right: 10px;">活动类型</span>
				<select id="bp_caseName" class="easyui-combobox" style="width:148px;height:26px"panelHeight="100" editable="false" >
					<option value="">所有类型</option>
					<c:forEach  var="sv" items="${listCaseName }" >
						<c:choose>
							<c:when test="${sv.caseName == caseName}">
								<option value="${sv.caseName}" selected="true">${sv.caseName}</option>
							</c:when><c:otherwise>
								<option value="${sv.caseName}">${sv.caseName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<span style="padding: 10px;">品牌</span>
				<select id="bp_brde" class="easyui-combobox" style="width:148px;height:26px"panelHeight="100"; editable="false">
					<option value="" <c:if test="${brde==''}">selected="true"</c:if>>活动的品牌</option>
					<option value="A" <c:if test="${brde=='A'}"> selected="true"</c:if>> AMII </option>
					<option value="R" <c:if test="${brde=='R'}">selected="true"</c:if>> Redefined</option>
				</select>
				<span style="padding: 10px;">活动说明</span>
				<input class="easyui-textbox" id="bp_caseDesc" type="text" value="${caseDesc}" />
			</div>
			<div style=" float: left; margin-bottom: 10px;">
				<input class="easyui-linkbutton"
					type="button" id="query" style="margin-left: 15px;"
					onclick="bp_query()" value="查询">
			</div>
			<div id="expertQuery" style=" float: left; margin-bottom: 10px;">
				<span style="margin-right: 10px;">开始时间</span>
				<input  class="easyui-datetimebox"  id="bp_caseSt" type="text" editable="false" value="${caseSt}" />
				<span style="padding: 10px;">结束时间</span>
				<input class="easyui-datetimebox" id="bp_caseEt" type="text"  editable="false" value="${caseEt}" />
				
			</div>
		</div>
		
		<div style="width:100%; float: left;">
			<table id="caseList" class="easyui-datagrid" singleSelect="true" data-options="onClickRow:chooseCaseId" >
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'caseId'">活动ID</th>
						<th data-options="field:'caseName'">活动名称</th>
						<th data-options="field:'a.case_st'" sortable="true">开始时间</th>
						<th data-options="field:'a.case_et'" sortable="true">结束时间</th>
						<th data-options="field:'caseDesc'">活动说明</th>
						<th data-options="field:'brde'" formatter="brdeFieldFmtr">品牌</th>
						<th data-options="field:'name'">渠道</th>
						<th data-options="field:'status'"formatter="statusFieldFmtr">活动状态</th>
					</tr>
				</thead>
				<c:forEach items="${refactorParaDtList}" var="refactorParaDt" varStatus="i">
					<tr>
						<td>${i.index+1 }</td>
						<td>
							<c:out value="${refactorParaDt.caseId }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.caseName }" />
						</td>
						<td>
						<fmt:setLocale value="zh_cn" />  
						<fmt:formatDate value="${refactorParaDt.caseSt}" type="both" dateStyle="default" />
						</td>
						<td>
						<fmt:setLocale value="zh_cn" />  
						<fmt:formatDate value="${refactorParaDt.caseEt }" type="both" dateStyle="default" />
						</td>
						<td>
							<c:out value="${refactorParaDt.caseDesc }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.brde }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.name }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.status }" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${rows }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="bp_offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${page }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="bp_reload()">跳转</button>
				<a onclick="bp_turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="bp_turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="bp_turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="bp_turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
