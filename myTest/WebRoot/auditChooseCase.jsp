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
<link href="css/style.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript">
  
</script>
</head>
<body>
<div  style="padding:0px 10px; margin-top: 20px; width=100%;">
     <div style="margin-top: 20px; width=100%;">
		<div id="inquire" class="toolbar" style="height: 30px;">
				<div style="float: left; margin-bottom: 10px;">
					<span style="padding: 10px;">产品编码</span>
					<input class="easyui-textbox" type="text" id="productCd" value="${productCd}" data-options="height:26">
					<span style="padding: 6px;"></span>
					<select id="sena" class="easyui-combobox" style="width:148px;height:26px"panelHeight="100"; editable="false">
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
					<span style="padding: 6px;"></span>
					<select id="spno" class="easyui-combobox"  style="width:148px;height:26px;margin-right: 15px""panelHeight="100" editable="false">
						<option value="">产品定位</option>
						<c:forEach  var="sp" items="${requestScope.spnoList }" >
							<c:choose>
								<c:when test="${sp== spno}">
									<option value="${sp}" selected="true">${sp}</option>
								</c:when><c:otherwise>
									<option value="${sp}" >${sp}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div style=" float: left; margin-bottom: 10px;">
					<span style="padding: 10px;">上架时间</span>
					<input  class="easyui-datetimebox"  id="jhdt" type="text" editable="false" value="${jhdt}"  style="width:148px;height:26px"/>
					<span style="padding: 10px;">下架时间</span>
					<input  class="easyui-datetimebox"  id="xjdt" type="text" editable="false" value="${xjdt}" style="width:148px;height:26px"/>
				</div>
				<div style=" float: left; margin-bottom: 10px;">
					<input class="easyui-linkbutton"
						type="button" id="inquire" style="margin-left: 15px;"
						onclick="inquire()" value="查询">
				</div>
			</div>
		</div>
		
			<div style=" float: left; margin-bottom: 10px;">
				<span class="easyui-menubutton" data-options="menu:'#skuType'" style="margin-left: 15px;">导出</span>
					<div id="skuType">
						<div onclick="window.location.href='paraCaseSgetparaDtSexport.action';">导出产品款</div>
						<div onclick="window.location.href='paraCaseSgetparaDtSSkuexport.action';">导出产品SKU</div>
					</div>
			</div>
		<div style="width:100%; float: left;">
			<table class="easyui-datagrid" width="100%" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCd '">产品编码</th>
						<th data-options="field:'status '" formatter="audiStatusFieldFmtr">活动状态</th>
						<th data-options="field:'avgAmt'">预测销量</th>
						<th data-options="field:'stock'">可用库存</th>
						<th data-options="field:'newOldFlag'">新/旧款</th>
						<th data-options="field:'colo'">颜色编码</th>
						<th data-options="field:'cona'">颜色名称</th>
						<th data-options="field:'sena'">季节</th>
						<th data-options="field:'spno'">产品定位</th>
						<th data-options="field:'SCaseAll'">已参与活动</th>
					</tr>
				</thead>
				<c:forEach items="${paraDtsList}" var="paradts" varStatus="i">
					<tr>
						<td>${i.index+1+offset*10}</td>
						<td>
							<c:out value="${paradts.productCd.productCode }" />
						</td>
						<td>
							<c:out value="${paradts.status }" />
						</td>
						<td>
							<c:out value="${paradts.avgAmt }" />
						</td>
						<td>
							<c:out value="${paradts.stock }" />
						</td>
						<td>
							<c:out value="${paradts.newOldFlag }" />
						</td>
						
						<td>
							<c:out value="${paradts.colo }" />
						</td>
						<td>
							<c:out value="${paradts.cona }" />
						</td>
						<td>
							<c:out value="${paradts.productCd.sena }" />
						</td>
						<td>
							<c:out value="${paradts.productCd.spno }" />
						</td>
						<td>
							<c:out value="${paradts.SCaseAll }" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offsetAudit" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="skip()">跳转</button>
				<a onclick="getturnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="getturnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="getturnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="getturnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
