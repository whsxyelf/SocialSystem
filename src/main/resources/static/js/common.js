Vue.component("nav-bar",{
template:"
<div class="head">
	<div class="head-content">
		<div class="head-logo clearfix">
			<a href="#"><img src="img/图标.png"><span> 商院 </span></a>
		</div>
		<div class="head-search clearfix">
			<div class="search">
				<input type="text" placeholder="请输入关键字">
				<a href="#"><img src="img/search.png"></a></div>
		</div>
		<div class="head-right">
			<div>
				<ul>
					<li>
						<div class="index_home">
							<a href="index.html">
								<img src="img/首页_wps图片.png" alt="">
								<span>首页</span>
							</a>
						</div>
						<div class="user">
							<div>
								<img src="img/登录_wps图片.png" alt="">
								<span>mistletoezzZ</span>
							</div>
							<div class="select">
								<div class="user-left">
									<img src="img/5755037373826825257.jpg" alt="">
								</div>
								<div class="user-right">
									<div><a href="">个人信息</a></div>
									<div><a href="">修改密码</a></div>
									<div><a href="">退出</a></div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>"
})
