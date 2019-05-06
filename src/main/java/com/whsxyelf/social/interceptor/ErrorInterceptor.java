package com.whsxyelf.social.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ErrorInterceptor implements HandlerInterceptor {
	private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501,999);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(errorCodeList.contains(response.getStatus())) {
			StringBuffer path = request.getRequestURL();
			String jump = path.delete(path.length() - request.getRequestURI().length(),path.length()).toString();
			response.sendRedirect(jump+"/social/errorpage");
			return false;
		}
		return true;
	}
	
}
