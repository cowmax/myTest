<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="js/easyui/demo/demo.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<script type="text/javascript" src="js/easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var gname;
	var msg;
	$("#groupName").textbox("textbox").blur(function() {
		$("#msg").html("");
		return checkGroupName();
	});

	$("#roleInfo").combobox({
    	 onSelect:function checkRoleName(params) {
    		$("#msg1").html("");
    		var roleInfo=$("#roleInfo").combobox("getValue");
    		if(roleInfo=='所有角色'){
    				$("#msg1").append("<font color='red'>*请选择角色名称</font>");
    			return false;
    		}
    	}
     });
	$("#saveedit").click(function() {
		$("#msg1").html("");
    	var roleInfo=$("#roleInfo").combobox("getValue");
    	if(roleInfo=='所有角色'){
    		$("#msg1").append("<font color='red'>*请选择角色名称</font>");
    		return false;
    	}
		checkGroupName({
			onComplete : function(data, succ) {
				if (succ) {
					gname = $("#groupName").val();
					gdesc=$("#groupDesc").val();
					roleInfo=$("#roleInfo").combobox("getValue");
					msg = $("#msg").html();
					if (gname.length > 0) {
						if (msg.length <= 0) {
							$.post(
								'pgmergeGroupInfoaction.action',{
									'pgroup.groupName' : gname,
									'pgroup.groupDesc' : gdesc,
									'roleId' : roleInfo,
								},
								function(data){
									if (data) {
										$.messager.show({
											msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">修改用户组信息成功！</div></div>',
											timeout : 12000,
											showSpeed : 200,
											showType : 'show',
											style : {
												right : '',
												top : '',
												bottom : ''
											}
										});
										window.location = 'pggetPglistaction';
										return true;
									}
								});
							} else {
								alert(msg);
								return false;
							}
						} else {
							alert("用户组名称不能为空！");
							return false;
						}
					} else {
					}
				}
			});
		});
});

	function checkGroupName(params) {
		gname = $("#groupName").val();
		if (gname == "") {
			$("#msg").html("用户组名称不能为空！");
			return false;
		} else if (gname.length>=2&&gname.length<=8) {
			$.ajax({
				type : 'POST',
				url : 'pgjudgeGnameaction.action',
				data : {
					'choose':'edit',
					'gname' : gname
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == true) {
							$("#msg").html("角色组名称已存在！");
							if (params != null && params.onComplete != null)
								params.onComplete(data, false);
							return false;
						} else {
							$("#msg").html("");
							if (params != null && params.onComplete != null)
								params.onComplete(data, true);
							return true;
						}
					}
				}

			});
		} else {
			$("#msg").html("角色组名称长度在2~8之间");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="easyui-panel" align="center">
		<div>
			<h3 class="tab-subtitle">修改用户组信息</h3>
		</div>
		<table class="form-table">
			<tr>
				<td>用户组名称</td>
				<td><input class="easyui-textbox" name="pgroup.groupName" id="groupName"
					value="${pgroup.groupName}" /> <span
					style="color: red;" id="msg"></span></td></td>
			</tr>
			
			<tr style="margin-bottom: 50px;">
				<td>角色名称</td>
				<td>
					<select id="roleInfo" class="easyui-combobox" editable="false" name="pgroup.roleId.roleId" style="width:150px;" panelHeight="100">
						<option value="所有角色">所有角色</option>
						<c:forEach  items="${rolis}" var="role">
							<c:choose>
								<c:when test="${role.roleId==pgroup.roleId.roleId}">
									<option value="${role.roleId}" selected="true">${role.roleName}</option>
								</c:when><c:otherwise>
									<option value="${role.roleId}">${role.roleName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> 
					<span style="color: red;" id="msg1"></span>
				</td>
			</tr>
			
			<tr>
				<td>用户组描述</td>
				<td><input class="easyui-textbox" name="pgroup.groupDesc" id="groupDesc"
					value="${pgroup.groupDesc}" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="padding-left: 100px;"><input
					class="easyui-linkbutton" type="button" id="saveedit" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="easyui-linkbutton" type="button" value="取消"
					onclick="javascript:history.go(-1);" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
