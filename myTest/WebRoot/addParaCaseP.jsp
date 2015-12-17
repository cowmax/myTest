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
<script type="text/javascript" src="script/addParaCaseP.js"></script>
<style type="text/css">
.form-table tr td:first-child {
	width: 8em;
}
</style>
</head>
<%
	if(request.getAttribute("ListStore")==null){
		response.sendRedirect("paraCasePallStorecode.action");
	}
 %>

<body>
	<div id="mydiv" class="easyui-panel" align="center" >
		<form id="saveform" action="paraCasePsaveParaCaseP.action" method="post" enctype="multipart/form-data">
			<div>
				<h3 class="tab-subtitle">添加营销活动类型</h3>
			</div>
			<table class="form-table">
				<tr>
					<td>活动编码</td>
					<td><input class="easyui-textbox" id="caseCode" type="text"
						name="paraCaseP.caseCode" value="" /><span
						style="margin-left: 20px" id="span_caseCode"></span></td>
				</tr>
				<tr>
					<td>活动类型名称</td>
					<td><input class="easyui-textbox" id="caseName" type="text"
						name="paraCaseP.caseName" value="" /><span
						style="margin-left: 20px" id="span_caseName"></span></td>
				</tr>
				<tr>
					<td>渠道/店铺</td>
				<td>
					<select id="chalCd" class="easyui-combobox" name="paraCaseP.chalCd.code" style="width:148px;"panelHeight="100" editable="false" 
					>
						<option value="所有渠道/店铺">渠道/店铺</option>
						<c:forEach  var="sv" items="${ListStore }" >
							<option value="${sv.code}">${sv.name}</option>
						</c:forEach>
					</select><span
						style="margin-left: 20px" id="span_chalCd"></span>
					</td>
				</tr>
				<tr>
					<td>活动级别</td>
					<td>
					<select id="caseLevel" class="easyui-combobox" name="paraCaseP.caseLevel" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="活动级别">活动级别</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select><span
						style="margin-left: 20px" id="span_caseLevel"></span>
					</td>
				</tr>
				<tr>
					<td>前向影响时间</td>
					<td><input class="easyui-textbox" id="preNum" type="text"
						name="paraCaseP.preNum" value="10" /><span
						style="margin-left: 20px" id="span_preNum"></span></td>
				</tr>
				<tr>
					<td>品牌</td>
					<td>
					<select id="brde" class="easyui-combobox" name="paraCaseP.brde" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="活动的品牌">活动品牌</option>
						<option value="A">AMII</option>
						<option value="R">Redefined</option>
					</select><span
						style="margin-left: 20px" id="span_brde"></span>
					</td>
				</tr>
				<tr>
					<td>选款数量</td>
					<td><input class="easyui-textbox" id="num" type="text"
						name="paraCaseP.num" value="" /><span
						style="margin-left: 20px" id="span_num"></span></td>
				</tr>
				<tr>
					<td>选款粒度</td>
					<td>
					<select id="CType" class="easyui-combobox" name="paraCaseP.CType" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="选款粒度">选款粒度</option>
						<option value="P">按产品（款）</option>
						<option value="S">按产品 SKU </option>
					</select><span
						style="margin-left: 20px" id="span_CType"></span>
					</td>
				</tr>
				<tr>
				<td></td>
					<td style="padding-top: 20px;"><input id="save"
						class="easyui-linkbutton" type="button" value="保存" />&nbsp;&nbsp;&nbsp;<input
						class="easyui-linkbutton" type="button"
						onclick="removeCurrentPanel()" value="返回" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
