<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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

<title>My JSP 'ParaDt_cut.jsp' starting page</title>

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
<script type="text/javascript" src="script/ParaDt_cut.js"></script>
</head>
<body >
	<div id="mydiv" class="easyui-panel" title="修改活动实例的基础信息">
		<form id="saveform" action="paraCaseDtupdateParaDt.action" method="post">
			<table class="form-table" >
				<tr>
					<td>活动名称</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraDt.caseName " value="${paraDt.caseName }" /></td>
				</tr>
				<tr>
					<td>活动编码</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.caseCode " value="${paraCaseP.caseCode } " /></td>
				</tr>
				<tr>
					<td>活动状态</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.sysDt " value="待处理" /></td>
				</tr>
				<tr>
					<td>选款维度</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.CType "
						<c:choose>
							<c:when test="${paraCaseP.CType=='P'}">value='按<款>' </c:when>
							<c:otherwise>value='按SKU' </c:otherwise>
						</c:choose> />
					</td>
				</tr>
				<tr>
					<td>活动描述</td>
					<td><input class="easyui-textbox" id="caseDesc" type="text"
						name="paraDt.caseDesc" value="${paraDt.caseDesc }" />
						<span style="margin-left: 20px" id="span_caseDesc"></span>
					</td>
				</tr>
				<tr>
					<td>开始时间</td>
					<td><input  class="easyui-datetimebox"  id="caseSt" type="text" editable="false"
						name="paraDt.caseSt" value="${paraDt.caseSt}" data-options="onChange:function(){onSelectStTime()}"/>
						<span style="margin-left: 20px" id="span_caseSt"></span>
					</td>
				</tr>
				<tr>
					<td>结束时间</td>
					<td>
					<input class="easyui-datetimebox" id="caseEt" type="text"  editable="false"
						name="paraDt.caseEt" value="${paraDt.caseEt }"  data-options="onChange:function(){onSelectEtTime()}"/>
					<span style="margin-left: 20px" id="span_caseEt"></span>
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
					<td>缺省数量</td>
					<td>
					<input class="easyui-textbox" id="defaultNum"  disabled="disabled"  type="text" name="paraCaseP.num"
					     value="${paraCaseP.num }" />
					</td>
				</tr>
				<tr>
					<td>选款数量</td>
					<td>
					<input class="easyui-textbox" id="num" type="text"
						name="paraDt.num" value="10" />
					<span style="margin-left: 20px" id="span_preNum"></span>
					</td>
				</tr>
				<tr>
					<td>更新时间</td>
					<td>
					<input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.sysDt " value="${paraCaseP.sysDt }" />
					</td>
				</tr>
				<tr>
					<td>操作用户</td>
					<td>
					<input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.sysUserId" value="${paraCaseP.sysUserId }" />
					</td>
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
