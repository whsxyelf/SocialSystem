package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.User;

public interface UserService {
	
	//	1.注册
	public int register(User user)throws Exception;
	
	//	2.邮箱登录
	public User loginByEmail(User user);
	
	//	3.手机登录
	public User loginByPhone(User user);

	//	4.更改密码
	public int changePassword(User user);
	
	//	5.找人
	public List<User> findUser(String userNick);
	
}

