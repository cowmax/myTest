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

<title>My JSP 'addParaSordata.jsp' starting page</title>

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
<script type="text/javascript" src="script/addParaSordata.js"></script>
</head>
<%
	if(request.getAttribute("tynalist")==null&&request.getAttribute("paraSardataTypeList")==null){
		response.sendRedirect("paraSordatacomboxLoad.action");
	}
 %>
<body>
	<div id="mydiv" class="easyui-panel" align="center" >
		<form id="saveform" action="paraSordatasaveParaSordata.action" method="post">
			<div>
				<h3 class="tab-subtitle">添加生产参数</h3>
			</div>
			<table class="form-table">
				<tr>
					<td>产品类目</td>
					<td>
					<select id="tyna" class="easyui-combobox" name="paraSordata.tyna" style="width:148px;"panelHeight="100" editable="false" 
					>
						<option value="所有类目">所有产品类目</option>
						<c:forEach  var="sv" items="${requestScope.tynalist }" >
							<option value="${sv}">${sv}</option>
						</c:forEach>
					</select><span
						style="margin-left: 20px" id="span_tyna"></span>
					</td>
					
				</tr>
				<tr>
					<td>参数类型</td>
					<td>
					<select id="typd" class="easyui-combobox" name="paraSordata.valueType" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="所有参数">所有参数类型</option>
						<c:forEach  var="ptlist" items="${paraSardataTypeList }" >
							<option value="${ptlist.valTypeId}" >${ptlist.valTypeName}</option>
						</c:forEach>
					</select><span
						style="margin-left: 20px" id="span_typd"></span>
					</td>
				</tr>
				<tr>
					<td>同值比例</td>
					<td><input class="easyui-textbox" id="valueRatio" type="text"
						name="paraSordata.valueRatio" value="" /><span
						style="margin-left: 20px" id="span_Ratio"></span></td>
				</tr>
				<tr>
					<td>生产周期最小值</td>
					<td><input class="easyui-textbox" id="valueMin" type="text"
						name="paraSordata.valueMin" value="" /><span
						style="margin-left: 20px" id="span_min"></span></td>
				</tr>
				<tr>
					<td>生产周期最大值</td>
					<td><input class="easyui-textbox" id="valueMax" type="text"
						name="paraSordata.valueMax" value="" /><span
						style="margin-left: 20px" id="span_max"></span></td>
				</tr>
				<tr>
					<td>值的描述</td>
					<td><input class="easyui-textbox"  type="text"
						name="paraSordata.valueDesc" value="" style="height: 80px;" data-options="multiline:true" /></td>
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
