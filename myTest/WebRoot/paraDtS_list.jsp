<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		var spno=$("#spno").combobox("getValue");
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
		
		var spno=$("#spno").combobox("getValue");
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
	function sureDel(id,code,status) {
		if(status==3||status==8||status==9){
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">该产活动已被采用，不允许删除！</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				style : {
					right : '',
					top : '',
					bottom : ''
				}
			});	
			return false;		
		}else{
			var msg = "确定要删除 ["+code+"] 产品吗？";
			if (confirm(msg) == true) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 *增加删除
	*/
	function sureAdd(status){
		var statusMsg;
		
		if(status != 2){
			if(status==3||status==8||status==9){
				statusMsg = "该活动处于已采用状态，不允许添加活动选款！";
			}else if(status = 1){
				statusMsg = "该活动处于已审核状态，不允许添加活动选款！";
			}else if(status = 5){
				statusMsg = "该活动处于待审核状态，不允许添加活动选款！";
			}
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">'+statusMsg+'</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				width : 300,
				style : {
					right : '',
					top : '',
					bottom : ''
				}
			});	
			return false;	
		}else{
			addPanel1('paraCaseScaseAddBProductP.action','增加营销活动选款');
			return true;
		}
	}
	
	/**
	 *缩略显示
	*/
	function subStForTable(val, row){
    	var val ="";
        if(val.length>5){
        	val +=val.substr(0,5);
        }else{
            val += val;
        }
        return val;
    }
    /**
     * 导入检测
    */
    function importBp(status){
    	
    	if(status==3||status==8||status==9){
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">该活动已被采用，不允许导入活动选款！</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				style : {
					right : '',
					top : '',
					bottom : ''
				}
			});	
			return false;		
		}else{
			$('#win').window('open');
			return true;
		}
    }
    
    /**
     *状态格式化
    */
    function statusFieldFmtr(val, row){
		if(val!=null){
			val = val.trim();
		}
		switch(val){
			case '0':
				val = "已删除";
			break;
			case '1':
				val = "已审核";
			break;
			case '2':
				val= "待选款";
			break;
			case '3':
				val= "已采用";
			break;
			case '5':
				val= "待审核";
			break;
			case '8':
				val= "已采用";
			break;
			case '9':
				val= "已采用";
			break;
		}
		return val;
	}
	
	/**
	 *导入	
	 */
	function showImpDlg(flag){
		
		$('#imp_flag').val(flag);
		$('#win').window('open');
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
				<input type="hidden" name="impflag"  id="imp_flag" value="" >
				<table>    
			    	<tr>  
			        	<td>上传文件&nbsp;&nbsp;</td>  
			            <td><input class="easyui-filebox"  name="myFile" id="uploadUrl" buttonText="浏览"></td>  
			        </tr> 
			        <tr style="text-align: center;">
			        	<td colspan="2" style="height:40px;"><a href="paraCaseSimportTemplate.action" style="color:blue;text-decoration:underline;">下载导入的模板</a></td>
			        </tr>
			        <tr style="text-align: center;">  
			            <td colspan="2" >
			            	<input class="easyui-linkbutton" type="submit" onclick="return checkUploadUrl()" value="上传">
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
			<div style="float: left;">
				<a class="easyui-linkbutton" onclick="showChooseWin()"  
					style="margin-right: 15px;color: white;background-image: url('images/butto.jpg');">选择活动</a>
			</div>
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
					<a onclick="sureAdd('${caseStatus}')" class="easyui-linkbutton"
						data-options="iconCls:'icon-add'" style="margin-right: 15px;">新增</a>
						<div style="float: left;">
							<span class="easyui-menubutton" data-options="menu:'#skuimp'" style="margin-right: 15px;">导入</span>
								<div id="skuimp">
								<div onclick="return showImpDlg(3);">导入已完成活动</div>
								<div onclick="return showImpDlg(2);">导入已审核活动</div>
								</div>
						</div>	
					<a href="paraCaseSgetParaDtSList" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'" style="margin-right: 15px;">刷新</a>
				</div>
				<div style="float: left; margin-bottom: 10px;">
					<span style="padding: 6px;">产品编码</span>
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
				</div>
				<div style=" float: left; margin-bottom: 10px;">
					<input class="easyui-linkbutton"
						type="button" id="query" style="margin-left: 15px;"
						onclick="query()" value="查询">
					<input class="easyui-linkbutton" type="button" onclick="showExpert()"
					 style="margin-left: 15px;" value="高级">
					 <input class="easyui-linkbutton" type="button"
					  onclick="statusCommit('${refParaDtsPd.caseId}','${refParaDtsPd.caseName}','${refParaDtsPd.caseSt}',
					  		'${refParaDtsPd.caseEt}','${refParaDtsPd.brde}','${refParaDtsPd.name}','${refParaDtsPd.status}','paraCaseSchangeStatus')"
					 style="margin-left: 15px;" value="提交">
				<div id="expertQuery" style=" float: left; margin-bottom: 10px;  display: none;">
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
					<span style="padding: 10px;">上架时间</span>
					<input  class="easyui-datetimebox" style="width:148px;height:26px" id="jhdt" type="text" editable="false" value="${jhdt}" />
					<span style="padding: 10px;">下架时间</span>
					<input  class="easyui-datetimebox"  style="width:148px;height:26px" id="xjdt" type="text" editable="false" value="${xjdt}" />
				</div>
					<span class="easyui-menubutton" data-options="menu:'#skuType'" style="margin-left: 15px;">导出</span>
						<div id="skuType">
							<div onclick="window.location.href='paraCaseSgetparaDtSexport.action';">导出产品款</div>
							<div onclick="window.location.href='paraCaseSgetparaDtSSkuexport.action';">导出产品SKU</div>
						</div>
				</div>
			</div>
			<div style="width: 100%; height: 40px;float: left;">
				<p style="line-height: 20px; font-size:14px;">${chooseCountMsg}</p>
			</div>
		</div>
		<div style="width:100%; float: left;">
			<table class="easyui-datagrid" style="width=100%;" singleSelect="true">
				<thead>
					<tr>
						<th data-options="field:'code'" width="">序号</th>
						<th data-options="field:'productCd.productCode '">产品编码</th>
						<th data-options="field:'colo'">颜色编码</th>
						<th data-options="field:'cona'">颜色名称</th>
						<th data-options="field:'newOldFlag'">新/旧款</th>
						<th data-options="field:'sena'">季节</th>
						<th data-options="field:'productCd.spno '">产品定位</th>
						<th data-options="field:'productCd.twpr '">类目</th>
						<th data-options="field:'productCd.tyna '">子类目</th>
						<th data-options="field:'avgAmt'">预测销量</th>
						<th data-options="field:'stock'">可用库存</th>
						<th data-options="field:'productCd.jhdt '">上架时间</th>
						<th data-options="field:'productCd.xjdt '">下架时间</th>
						<th data-options="field:'SCaseAll'">参与活动</th>
						<th data-options="field:'status'" formatter="statusFieldFmtr">状态</th>
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
									<c:out value="${paradts.colo }" />
								</td>
								
								<td>
									<c:out value="${paradts.cona }" />
								</td>
								
								<td>
									<c:out value="${paradts.newOldFlag }" />
								</td>
								
								<td>
									<c:out value="${paradts.productCd.sena }" />
								</td>
								
								<td>
									<c:out value="${paradts.productCd.spno }" />
								</td>
								
								<td>
									<c:out value="${paradts.productCd.twpr }" />
								</td>
								
								<td>
									<c:out value="${paradts.productCd.tyna }" />
								</td>
								
								<td>
									<c:out value="${paradts.avgAmt }" />
								</td>
								
								<td>
									<c:out value="${paradts.stock }" />
								</td>

								<td>
									<fmt:setLocale value="zh_cn" />  
									<fmt:formatDate value="${paradts.productCd.jhdt }" type="both" pattern="yyyy-MM-dd HH:mm"/>
								</td>
								
								<td>
									<fmt:setLocale value="zh_cn" />  
									<fmt:formatDate value="${paradts.productCd.xjdt }" type="both" pattern="yyyy-MM-dd HH:mm"/>
								</td>
								
								<td>
									<c:out value="${paradts.SCaseAll }" />
								</td>
								
								<td>
									<c:out value="${paradts.status }" />
								</td>
						
								
								
								<td>
									<a href="paraCaseSdelParaDts?paraDtDId=${paradts.id}" 
									 onclick="javascript:return sureDel('${paradts.id }','${paradts.productCd.productCode}','${caseStatus}')">删除</a>
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
