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
		url: path + "news/newstest",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		success: function(data) {
			if (data.success) {
				newsList = data.newsList
				newsStr = ''
				$.each(newsList,function(index,obj) {
					newsTitle = obj.newsTitle
					if(newsTitle.length > 10) {
						newsTitle = newsTitle.slice(0,11) + "..."
					}
					newsStr += '<li><a href="'+path+"news/"+obj.newsId+'">' + newsTitle + '</a><span></span></li>'
				})
				$("#news-bar").html(newsStr)
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
				$.each(essayList, function(index, obj) {
					photoStr = ''
					photoUrls = obj.essayPhoto
					if(photoUrls != null) {
						urlparts = photoUrls.split('_')
						for(var i=1;i<=parseInt(urlparts[1]);i++) {
							photoStr += '<img src="' + urlparts[0]+"_"+urlparts[1]+"_"+i+'.jpg">'
						}
					}
					console.log(photoStr)
					tableStr += '<div class="box"><div class="box-top"><div class="box-top-left clearfix">'+
								'<img src="' + obj.userPhoto + '"></div><div class="box-top-right clearfix">'+
								'<div class="mid"><div class="top-name">' + obj.userNick + '</div>' +
								'<div class="top-time">' + renderTime(obj.createTime) + '</div></div></div></div>' +
								'<div class="box-middle"><div class="box-middle-text">' + obj.essayContent + '</div>' +
								'<div class="box-middle-img">'+
								 photoStr +
								'</div>'+
								'</div><div class="box-foot"><div class="box-foot-a">'+
								'<a href="javascript:void(0)">收藏</a>'+
								'<a href="javascript:void(0)">转发</a>'+
								'<a href="javascript:void(0)">评论</a>'+
								'<a href="javascript:void(0)">点赞</a>'+
								'</div></div></div>'
				})
				if(tableStr != '') {
					$("#box-bar").html(tableStr)
				}
			} else {
				layer.msg(data.error)
			}
		}
	})
}
