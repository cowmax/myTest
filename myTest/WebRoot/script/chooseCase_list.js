	/**
	 * 根据制定页面跳转
	 */
	function bp_reload() {
		var offset = document.getElementById("bp_offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		var trgUrl = 'paraCaseDtchooseParaDt.action?offset=' + idx;
		$('#chooseCaseWin').window('refresh', trgUrl); 
	}

	/**
	* 表格字段格式化函数
	**/
	function brdeFieldFmtr(val, row){
	val = val.trim();
	switch(val.toUpperCase()){
		case 'A':
			val = "AMII";
		break;
		case 'R':
			val= "Redefine";
		break;
	}
	return val;
	}
	
	function statusFieldFmtr(val, row){
		val = val.trim();
		switch(val){
			case '0':
				val = "已删除";
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
		}
		return val;
	}

	
	/**
	 * 根据条件查询
	 */
	function bp_query() {
		var caseName = $("#bp_caseName").combobox("getValue");
		var brde = $("#bp_brde").combobox("getValue");
		var caseSt = $("#bp_caseSt").datetimebox('getValue');
		var caseEt = $("#bp_caseEt").datetimebox('getValue');
		var caseDesc = $("#bp_caseDesc").val();
		
		var trgUrl = 'paraCaseDtchooseParaDt.action?caseName='
						+caseName+'&brde='+brde+'&caseSt='+caseSt+
						'&caseEt='+caseEt+'&caseDesc='+caseDesc;
		$('#chooseCaseWin').window('refresh', trgUrl); 
	}
	
	/**
	 * 点击表头排序
	 */
	 function alertColumn(sort,order){  
		var caseName = $("#bp_caseName").combobox("getValue");
		var brde = $("#bp_brde").combobox("getValue");
		var caseSt = $("#bp_caseSt").datetimebox('getValue');
		var caseEt = $("#bp_caseEt").datetimebox('getValue');
		var caseDesc = $("#bp_caseDesc").val();
		
        var trgUrl='paraCaseDtchooseParaDt.action?caseName='
						+caseName+'&brde='+brde+'&caseSt='+caseSt+
						'&caseEt='+caseEt+'&caseDesc='+caseDesc+'&sort='+sort+'&order='+order;
		$('#chooseCaseWin').window('refresh', trgUrl); 
    } 	

	
	/**
	* 翻到给定偏移量的页面
	*/
	function bp_turnPage(offset){
		var caseName = $("#bp_caseName").combobox("getValue");
		var brde = $("#bp_brde").combobox("getValue");
		var caseSt = $("#bp_caseSt").datetimebox('getValue');
		var caseEt = $("#bp_caseEt").datetimebox('getValue');
		var caseDesc = $("#bp_caseDesc").val();
		
		var trgUrl = 'paraCaseDtchooseParaDt.action?caseName='
						+caseName+'&brde='+brde+'&caseSt='+caseSt+
						'&caseEt='+caseEt+'&caseDesc='+caseDesc
						+ '&offset=' + offset;
		$('#chooseCaseWin').window('refresh', trgUrl); 
	}
	
	function chooseCaseId(index,value){ 
		var caseId = value['caseId'];
		var caseName = value['caseName'];
		
		window.location = 'paraCaseSgetParaDtSList.action?caseId='+ caseId+'&caseName='+caseName;
	}

		
	
