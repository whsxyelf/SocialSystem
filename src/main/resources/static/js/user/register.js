var path = 'http://localhost:8080/social/'

initMethods()

function formReset() {
	document.getElementById("signupForm").reset();
}

function initMethods() {
	$.validator.addMethod(
		"checkNickName",
		function(value, element, params) {
			var flag = false
			userStr = {
				"userNick":value
			}
			$.ajax({
				async:false,
				type: 'POST',
				url: path + 'user/userInfoIsExist',
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				data: {
					"userStr": JSON.stringify(userStr)
				},
				success: function(data) {
					flag = data.success
				}
			})
			return !flag
		}
	);
	
	$.validator.addMethod(
		"checkEmail",
		function(value, element, params) {
			var flag = false
			userStr = {
				"userEmail":value
			}
			$.ajax({
				async:false,
				type: 'POST',
				url: path + 'user/userInfoIsExist',
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				data: {
					"userStr": JSON.stringify(userStr)
				},
				success: function(data) {
					flag = data.success
				}
			})
			return !flag
		}
	);

	$(function(){
		$("#signupForm").validate({
			rules: {
				nickname: {
					required: true,
					checkNickName:true,
					minlength: 2,
					maxlength: 6
				},
				psw: {
					required: true,
					minlength: 6,
					maxlength: 12
				},
				checkpsw: {
					required: true,
					minlength: 6,
					maxlength: 12,
					equalTo: "#psw"
				},
				email: {
					required: true,
					checkEmail: true,
					email: true
				},
			},
			messages: {
				nickname: {
					required: "请输入昵称",
					checkNickName:"昵称已存在",
					minlength: "昵称至少由2-6个字符组成",
					maxlength: "昵称至少由2-6个字符组成"
				},
				psw: {
					required: "请输入密码",
					minlength: "密码长度由6-12个字符组成",
					maxlength: "密码长度由6-12个字符组成"
				},
				checkpsw: {
					required: "请输入密码",
					minlength: "密码长度由6-12个字符组成",
					maxlength: "密码长度由6-12个字符组成",
					equalTo: "两次密码输入不一致"
				},
				email: {
					required: "请输入邮箱",
					checkEmail: "邮箱已存在",
					email: "请输入一个正确的邮箱"
				}
			},
			submitHandler: function(form) {
				nickname = $("#nickname").val()
				email = $("#email").val()
				psw = $("#psw").val()
				userStr = {
					"userNick":nickname,
					"userEmail":email,
					"password":psw
				}
				$.ajax({
					type: 'POST',
					url: path + 'user/register',
					contentType: 'application/x-www-form-urlencoded',
					dataType: 'json',
					data: {
						"userStr": JSON.stringify(userStr)
					},
					success:function(data) {
						if(data.success) {
							layer.msg("注册成功，将在3秒钟后回到首页...")
							setTimeout(function() {
								window.location.href=path+"home"
							}, 3000);
						} else {
							alert(data.error)
						}
					}
				})
			}
		});
	})



}
