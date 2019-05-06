package com.whsxyelf.social.controller.Route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/nav/search/{key}",method={RequestMethod.GET,RequestMethod.POST})
	public void navsearch(HttpServletRequest request,HttpServletResponse response,@PathVariable String key) throws IOException {
		StringBuffer path = request.getRequestURL();
		String jump = path.delete(path.length() - request.getRequestURI().length(),path.length()).toString();
		response.sendRedirect(jump+"/social/search/"+key);
	}
	
	@RequestMapping(value="/search",method={RequestMethod.GET,RequestMethod.POST})
	public String search(HttpServletRequest request) {
		return "search";
	}
	
	@RequestMapping(value="/mainsearch",method={RequestMethod.GET,RequestMethod.POST})
	public String mainsearch(HttpServletRequest request) {
		return "search1";
	}
	
	@RequestMapping(value="/news",method={RequestMethod.GET,RequestMethod.POST})
	public String news(HttpServletRequest request) {
		return "news";
	}
	
	@RequestMapping(value="/errorpage",method={RequestMethod.GET,RequestMethod.POST})
	public String errorpage(HttpServletRequest request) {
		return "errorpage";
	}
	
}
