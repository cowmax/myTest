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
<script type="text/javascript" src="script/chooseCase_list.js"></script>
<script type="text/javascript">

	/**
	 *打开修改密码窗口
	 */
	function showChooseWin(){
		$("#chooseCaseWin").window('open');
		
	}
	/**
	 *关闭修改密码窗口
	 */
	function closeChooseWin(){
		$("#chooseCaseWin").window('close');
	}
	
   /**
	 * 根据制定页面跳转
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		window.location = 'paraCaseSgetParaDtSList?offset=' + idx;
	}
	/**
	 * 根据条件查询
	 */
	function query() {
		var productCd=$("#productCd").val();
		var sena=$("#sena").combobox("getValue");
		var spno=$("#spno").val();
		var jhdt = $("#jhdt").datetimebox('getValue');
		var xjdt = $("#xjdt").datetimebox('getValue');
		
		window.location = 'paraCaseSgetParaDtSList.action?sena='+ sena
					+'&spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt;
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function turnPage(offset){
		var productCd=$("#productCd").val();
		var sena=$("#sena").combobox("getValue");
		
		var spno=$("#spno").val();
		var jhdt = $("#jhdt").datetimebox('getValue');
		var xjdt = $("#xjdt").datetimebox('getValue');
		
		window.location = 'paraCaseSgetParaDtSList.action?sena='+ sena
					+'&spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt+ '&offset=' + offset;			
	}
	
	/**
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDel(id,code) {
		var msg = "确定要删除 ["+code+"] 产品吗？";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>

</head>

<body>
	<div style="display: none">
		<div id="chooseCaseWin" class="easyui-window" title="选择活动" 
			collapsible="false" minimizable="false" maximizable="false" closed="true" 
			 style="width:800px;height:520px;" href="chooseCase_list.jsp"> 
		</div>  
		<div id="win" class="easyui-window" title="文件上传"  style="width:350px;height:200px;" collapsible="false" minimizable="false" maximizable="false" closed="true" >
			<div align="center" style="margin-top: 20px;" class="toolbar">
			<form action="paraCaseSuploadFiles.action" method="post" enctype="multipart/form-data">  
				<table>    
			    	<tr>  
			        	<td>上传文件&nbsp;&nbsp;</td>  
			            <td><input class="easyui-filebox"  name="myFile" buttonText="浏览"></td>  
			        </tr> 
			        <tr style="text-align: center;">
			        	<td colspan="2" style="height:40px;"><a href="paraCaseSimportTemplate.action" style="color:blue;text-decoration:underline;">下载导入的模板</a></td>
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
	<div style="margin-top:20px;width=100%;">
		<div id="options" class="toolbar" style="height: 30px;">
			<div style="float: left;"><a class="easyui-linkbutton" onclick="showChooseWin()"  style="margin-right: 15px;color: white;background-image: url('images/butto.jpg');">选择活动</a></div>
			<div  id="operation" 
				<c:choose>
					<c:when test="${caseId!=null}">
						style="display: block"
					</c:when><c:otherwise>
						style="display: none"
					</c:otherwise>
				</c:choose>
			>
				<div style="float: left;">
					<a onclick="addPanel1('paraCaseScaseAddBProductP.action','增加营销活动选款')" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
					<a class="easyui-linkbutton"
						onclick="$('#win').window('open')" style="margin-right: 15px;">导入</a>
					<a href="paraCaseSgetParaDtSList" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
				</div>
				<div style="float: left; margin-bottom: 10px;">
					<span style="padding: 10px;">产品编码</span>
					<input class="easyui-textbox" type="text" id="productCd" value="${productCd}" data-options="height:26">
					<span style="padding: 10px;">季节</span>
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
				</div>
				<div style=" float: left; margin-bottom: 10px;">
					<input class="easyui-linkbutton"
						type="button" id="query" style="margin-left: 15px;"
						onclick="query()" value="查询">
					<span class="easyui-menubutton" data-options="menu:'#skuType'" style="margin-left: 15px;">导出</span>
						<div id="skuType">
							<div onclick="window.location.href='paraCaseSgetparaDtSexport.action';">导出产品款</div>
							<div onclick="window.location.href='paraCaseSgetparaDtSSkuexport.action';">导出产品SKU</div>
						</div>
	
					<a class="easyui-linkbutton" onclick="showExpert()" style="margin-left: 15px;">高级</a>
					
				</div>
				<div id="expertQuery" style=" float: left; margin-bottom: 10px;  display: none;">
					<span style="padding: 10px;">产品定位</span><input class="easyui-textbox" type="text" id="spno" value="" data-options="height:26">
					<span style="padding: 10px;">上架时间</span>
					<input  class="easyui-datetimebox"  id="jhdt" type="text" editable="false" value="${jhdt}" />
					<span style="padding: 10px;">下架时间</span>
					<input  class="easyui-datetimebox"  id="xjdt" type="text" editable="false" value="${xjdt}" />
				</div>
			</div>
		</div>
		<div style="width:100%; float: left;">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCd '">产品编码</th>
						<th data-options="field:'status '">活动状态</th>
						<th data-options="field:'avgAmt'">产品的平均销量</th>
						<th data-options="field:'stock'">产品的当前库存数量</th>
						<th data-options="field:'newOldFlag'">产品的新/旧款标志</th>
						<th data-options="field:'colo'">产品的颜色</th>
						<th data-options="field:'cona'">产品的颜色名称</th>
						<th data-options="field:'sena'">季节</th>
						<th data-options="field:'SCaseAll'">产品已经参与的活动列表</th>
						<th data-options="field:'操作'">操作</th>
					</tr>
				</thead>
				
				<c:choose>
					<c:when test="${paraDtsList.size()<=0}">
						<tr style="text-align: center;">
							<td colspan="11">
								<span style="color: grey;">活动 【${caseName}】 暂无选款结果！</span>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${paraDtsList}" var="paradts" varStatus="i">
							<tr>
								<td>${i.index+1 }</td>
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
									<c:out value="${paradts.SCaseAll }" />
								</td>
								<td>
									<a href="paraCaseSdelParaDts?paraDtDId=${paradts.id}" 
									 onclick="javascript:return sureDel('${paradts.id }',${paradts.productCd.productCode})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</table>
			<div class="pager" id="pagebar">
				共<b id="ttCount">${totalcount }</b>条记录 转到&nbsp;<input value="${offset+1}" size="2" id="offset" class="easyui-textbox" />&nbsp;页/<b id="ttPage">${totalpage }</b>页
				<button class="easyui-linkbutton jump-btn" width="20" onclick="reload()">跳转</button>
				<a onclick="turnPage(0)">&lt;&lt; 第一页</a> <a
					onclick="turnPage(${offset-1})">&lt; 上一页</a> <a
					onclick="turnPage(${offset+1})">下一页 &gt;</a> <a
					onclick="turnPage(${totalpage-1})">最后一页 &gt;&gt;</a>
			</div>
		</div>
	</div>

</body>
</html>
