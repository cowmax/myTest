<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp' starting page</title>
    
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
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
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
$(function(){
        $("#accordion").accordion('getSelected').panel('collapse');
});
</script>
</head>
  
  <body>
    <ul style=" padding-top: 3px;" id="tt"></ul>
    <div class="easyui-accordion" data-options="fillSpace:true,fit: true,border: false,animate: true,selected:false" id="accordion" fit="true" border="false">
			<!-- selected -->
			<div title="用户权限管理" style="padding:10px;">
				<ul id="tt" class="easyui-tree"  selected="false">
					<li><span>用户管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('list.jsp','所有用户信息')">查看用户信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addPuserInfo.jsp','增加用户信息')">增加用户信息</a> </span></li>
						</ul>
					</li>
					<li><span>角色管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('pr_list.jsp','所有角色信息')">查看角色信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addProleInfo.jsp','增加角色信息')">增加角色信息</a> </span></li>
						</ul>
					</li>
					<li><span>用户组管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('pg_list.jsp','所有用户组信息')">查看用户组信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addPgroupInfo.jsp','增加用户组信息')">增加用户组信息</a> </span></li>
						</ul>
					</li>
					<li><span>用户权限管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('pgu_list.jsp','所有用户权限信息')">查看用户权限信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addPgroupUserInfo.jsp','增加用户权限信息')">增加用户权限信息</a> </span></li>
						</ul>
					</li>
				</ul>
			</div>
			<div title="数据管理" title="Help" style="overflow:auto;padding:10px;">
				<ul id="tt" class="easyui-tree"  selected="false" >
					<li><span>生产参数管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('ParaSordata_show.jsp','查看生产参数信息')" >查看生产参数信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addParaSordata.jsp','增加生产参数')">增加生产参数</a> </span></li>
						</ul>
					</li>
					<li><span>计算参数管理</span>
						<ul>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('ParaSysValueP_show.jsp','查看计算参数信息')" >查看计算参数信息</a> </span></li>
							<li><span><a href="javascript:void(0)"  onclick="addPanel('addParaSysValueP.jsp','增加计算参数')">增加计算参数</a> </span></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
  </body>
</html>
