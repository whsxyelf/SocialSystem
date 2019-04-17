package com.whsxyelf.social.service;

import java.util.ArrayList;

import com.whsxyelf.social.bean.User;

public interface UserService {
	/*
	 * register注册接口
	 */
	public void register(String userEmail,String password);
	
	public ArrayList<User> getUsers();
	
	
}

