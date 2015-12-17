<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>My JSP 'da.jsp' starting page</title>

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
<script type="text/javascript">

	/**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'paraCasePgetByOptionsPCP.action?offset=' + idx;
	}
	/**
	 * 根据条件查询
	 */
	function query() {
		var caseCode=$("#caseCode").val();
		var caseName=$("#caseName").val();
		var chalCd = $("#chalCd").combobox("getValue");
		var caseLevel = $("#caseLevel").combobox("getValue");
		var brde = $("#brde").combobox("getValue");

		window.location = 'paraCasePgetByOptionsPCP.action?caseCode=' + caseCode+'&caseName='
					+ caseName+'&chalCd='+ chalCd+'&caseLevel='+ caseLevel+'&brde='+ brde;
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var caseCode=$("#caseCode").val();
		var caseName=$("#caseName").val();
		var chalCd = $("#chalCd").combobox("getValue");
		var caseLevel = $("#caseLevel").combobox("getValue");
		var brde = $("#brde").combobox("getValue");
		
		window.location = 'paraCasePgetByOptionsPCP.action?caseCode=' + caseCode+'&caseName='
					+ caseName+ '&offset=' + offset+'&chalCd='+ chalCd+'&caseLevel='+ caseLevel+'&brde='+ brde;
	}
	
	/**
	* 表格字段格式化函数
	**/
	function selTypeFieldFmtr(val, row){
		val = val.trim();
		switch(val.toUpperCase()){
			case 'P':
				val = "产品款";
			break;
			case 'S':
				val= "产品SKU";
			break;
		}
		return val;
	}
	
	function brdeFieldFmtr(val, row){
	val = val.trim();
	switch(val.toUpperCase()){
		case 'A':
			val = "AMII";
		break;
		case 'R':
			val= "Redefine";
		break;
	}
	return val;
	}
	
	
	function numFieldFmtr(val, row){
		val = val.trim();
		if (val.length == 0){
			val = "全店";
		}
		return val;
	}
	
	/**
	* 删除
	**/
	function sureDel(caseCode) {
	var msg = "确定要删除 ["+caseCode+"] 吗？";
		$.ajax({
			type : 'POST',
			url : 'paraCasePgetCaseCodeBeParaDt.action',
			data : {
				'caseCode' : caseCode,
			},
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					if (data == true) {
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">活动类型已被引用，不允许删除！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								left: $('.datagrid').position().left + $('.datagrid').width()/2 - 150,
								top : $('.datagrid').position().top + $('.datagrid').height()/2 - 80
							}
						});
					return false;
					} else if (confirm(msg) == true) {
						$.ajax({
						type : 'POST',
						url : 'paraCasePdelParaCaseP.action',
						data : {
							'caseCode' : caseCode,
						},
						dataType : 'json',
						success : function(data) {		
						}
					});
					window.location = 'paraCasePgetByOptionsPCP.action';
					return true;
					} else {
						return false;
					}
				}
			}

		});
	}
	
