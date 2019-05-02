package com.whsxyelf.social.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.UserServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> UserRegister(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userStr = StringUtil.getStringParam(request, "userStr");
		User params = null;
		try {
			params = JSON.parseObject(userStr, User.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		if(params != null) {
			boolean result = userServiceImpl.Register(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "调用注册接口异常");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> UserLogin(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userStr = StringUtil.getStringParam(request, "userStr");
		User params = null;
		try {
			params = JSON.parseObject(userStr, User.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		if(params != null) {
			User result = userServiceImpl.Login(params);
			if(result != null) {
				resultMap.put("success", true);
				request.getSession().setAttribute("user", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "登陆失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/userInfoIsExist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkUserInfo(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userStr = StringUtil.getStringParam(request, "userStr");
		User params = null;
		try {
			params = JSON.parseObject(userStr, User.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		if(params != null) {
			boolean result = userServiceImpl.isExist(params);
			if(result) {
				resultMap.put("success", true);
				resultMap.put("msg", "用户名/邮箱/手机号在数据库中已存在");
			} else {
				resultMap.put("success", false);
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userStr = StringUtil.getStringParam(request, "userStr");
		User params = null;
		try {
			params = JSON.parseObject(userStr, User.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			params.setLastEditTime(new Date());
			boolean result = userServiceImpl.Update(params);
			if(result) {
				resultMap.put("success", true);
				resultMap.put("msg", "修改成功");
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "修改失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> search(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userNick = StringUtil.getStringParam(request, "userNick");
		
		if(userNick != null && !userNick.equals("")) {
			List<User> userList = userServiceImpl.Search(userNick);
			if(userList!=null) {
				resultMap.put("success", true);
				resultMap.put("userList", userList);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "搜索失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "关键字为空");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/recommend",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> recommend(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
	}
}
