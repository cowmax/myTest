<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%
	if(request.getAttribute("rolis")==null){
		response.sendRedirect("pggetAllRoleaction");
	}
 %>
<title>My JSP 'index.jsp' starting page</title>
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

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>

<script type="text/javascript">
	
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
	<div class="easyui-panel" align="center">
		<form action="guaddGroupUseraction" id="saveform" method="post">
			<div>
				<div>
					<h3 class="tab-subtitle">增加用户权限信息</h3>
				</div>
				<table class="form-table">
					<tr>
						<td>用户组名称</td>
						<td><input class="easyui-textbox" id="groupName"
							name="pgroup.groupName" value=""/>
							<span style="color: red;" id="msg"></span>
						</td>
					</tr>
					
					<tr style="margin-bottom: 50px;">
						<td>角色名称</td>
						<td>
							<select id="roleInfo" class="easyui-combobox" editable="false" name="pgroup.roleId.roleId" style="width:150px;" panelHeight="100">
								<option value="所有角色">所有角色</option>
								<c:forEach  items="${rolis}" var="role">
									<option value="${role.roleId}">${role.roleName}</option>
								</c:forEach>
							</select> 
							<span style="color: red;" id="msg1"></span>
						</td>
					</tr>

					<tr style="margin-bottom: 50px;">
						<td>用户组描述</td>
						<td><input name="pgroup.groupDesc" value=""
							class="easyui-textbox" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" style="padding-left: 100px;"><input
							type="button" id="savepgroup" value='保存' class="easyui-linkbutton" />&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" value="取消" onclick="javascript:history.go(-1);"
							class="easyui-linkbutton">
						</td>
					</tr>

				</table>
			</div>
		</form>
	</div>
</body>
</html>