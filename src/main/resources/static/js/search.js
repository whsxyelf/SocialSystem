path = "http://" + window.location.host + "/social/"

function startSearch(key) {
	$.ajax({
		type: 'POST',
		url: path + "essay/matchEssayList",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			"key":key
		},
		success:function(data) {
			if(data.success) {
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
					tableStr += '<div class="box clearfix"><div class="box-top"><div class="box-top-left clearfix">'+
					'<img src="' + obj.userPhoto + '"></div><div class="box-top-right clearfix"><div class="mid">'+
					'<div class="top-name">'+ obj.userNick +'</div><div class="top-time">' + renderTime(obj.createTime) + '</div>'+
					'</div></div></div><div class="box-middle"><div class="box-middle-text">' + obj.essayContent + '</div>'+
					'<div class="box-middle-img">'+
					photoStr+
					'</div></div><div class="box-foot"><div class="box-foot-a">'+
					'<a href="javascript:void(0)">收藏</a>'+
					'<a href="javascript:void(0)">转发</a>'+
					'<a href="javascript:void(0)">评论</a>'+
					'<a href="javascript:void(0)">点赞</a>'+
					'</div></div></div>'
				})
				// if(tableStr != '') {
				// 	$("#bigbox").html(tableStr)
				// }
				$("#bigbox").html(tableStr)
			} else {
				layer.msg(data.error)
			}
		}
	})
}

var key = ''
var url = location.search;
if (url.indexOf("?") != -1) {
	var str = url.substr(1);
	strs = str.split("=");
	key = strs[1]
}

if (key != '') {
	startSearch(key)
}



var page = 0
console.log(path)
$("#search").click(function() {
	window.location.href = path + "mainsearch"
})

$("#inner-search").click(function() {
	key = $("#inner-input").val()
	if (key != "") {
		window.location.href = path + "searchTo/" + key
	} else {
		window.location.href = path + "search"
	}
})



$("#inner-search").click(function() {
	var innerInput = $("#inner-input").val()
	startSearch(innerInput)
})



$(window).scroll(function() {
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();

	if (scrollTop == 0) {

	}

	if (scrollTop + windowHeight == scrollHeight) {
		page = page + 1
		console.log(page)
	}
})
