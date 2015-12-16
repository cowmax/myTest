<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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

<title>My JSP 'ParaSordata_cut.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>
<script type="text/javascript" src="js/easyui/themes/easyui-lang-zh_CN.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="script/ParaSordata_cut.js">
</script>
</head>
<body>
	<div id="mydiv" class="easyui-panel" title="修改产品">
		<form id="saveform" action="paraSordataupdateParaSordata.action" method="post"
			id="changeParaSordataForm">
			<div class="tab-subtitle"><h3>修改产品</h3></div>
			<table class="form-table">
				<tr>
					<td>产品类目</td>
					<td><input class="easyui-textbox" id="tyna" disabled="disabled" type="text" name="paraSordata.tyna"
						value="${ParaSordata.id.tyna}" /></td>
				</tr>
				<tr>
					<td>参数类型</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraSordata.valueType" value="${ParaSardataType.valTypeName}" /></td>
				</tr>
				<tr>
					<td>同值比例</td>
					<td><input class="easyui-textbox" id="valueRatio"  type="text"
						name="paraSordata.valueRatio" value="${ParaSordata.valueRatio}" /><span style="margin-left: 20px"
						id="span_ratio"></span></td>
				</tr>
				<tr>
					<td>生产周期最小值</td>
					<td><input class="easyui-textbox" id="valueMin"  type="text"
						name="paraSordata.valueMin" value="${ParaSordata.valueMin }" /><span style="margin-left: 20px"
						id="span_min"></span></td>
				</tr>
				<tr>
					<td>生产周期最大值</td>
					<td><input class="easyui-textbox" id="valueMax"  type="text"
						name="paraSordata.valueMax" value="${ParaSordata.valueMax }" /><span style="margin-left: 20px"
						id="span_max"></span></td>
				</tr>
				<tr>
					<td>值的描述</td>
					<td><input class="easyui-textbox" id="valueDesc" type="text"
						name="paraSordata.valueDesc" value="${ParaSordata.valueDesc }" /><span style="margin-left: 20px"
						id="span_desc"></span></td>
				</tr>
				<tr>
					<td>生成时间</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraSordata.sysDt" value="${ParaSordata.sysDt }" /></td>
				</tr>
				<tr>
					<td>操作用户</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="ParaSordata.sysUserId" value="${ParaSordata.sysUserId }" /></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left: 100px;"><input id="save" class="easyui-linkbutton" type="button" value="保存" />&nbsp;&nbsp;&nbsp;
						<input class="easyui-linkbutton" type="button" onclick="javascript:history.go(-1)"
						value="返回" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
