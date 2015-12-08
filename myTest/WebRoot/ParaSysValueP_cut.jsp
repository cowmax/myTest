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

<script type="text/javascript" src="script/ParaSysValueP_cut.js">
</script>
</head>
<body>
	<div id="mydiv" class="easyui-panel" title="修改计算参数">
		<form id="saveform" action="paraSysValuePupdateParaSysValueP.action" method="post"
			id="changeParaSordataForm">
			<table class="form-table">
				<tr>
					<td>产品类目</td>
					<td><input class="easyui-textbox" id="tyna" disabled="disabled" type="text" name="paraSysValueP.tyna"
						value="${paraSysValueP.tyna}" /></td>
				</tr>
				<tr>
					<td>同款补单延期</td>
					<td><input class="easyui-textbox" id="offDay" type="text"
						name="paraSysValueP.offDay" value="${paraSysValueP.offDay}" /><span style="margin-left: 20px"
						id="span_offDay"></span></td></td>
				</tr>
				<tr>
					<td>预期补单次数</td>
					<td><input class="easyui-textbox" id="reNum"  type="text"
						name="paraSysValueP.reNum" value="${paraSysValueP.reNum}" /><span style="margin-left: 20px"
						id="span_reNum"></span></td>
				</tr>
				<tr>
					<td>自有数据比例</td>
					<td><input class="easyui-textbox" id="ownerRatio"  type="text"
						name="paraSysValueP.ownerRatio" value="${paraSysValueP.ownerRatio }" /><span style="margin-left: 20px"
						id="span_owner"></span></td>
				</tr>
				<tr>
					<td>数据记录时间</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraSysValueP.sysDt " value="${paraSysValueP.sysDt }" /></td>
				</tr>
				<tr>
					<td>操作用户</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraSysValueP.sysUserId" value="${paraSysValueP.sysUserId }" /></td>
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
