$(function(){
	//显示回到顶部图标
	   $(window).scroll(function () {
	     if ($(this).scrollTop() > 300) {
	       $('#elevator').css("display","block")
	     }else{
				 $('#elevator').css("display","none")

			 }
	   });
	//回到顶部
		$("#elevator").click(function(){
	              if ($('html').scrollTop()) {
	                  $('html').animate({ scrollTop: 0 }, 100);//动画效果
	                  return false;
	              }
	              $('body').animate({ scrollTop: 0 }, 100);
	              return false;
	  });
	  
	  path = "http://" + window.location.host + "/social/"
	  $(".shouye").click(function(){
		console.log(path)
	  	window.location.href=path+"home"
	  })
	  $("#register").click(function(){
	  	window.location.href=path+"reg"
	  })
	  $("#login").click(function(){
	  	window.location.href=path+"login"
	  })
	  $("#user-exit").click(function() {
	  	layer.confirm('确定退出吗？',{
	  		btn:['确定','取消']
	  	},function() {
	  		window.location.href=path+"logout"
	  	},function() {
	  		
	  	})
	  })
	  $("#user-img").click(function(){
	  	window.location.href=path+"user"
	  })
	  
	  $.ajax({
	  	type: 'POST',
	  	url: path+"user/userInfo",
	  	contentType: 'application/x-www-form-urlencoded',
	  	dataType: 'json',
	  	success:function(data){
	  		if(data.success) {
	  			$("#user-img").attr("src",data.user.userPhoto)
				userNick = data.user.userNick
				if(userNick.length > 5){
					userNick = userNick.slice(0,5)+".."
				}
	  			$("#user-nick").html(userNick)
	  			$(".header-login-user").css("display","")
	  		} else {
	  			$(".header-user").css("display","")
	  		}
	  	}
	  })
})