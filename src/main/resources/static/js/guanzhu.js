path = "http://" + window.location.host + "/social/"

initPage()
function initPage() {
	$.ajax({
		type: 'POST',
		url: path + "concern/getConcernList",
		contentType: 'application/x-www-form-urlencoded',
		dataType: 'json',
		data: {
			"userId":"-1"
		},
		success:function(data){
			if(data.success) {
				concernList = data.concernList
				concernStr = '<div class="my-top"><strong> 全部关注 </strong><span style="cursor:pointer;">' + concernList.length + '</span></div>'
				$.each(concernList,function(index,obj){
					concernStr += '<div class="my-box"><div class="my-head"><img src="' + obj.userPhoto + '">'+
					'</div><div class="my-content"><div class="my-name"><strong>' + obj.userNick + '</strong>'+
					'</div><div class="my-qian">' + obj.signature + '</div><div id="' + obj.userId + '" class="my-delate">取消关注</div></div></div>'
				})
				$(".my").html(concernStr)
				$(".my-delate").on("click",function(event){
					concernedId = event.target.id
					$.ajax({
						type: 'POST',
						url: path + "concern/delete",
						contentType: 'application/x-www-form-urlencoded',
						dataType: 'json',
						data: {
							"concernedId":concernedId
						},
						success:function(data) {
							if(data.success) {
								initPage()
							} else {
								layer.msg(data.error)
							}
						}
					})
				})
			} else {
				layer.msg(data.error)
			}
		}
	})
}
