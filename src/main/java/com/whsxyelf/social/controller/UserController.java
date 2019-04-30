//package com.whsxyelf.social.controller;
//
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.whsxyelf.social.bean.User;
//import com.whsxyelf.social.config.RedisService;
//import com.whsxyelf.social.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private RedisService rs;
//	@RequestMapping(value="/register",method=RequestMethod.GET)
//	@ResponseBody
//	public String register(String userEmail,String password){
//		
//		//重定向到登录页面
//		return "redirect:";
//	}
//	
//	
//	
//	
//	
//	
//	@RequestMapping(value="/login",method=RequestMethod.GET)
//	@ResponseBody
//	public String login(HttpSession session,String userEmail,String password) {
//		//调用login的Service
//		
//		//将登录的用户登录邮箱存入session
//		session.setAttribute("userEmail",userEmail );
//		//重定向到社交网站的首页
//		return "redirect:";
//	}
//	
//	@RequestMapping(value="/logout",method=RequestMethod.GET)
//	@ResponseBody
//	public String logout(HttpSession session) {
//		
//		//清除session中的用户信息
//		session.invalidate();
//		//重定向到社交网站的首页
//		return "redirect:";
//	}
//}
