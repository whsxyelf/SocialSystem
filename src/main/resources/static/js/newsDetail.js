path = "http://" + window.location.host + "/social/"
var newsId = ''
var url = location.search;
if (url.indexOf("?") != -1) {
	var str = url.substr(1);
	strs = str.split("=");
	newsId = strs[1]
}

$.ajax({
	type: 'POST',
	url: path + "news/newsInfo",
	contentType: 'application/x-www-form-urlencoded',
	dataType: 'json',
	data: {
		"newsId":newsId
	},
	success:function(data) {
		if(data.success) {
			// console.log(data.newsContent)
			$(".news-body").html(data.newsContent)
			// console.log(data.news)
			$("#news-id").val(data.news.newsId)
			$(".news-title-title").html(data.news.newsTitle)
			$(".news-title-time").html(timeFormat(data.news.createTime))
		} else {
			window.location.href=path+"errorpage"
		}
	}
})

initCommentList()

function initCommentList() {
	$.ajax({
		type: 'POST',
		url: path + "comment/getCommentList",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			"commentType":1,
			"commentedId":newsId
		},
		success:function(data){
			if(data.success) {
				commentList = data.commentList
				commentBarStr = ''
				$.each(commentList,function(index,obj) {
					console.log(obj.userPhoto)
					commentBarStr += '<li><div class="pinglun-content"><div class="pinglun-img">'+
					'<a href="javascript:;"><img src="' + obj.userPhoto + '"></a></div>'+
					'<div class="pinglun-text">' + obj.commentContent + '</div><div class="dianzan">'+
					'<img src="images/点赞1.png"></div><div class="ping"><img src="images/评论.png">'+
					'<span></span></div></div></li>'
				})
				$("#comment-bar").html(commentBarStr)
			} else {
				console.log(data.error)
			}
		}
		
	})
}

$("#submit").click(function(){
	comment = $("#commit-input").val()
	if(comment == '') {
		layer.msg("说点什么呗")
	} else {
		commentStr = {
			"commentContent":comment,
			"commentedId":$("#news-id").val(),
			"commentType":1
		}
		$.ajax({
			type: 'POST',
			url: path + "comment/add",
			contentType: 'application/x-www-form-urlencoded',
			dataType: 'json',
			data: {
				"commentStr": JSON.stringify(commentStr)
			},
			success:function(data) {
				if(data.success) {
					// window.location.href = path+"newsDetail?newsId="+$("#news-id").val()
					initCommentList()
				} else {
					layer.msg(data.error)
					setTimeout(function(){
						window.location.href=path+"login"
					},2000)
				}
			}
		})
	}
	
})