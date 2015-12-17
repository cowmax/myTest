var flag1;
var flag2;
var flag3;
var flag4;
$(document).ready(function() {
	
	$("#tyna").combobox({
   	 onSelect:function checkId(params) {
   			$("#span_tyna").html("");
   			var tyna=$("#tyna").combobox("getValue");
   			if(tyna!='所有类目'){
   				$.ajax({
   					type : 'POST',
   					url : 'paraSysValuePgetPId.action',
   					data : {
   						'tyna':tyna	
   					},
   					tyna: 'json',
   					success : function(data) {
   						if (data != null) {
   							if (data == true) {
   								$("#span_tyna").append("<font color='red'>*已存在</font>");
   						     	 flag4 = false;
   								if(params!=null&&params.onComplete!=null)
   									params.onComplete(data,false);
   								return false;
   							} else {
   								$("#span_tyna").html("");
   							    flag4 = true;
   								if(params!=null&&params.onComplete!=null)
   									params.onComplete(data,true);
   								return true;
   							}
   						}
   					}
   				});
   			}else{
   				if(tyna=='所有类目'){
   					$("#span_tyna").append("<font color='red'>*请选择类目</font>");
   				 flag4 = false;
   				}
   		
   			}	

   			}
    });
    
    
	$("#offDay").textbox("textbox").blur(function(){
		var reg =/^([12][0-9]|30|[1-9])$/ ;
		var offDay = $("#offDay").val();
		$("#span_offDay").html("");
		if(offDay==""){
			$("#span_offDay").append("<font color='red'>*不能为空</font>");
			 flag1 = false;
		}else if(reg.test(offDay)){
			$("#span_offDay").html("");
			 flag1 = true;
		}else{
			$("#span_offDay").append("<font color='red'>*必须为0到30之间的整数</font>");
			 flag1 = false;
		}
	});
	$("#reNum").textbox("textbox").blur(function(){
		var reg1 = /^(0|[0-9][0-9]?|100)$/;
		var reNum = $("#reNum").val().trim();
		$("#span_reNum").html("");
		if(reNum==""){
			$("#span_reNum").append("<font color='red'>*不能为空</font>");
			 flag2 = false;
		}else if(reg1.test(reNum)){
			$("#span_reNum").html("");
			 flag2 = true;
		}else{
			$("#span_reNum").append("<font color='red'>*必须为0-100之间的整数</font>");
			 flag2 = false;
		}
	});
	$("#ownerRatio").textbox("textbox").blur(function(){
		var reg2 = /^0*\.\d+$/;
		var ownerRatio = $("#ownerRatio").val().trim();
		$("#span_owner").html("");
		if(ownerRatio==""){
			$("#span_owner").append("<font color='red'>*不能为空</font>");
			 flag3 = false;
		}else if(reg2.test(ownerRatio)){
			$("#span_owner").html("");
			 flag3 = true;
		}else{
			$("#span_owner").append("<font color='red'>*必须为0-1之间的小数</font>");
			 flag3 = false;
		}
	});

	$("#save").click(function() {
		check();
		var tyna=$("#tyna").combobox("getValue");
		if(tyna=='所有类目'){
			$("#span_tyna").html("");
			$("#span_tyna").append("<font color='red'>*请选择类目</font>");
			return false;
		}
			if(flag1&&flag2&&flag3&&flag4){
				$("#saveform").submit();
				return true;
			} else {
			return false;
			}
	});
});
function check() {
	var reg =/^([12][0-9]|30|[1-9])$/ ;
	var offDay = $("#offDay").val().trim();
	$("#span_offDay").html("");
	if(offDay==""){
		$("#span_offDay").append("<font color='red'>*不能为空</font>");
		 flag1 = false;
	}else if(reg.test(offDay)){
		$("#span_offDay").html("");
		 flag1 = true;
	}else{
		$("#span_offDay").append("<font color='red'>*必须为0到30之间的整数</font>");
		 flag1 = false;
	}
	var reg1 = /^(0|[0-9][0-9]?|100)$/;
	var reNum = $("#reNum").val().trim();
	$("#span_reNum").html("");
	if(reNum==""){
		$("#span_reNum").append("<font color='red'>*不能为空</font>");
		 flag2 = false;
	}else if(reg1.test(reNum)){
		$("#span_reNum").html("");
		 flag2 = true;
	}else{
		$("#span_reNum").append("<font color='red'>*必须为0-100之间的整数</font>");
		 flag2 = false;
	}
	var reg2 = /^0*\.\d+$/;
	var ownerRatio = $("#ownerRatio").val().trim();
	$("#span_owner").html("");
	if(ownerRatio==""){
		$("#span_owner").append("<font color='red'>*不能为空</font>");
		 flag3 = false;
	}else if(reg2.test(ownerRatio)){
		$("#span_owner").html("");
		 flag3 = true;
	}else{
		$("#span_owner").append("<font color='red'>*必须为0-1之间的小数</font>");
		 flag3 = false;
	}

}
