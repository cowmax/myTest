var flag_caseName;
var flag_preNum;
$(document).ready(function() {
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
	// 保存
	$("#save").click(function() {
		check();
		if(flag_caseName&&flag_preNum){
		$("#saveform").submit();
		return true;
		} else {
		return false;
		}
	});
});

function check() {
	// 验证活动名称是否存在
		var caseName = $("#caseName").val();
		$("#span_caseName").html("");
		if(caseName==""){
			$("#span_caseName").append("<font color='red'>*不能为空</font>");
			flag_caseName= false;
		}else{
			flag_caseName= true;
		}
	
	// 活动前向影响时间
		var preNum = $("#preNum").val();
		$("#span_preNum").html("");
		if(preNum==""){
			$("#span_preNum").append("<font color='red'>*不能为空</font>");
			flag_preNum = false;
		}else{
			flag_preNum = true;
		}
}