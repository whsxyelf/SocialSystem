package com.whsxyelf.social.interceptor;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.whsxyelf.social.bean.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			StringBuffer path = request.getRequestURL();
			String jump = path.delete(path.length() - request.getRequestURI().length(),path.length()).toString();
			response.sendRedirect(jump+"/social/redirect");
			return false;
		} else {
			return true;
		}
	}
	
	
	
}
