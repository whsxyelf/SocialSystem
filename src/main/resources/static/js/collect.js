path = "http://" + window.location.host + "/social/"
$.ajax({
	type: 'POST',
	url: path + "collect/getCollectList",
	contentType: 'application/x-www-form-urlencoded',
	dataType: 'json',
	success:function(data) {
		if(data.success) {
			collectList = data.collectList
			collectStr = ''
			$.each(collectList,function(index,obj) {
				collectContent = obj.essayContent
				if(collectContent.length > 20) {
					collectContent = collectContent.slice(0,21)
				}
				collectStr += '<div style="border:1px solid black;" class="collect-select"><div class="collect-head">'+
				'<img src="' + obj.userPhoto + '"></div><div class="collect-name">'+
				obj.userNick + '</div><div class="collect-content">' + collectContent + '</div></div>'
			})
			$(".collect").html(collectStr)
		} else {
			layer.msg(data.error)
		}
	}
})