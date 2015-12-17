var flag1;
var flag2;
var flag3;
var succ;
$(document).ready(function() {
/*function checkId(params) {
	$("#span_tyna").html("");
	$("#span_typd").html("");
	var tyna=$("#tyna").combobox("getValue");
	var valueType=$("#typd").combobox("getValue");
	if(tyna!='所有类目'&&valueType!='所有参数'){
		$.ajax({
			type : 'POST',
			url : 'judgeId.action',
			data : {
				'tyna':tyna,
				'valueType' : valueType			
			},
			tyna: 'json',
			dataType : 'json',
			success : function(data) {
				if (data != null) {
					if (data == true) {
						$("#span_tyna").append("<font color='red'>*已存在</font>");
						$("#span_typd").append("<font color='red'>*已存在</font>");
						if(params!=null&&params.onComplete!=null)
							params.onComplete(data,false);
						return false;
					} else {
						$("#span_tyna").append("<font color='green'>*通过验证</font>");
						$("#span_typd").append("<font color='green'>*通过验证</font>");
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
		}
		if(valueType=='所有参数'){
			$("#span_typd").append("<font color='red'>*请选择参数</font>");
			//$("#span_tyna").append("<font color='green'>*通过验证</font>");
		}
		
	}	

	}*/
     $("#tyna").combobox({
    	 onSelect:function checkId(params) {
    			$("#span_tyna").html("");
    			$("#span_typd").html("");
    			var tyna=$("#tyna").combobox("getValue");
    			var valueType=$("#typd").combobox("getValue");
    			if(tyna!='所有类目'&&valueType!='所有参数'){
    				$.ajax({
    					type : 'POST',
    					url : 'paraSordatajudgeId.action',
    					data : {
    						'tyna':tyna,
    						'valueType' : valueType			
    					},
    					tyna: 'json',
    					dataType : 'json',
    					success : function(data) {
    						if (data != null) {
    							if (data == true) {
    								$("#span_tyna").append("<font color='red'>*已存在</font>");
    								$("#span_typd").append("<font color='red'>*已存在</font>");
    								if(params!=null&&params.onComplete!=null)
    									params.onComplete(data,false);
    								return false;
    							} else {
    				    			$("#span_tyna").html("");
    				    			$("#span_typd").html("");
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
    				}
    				if(valueType=='所有参数'){
    					$("#span_typd").append("<font color='red'>*请选择参数</font>");
    					//$("#span_tyna").append("<font color='green'>*通过验证</font>");
    				}
    				
    			}	

    			}
     });
     
     $("#typd").combobox({
    	 onSelect:function checkId(params) {
    			$("#span_tyna").html("");
    			$("#span_typd").html("");
    			var tyna=$("#tyna").combobox("getValue");
    			var valueType=$("#typd").combobox("getValue");
    			if(tyna!='所有类目'&&valueType!='所有参数'){
    				$.ajax({
    					type : 'POST',
    					url : 'paraSordatajudgeId.action',
    					data : {
    						'tyna':tyna,
    						'valueType' : valueType			
    					},
    					tyna: 'json',
    					dataType : 'json',
    					success : function(data) {
    						if (data != null) {
    							if (data == true) {
    								$("#span_tyna").append("<font color='red'>*已存在</font>");
    								$("#span_typd").append("<font color='red'>*已存在</font>");
    								if(params!=null&&params.onComplete!=null)
    									params.onComplete(data,false);
    								return false;
    							} else {
    								$("#span_tyna").html("");
    								$("#span_typd").html("");
    								if(params!=null&&params.onComplete!=null)
    									params.onComplete(data,true);
    								succ=true;
    								return true;
    							}
    						}
    					}
    				});
    			}else{
    				if(tyna=='所有类目'){
    					$("#span_tyna").append("<font color='red'>*请选择类目</font>");
    				}
    				if(valueType=='所有参数'){
    					$("#span_typd").append("<font color='red'>*请选择参数</font>");
    					//$("#span_tyna").append("<font color='green'>*通过验证</font>");
    				}
    				succ=false;
    			}	

    			}
     });
	$("#valueRatio").textbox("textbox").blur(function(){
		check();
	});
	$("#valueMin").textbox("textbox").blur(function(){
		check();
	});
	$("#valueMax").textbox("textbox").blur(function(){
		check();
	});

	$("#save").click(function() {
		check();
		var tyna=$("#tyna").combobox("getValue");
		var valueType=$("#typd").combobox("getValue");
		if(tyna=='所有类目'){
			$("#span_tyna").html("");
			$("#span_tyna").append("<font color='red'>*请选择类目</font>");
			return false;
		}
		if(valueType=='所有参数'){
			$("#span_typd").html("");
			$("#span_typd").append("<font color='red'>*请选择参数</font>");
			return false;
		}
		if(succ&&flag1&&flag2&&flag3){
				$("#saveform").submit();
				return true;
			} else {
			return false;
			}
	});
});
function check() {	
	
	
	
	var reg1 = /^0*\.\d+$/;
	var valueRatio = $("#valueRatio").val().trim();
	$("#span_Ratio").html("");
	if(valueRatio==""){
		$("#span_Ratio").append("<font color='red'>*不能为空</font>");
		flag1 = false;
	}else if(reg1.test(valueRatio)){
		$("#span_Ratio").html("");
		flag1= true;
	}else{
		$("#span_Ratio").append("<font color='red'>*必须为0到1之间的两位小数</font>");
		flag1 = false;
	}

	var reg2 = /^((\d|[0-9]\d)(\.\d+)?|100)$/;
	var valueMin = $("#valueMin").val().trim();
	$("#span_min").html("");
	if(valueMin==""){
		$("#span_min").append("<font color='red'>*不能为空</font>");
		flag2 = false;
	}else if(reg2.test(valueMin)){
		$("#span_min").html("");
		flag2 = true;
	}else{
		$("#span_min").append("<font color='red'>*必须为0-100之间的整数</font>");
		flag2 = false;
	}

	var reg3 = /^((\d|[0-9]\d)(\.\d+)?|100)$/;
	var valueMax = $("#valueMax").val().trim();
	$("#span_max").html("");
	if(valueMax==""){
		$("#span_max").append("<font color='red'>*不能为空</font>");
		flag3 = false;
	}else if(reg3.test(valueMax)){
		$("#span_max").html("");
		flag3 = true;
	}else{
		$("#span_max").append("<font color='red'>*必须为0-100之间的整数</font>");
		flag3 = false;
	}


}
