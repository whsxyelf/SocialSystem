package com.whsxyelf.social.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	public static String getStringParam(HttpServletRequest request,String key) {
		String param = request.getParameter(key);
		return param;
	}
	
	public static int getIntParam(HttpServletRequest request,String key) {
		int param = 0;
		try {
			param = Integer.parseInt(request.getParameter(key));
		} catch (NumberFormatException e) {
			param = 0;
		}
		return param;
	}
	
	public static float getFloatParam(HttpServletRequest request,String key) {
		float param = 0.0F;
		try {
			param = Float.parseFloat(request.getParameter(key));
		} catch (NumberFormatException e) {
			param = 0;
		}
		return param;
	}
	
	public static double getDoubleParam(HttpServletRequest request,String key) {
		double param = 0.0;
		try {
			param = Double.parseDouble(request.getParameter(key));
		} catch (NumberFormatException e) {
			param = 0;
		}
		return param;
	}
}
