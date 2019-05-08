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
			$("#news-length").val(get_length(Trim(del_html_tags(data.newsContent),"g")))
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
				$("#comment-count").html(commentList.length);
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
					$("#commit-input").val('')
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

//去除html标签：
function del_html_tags(str)
{
    var words = '';
    words = str.replace(/<[^>]+>/g,"");
    return words;
}
//去除空格：
function Trim(str,is_global)
{
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global.toLowerCase()=="g")
    result = result.replace(/\s/g,"");
    return result;
} 
function get_length(str)
{
    var char_length = 0;
    for (var i = 0; i < str.length; i++){
        var son_char = str.charAt(i);
        //如果是汉字，长度大于2，其他任何字符（包括￥等特殊字符，长度均为1）另外：根据需求规则，限制n个字，一个字=2个字符
        encodeURI(son_char).length > 2 ? char_length += 1 : char_length += 0.5;
    }
        return char_length; 
}

setTimeout(function(){
	setInterval(function() {
		length = $("#news-length").val()
		$.ajax({
			type: 'POST',
			url: path + "history/update",
			contentType: 'application/x-www-form-urlencoded',
			dataType: 'json',
			data: {
				"newsId":newsId,
				"length":length
			},
			success:function(data) {
				if(data.success){
					
				} else {
					console.log(data.error)
				}
			}
		})
	},10000)
},10000)