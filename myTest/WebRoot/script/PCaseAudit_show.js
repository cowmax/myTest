var caseId;//活动id
var caseName;//活动名称
var flag_auditResult;//判断审核结果
var flag_auditText;//判断审核意见
/**
 * 打开选款结果详情页面窗口
 */
function showChooseWin(url) {

	$("#showPDSDtel").window('open');
	$('#showPDSDtel').window('refresh', url);

}
/**
 * 关闭选款结果详情页面窗口
 */
function closeChooseWin() {
	$("#showPDSDtel").window('close');
}

/**
 * 根据制定页面跳转
 */
function reload() {
	var offset = document.getElementById("offset").value;
	var idx = (offset == null) ? 0 : parseInt(offset) - 1;
	window.location = 'pcashowPCaseAudit.action?offset=' + idx;
}

/**
 * 表格字段格式化函数
 */
function brdeFieldFmtr(val, row) {
	val = val.trim();
	switch (val.toUpperCase()) {
	case 'A':
		val = "AMII";
		break;
	case 'R':
		val = "Redefine";
		break;
	}
	return val;
}

function numFieldFmtr(val, row) {
	val = val.trim();
	if (val.length == 0) {
		val = "全店";
	}
	return val;
}

function statusFieldFmtr(val, row) {
	val = val.trim();
	switch (val) {
	case '0':
		val = "已删除";
		break;
	case '2':
		val = "待选款";
		break;
	case '3':
		val = "已采用";
		break;
	case '5':
		val = "待审核";
		break;
	}
	return val;
}

/**
 * 根据条件查询
 */
function query() {
	var caseName = $("#caseName").combobox("getValue");
	var brde = $("#brde").combobox("getValue");
	var caseSt = $("#caseSt").datetimebox('getValue');
	var caseEt = $("#caseEt").datetimebox('getValue');
	var caseDesc = $("#caseDesc").val();

	window.location = 'pcashowPCaseAudit.action?caseName=' + caseName
			+ '&brde=' + brde + '&caseSt=' + caseSt + '&caseEt=' + caseEt
			+ '&caseDesc=' + caseDesc;
}

/**
 * 翻到给定偏移量的页面
 */
function turnPage(offset) {
	var caseName = $("#caseName").combobox("getValue");
	var brde = $("#brde").combobox("getValue");
	var caseSt = $("#caseSt").datetimebox('getValue');
	var caseEt = $("#caseEt").datetimebox('getValue');
	var caseDesc = $("#caseDesc").val();

	window.location = 'pcashowPCaseAudit.action?caseName=' + caseName
			+ '&brde=' + brde + '&caseSt=' + caseSt + '&caseEt=' + caseEt
			+ '&caseDesc=' + caseDesc + '&offset=' + offset;
}

/**
 * 选款结果详情页面的根据制定页面跳转
 */

function skip() {
	var offset = document.getElementById("offsetAudit").value;
	var idx = (offset == null) ? 0 : parseInt(offset) - 1;
	var skUrl = 'paraCaseSgetPcaPdsList.action?offset=' + idx;
	$('#showPDSDtel').window('refresh', skUrl);
}

/**
 * 选款结果详情页面的根据条件查询
 */
function inquire() {
	var productCd = $("#productCd").val();
	var sena = $("#sena").combobox("getValue");
	var spno = $("#spno").combobox("getValue");
	var jhdt = $("#jhdt").datetimebox('getValue');
	var xjdt = $("#xjdt").datetimebox('getValue');

	var trgUrl = 'paraCaseSgetPcaPdsList.action?sena=' + sena + '&spno=' + spno
			+ '&jhdt=' + jhdt + '&productCd=' + productCd + '&xjdt=' + xjdt;

	$('#showPDSDtel').window('refresh', trgUrl);
}

function audiStatusFieldFmtr(val, row) {
	val = val.trim();
	switch (val) {
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
 * 选款结果详情页面的翻到给定偏移量的页面
 */
function getturnPage(offset) {
	var productCd = $("#productCd").val();
	var sena = $("#sena").combobox("getValue");

	var spno = $("#spno").combobox("getValue");
	var jhdt = $("#jhdt").datetimebox('getValue');
	var xjdt = $("#xjdt").datetimebox('getValue');

	var trgUrl = 'paraCaseSgetPcaPdsList.action?sena=' + sena + '&spno=' + spno
			+ '&jhdt=' + jhdt + '&productCd=' + productCd + '&xjdt=' + xjdt
			+ '&offset=' + offset;
	$('#showPDSDtel').window('refresh', trgUrl);
}


/**
 * 打开审核窗口
 */
function audit(id,name) {
	 caseId=id;
	 caseName=name
	$("#audit").window('open');
}
/**
 * 关闭审核窗口
 */
function closeaudit() {
	$("#audit").window('close');
}

/**
 * 保存
 */
$(document).ready(function() {
	// 处理【保存】事件
	$("#btnSave").click(function() {
		if(checkAuditInput()){
			var obj=document.getElementById('auditform'); 
			obj.action="pcaaddPCaseAudit.action?caseId="+caseId+"&caseName="+caseName; 
			obj.submit(); 
		return true;
		}else{
		return false;	
		}	
	});
	
	
});
/**
 * 验证用户输入的正确性
 */
function checkAuditInput(){
	//验证审查结果
	$("#span_auditResult").html("");
	var boolCheck=$('#audit input:radio[name="caseAudit.auditResult"]').is(":checked");
	if(boolCheck){
		$("#span_auditResult").html("");
		flag_auditResult=true;
    }else{
    	$("#span_auditResult").append("<font color='red'>*请选择审核结果</font>");
    	flag_auditResult=false;
    }
	
	//验证审核意见
	$("#span_auditText").html("");
	var auditText=document.getElementById('auditText').value;
	if(auditText=="审核意见"){
		$("#span_auditText").append("<font color='red'>*请输入审核意见</font>");
		flag_auditText=false;
    }else if(auditText.length>100){
    	$("#span_auditText").append("<font color='red'>*请输入审核意见在100个字以内</font>");
        flag_auditText=false;
    }else{
    	$("#span_auditText").html("");
    	flag_auditText=true;
    }
	return (flag_auditResult && flag_auditText);
}