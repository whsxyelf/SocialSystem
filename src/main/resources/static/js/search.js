$(function(){
	path = "http://" + window.location.host + "/social/"
	console.log(path)
	 $("#search").click(function(){
	 	window.location.href=path+"mainsearch"
	 })
})