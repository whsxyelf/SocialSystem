var app = new Vue({
 	el: '#app',
 	data() {
 		return {
 			imgList: [],
 			size: 0
 		}
 	},
 	methods: {
 		fileClick() {
 			document.getElementById('upload_file').click()
 		},
 		fileChange(el) {
 			if (!el.target.files[0].size) return;
 			this.fileList(el.target);
 			el.target.value = ''
 		},
 		fileList(fileList) {
 			let files = fileList.files;
 			for (let i = 0; i < files.length; i++) {
 				//判断是否为文件夹
 				if (files[i].type != '') {
 					this.fileAdd(files[i]);
 				} else {
 					//文件夹处理
 					this.folders(fileList.items[i]);
 				}
 			}
 		},
 		//文件夹处理
 		folders(files) {
 			let _this = this;
 			//判断是否为原生file
 			if (files.kind) {
 				files = files.webkitGetAsEntry();
 			}
 			files.createReader().readEntries(function(file) {
 				for (let i = 0; i < file.length; i++) {
 					if (file[i].isFile) {
 						_this.foldersAdd(file[i]);
 					} else {
 						_this.folders(file[i]);
 					}
 				}
 			})
 		},
 		foldersAdd(entry) {
 			let _this = this;
 			entry.file(function(file) {
 				_this.fileAdd(file)
 			})
 		},
 		fileAdd(file) {
 			//总大小
 			this.size = this.size + file.size;
 			//判断是否为图片文件
 			if (file.type.indexOf('image') == -1) {
 				file.src = 'wenjian.png';
 				this.imgList.push({
 					file
 				});
 			} else {
 				let reader = new FileReader();
 				reader.vue = this;
 				reader.readAsDataURL(file);
 				reader.onload = function() {
 					file.src = this.result;
 					this.vue.imgList.push({
 						file
 					});
 				}
 			}
 		},
 		fileDel(index) {
 			this.size = this.size - this.imgList[index].file.size; //总大小
 			this.imgList.splice(index, 1);
 		},
 		bytesToSize(bytes) {
 			if (bytes === 0) return '0 B';
 			let k = 1000, // or 1024
 				sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
 				i = Math.floor(Math.log(bytes) / Math.log(k));
 			return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
 		},
 		dragenter(el) {
 			el.stopPropagation();
 			el.preventDefault();
 		},
 		dragover(el) {
 			el.stopPropagation();
 			el.preventDefault();
 		},
 		drop(el) {
 			el.stopPropagation();
 			el.preventDefault();
 			this.fileList(el.dataTransfer);
 		},
 		publish(el) {
			path = "http://" + window.location.host + "/social/"
 			var data = new FormData();
			essayContent = $("#essay-content").val()
 			if(essayContent == '') {
				layer.msg("说点什么呗")
			} else {
				for (var i = 0; i < this.imgList.length; i++) {
					data.append("file", this.imgList[i].file);
				}
				data.append("essayContent",essayContent)
				console.log(this.imgList.length)
				$.ajax({
					type: 'POST',
					url: path+"essay/publish",
					data: data,
					dataType: 'JSON',
					cache: false,
					processData: false,
					contentType: false,
					success:function(data) {
						if(data.success) {
							window.location.href = path+"news"
						} else {
							layer.msg(data.error)
						}
					}
					
				})
			}
 		}
 	}
 })
