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
 * 删除tab标签
 */
function removeCurrentPanel(){
    var tabbar = window.parent.$('#tabs');
	var tab = tabbar.tabs('getSelected');
	if (tab){
	var index = tabbar.tabs('getTabIndex', tab);
	tabbar.tabs('close', index);
	}
}
	/**
	 * 返回
	 */
	function back(){
		history.go(-1);
	}
	
	
	
	