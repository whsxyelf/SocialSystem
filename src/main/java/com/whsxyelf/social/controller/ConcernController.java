package com.whsxyelf.social.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.ConcernServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/concern")
public class ConcernController {
	@Autowired
	ConcernServiceImpl concernServiceImpl;
	
	@RequestMapping(value="/getConcernList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getConcernList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int userId = StringUtil.getIntParam(request, "userId");
		
		if(userId > 0) {
			List<Concern> result = concernServiceImpl.GetConcernList(userId);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("concernList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到关注列表");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int concernedId = StringUtil.getIntParam(request, "concernedId");
		
		User user = (User)request.getSession().getAttribute("user");
		if(user.getUserId() == concernedId) {
			resultMap.put("success", false);
			resultMap.put("error", "不能自己关注自己");
		}
		
		if(concernedId > 0 && user != null) {
			boolean result = concernServiceImpl.Add(user.getUserId(), concernedId);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "添加关注失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int concernId = StringUtil.getIntParam(request, "concernId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(concernId > 0 && user != null) {
			boolean result = concernServiceImpl.Delete(user.getUserId(), concernId);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "取消关注失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/concernCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> concernCount(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int userId = StringUtil.getIntParam(request, "userId");
		
		if(userId > 0) {
			int count = concernServiceImpl.CountConcern(userId);
			resultMap.put("success", true);
			resultMap.put("count", count);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/fansCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> fansCount(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int userId = StringUtil.getIntParam(request, "userId");
		
		if(userId > 0) {
			int count = concernServiceImpl.CountFans(userId);
			resultMap.put("success", true);
			resultMap.put("count", count);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
}
