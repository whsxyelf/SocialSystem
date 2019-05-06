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
				console.log(obj)
				tableStr += '<div class="history-select"><a href="' + obj.newsUrl + '">' + 
				'<span class="history-select-title">' + obj.newsTitle + '</span><br />' +
				'<span class="history-select-time">' + timeFormat(obj.createTime) + '</span></a></div>'
			})
			$(".history").html(tableStr)
		} else {
			parent.layer.msg("历史记录表加载失败")
		}
	}
})
