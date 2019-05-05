package com.whsxyelf.social.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.FileUpLoadServiceImpl;
import com.whsxyelf.social.service.impl.UserServiceImpl;
import com.whsxyelf.social.util.CheckCodeUtil;
import com.whsxyelf.social.util.EmailUtil;
import com.whsxyelf.social.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	EmailUtil email;
	@Autowired
	FileUpLoadServiceImpl fileUpLoadServiceImpl;
	
	@RequestMapping(value="/userInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userInfo(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			resultMap.put("success", true);
			resultMap.put("user", user);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "未登录");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> UserRegister(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String userStr = StringUtil.getStringParam(request, "userStr");
		User params = null;
		try {
			params = JSON.parseObject(userStr,User.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
		}
		
		if(params != null) {
			boolean result = userServiceImpl.Register(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "注册失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "JSON字符串解析失败");
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
		
		System.out.println(params);
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			params.setLastEditTime(new Date());
			boolean result = userServiceImpl.Update(params);
			if(result) {
				params.setUserEmail(user.getUserEmail());
				params.setPhone(user.getPhone());
				request.getSession().setAttribute("user", params);
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
	
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendEmail(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = (User)request.getSession().getAttribute("user");
		String code = CheckCodeUtil.createCheckCode();
		
		if(user != null) {
			try {
				email.sendSimpleEmail(user.getUserEmail(), "【商院社交平台】验证码", "您的验证码为："+code+" 请尽快进行验证");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.getSession().setAttribute("emailCode", code);
				resultMap.put("success", true);
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "未登录");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/checkEmail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkEmail(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String emailCode = (String)request.getSession().getAttribute("emailCode");
		String checkCode = StringUtil.getStringParam(request, "checkCode");
		
		if(emailCode != null && checkCode != null) {
			if(emailCode.equals(checkCode)) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "验证码错误");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/upload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upload(MultipartFile avatar_file,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(avatar_file.isEmpty()) {
			resultMap.put("success", false);
			resultMap.put("error", "无文件");
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			try {
				String fileUrl = fileUpLoadServiceImpl.saveImage(avatar_file);
				resultMap.put("success", true);
				resultMap.put("fileUrl", fileUrl);
			} catch (IOException e) {
				resultMap.put("success", false);
				resultMap.put("error", "IO异常");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数异常");
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
