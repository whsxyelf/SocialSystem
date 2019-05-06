package com.whsxyelf.social.controller.Route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public String login(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="/reg",method={RequestMethod.GET,RequestMethod.POST})
	public String reg(HttpServletRequest request) {
		return "register";
	}
	
	@RequestMapping(value="/updatepwd",method={RequestMethod.GET,RequestMethod.POST})
	public String updatepwd(HttpServletRequest request) {
		return "updatepsw";
	}
	
	@RequestMapping(value="/logout",method={RequestMethod.GET,RequestMethod.POST})
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		String path = request.getRequestURL().toString();
		path = path.replace("logout", "");
		response.sendRedirect(path+"home");
	}
	
	@RequestMapping(value="/redirect",method={RequestMethod.GET,RequestMethod.POST})
	public String redirect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "redirect";
	}
	
}
