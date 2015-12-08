var flag_caseCode;
var flag_caseName;
var flag_preNum;
var flag_chalCd;
var flag_caseLevel;
var flag_brde;
var flag_CType;
$(document).ready(function() {
	// 验证活动编码是否存在
	$("#caseCode").textbox("textbox").blur(function(){
		var caseCode = $("#caseCode").val();
		$("#span_caseCode").html("");
		if(caseCode==""){
			$("#span_caseCode").append("<font color='red'>*不能为空</font>");
		}else{
			$.ajax({
				type : 'POST',
				url : 'getIdBePCP.action',
				data : {
					'caseCode':caseCode,		
				},
				dataType: 'json',
				success : function(data) {
						if (data == true) {
							$("#span_caseCode").append("<font color='red'>*已存在</font>");
							flag_caseCode= false;
						} else {
							$("#span_caseCode").html("");
							flag_caseCode= true;
						}
					
				}
		});
		}
		
});
	// 验证活动名称是否存在
	$("#caseName").textbox("textbox").blur(function(){
		var caseName = $("#caseName").val();
		var caseCode = $("#caseCode").val();
		$("#span_caseName").html("");
		if(caseName==""){
			$("#span_caseName").append("<font color='red'>*不能为空</font>");
		}else{
			$.ajax({
				type : 'POST',
				url : 'paraCasePgetNameBePCP.action',
				data : {
					'caseName':caseName,
					'caseCode':caseCode,
				},
				caseName: 'json',
				success : function(data) {
						if (data == false) {
							$("#span_caseName").append("<font color='red'>*已存在</font>");
							flag_caseName= false;
						} else {
							$("#span_caseName").html("");
							flag_caseName= true;
						}
					
				}
		});
		
		}
	});
	
	// 活动前向影响时间
	$("#preNum").textbox("textbox").blur(function(){
		var regular_preNum = /^(0|[0-9][0-9]?|100)$/;
		var preNum = $("#preNum").val();
		$("#span_preNum").html("");
		if(preNum==""){
			$("#span_preNum").append("<font color='red'>*不能为空</font>");
			flag_preNum = false;
		}else if(regular_preNum.test(preNum)){
			$("#span_preNum").html("");
			flag_preNum = true;
		}else{
			$("#span_preNum").append("<font color='red'>*必须为0-100之间的整数</font>");
			flag_preNum = false;
		}
	});
	// 点击选择渠道/店铺去掉提示
	$("#chalCd").combobox({
	   	 onSelect:function(){
		$("#span_chalCd").html("");
		flag_chalCd=true;
	}
	});
	//点击选择活动级别去掉提示
	$("#caseLevel").combobox({
	   	 onSelect:function(){
	   		$("#span_caseLevel").html("");
		flag_caseLevel=true;
	}
	});
	
	// 点击选择活动的品牌去掉提示
	$("#brde").combobox({
	   	 onSelect:function(){
	   		$("#span_brde").html("");
	   		flag_brde=true;
	}
	});
	
	// 点击选择选款粒度去掉提示
	$("#CType").combobox({
	   	 onSelect:function(){
	   		$("#span_CType").html("");
	   		flag_CType=true;
	}
	});
	
	// 保存
	$("#save").click(function() {
		check();
		if(flag_caseCode&&flag_caseName&&flag_preNum&&flag_chalCd&&flag_caseLevel&&flag_brde&&flag_CType){
		$("#saveform").submit();
		return true;
		} else {
		return false;
		}
	});
});

function check() {
	// 验证活动编码是否存在
		var caseCode = $("#caseCode").val();
		$("#span_caseCode").html("");
		if(caseCode==""){
			$("#span_caseCode").append("<font color='red'>*不能为空</font>");
			flag_caseCode= false;
		}else{
			flag_caseCode= true;
		}
		
	// 验证活动名称是否存在
		var caseName = $("#caseName").val();
		$("#span_caseName").html("");
		if(caseName==""){
			$("#span_caseName").append("<font color='red'>*不能为空</font>");
			flag_caseName= false;
		}else{
    		flag_caseName= true;					
		}

	
	
	// 渠道/店铺
	var chalCd=$("#chalCd").combobox("getValue");
	if(chalCd=='所有渠道/店铺'){
		$("#span_chalCd").html("");
		$("#span_chalCd").append("<font color='red'>*请选择渠道/店铺</font>");
		flag_chalCd=false;
	}else{
		flag_chalCd=true;
	}
	// 活动级别
	var caseLevel=$("#caseLevel").combobox("getValue");
	if(caseLevel=='活动级别'){
		$("#span_caseLevel").html("");
		$("#span_caseLevel").append("<font color='red'>*请选择活动级别</font>");
		flag_caseLevel=false;
	}else{
		flag_caseLevel=true;
	}
	// 参加活动的品牌
	var brde=$("#brde").combobox("getValue");
	if(brde=='活动的品牌'){
		$("#span_brde").html("");
		$("#span_brde").append("<font color='red'>*请选择活动的品牌</font>");
		flag_brde=false;
	}else{
		flag_brde=true;
	}
	// 活动的选款粒度
	var brde=$("#CType").combobox("getValue");
	if(brde=='选款粒度'){
		$("#span_CType").html("");
		$("#span_CType").append("<font color='red'>*请选择选款粒度</font>");
		flag_CType=false;
	}else{
		flag_CType=true;
	}
	// 活动前向影响时间
		var regular_preNum = /^(0|[0-9][0-9]?|100)$/;
		var preNum = $("#preNum").val();
		$("#span_preNum").html("");
		if(preNum==""){
			$("#span_preNum").append("<font color='red'>*不能为空</font>");
			flag_preNum = false;
		}else if(regular_preNum.test(preNum)){
			$("#span_preNum").html("");
			flag_preNum = true;
		}else{
			$("#span_preNum").append("<font color='red'>*必须为0-100之间的整数</font>");
			flag_preNum = false;
		}

}