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
	/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'pcashowPCaseAudit.action?offset=' + idx;
	}

	/**
	* 表格字段格式化函数
	**/
	function brdeFieldFmtr(val, row){
	val = val.trim();
	switch(val.toUpperCase()){
		case 'A':
			val = "AMII";
		break;
		case 'R':
			val= "Redefine";
		break;
	}
	return val;
	}
	
	function numFieldFmtr(val, row){
		val = val.trim();
		if (val.length == 0){
			val = "全店";
		}
		return val;
	}
	
	function statusFieldFmtr(val, row){
		val = val.trim();
		switch(val){
			case '0':
				val = "已删除";
			break;
			case '2':
				val= "待选款";
			break;
			case '3':
				val= "已采用";
			break;
			case '5':
				val= "待审核";
			break;
		}
		return val;
	}
	
	
	
	/**
	 * 根据条件查询
	 */
	function query() {
		var caseName = $("#caseName").combobox("getValue");
		var brde = $("#brde").combobox("getValue");
		var caseSt = $("#caseSt").datetimebox('getValue');
		var caseEt = $("#caseEt").datetimebox('getValue');
		var caseDesc = $("#caseDesc").val();
		
		window.location = 'pcashowPCaseAudit.action?caseName='
						+caseName+'&brde='+brde+'&caseSt='+caseSt+
						'&caseEt='+caseEt+'&caseDesc='+caseDesc;
	}
	

	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var caseName = $("#caseName").combobox("getValue");
		var brde = $("#brde").combobox("getValue");
		var caseSt = $("#caseSt").datetimebox('getValue');
		var caseEt = $("#caseEt").datetimebox('getValue');
		var caseDesc = $("#caseDesc").val();
		
		window.location = 'pcashowPCaseAudit.action?caseName='
						+caseName+'&brde='+brde+'&caseSt='+caseSt+
						'&caseEt='+caseEt+'&caseDesc='+caseDesc
						+ '&offset=' + offset;
	}

	
</script>
</head>
<%
	if (request.getAttribute("refactorParaDtList") == null){
		if (request.getParameter("offset") != null) {
			response.sendRedirect("pcashowPCaseAudit.action?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("pcashowPCaseAudit.action");

		}
	}
 %>

<body>
	<div class="mydatagrid" style="margin-top:20px;width=100%;">
		<div id="query" style="height: 30px;" class="toolbar">
			<div style="float: left;">
				<a onclick=" " class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
				<a href="pcashowPCaseAudit.action" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
			</div>
			<div style="float: left; margin-bottom: 10px;">
				<span style="margin-right: 10px;">活动类型</span>
				<select id="caseName" class="easyui-combobox" style="width:148px;height:26px"panelHeight="100" editable="false" >
					<option value="">所有活动类型</option>
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
				<select id="brde" class="easyui-combobox" style="width:148px;height:26px"panelHeight="100"; editable="false">
					<option value="" <c:if test="${brde==''}">selected="true"</c:if>>活动的品牌</option>
					<option value="A" <c:if test="${brde=='A'}"> selected="true"</c:if>> AMII </option>
					<option value="R" <c:if test="${brde=='R'}">selected="true"</c:if>> Redefined</option>
				</select>
			</div>
			<div style=" float: left; margin-bottom: 10px;">
				<input class="easyui-linkbutton"
					type="button" id="query" style="margin-left: 15px;"
					onclick="query()" value="查询">
				<a class="easyui-linkbutton" onclick="showExpert()" style="margin-left: 15px;">高级</a>
			</div>
			<div id="expertQuery" style=" float: left; margin-bottom: 10px;  display: none;">
				<span style="margin-right: 10px;">活动开始时间</span>
				<input  class="easyui-datetimebox"  id="caseSt" type="text" editable="false" value="${caseSt}" />
				<span style="padding: 10px;">活动结束时间</span>
				<input class="easyui-datetimebox" id="caseEt" type="text"  editable="false" value="${caseEt}" />
				<span style="padding: 10px;">活动说明</span>
				<input class="easyui-textbox" id="caseDesc" type="text" value="${caseDesc}" />
			</div>
			
		</div>
		
		<div style="width:100%; float: left;">
			<table  id="dg" class="easyui-datagrid" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'caseId'">活动ID</th>
						<th data-options="field:'caseName '">活动名称</th>
						<th data-options="field:'a.case_st'">活动开始时间</th>
						<th data-options="field:'a.case_et'">活动结束时间</th>
						<th data-options="field:'caseDesc'">活动说明</th>
						<th data-options="field:'caseLevel'">活动级别</th>
						<th data-options="field:'preNum'">参考周期</th>
						<th data-options="field:'ratioNew'" >是新款占比</th>
						<th data-options="field:'num'"  formatter="numFieldFmtr">参与款数</th>
						<th data-options="field:'brde'" formatter="brdeFieldFmtr">品牌</th>
						<th data-options="field:'name'">渠道</th>
						<th data-options="field:'status'"formatter="statusFieldFmtr">活动状态</th>
						<th data-options="field:'sysDt'">修改时间</th>
						<th data-options="field:'sysUserId'">操作用户</th>
						<th data-options="field:'操作'">操作</th>
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
							<c:out value="${refactorParaDt.caseLevel }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.preNum }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.ratioNew }" />
						</td>
						<td>
							<c:out value="${refactorParaDt.num }" />
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
						<td>
							<fmt:setLocale value="zh_cn" />  
							<fmt:formatDate value="${refactorParaDt.sysDt }" type="both" dateStyle="default" />
						</td>
						<td>
							<c:out value="${refactorParaDt.sysUserId }" />
						</td>
						<td><a onclick="">审核</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${rows }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${page }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
				<a onclick="turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
