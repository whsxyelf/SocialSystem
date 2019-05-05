package com.whsxyelf.social.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value="/home",method={RequestMethod.GET,RequestMethod.POST})
	public String home(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value="/user",method={RequestMethod.GET,RequestMethod.POST})
	public String user(HttpServletRequest request) {
		return "user";
	}
	
}
