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
	
	//处理保存事件
	$("#save").click(function() {
		if(checkId()){
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
		var valueRatio = $("#valueRatio").val().trim();
		$("#span_ratio").html("");
		if(valueRatio==""){
			$("#span_ratio").append("<font color='red'>*不能为空</font>");
			 flag1 = false;
		}else if(reg.test(valueRatio)){
			$("#span_ratio").html("");
			 flag1 = true;
		}else{
			$("#span_ratio").append("<font color='red'>*必须为0到1之间的两位小数</font>");
			 flag1 = false;
		}
		var reg1 = 	/^(?:0|[1-9][0-9]?|100)$/;
		var valueMin = $("#valueMin").val().trim();
		$("#span_min").html("");
		if(valueMin==""){
			$("#span_min").append("<font color='red'>*不能为空</font>");
			 flag2 = false;
		}else if(reg1.test(valueMin)){
			$("#span_min").html("");
			 flag2 = true;
		}else{
			$("#span_min").append("<font color='red'>*必须为0-100之间的整数</font>");
			 flag2 = false;
		}
		var reg2 =/^(?:0|[1-9][0-9]?|100)$/;
		var valueMax = $("#valueMax").val().trim();
		$("#span_max").html("");
		if(valueMax==""){
			$("#span_max").append("<font color='red'>*不能为空</font>");
			 flag3 = false;
		}else if(reg2.test(valueMax)){
			$("#span_max").html("");
			 flag3 = true;
		}else{
			$("#span_max").append("<font color='red'>*必须为0-100之间的整数</font>");
			 flag3 = false;
		}
		return (flag1 && flag2 && flag3);
	}
	
	
	