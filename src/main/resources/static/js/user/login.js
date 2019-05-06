var path = 'http://localhost:8080/social/'
initMethods()

function initMethods() {
	$("#login-btn").click(function() {
		username = $("#username").val()
		psw = $("#psw").val()
		re_phone = /^1\d{10}$/
		re_email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
		
		userStr = {}
		if(!re_phone.test(username) && !re_email.test(username)) {
			layer.msg("您输入的格式错误")
			return
		} else if(re_email.test(username)) {
			userStr.userEmail = username
		} else if(re_phone.test(username)) {
			userStr.phone= username
		}
		userStr.password = psw
		
		console.log(userStr)
		
		$.ajax({
			type:'POST',
			url:path + 'user/login',
			contentType:'application/x-www-form-urlencoded',
			dataType:'json',
			data: {
				"userStr":JSON.stringify(userStr)
			},
			success:function(data) {
				if(data.success){
					window.location.href=path+'user'
				} else {
					layer.msg(data.error)
				}
			}
		})
	})
}