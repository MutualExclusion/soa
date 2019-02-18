var REGISTER={
		param:{
			//单点登录系统的url
			surl:""
		},
		inputcheck:function(){
			var flag = true;
			//不能为空检查
			if ($("#regName").val() == "") {
				flag = false;
			}
			if ($("#pwd").val() == "") {
				flag = false;
			}
			if ($("#phone").val() == "") {
				flag = false;
			}
			//密码检查
			if ($("#pwd").val() != $("#pwdRepeat").val()) {
				flag = false;
			}
			return flag;
		},
		beforeSubmit:function() {
			//检查用户是否已经被占用
			$.ajax({
				url : REGISTER.param.surl + "/user/check/"+escape($("#regName").val())+"/1?r=" + Math.random(),
				success : function(data) {
					if (data.data) {
						//检查手机号是否存在
						$.ajax({
							url : REGISTER.param.surl + "/user/check/"+$("#phone").val()+"/2?r=" + Math.random(),
							success : function(data) {
								if (data.data) {
									REGISTER.doSubmit();
								} else {
									layer.alert('该手机号已注册！',{icon: 2});
								}
							}
						});
					} else {
						layer.alert('该用户名已注册！',{icon: 2});
					}	
				}
			});

		},
		doSubmit:function() {
			$.post("/user/register",$("#regForm_mod").serialize(), function(data){
				if(data.status == 200){
					layer.alert('用户注册成功，请登录！',{icon: 1}, function(){
						REGISTER.login();
					});
				} else {
					layer.alert("注册失败！",{icon: 2});
				}
			});
		},
		login:function() {
			location.href = "/user/login.html";
			return false;
		},
		reg:function() {
			if (this.inputcheck()) {
				this.beforeSubmit();
			}
		}
};