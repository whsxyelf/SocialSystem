var vm = new Vue({
	el: "#container",
	data: {
		phone: true,
		flag: 0, //flag为0时，身份验证界面(isPassed0:flase,isActive0: true)，flag为1是设置密码(isPassed0: true,isActive0: false,isPassed1: false,isActive1: true,),flag为2是完成
		isPassed0: false,
		isActive0: true,
		isPassed1: false,
		isActive1: false,
	},
	methods: {
// 		change: function() {
// 			if (this.phone == true) {
// 
// 				this.phone = 0;
// 			} else {
// 				this.phone = 1;
// 			}
// 		}
	}
})
