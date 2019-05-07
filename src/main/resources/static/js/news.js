path = "http://" + window.location.host + "/social/"
initPage()

function initPage() {
	$.ajax({
		type: 'POST',
		url: path + "user/userDetailInfo",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		success: function(data) {
			if (data.success) {
				if (data.user.userPhoto != null && data.user.userPhoto != "") {
					$("#user-img-right").attr("src", data.user.userPhoto)
				}
				$(".right-top-mid").html(data.user.userNick)
				$("#concernCount").html(data.concernCount)
				$("#concernedCount").html(data.concernedCount)
				$("#essayCount").html(data.essayCount)
			} else {
				layer.msg(data.error)
			}
		}
	})

	$.ajax({
		type: 'POST',
		url: path + "essay/getEssayList",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			"userId":"-1"
		},
		success: function(data) {
			if (data.success) {
				essayList = data.essayList
				tableStr = ''
				console.log("enter")
				$.each(essayList, function(index, obj) {
					console.log(obj)
					tableStr += '<div class="box"><div class="box-top"><div class="box-top-left clearfix">' +
						'<img src="' + obj.userPhoto + '"></div><div class="box-top-right clearfix">' +
						'<div class="mid"><div class="top-name">' + obj.userNick + '</div>' +
						'<div class="top-time">' + timeFormat(obj.createTime) + '</div></div></div></div><div class="box-middle">' +
						'<div class="box-middle-text">' + obj.essayContent + '</div>' +
						'<div class="box-middle-img"><img src="' + '"></div>' +
						'<div class="box-middle-img"><img src="' + '"></div>' +
						'</div><div class="box-foot">' +
						'<div class="box-foot-a"><a href="javascript:void(0)">收藏</a>' +
						'<a href="javascript:void(0)">转发</a>' +
						'<a href="javascript:void(0)">评论</a>' +
						'<a href="javascript:void(0)">点赞</a></div></div></div>'
				})
				$("#table-box").html(tableStr)
			} else {
				layer.msg(data.error)
			}
		}
	})
}
