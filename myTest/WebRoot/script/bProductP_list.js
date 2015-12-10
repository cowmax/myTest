	/**
	 *打开修改密码窗口
	 */
	function showChooseWin(){
		$("#chooseProductWin").window('open');
	}
	
	/**
	 * 根据制定页面跳转
	 */
	function bp_reload() {
		var offset = document.getElementById("bp_offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		var trgUrl = 'paraCaseSloadBProductPList.action?offset=' + idx;
		$('#chooseProductWin').window('refresh', trgUrl); 
	}

	/**
	 * 根据条件查询
	 */
	function bp_query() {
		var productCd=$("#bp_productCd").val();
		var sena=$("#bp_sena").combobox("getValue");
		var brde = $("#bp_brde").combobox("getValue");
		var spno=$("#bp_spno").val();
		var jhdt = $("#bp_jhdt").datetimebox('getValue');
		var xjdt = $("#bp_xjdt").datetimebox('getValue');
		
		var trgUrl = 'paraCaseSloadBProductPList.action?sena='+ sena
					+'&spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt+'&brde='+ brde;
					
		$('#chooseProductWin').window('refresh', trgUrl); 
	}
	
	
	/**
	* 翻到给定偏移量的页面
	*/
	function bp_turnPage(offset){
	
		var productCd=$("#bp_productCd").val();
		var sena=$("#bp_sena").combobox("getValue");
		var brde = $("#bp_brde").combobox("getValue");
		var spno=$("#bp_spno").val();
		var jhdt = $("#bp_jhdt").datetimebox('getValue');
		var xjdt = $("#bp_xjdt").datetimebox('getValue');
		
		var trgUrl = 'paraCaseSloadBProductPList.action?sena='+ sena
					+'&spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt+'&brde='+ brde+ '&offset=' + offset;
					
		$('#chooseProductWin').window('refresh', trgUrl); 
	}
	
	function chooseBProductP(index,value){ 
		var productCd = value['productCode'];
		window.location = 'paraCaseSgetBProductPCode.action?productCd='+ productCd;
	}
	
	