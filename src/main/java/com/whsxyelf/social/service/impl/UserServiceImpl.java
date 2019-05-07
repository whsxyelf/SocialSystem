package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.mapper.UserMapper;
import com.whsxyelf.social.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	
	@Override
	public boolean Register(User user) {	
		int result = 0;
		if(!isExist(user)) {
			if(user.getPhone() != null) {
				result = mapper.addMobileUser(user);
			} else {
				result = mapper.addOne(user);
			}
		}
		return result>0?true:false;
	}

	@Override
	public User Login(User user) {
		User result = mapper.findUserByParams(user);
		return result;
	}

	@Override
	public boolean isExist(User user) {
		User result = mapper.findUserByEmailOrPhone(user);
		return result!=null?true:false;
	}
	
	@Override
	public boolean Update(User user) {
		int result = 0;
		if(!isExist(user)) {
			result = mapper.updateUser(user);
		}
		return result>0?true:false;
	}

	@Override
	public List<User> Search(String userNick) {
		List<User> userList = mapper.findUserByUserName(userNick);
		return userList;
	}

	@Override
	public List<User> Recommend(int[] userId) {
		List<User> userList = mapper.findUsersByList(userId);
		return userList;
	}
	
	
	
	
}
