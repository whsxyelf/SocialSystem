path = "http://" + window.location.host + "/social/"

initPage()
function initPage() {
	$.ajax({
		type: 'POST',
		url: path + "concern/getFansList",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			"userId":"-1"
		},
		success:function(data){
			if(data.success) {
				concernList = data.concernList
				concernStr = '<div class="my-top"><strong> 全部粉丝 </strong><span>' + concernList.length + '</span></div>'
				$.each(concernList,function(index,obj){
					concernStr += '<div class="my-box"><div class="my-head"><img src="' + obj.userPhoto + '">'+
					'</div><div class="my-content"><div class="my-name"><strong>' + obj.userNick + '</strong>'+
					'</div><div class="my-qian">' + obj.signature + '</div></div></div>'
				})
				$(".my").html(concernStr)
				
			} else {
				layer.msg(data.error)
			}
		}
	})
}
