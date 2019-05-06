var vm = new Vue({
	el: "#container",
	data: {
		phone: true,
		flag: 0, //flag为0时，身份验证界面(isPassed0:flase,isActive0: true)，flag为1是设置密码(isPassed0: true,isActive0: false,isPassed1: false,isActive1: true,),flag为2是完成
		isPassed0: false,
		isActive0: true,
		isPassed1: false,
		isActive1: false,
		isPassed2: false,
		isActive2: false
	},
	methods: {
		// change: function() {
		// 	if (this.phone == true) {
		// 		this.phone = 0;
		// 	} else {
		// 		this.phone = 1;
		// 	}
		// }
		clickBtn1:function(){
			var emailReg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
			var email = $.trim($('.phone1').val());
			if (!emailReg.test(email)) {
				layer.msg("请输入有效的邮箱地址")
			} else {
				code = $(".code11").val()
				result = 0
				$.ajax({
					async:false,
					type: 'POST',
					url: path + "user/checkEmail",
					contentType: 'application/x-www-form-urlencoded',
					dataType: 'json',
					data: {
						"checkCode":code
					},
					success:function(data){
						if(data.success) {
							layer.msg("验证成功")
							result = 1
					} else {
							layer.msg(data.error)
							result = 0
						}
					}
				})
				if(result == 0) {
					this.flag = 0
					this.isPassed0 = false
					this.isActive0 = true
					this.isPassed1 = false
					this.isActive1 = false
					this.isPassed2 = false
					this.isActive2 = false
				} else {
					this.flag = 1
					this.isPassed0 = true
					this.isActive0 = false
					this.isPassed1 = false
					this.isActive1 = true
					this.isPassed2 = false
					this.isActive2 = false
				}
				
			}
		},
		clickBtn2:function() {
			pwd = $("#psw").val()
			checkpsw = $("#checkpsw").val()
			if(pwd != checkpsw) {
				layer.msg("两次输入的密码不一致")
			} else if(pwd.length < 6) {
				layer.msg("密码不能少于6位")
			} else if(pwd.length > 12) {
				layer.msg("密码不能多于12位")
			} else {
				$.ajax({
					async:false,
					type: 'POST',
					url: path + "user/updatepwd",
					contentType: 'application/x-www-form-urlencoded',
					dataType: 'json',
					data: {
						"pwd":pwd
					},
					success:function(data){
						if(data.success) {
							layer.msg("验证成功")
							result = 2
					} else {
							layer.msg(data.error)
							result = 1
						}
					}
				})
				if(result == 1) {
					this.flag = 1
					this.isPassed0 = true
					this.isActive0 = false
					this.isPassed1 = false
					this.isActive1 = true
					this.isPassed2 = false
					this.isActive2 = false
				} else {
					this.flag = 2
					this.isPassed0 = true
					this.isActive0 = false
					this.isPassed1 = true
					this.isActive1 = false
					this.isPassed2 = false
					this.isActive2 = true
				}
			}
		}
	}
})
