var flag1 ;
var flag2 ;
var flag3 ;
$(document).ready(function (){
	$("#valueRatio").textbox("textbox").blur(function(){
		checkId();
	});
	
	$("#valueMin").textbox("textbox").blur(function(){
		checkId();
	});
	
	$("#valueMax").textbox("textbox").blur(function(){
		checkId();
	});
	
	
	$("#save").click(function() {
		if(flag1&&flag2&&flag3){
			 $("#saveform").submit();
		     $.messager.show({
			   msg :'保存成功！',
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
		var reg = /^0*\.\d+$/;
		var valueRatio = $("#valueRatio").val();
		$("#span_ratio").html("");
		if(valueRatio==""){
			$("#span_ratio").append("<font color='red'>*不能为空</font>");
			 flag1 = false;
		}else if(reg.test(valueRatio)){
			$("#span_ratio").append("<font color='green'>*验证通过</font>");
			 flag1 = true;
		}else{
			$("#span_ratio").append("<font color='red'>*必须为0到1之间的两位小数</font>");
			 flag1 = false;
		}
		var reg1 = /^((\d|[0-9]\d)(\.\d+)?|100)$/;
		var valueMin = $("#valueMin").val();
		$("#span_min").html("");
		if(valueMin==""){
			$("#span_min").append("<font color='red'>*不能为空</font>");
			 flag2 = false;
		}else if(reg1.test(valueMin)){
			$("#span_min").append("<font color='green'>*验证通过</font>");
			 flag2 = true;
		}else{
			$("#span_min").append("<font color='red'>*必须为0-100之间</font>");
			 flag2 = false;
		}
		var reg2 = /^((\d|[0-9]\d)(\.\d+)?|100)$/;
		var valueMax = $("#valueMax").val();
		$("#span_max").html("");
		if(valueMax==""){
			$("#span_max").append("<font color='red'>*不能为空</font>");
			 flag3 = false;
		}else if(reg2.test(valueMax)){
			$("#span_max").append("<font color='green'>*验证通过</font>");
			 flag3 = true;
		}else{
			$("#span_max").append("<font color='red'>*必须为0-100之间</font>");
			 flag3 = false;
		}
	}
	
	
	