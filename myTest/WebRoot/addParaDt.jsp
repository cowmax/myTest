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

<title></title>

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
<script type="text/javascript" src="script/addParaDt.js"></script>
<script type="text/javascript">

</script>
</head>
<%
	if(request.getAttribute("listCaseName")==null){
		response.sendRedirect("paraCaseDtgetAllName.action");
	}
 %>

<body>
	<div id="mydiv" class="easyui-panel" align="center" >
		<form id="saveform" action="paraCaseDtsavePCD.action" method="post" >
			<div>
				<h3 class="tab-subtitle">添加营销活动实例</h3>
			</div>
			<table class="form-table">
				<tr>
					<td>活动名称</td>
					<td>
					<select id="caseName" class="easyui-combobox" name="paraCaseP.caseName" style="width:148px;"panelHeight="100" editable="false" >
						<option value="所有活动类型">所有类型</option>
						<c:forEach  var="sv" items="${listCaseName }" >
							<option value="${sv.caseName}">${sv.caseName}</option>
						</c:forEach>
					</select><span style="margin-left: 20px" id="span_caseName"></span>
					</td>
				</tr>
				<tr>
					<td>活动编码</td>
					<td><input class="easyui-textbox" id="caseCode" disabled="disabled"  type="text"
						name="paraCaseP.caseCode" value="" /></td>
				</tr>
				<tr>
					<td>活动状态</td>
					<td><input class="easyui-textbox" id="status" disabled="disabled" type="text"
						name="paraDt.status" value="待审核" /></td>
				</tr>
				<tr>
					<td>选款维度</td>
					<td><input class="easyui-textbox" id="CType"  disabled="disabled"  type="text"
						name="paraCaseP.CType" value="" /></td>
				</tr>
				<tr>
					<td>开始时间</td>
					<td><input  class="easyui-datetimebox"  id="caseSt" type="text" editable="false"
						name="paraDt.caseSt" value="" data-options="onChange:function(){onSelectStTime()}"/><span
						style="margin-left: 20px" id="span_caseSt"></span></td>
				</tr>
				<tr>
					<td>结束时间</td>
				<td>
					<input class="easyui-datetimebox" id="caseEt" type="text"  editable="false"
						name="paraDt.caseEt" value=""  data-options="onChange:function(){onSelectEtTime()}"/>
					<span
						style="margin-left: 20px" id="span_caseEt"></span>
					</td>
				</tr>
				<tr>
					<td>新款占比</td>
					<td>
					<input class="easyui-textbox" id="ratioNew" type="text"
						name="paraDt.ratioNew" value=""  data-options="onChange:function(){ratioNewWay()}" />
					<span style="margin-left: 20px" id="span_ratioNew"></span>
					</td>
				</tr>
				<tr>
					<td>旧款占比</td>
					<td><input class="easyui-textbox" id="ratioRetro"  disabled="disabled" type="text"
						 value="" /></td>
				</tr>
				<tr>
					<td>选款数量</td>
					<td><input class="easyui-textbox" id="num" type="text"
						name="paraDt.num" value="10" /><span
						style="margin-left: 20px" id="span_preNum"></span></td>
				</tr>
				<tr>
					<td>活动描述</td>
					<td><input class="easyui-textbox" id="caseDesc" type="text"
						name="paraDt.caseDesc" value="" style="height: 80px;" data-options="multiline:true" />
						<span style="margin-left: 20px" id="span_caseDesc"></span></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left: 100px;"><input id="save"
						class="easyui-linkbutton" type="button" value="保存" />&nbsp;&nbsp;&nbsp;<input
						class="easyui-linkbutton" type="button"
						onclick="removeCurrentPanel()" value="返回" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
