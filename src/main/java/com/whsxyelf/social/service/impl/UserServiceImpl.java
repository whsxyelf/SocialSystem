package com.whsxyelf.social.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.mapper.UserMapper;
import com.whsxyelf.social.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper usermapper;	
	/*
	  * 输入注册信息点击注册-->查询输入信息是否存在-->
	  * 1存在：注册信息已存在!
	  * 2不存在：添加注册数据到数据库
	 */
	@Override
	public void register(String userEmail,String password) {
		usermapper.addOne(userEmail, password);
	}
	@Override
	public ArrayList<User> getUsers(){
		ArrayList<User> userList = usermapper.findAll();
		return userList;
	}
	
	
	
}
