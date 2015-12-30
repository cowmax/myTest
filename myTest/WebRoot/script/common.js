/**
 * 添加tab标签
 * @param url
 * @param title
 */
var index = 0;
function addPanel(url,title){
	if(!$('#tabs').tabs('exists', title)){
		$('#tabs').tabs('add',{ 
			title: title,
			content: '<iframe src="'+url+'" frameBorder="0" border="0" style="width: 100%; height: 100%;"/>',
			closable: true
		});
	}else{
		$('#tabs').tabs('select', title);
	}
}

/**
 * 添加tab标签(tab页中添加tab标签)
 * @param url
 * @param title
 */
var index = 0;
function addPanel1(url, title) {
	if (!this.parent.$("#tabs").tabs('exists', title)) {
		this.parent.$("#tabs").tabs(
				'add',
				{
					title : title,
					content : '<iframe src="'
						+ url
						+ '" frameBorder="0" border="0" style="width: 100%; height: 100%;"/>',
						closable : true
				});
	} else {
		this.parent.$("#tabs").tabs('select', title);
	}
}

/**
 * 添加tab标签(tab页中添加tab标签)
 * @param url
 * @param title
 */
var index = 0;
function addPanelExists(url, title) {
	
	if (!this.parent.$("#tabs").tabs('exists', title)) {
		this.parent.$("#tabs").tabs(
				'add',
				{
					title : title,
					content : '<iframe src="'
						+ url
						+ '" frameBorder="0" border="0" style="width: 100%; height: 100%;"/>',
						closable : true
				});
	} else {
		this.parent.$("#tabs").tabs('select', title);
		var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
	    self.parent.$('#tabs').tabs('update', {
	    	tab : currTab,
	        options : {
	        	content : '<iframe src="'
					+ url
					+ '" frameBorder="0" border="0" style="width: 100%; height: 100%;"/>',
					closable : true
	        }
	     });
	}
}

/**
 * 删除tab标签
 */
function removeCurrentPanel(url,title){
	var tabbar = window.parent.$('#tabs');
	var tab = tabbar.tabs('getSelected');

	if (tab){
		var index = tabbar.tabs('getTabIndex', tab);
		
		if(url != null){
			addPanelExists(url,title);
			tabbar.tabs('close', index);
		}else{
			tabbar.tabs('close', index);
		}
	}
}

/**
 * 返回
 */
function back(){
	history.go(-1);
}

//高级按钮显示更多的查询条件
function showExpert(){
	
	if($("#expertQuery").css("display")=="none"){
		$("#expertQuery").css("display", "block");
		$("#expertQuery").show();
	}else{
		$("#expertQuery").hide();
	}
}

//高级按钮显示更多的查询条件
function showMoreFilters(){
	var bar = $("#query");
	var more = $("#expertQuery");
	
	if(more.css("display")=="none"){
		bar.height(bar.height()*2);
		more.show();
	}else{
		bar.height(bar.height()/2);
		more.hide();
	}
}

/**
 * 缩略显示超长字段
 * @param val
 * @param row
 * @returns
 */
function cutstr(val, row) {
 	var limtLen = 8;
	var str = val.trim();
 	var char_length = 0;
 	
    for (var i = 0; i < str.length; i++){
        //var son_char = val.charAt(i);
        //encodeURI(son_char).length > 2 ? char_length += 1 : char_length += 0.5;
        if(str.charCodeAt(i) > 128){
		    char_length += 2;
		}else{ 
		    char_length++;
		}
    }
    if(char_length > limtLen){
        var str_length = 0;
        var str_len = 0;
        
        str_cut = new String();
        str_len = str.length;
        for (var i = 0; i < str_len; i++) {
            a = str.charAt(i);
            str_length++;
            if (escape(a).length > 4) {
                //中文字符的长度经编码之后大于4  
                str_length++;
            }
            str_cut = str_cut.concat(a);
            if (str_length >= limtLen) {
                str_cut = str_cut.concat("...");
                return str_cut;
            }
        }
        //如果给定字符串小于指定长度，则返回源字符串；  
       
    }else{
        return str;
    }
}



/**
 * 提交
 */
function statusCommit(caseId, caseName, caseSt, caseEt, brde, name, status,url) {
	//修改时间格式
	var timestamp = Date.parse(new Date(caseSt));
	timestamp = timestamp / 1000;
	var newDate = new Date();
	newDate.setTime(timestamp * 1000);
	var caseStDate = newDate.toLocaleDateString();

	//修改brde提示语
	var brdeval;
	switch (brde) {
	case 'A':
		brdeval = "AMII";
		break;
	case 'R':
		brdeval = "Redefine";
		break;
	}

	var msg = caseName + "(id=" + caseId + ")\n" + "开始时间： " + caseStDate
			+ "\n" + "品牌： " + brdeval + "\n" + "渠道： " + name + "\n"
			+ "确定要提交审核吗？";
	
	if(status != '2'){
		//定义一个变量接受状态的翻译
		var statusName;
		//判断状态
		switch (status) {
		case '0':
			statusName = "已删除";
			break;
		case '1':
			statusName = "已审核";
			break;
		case '3':
			statusName = "已采用";
			break;
		case '5':
			statusName = "待审核";
			break;
		case '8':
			statusName = "已采用";
			break;
		case '9':
			statusName = "已采用";
			break;
		}
		$.messager.show({
			msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">该活动为【'
					+ statusName + '】状态，不能提交！</div></div>',
			timeout : 800,
			showSpeed : 200,
			showType : 'show',
			style : {
				right : '',
				top : '',
				bottom : ''
			}
		});
		return false;		
	}else{
		if (confirm(msg) == true) {
			window.location = url+'.action?caseId='+ caseId + '&status=' 
			+ status+ '&caseName=' + caseName;
			return true;
		} else {
			return false;
		}
	}
}

/**
 * 判断上传文件是否为空
*/
function checkUploadUrl(){
	var imgPath = $("#uploadUrl").filebox('getValue');

	if(imgPath == ""){
		$.messager.show({
			msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">请选择上传文件！</div></div>',
			timeout : 600,
			showSpeed : 200,
			showType : 'show',
			width : 300,
			style : {
				right : '',
				top : '',
				bottom : ''
			}
		});	
		return false;
	}
	return true;
}


