var path = "http://" + window.location.host + "/social/"

initUserInfo()
initMethods()

function initUserInfo() {
	$.ajax({
		type:'POST',
		url:path + 'user/userInfo',
		contentType:'application/x-www-form-urlencoded',
		dataType:'json',
		success:function(data) {
			if(data.success) {
				if(data.user.userPhoto != null && data.user.userPhoto != "") {
					$("#aaa").attr("src",data.user.userPhoto)
				}
				$("#name-span").html(data.user.userNick)
				$("#con-span").html(data.user.signature)
				if(data.user.sex == 1) {
					$("#sex-span").html('男')
				} else {
					$("#sex-span").html('女')
				}
				
				$("#name-input").val(data.user.userNick)
				$("#con-input").val(data.user.signature)
			} else {
				alert(data.error)
			}
		}
	})
}

function initMethods() {
	$("#save").click(function() {
		userStr = {}
		nickname = $("#name-input").val()
		signature = $("#con-input").val()
		sex = $("#sex-span").html()
		imageUrl = $("#aaa").attr("src")
		
		if(!checkNickName(nickname)) {
			alert("昵称已存在")
			return
		}
		userStr.userNick = nickname
		userStr.signature = signature
		userStr.userPhoto = imageUrl
		
		if(sex == "男"){
			userStr.sex = 1
		} else {
			userStr.sex = 2
		}
		
		$.ajax({
			type: 'POST',
			url: path + 'user/update',
			contentType: 'application/x-www-form-urlencoded',
			dataType: 'json',
			data: {
				"userStr": JSON.stringify(userStr)
			},
			success: function(data) {
				if(data.success) {
					window.location.href=path+"user"
				} else {
					alert(data.error)
				}
			}
		})
		
		
	})
}

function checkNickName(nickname) {
	flag = false
	userStr = {
		"nickName":nickname
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
