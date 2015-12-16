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
function removeCurrentPanel(url){
	var tabbar = window.parent.$('#tabs');
	var tab = tabbar.tabs('getSelected');

	if (tab){
		var index = tabbar.tabs('getTabIndex', tab);
		
		if(url != null){
			top.location.href="index.jsp";
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


