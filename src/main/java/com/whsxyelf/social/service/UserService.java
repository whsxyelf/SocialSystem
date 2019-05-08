package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.User;

public interface UserService {
	public boolean Register(User user);
	public User Login(User user);
	public boolean isExist(User user);
	public boolean Update(User user);
	public List<User> Search(String userNick);
	
	public List<User> Recommend(List<Integer> userId);
}
