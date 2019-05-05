package com.whsxyelf.social.controller;

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
import com.whsxyelf.social.bean.Collect;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.CollectServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@RequestMapping("/collect")
public class CollectController {
	@Autowired
	CollectServiceImpl collectServiceImpl;
	
	@RequestMapping(value="/getCollectList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> UserRegister(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int userId = StringUtil.getIntParam(request, "userId");
		
		if(userId > 0) {
			List<Collect> result = collectServiceImpl.GetCollectList(userId);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("collectList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到收藏列表");
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
		
		String collectStr = StringUtil.getStringParam(request, "collectStr");
		Collect params = null;
		try {
			params = JSON.parseObject(collectStr, Collect.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			boolean result = collectServiceImpl.Add(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "添加收藏失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/deleteByCollectId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteByCollectId(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int collectId = StringUtil.getIntParam(request, "collectId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(collectId > 0 && user != null) {
			boolean result = collectServiceImpl.DeleteByCollectId(collectId, user.getUserId());
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "取消收藏失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/deleteByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteByUserId(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			boolean result = collectServiceImpl.DeleteByUserId(user.getUserId());
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "取消收藏失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/collectCount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> collectCount(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int collectType = StringUtil.getIntParam(request, "collectType");
		int collectedId = StringUtil.getIntParam(request, "collectedId");
		
		if(collectType > 0 && collectedId > 0) {
			int count = collectServiceImpl.CountCollect(collectType, collectedId);
			resultMap.put("success", true);
			resultMap.put("count", count);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}

}
