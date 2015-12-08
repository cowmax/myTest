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
<script type="text/javascript" src="script/ParaCase_cut.js"></script>
<script type="text/javascript">
/*
$(document).ready(function(){
   默认选中下拉框的值
   var send_caseLevel="${paraCaseP.caseLevel}";
   $("#caseLevel").combobox('select', send_caseLevel);
    
   var send_brde="${paraCaseP.brde }";
   $("#brde").combobox('select', send_brde);  
    
   var send_CType="${paraCaseP.CType }";
   $("#CType").combobox('select', send_CType);  
  
});
*/
</script>

<style type="text/css">
.form-table tr td:first-child {
	width: 8em;
}
</style>
</head>
<body >
	<div id="mydiv" class="easyui-panel" title="修改活动信息">
		<form id="saveform" action="paraCasePupdateParaCaseP.action" method="post" enctype="multipart/form-data">
			<table class="form-table" >
				<tr>
					<td>活动编码</td>
					<td><input class="easyui-textbox" id="caseCode" disabled="disabled" type="text" name="paraCaseP.caseCode"
						value="${paraCaseP.caseCode}" /></td>
				</tr>
				<tr>
					<td>活动名称</td>
					<td><input class="easyui-textbox" id="caseName" type="text"
						name="paraCaseP.caseName" value="${paraCaseP.caseName }" /><span
						style="margin-left: 20px" id="span_caseName"></span></td>
				</tr>
				<tr>
					<td>渠道/店铺</td>
				<td>
					<select id="chalCd" class="easyui-combobox" name="paraCaseP.chalCd.code" style="width:148px;"panelHeight="100" editable="false" >
						<c:forEach  items="${ListStore}" var="sv">
							<c:choose>
								<c:when test="${sv.code==paraCaseP.chalCd.code}">
									<option value="${sv.code}" selected="true">${sv.name}</option>
								</c:when><c:otherwise>
									<option value="${sv.code}">${sv.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select><span
						style="margin-left: 20px" id="span_chalCd"></span>
					</td>
				</tr>
				<tr>
					<td>活动级别</td>
					<td>
					
					<select id="caseLevel" class="easyui-combobox" name="paraCaseP.caseLevel" style="width:148px;"panelHeight="100"editable="false" >
					 	<option value="A" <c:if test="${paraCaseP.caseLevel=='A'}">selected='selected'</c:if> >A</option>
					 	<option value="B" <c:if test="${paraCaseP.caseLevel=='B'}">selected='selected'</c:if> >B</option>
					 	<option value="C" <c:if test="${paraCaseP.caseLevel=='C'}">selected='selected'</c:if> >C</option>
					</select><span
						style="margin-left: 20px" id="span_caseLevel"></span>
					</td>
				</tr>
				<tr>
					<td>活动前向影响时间</td>
					<td><input class="easyui-textbox" id="preNum" type="text"
						name="paraCaseP.preNum" value="${paraCaseP.preNum }" /><span
						style="margin-left: 20px" id="span_preNum"></span></td>
				</tr>
				<tr>
					<td>参加活动的品牌</td>
					<td>
					<select id="brde" class="easyui-combobox" name="paraCaseP.brde" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="A" <c:if test="${paraCaseP.brde=='A'}">selected='selected'</c:if>  >AMII</option>
						<option value="R" <c:if test="${paraCaseP.brde=='R'}">selected='selected'</c:if> >Redefined</option>
					</select><span
						style="margin-left: 20px" id="span_brde"></span>
					</td>
				</tr>
				<tr>
					<td>参加活动的产品</td>
					<td><input class="easyui-textbox" id="num" type="text"
						name="paraCaseP.num" value="${paraCaseP.num }" /><span
						style="margin-left: 20px" id="span_num"></span></td>
				</tr>
				<tr>
					<td>活动的选款粒度</td>
					<td>
					<select id="CType" class="easyui-combobox" name="paraCaseP.CType" style="width:148px;"panelHeight="100"editable="false" 
					 >
						<option value="P" <c:if test="${paraCaseP.CType=='P'}">selected='selected'</c:if> >按产品（款）</option>
						<option value="S" <c:if test="${paraCaseP.CType=='S'}">selected='selected'</c:if> >按产品 SKU </option>
					</select><span
						style="margin-left: 20px" id="span_CType"></span>
					</td>
				</tr>
				<tr>
					<td>数据记录时间</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.sysDt " value="${paraCaseP.sysDt }" /></td>
				</tr>
				<tr>
					<td>操作用户</td>
					<td><input class="easyui-textbox" type="text" disabled="disabled"
						name="paraCaseP.sysUserId" value="${paraCaseP.sysUserId }" /></td>
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
