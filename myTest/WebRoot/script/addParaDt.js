var flag_caseName=null;//判断活动类型
var flag_caseDesc=null;//判断活动描述
var flag_caseSt=null;//判断活动开始时间
var flag_caseEt=null;//判断活动结束时间
var flag_ratioNew=null;//判断新款占比
var flag_num=null;//判断参与选款
//var num=null;//选款数
$(document).ready(function() {
	// 点击选择活动类型加载值
	$("#caseName").combobox({
		onSelect : function() {
			// 点击选择活动类型去掉提示
			$("#span_caseName").html("");
			
			var caseName = $("#caseName").combobox("getValue");
			$.messager.progress({ 
				title:'请稍后', 
				msg:'页面加载中...' 
				}); 
			$.ajax({
				type : 'POST',
				url : 'paraCaseDtgetNamePCP.action',
				data : {
					'caseName' : caseName,
				},
				dataType : 'json',	
				success : function(data) {
					var d = data;//接受json
					$("#caseCode").textbox('setValue',d.caseCode)//赋值
					var CType=d.CType;
					if(CType.trim("P")){
						$("#CType").textbox('setValue',"按<款>")//赋值
					}else if(CType.trim("S")){
						$("#CType").textbox('setValue',"按SKU")//赋值
					}
					$("#defaultNum").textbox('setValue',d.num)//赋值
					$.messager.progress('close');
				}
			});
		}
	});
	
	
	//验证活动描述
	$("#caseDesc").textbox("textbox").blur(function(){
		var caseDesc = $("#caseDesc").val();
		$("#span_caseDesc").html("");
		if(caseDesc==""){
			$("#span_caseDesc").append("<font color='red'>*不能为空</font>");
			flag_caseDesc = false;
		}else{
			$("#span_caseDesc").html("");
			flag_caseDesc = true;
		}
	});
		
	//验证新款占比
	$("#ratioNew").textbox("textbox").blur(function(){
		//正则表达式0-1之间的小数
		var regular_ratioNew = /^0*\.\d+$/;
		var ratioNew = $("#ratioNew").val();
		$("#span_ratioNew").html("");
		if(ratioNew==""){
			$("#span_ratioNew").append("<font color='red'>*不能为空</font>");
			flag_ratioNew = false;
		}else if(regular_ratioNew.test(ratioNew)){
			$("#span_ratioNew").html("");
			flag_ratioNew = true;
		}else{
			$("#span_ratioNew").append("<font color='red'>*必须为0-1之间的小数</font>");
			flag_ratioNew = false;
		}
	});
	
	//验证参与选款
	$("#num").textbox("textbox").blur(function(){
		//正则表达式0-100之间的整数
		var regular_num = /^(0|[0-9][0-9]?|100)$/;
		var num = $("#num").val();
		$("#span_num").html("");
		if(num==""){
			$("#span_num").append("<font color='red'>*不能为空</font>");
			flag_num = false;
		}else if(regular_num.test(num)){
			$("#span_num").html("");
			flag_num = true;
		}else{
			$("#span_num").append("<font color='red'>*必须为0-100之间的整数</font>");
			flag_num = false;
		}
	});
	
	//保存
	$("#save").click(function() {
		check();
		if(flag_caseName&&flag_caseDesc&&flag_caseSt&&flag_caseEt&&flag_ratioNew){
			$("#saveform").submit();
			return true;
			} else {
			return false;
			}
		
	});
	
	
	
});
	
	//自己点击保存验证
	function check() {
		// 活动类型
		$("#span_caseName").html("");
		var caseName=$("#caseName").combobox("getValue");
		if(caseName=='所有活动类型'){
			$("#span_caseName").append("<font color='red'>*请选择所有活动类型</font>");
			flag_caseName=false;
		}else{
			flag_caseName=true;
		}
		
		//验证活动描述
		$("#span_caseDesc").html("");
		var caseDesc = $("#caseDesc").val();
		if(caseDesc==""){
			$("#span_caseDesc").append("<font color='red'>*不能为空</font>");
			flag_caseDesc = false;
		}else{
			flag_caseDesc = true;
		}
		
		//验证活动开始
		$("#span_caseSt").html("");
		var caseSt = $("#caseSt").datebox('getValue');
		if(caseSt==""){
			$("#span_caseSt").append("<font color='red'>*不能为空</font>");
			flag_caseSt = false;
		}else{
			$("#span_caseSt").html("");
			flag_caseSt = true;
		}
		
		//验证活动结束时间
		$("#span_caseEt").html("");
		var caseEt = $("#caseEt").datebox('getValue');
		if(caseEt==""){
			$("#span_caseEt").append("<font color='red'>*不能为空</font>");
			flag_caseEt = false;
		}else{
			flag_caseEt = true;
		}
		
		//验证新款占比
		$("#span_ratioNew").html("");
			var ratioNew = $("#ratioNew").val();
			if(ratioNew==""){
				$("#span_ratioNew").append("<font color='red'>*不能为空</font>");
				flag_ratioNew = false;
			}else{
				flag_ratioNew = true;
			}
		
		//验证参与选款
			var num = $("#num").val();
			if(num==""){
				var defaultNum = $("#defaultNum").val();//缺省
				num=defaultNum
			}
	}	
	
	
	 //输入新占款比计算旧占款比
    function ratioNewWay(){
	   //获取文本值
	   var ratioNew=$("#ratioNew").textbox("getValue");
	   //计算旧款占比
	   var ratioRetro=1-ratioNew;
	   //赋值
	   $("#ratioRetro").textbox('setValue',ratioRetro);
    }
    
    //验证活动开始时间
    function onSelectStTime(){
    	//获取时间
		var caseSt = $("#caseSt").datetimebox('getValue');
		var caseEt = $("#caseEt").datetimebox('getValue');
		var caseName = $("#caseName").combobox("getValue");
		$("#span_caseEt").html("");
		$("#span_caseSt").html("");
		//转化时间
		var beginTimes=new Date(caseSt);
		var endTimes=new Date(caseEt);
		
		var sTtimes = parseInt(beginTimes.getTime()/1000);
		var eDtimes = parseInt(endTimes.getTime()/1000);
		
		var d=sTtimes-eDtimes;
		
		if(caseSt==""){
			$("#span_caseSt").append("<font color='red'>*不能为空</font>");
			flag_caseSt = false;
		}else{
			flag_caseSt = true;
		}
		
		if(caseEt!=""){
			if(d >=0){
				$("#span_caseSt").append("<font color='red'>*开始时间要小于结束时间</font>");
				flag_caseSt = false;
			}else{
				 $.ajax({
						type : 'POST',
						url : 'paraCaseDtjudgeNameRepeat.action',
						data : {
							'caseName' : caseName,
							'caseSt' : caseSt,
							'caseEt' : caseEt,
						},
						dataType : 'json',	
						success : function(data) {
							if(data){
								$("#span_caseSt").html("");
								$("#span_caseSt").append("<font color='red'>*活动已存在</font>");
								flag_caseSt = false;
							}else{
								$("#span_caseSt").html("");
								flag_caseSt = true;
							}
						}
					});
			}
		}
    }   
     //验证活动结束时间
    function onSelectEtTime(){
	  	//获取时间
    	 	var caseName = $("#caseName").combobox("getValue");
    	    var caseSt = $("#caseSt").datetimebox('getValue');
			var caseEt = $("#caseEt").datetimebox('getValue');
			$("#span_caseSt").html("");
			$("#span_caseEt").html("");
			
			//转化时间
			var beginTimes=new Date(caseSt);
			var endTimes=new Date(caseEt);
			
			var sTtimes = parseInt(beginTimes.getTime()/1000);
			var eDtimes = parseInt(endTimes.getTime()/1000);
			
			var d=sTtimes-eDtimes;
			
			if(caseEt==""){
				$("#span_caseEt").append("<font color='red'>*不能为空</font>");
				flag_caseEt = false;
			}else if(d >0){
				$("#span_caseEt").append("<font color='red'>*结束时间要大于开始时间</font>");
			}else if(d ==0){
				$("#span_caseEt").append("<font color='red'>*结束时间要大于开始时间</font>");
				flag_caseEt = false;
			}else{
				//ajax验证活动是否相同
			   
			    $.ajax({
					type : 'POST',
					url : 'paraCaseDtjudgeNameRepeat.action',
					data : {
						'caseName' : caseName,
						'caseSt' : caseSt,
						'caseEt' : caseEt,
					},
					dataType : 'json',	
					success : function(data) {
						if(data){
							$("#span_caseEt").html("");
							$("#span_caseEt").append("<font color='red'>*活动已存在</font>");
							flag_caseEt = false;
						}else{
							$("#span_caseEt").html("");
							flag_caseEt = true;
						}
					}
				});
			}
	  }