</script>
</style>
</head>
<%
	if (request.getAttribute("paraCasePList") == null){
		if (request.getParameter("offset") != null) {
			response.sendRedirect("paraCasePgetByOptionsPCP.action?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("paraCasePgetByOptionsPCP.action");

		}
	}else{
		
	}
 %>

<body>
	<div style="display: none">
		<div id="win" class="easyui-window" title="文件上传"  style="width:350px;height:200px;" collapsible="false" minimizable="false" maximizable="false" closed="true" >
			<div align="center" style="margin-top: 20px;" class="toolbar">
			<form action="paraCasePparaCasePAction.action" method="post" enctype="multipart/form-data">  
				<table>    
			    	<tr>  
			        	<td>上传文件&nbsp;&nbsp;</td>  
			            <td><input class="easyui-filebox"  name="myFile" buttonText="浏览"></td>  
			        </tr> 
			        <tr style="text-align: center;">
			        	<td colspan="2" style="height:40px;"><a href="paraCasePimportTemplate.action" style="color:blue;text-decoration:underline;">下载导入模板</a></td>
			        </tr>
			        <tr style="text-align: center;">  
			            <td colspan="2" >
			            	<input class="easyui-linkbutton" type="submit" value="上传">
			            	<span style="margin-right: 10px;"></span>
			            	<input class="easyui-linkbutton" type="reset" value="重置">
			            </td>  
			         </tr>  
			     </table>  
			  </form>  
			  </div>
		</div>
	</div>
	<div class="mydatagrid" style="margin-top:20px;width=100%;">
		<div id="query"  class="toolbar">
			<a onclick="addPanel1('addParaCaseP.jsp','增加活动类型')" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
			<a href="paraCasePgetByOptionsPCP.action" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
			<span style="margin-right: 10px;">活动编码</span><input class="easyui-textbox" type="text" id="caseCode" value="${caseCode}" data-options="height:26">
			<span style="padding: 10px;">活动名称</span><input class="easyui-textbox" type="text" id="caseName" value="${caseName}" data-options="height:26">
			<span style="padding: 6px;"></span>
			<select id="chalCd" class="easyui-combobox"  style="width:120px;height:26px;margin-right: 15px""panelHeight="100" editable="false">
				<option value="渠道/店铺">渠道/店铺</option>
				<c:forEach  var="sv" items="${ListStore }" >
					<c:choose>
						<c:when test="${sv.code == chalCd}">
						<option value="${sv.code}" selected="true">${sv.name}</option>
						</c:when><c:otherwise>
						<option value="${sv.code}" >${sv.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<span style="padding: 6px;"></span>
			<select id="caseLevel" class="easyui-combobox"  style="width:90px;height:26px;margin-right: 15px"panelHeight="100"editable="false">
						<option value=""  <c:if test="${caseLevel==''}">selected="true"</c:if>>活动级别</option>
						<option value="A" <c:if test="${caseLevel=='A'}">selected="true"</c:if>>A</option>
						<option value="B" <c:if test="${caseLevel=='B'}">selected="true"</c:if>>B</option>
						<option value="C" <c:if test="${caseLevel=='C'}">selected="true"</c:if>>C</option>
			</select>
			<span style="padding: 6px;"></span>
			<select id="brde" class="easyui-combobox"  style="width:90px;height:26px;margin-right: 15px"panelHeight="100"editable="false" >
					<option value="" <c:if test="${brde==''}">selected="true"</c:if>>活动品牌</option>
					<option value="A" <c:if test="${brde=='A'}"> selected="true"</c:if>> AMII </option>
					<option value="R" <c:if test="${brde=='R'}">selected="true"</c:if>> Redefined</option>
			</select>
			<input class="easyui-linkbutton"
				type="button" id="query" style="margin-left: 15px;"
				onclick="query()" value="查询">
				<a class="easyui-linkbutton"
				onclick="$('#win').window('open')" style="margin-left: 15px;">&nbsp;&nbsp;导入&nbsp;&nbsp;</a>
				<a class="easyui-linkbutton"
				href="paraCasePgetexport.action" style="margin-left: 15px;">&nbsp;&nbsp;导出&nbsp;&nbsp;</a>
		</div>
		<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
			<thead>
				<tr>
					<th data-options="field:'code'" width="">序号</th>
					<th data-options="field:'caseCode'">活动编码</th>
					<th data-options="field:'caseName '">活动名称</th>
					<th data-options="field:'chalCd '">渠道/店铺</th>
					<th data-options="field:'caseLevel'">活动级别</th>
					<th data-options="field:'preNum'">前向影响时间</th>
					<th data-options="field:'brde'" formatter="brdeFieldFmtr">品牌</th>
					<th data-options="field:'num'" formatter="numFieldFmtr">选款数量</th>
					<th data-options="field:'CType'" formatter="selTypeFieldFmtr">选款粒度</th>
					<th data-options="field:'sysDt'">修改时间</th>
					<th data-options="field:'sysUserId'">操作用户</th>
					<th data-options="field:'操作'">操作</th>
				</tr>
			</thead>
			<c:forEach items="${paraCasePList}" var="paraCaseP" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>
						<c:out value="${paraCaseP.caseCode }" />
					</td>
					<td>
						<c:out value="${paraCaseP.caseName }" />
					</td>
					<td>
						<c:out value="${paraCaseP.chalCd.name }" />
					</td>
					<td>
						<c:out value="${paraCaseP.caseLevel }" />
					</td>
					<td>
						<c:out value="${paraCaseP.preNum }" />
					</td>
					<td>
						<c:out value="${paraCaseP.brde }" />
					</td>
					<td>
						<c:out value="${paraCaseP.num }" />
					</td>
					<td>
						<c:out value="${paraCaseP.CType }" />
					</td>
					<td>
						<fmt:setLocale value="zh_cn" />  
						<fmt:formatDate value="${paraCaseP.sysDt }" type="both" dateStyle="default" />
					</td>
					<td>
						<c:out value="${paraCaseP.sysUserId }" />
					</td>
					<td><a
						href="paraCasePgetParaCasePId.action?caseCode=${paraCaseP.caseCode }">修改</a>&nbsp;&nbsp;&nbsp;
						<a style="text-decoration: underline;" onclick="javascript:return sureDel('${paraCaseP.caseCode }')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pager" id="pagebar">
			共<b id="ttCount">${rows }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${page}</b>页
			<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
			<a onclick="turnPage(0)">&lt;&lt; 第一页</a> <a
				onclick="turnPage(${offset-1})">&lt; 上一页</a> <a
				onclick="turnPage(${offset+1})">下一页 &gt;</a> <a
				onclick="turnPage(${rows-1})">最后一页 &gt;&gt;</a>
		</div>
	</div>

</body>
</html>
