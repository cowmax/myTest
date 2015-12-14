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

<link href="css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	$(function(){
		checkProductCode();
		
		$("#avgAmt").textbox("textbox").blur(function(){
			$("#avgAmtMsg").html("");
			checkAvgAmt();
		});
		$("#save").click(function() {
			
			if(checkAvgAmt()&&checkProductCode()){
				var caseId = $("#caseId").val();
				var colo = $("#color").combobox("getValue");
				var coname = $("#color").combobox("getText");
				
				if(coname == "选择颜色"){
					var cona = "";
				}else{
					var cona = coname.substring(0,coname.indexOf('('));
				}
				
				var newOldFlag = $("#newOldFlag").val();
				var avgAmt = $("#avgAmt").val();
				
				$.post("paraCaseSsaveParaDtS.action", 
					{ 
						caseId: caseId, 
						colo: colo,
						cona: cona,
						newOldFlag: newOldFlag,
						avgAmt: avgAmt
					},
			   		function(data){
			     		window.location.href = 'addSuccess.jsp';
			   	});
			}
		});
	});
	
	function checkProductCode(){
		var productCode = $("#productCd").val();
		if(productCode == ""){
			$("#codeMsg").css("color","red");
			return false;
		}else{
			$("#codeMsg").css("color","green");
			return true;
		}
	}
	
	function checkAvgAmt(){
		var avgAmt=$("#avgAmt").val();
		var avgAmtReg = /^\d+(\d|(\.[1-9]{1,2}))$/;
		if(avgAmtReg.test(avgAmt)){
			$("#avgAmtMsg").html("");
			return true;
		}else{
			$("#avgAmtMsg").append("<font color='red'>产品平均销量必须为数字</font>");
			return false;
		}
	}
</script>

<style type="text/css">
	.form-table tr td:first-child {
		width:9em;
	}
</style>
<script type="text/javascript" src="script/bProductP_list.js"></script>
</head>

<body>
	<div id="chooseProductWin" class="easyui-window" title="选择产品" 
		collapsible="false" minimizable="false" maximizable="false" closed="true" 
		style="width:820px;height:520px; " href="bProductP_list.jsp"> 
	</div>  
	<div id="mydiv" class="easyui-panel" align="center" >
		
		<form id="saveform" action="" method="post" >
			<div>
				<h3 class="tab-subtitle">添加营销活动选款</h3>
			</div>
			<table class="form-table">
				<tr>
					<td>活动ID</td>
					<td><input class="easyui-textbox" id="caseId" disabled="disabled" type="text"
						name="pds.caseId" value="${pds.caseId}" /></td>
				</tr>
				<tr>
					<td>活动状态</td>
					<td><input class="easyui-textbox" id="status" disabled="disabled" type="text"
						name="pds.status" value="${pds.status}" /></td>
				</tr>
				<tr>
					<td>产品编号</td>
					<td><input class="easyui-textbox" id="productCd" type="text"
						name="pds.productCd.productCode" value="${pds.productCd.productCode}" />
						<a onclick="showChooseWin()" id="codeMsg" style="margin-right: 15px;">选择产品</a>
					</td>
				</tr>
				<tr>
					<td>可用库存</td>
					<td><input  class="easyui-textbox"  id="stock"  disabled="disabled" type="text" editable="false"
						name="pds.stock" value=""/></td>
				</tr>
				<tr>
					<td>新/旧款</td>
					<td>
					<input class="easyui-textbox" id="newOldFlag"  disabled="disabled" type="text"  editable="false"
						name="pds.productCd.newOldFlag" 
						<c:choose>
							<c:when test="${pds.productCd.isNew == 1}">
								value="NEW"
							</c:when><c:otherwise>
								value="OLD"
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>预测销量</td>
					<td>
					<input class="easyui-textbox" id="avgAmt" type="text"
						name="pds.avgAmt" value="" /><span id="avgAmtMsg"></span>
					</td>
				</tr>
				<tr>
					<td>颜色名称</td>
					<td>
						<select id="color" class="easyui-combobox" style="width:148px;"panelHeight="100" editable="false" >
							<option value="">选择颜色</option>
							<c:forEach  var="color" items="${colorMap}" >
								<option value="${color.key}">${color.value} (${color.key}) </option>
							</c:forEach>
						</select>
					</td>
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
