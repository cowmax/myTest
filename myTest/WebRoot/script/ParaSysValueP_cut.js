var flag1 ;
var flag2 ;
var flag3 ;
$(document).ready(function (){
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
		var reNum = $("#reNum").val();
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
		var ownerRatio = $("#ownerRatio").val();
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
		
		checkId();
		if(flag1&&flag2&&flag3){
			 $("#saveform").submit();
		     $.messager.show({
			   msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">保存成功！</div></div>',
			   showType:'show',
			    style:{
				      right:'',
				      top:document.body.scrollTop+document.documentElement.scrollTop,
				      bottom:''
			    }
			    });	
			    }else{
			     return false;
			}
		
		
				 
	});
});
	function checkId() {
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
		var reg1 = /^(0|[0-9][0-9]?|100)$/;
		var reNum = $("#reNum").val();
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
		var ownerRatio = $("#ownerRatio").val();
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
	
	
	