path = "http://" + window.location.host + "/social/"
initPage()
initMethods()
initEssayList()
initRecommendList()

function initRecommendList() {
	$.ajax({
		type: 'POST',
		url: path + "user/recommend",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		success: function(data) {
			if(data.success) {
				page = parseInt($("#page").val())
				userList = data.userList
				console.log(userList)
				tableStr = ''
				$.each(userList,function(index,obj) {
					if(index>=(page*4) && index<((page+1)*4)) {
						tableStr += '<div class="user1"><div class="user1-left">'+
						'<img src="' + obj.userPhoto + '"></div><div class="user1-right">'+
						'<strong>' + obj.userNick + '</strong></div><div class="guanzhu">'+
						'<img src="images/关注.png"></div></div>'
					}
				})
				$("#recommend-bar").html(tableStr)
			} else {
				layer.msg(data.error)
			}
		}
	})
}

function initEssayList() {
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
					console.log(obj.essayId)
					tableStr += '<div class="box"><div style="position: relative;" class="box-top"><img name="'+ obj.essayId +'" class="del-btn" src="images/delete.png"/><div class="box-top-left clearfix">'+
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
				} else {
					$("#box-bar").html('<div class="box" style="background: url(images/empty.jpg@1280w_1l_2o_100sh.jpg);background-position: 50% 50%;">'+
					'<div class="box-top"></div><div class="box-middle" style="height: 500px;"></div>'+
					'<div class="box-foot"></div></div>')
				}
				initMethods()
			} else {
				layer.msg(data.error)
			}
		}
	})
}

function initMethods() {
	$(".del-btn").on("click",function(event) {
		layer.confirm('确定删除吗？',{
			btn:['确定','取消']
		},function() {
			essayId = event.target.name
			$.ajax({
				type: 'POST',
				url: path + "essay/delete",
				contentType: 'application/x-www-form-urlencoded',
				dataType: 'json',
				data: {
					"essayId":essayId
				},
				success:function(data) {
					if(data.success) {
						layer.close(layer.index)
						initEssayList()
						initUser()
					} else {
						layer.msg(data.error)
					}
				}
			})
		},function() {
			
		})
	})
}

function initUser() {
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
}

function initPage() {
	$(".flash").on('click',function(){
		page = parseInt($("#page").val())
		$("#page").val((page+1)%4)
		initRecommendList()
	})
	
	initUser()
	
	$.ajax({
		type: 'POST',
		url: path + "news/getRandomNews",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			'limit':30
		},
		success: function(data) {
			if (data.success) {
				newsList = data.newsList
				newsStr = ''
				$.each(newsList,function(index,obj) {
					newsTitle = obj.newsTitle
					if(newsTitle.length > 10) {
						newsTitle = newsTitle.slice(0,11) + "..."
					}
					newsStr += '<li style="cursor:pointer;"><a target="_blank" href="'+path+"news/"+obj.newsId+'">' + newsTitle + '</a><span></span></li>'
				})
				$("#news-bar").html(newsStr)
			} else {
				layer.msg(data.error)
			}
		}
	})
}
