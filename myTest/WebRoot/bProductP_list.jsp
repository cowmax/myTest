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
<%
	if (request.getAttribute("loadBPList") == null){
		if (request.getParameter("offset") != null) {
			response.sendRedirect("paraCaseSloadBProductPList.action?offset="
					+ request.getParameter("offset"));
		} else {
			response.sendRedirect("paraCaseSloadBProductPList.action");

		}
	}
 %>
<script type="text/javascript">

</script>
</head>

<body>
	<div style="margin-top:20px;width=100%;">
		<div id="options" class="toolbar" style="height: 30px;">
			<div style="float: left; margin-bottom: 10px;">
				<a href="paraCaseSloadBProductPList" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'">刷新</a>
				<span style="padding: 10px;">产品编码</span>
				<input class="easyui-textbox" type="text" id="bp_productCd" value="${productCd}" data-options="height:26">
				<span style="padding: 10px;">季节</span>
				<select id="bp_sena" class="easyui-combobox" style="width:148px;"panelHeight="100"; editable="false">
					<option value="" <c:if test="${sena==''}">selected="true"</c:if>>季节</option>
					<option value="全年" <c:if test="${sena=='全年'}">selected="true"</c:if>>全年</option>
					<option value="春 " <c:if test="${sena=='春'}"> selected="true"</c:if>> 春 </option>
					<option value="夏" <c:if test="${sena=='夏'}">selected="true"</c:if>> 夏</option>
					<option value="秋" <c:if test="${sena=='秋'}">selected="true"</c:if>> 秋</option>
					<option value="冬" <c:if test="${sena=='冬'}">selected="true"</c:if>> 冬</option>
					<option value="春夏" <c:if test="${sena=='春夏'}">selected="true"</c:if>> 春夏</option>
					<option value="春秋" <c:if test="${sena=='春秋'}">selected="true"</c:if>> 春秋</option>
					<option value="秋冬" <c:if test="${sena=='秋冬'}">selected="true"</c:if>> 秋冬</option>
				</select>
				<span style="padding: 10px;">品牌</span>
				<select id="bp_brde" class="easyui-combobox" style="width:148px;"panelHeight="100"; editable="false">
					<option value="" <c:if test="${brde==''}">selected="true"</c:if>>活动的品牌</option>
					<option value="A" <c:if test="${brde=='A'}"> selected="true"</c:if>> AMII </option>
					<option value="R" <c:if test="${brde=='R'}">selected="true"</c:if>> Redefined</option>
				</select>
				
			</div>
			<div style=" float: left; margin-bottom: 10px;">
				<input class="easyui-linkbutton"
					type="button" id="bp_query" style="margin-left: 15px;"
					onclick="bp_query()" value="查询">

				<a class="easyui-linkbutton" onclick="showExpert()" style="margin-left: 15px;">高级</a>
				
			</div>
			<div id="expertQuery" style=" float: left; margin-bottom: 10px;  display: none;">
				
				<span style="padding: 10px;">产品定位</span><input class="easyui-textbox" type="text" id="bp_spno" value="" data-options="height:26">
				<span style="padding: 10px;">上架时间</span>
				<input  class="easyui-datetimebox"  id="bp_jhdt" type="text" editable="false" value="${jhdt}" />
				<span style="padding: 10px;">下架时间</span>
				<input  class="easyui-datetimebox"  id="bp_xjdt" type="text" editable="false" value="${xjdt}" />
			</div>
		</div>
		<div style="width:100%; float: left;">
			<table class="easyui-datagrid" style="width=100%;"  id="bProductPList" singleSelect="true"  data-options="onClickRow:chooseBProductP">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCode'">产品编码</th>
						<th data-options="field:'brde'">品牌</th>
						<th data-options="field:'tyna'">产品类目</th>
						<th data-options="field:'sena'">季节</th>
						<th data-options="field:'jhdt'">上架时间</th>
						<th data-options="field:'xjdt'">下架时间</th>
					</tr>
				</thead>
				<c:forEach items="${loadBPList}" var="product" varStatus="i">
					<tr>
						<td>${i.index+1 }</td>
						<td>
							<c:out value="${product.productCode }" />
						</td>
						<td>
							<c:out value="${product.brde }" />
						</td>
						<td>
							<c:out value="${product.tyna }" />
						</td>
						<td>
							<c:out value="${product.sena }" />
						</td>
						<td>
							<c:out value="${product.jhdt }" />
						</td>
						<td>
							<c:out value="${product.xjdt }" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="bp_offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="bp_reload()">跳转</button>
				<a onclick="bp_turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="bp_turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="bp_turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="bp_turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
