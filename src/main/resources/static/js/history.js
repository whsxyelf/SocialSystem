path = "http://" + window.location.host + "/social/"
$.ajax({
	type: 'POST',
	url: path + "news/getNewsList",
	contentType: 'application/x-www-form-urlencoded',
	dataType: 'json',
	success: function(data) {
		if (data.success) {
			newsList = data.newsList
			tableStr = ''
			$.each(newsList, function(index, obj) {
				newsUrl = path+"news/"+obj.newsId
				tableStr += '<div class="history-select"><a onclick=window.top.location="' + newsUrl + '" href="javascript:;">' + 
				'<span class="history-select-title">' + obj.newsTitle + '</span><br />' +
				'<span class="history-select-time">' + renderTime(obj.createTime) + '</span></a></div>'
			})
			$(".history").html(tableStr)
			// initMethods()
		} else {
			parent.layer.msg("历史记录表加载失败")
		}
	}
})

function initMethods() {
	$(".history-news").on('click',function(){
		console.log()
	})
}
