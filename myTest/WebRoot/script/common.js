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



