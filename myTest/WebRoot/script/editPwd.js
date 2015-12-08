
	var oldPwd;
	/**
	 *验证旧密码是否正确
	 */
	function checkPwd(params){
		oldPwd= $("#oldPwd").val();
		$("#msg").html("");
		if(oldPwd.length<=0){
			$("#msg").html("请输入原密码！");
			return false;
		}else{
			$.ajax({
				type : 'POST',
				url : 'pupwdCheckaction.action',
				data : {
					'oldPwd':oldPwd
				},
				dataType : 'json',
				success : function(data) {
					if (data != null) {
						if (data == false) {
							$("#msg").html("密码输入错误！");
							if (params != null && params.onComplete != null)
								params.onComplete(data, false);
							return false;
						} else{
							$("#msg").html("");
							if (params != null && params.onComplete != null)
								params.onComplete(data, true);
							return true;
						}
					}
				}
			});
		}
	}
	
	function editPwd(){
		checkPwd({
			onComplete : function(data, succ) {
				if (succ) {
					var newPwd = $("#newPwd").val();
					if(newPwd.length<=0){
						$("#msg1").html("请输入新密码！");
						return false;
					}else{
						$.ajax({
							type : 'POST',
							url : 'pueditPwdaction.action',
							data : {
								'newPwd':newPwd
							},
							dataType : 'json',
							success : function(result) {
								if (result != null) {
									if (result == true) {
										$.messager.show({
											msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">密码修改成功！</div></div>',
											timeout : 600,
											showSpeed : 200,
											showType : 'show',
											style : {
												right : '',
												top : '',
												bottom : ''
											}
										});
										return false;
									} else{
										$("#msg2").html("");
										return true;
									}
								}
							}
						});
					}
				}
			}
		})
	}
	
