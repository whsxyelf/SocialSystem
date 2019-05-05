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
import com.whsxyelf.social.bean.SubComment;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.SubCommentServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@RequestMapping("/subcomment")
public class SubCommentController {
	@Autowired
	SubCommentServiceImpl subCommentServiceImpl;
	
	@RequestMapping(value="/getSubCommentList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSubCommentList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int commentedId = StringUtil.getIntParam(request, "commentedId");
		
		if(commentedId > 0) {
			List<SubComment> result = subCommentServiceImpl.GetSubCommentList(commentedId);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("subCommentList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到子评论列表");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数为空");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String subCommentStr = StringUtil.getStringParam(request, "subCommentStr");
		SubComment params = null;
		try {
			params = JSON.parseObject(subCommentStr, SubComment.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			boolean result = subCommentServiceImpl.Add(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "添加子评论失败");
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
		
		String subCommentStr = StringUtil.getStringParam(request, "subCommentStr");
		SubComment params = null;
		try {
			params = JSON.parseObject(subCommentStr, SubComment.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			boolean result = subCommentServiceImpl.Update(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "修改子评论失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int subCommentId = StringUtil.getIntParam(request, "subCommentId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(subCommentId > 0 && user != null) {
			boolean result = subCommentServiceImpl.DeleteOne(subCommentId,user.getUserId());
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "删除子评论失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/deleteByComment",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteByComment(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int commentId = StringUtil.getIntParam(request, "commentId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(commentId > 0 && user != null) {
			boolean result = subCommentServiceImpl.DeleteByComment(commentId,user.getUserId());
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "删除父评论下的所有子评论失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/count",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> count(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int commentedId = StringUtil.getIntParam(request, "commentedId");
		
		if(commentedId > 0) {
			int count = subCommentServiceImpl.Count(commentedId);
			resultMap.put("success", true);
			resultMap.put("count", count);
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
}
