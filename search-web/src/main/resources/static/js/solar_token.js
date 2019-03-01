var SOLAR = {
	checkLogin : function(){
		var _ticket = this.getToken();
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.view.solarcat.cn/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到solarcat！<a href=\"http://sso.solarcat.cn/user/logout\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},
	getToken:function(){
	    var strcookie = document.cookie;//获取cookie字符串
	    var arrcookie = strcookie.split("; ");//分割
	    //遍历匹配
	    for ( var i = 0; i < arrcookie.length; i++) {
	        var arr = arrcookie[i].split("=");
	        if (arr[0] == "token"){
	            return arr[1];
	        }
	    }
	    return "";
	}

}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	SOLAR.checkLogin();
});