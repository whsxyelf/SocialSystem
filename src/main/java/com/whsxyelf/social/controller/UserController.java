package com.whsxyelf.social.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	@ResponseBody
	public String register() {
		String email = "1353595@qq.com";
		String pass = "337zuiniubi";
		userService.register(email,pass);
		return "Register Success!!!";
	}
	
	//测试用
//	@RequestMapping(value="/gets",method=RequestMethod.GET)
//	@ResponseBody
//	public ArrayList<User> gets() {
//		ArrayList<User> userList = userService.getUsers();
//		HashMap<String, User> resultMap = new HashMap<String, User>();
//		for(User user : userList) {
//			resultMap.put(user.getUserNo(), user);
//		}
//		return userList;
//	}
}
