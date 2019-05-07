$(function() {
	var page = 0
	path = "http://" + window.location.host + "/social/"
	console.log(path)
	$("#search").click(function() {
		window.location.href = path + "mainsearch"
	})

	$("#inner-search").click(function(){
		key = $("#inner-input").val()
		if(key != "") {
			window.location.href = path+"searchTo/"+key
		} else {
			window.location.href = path+"search"
		}
	})
	
	$(window).scroll(function() {
	      var scrollTop = $(this).scrollTop();
	      var scrollHeight = $(document).height();
	      var windowHeight = $(this).height();

	      if(scrollTop == 0){
			  
	      }

	      if(scrollTop + windowHeight == scrollHeight  ){
			page = page + 1
			
	      }
	    })
})
