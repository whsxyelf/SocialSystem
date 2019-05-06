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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.CommentServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentServiceImpl commentServiceImpl;
	
	@RequestMapping(value="/getCommentList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCommentList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int commentType = StringUtil.getIntParam(request, "commentType");
		int commentedId = StringUtil.getIntParam(request, "commentedId");
		
		if(commentType > 0 && commentedId > 0) {
			List<Comment> result = commentServiceImpl.GetCommentList(commentType, commentedId);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("commentList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到评论列表");
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
		
		String commentStr = StringUtil.getStringParam(request, "commentStr");
		Comment params = null;
		try {
			params = JSON.parseObject(commentStr, Comment.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			boolean result = commentServiceImpl.Add(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "添加评论失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String commentStr = StringUtil.getStringParam(request, "commentStr");
		Comment params = null;
		try {
			params = JSON.parseObject(commentStr, Comment.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(params != null && user != null) {
			params.setUserId(user.getUserId());
			boolean result = commentServiceImpl.Update(params);
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "修改评论失败");
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
		
		int commentId = StringUtil.getIntParam(request, "commentId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(commentId > 0 && user != null) {
			boolean result = commentServiceImpl.Delete(commentId,user.getUserId());
			if(result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "删除评论失败");
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
		
		int commentType = StringUtil.getIntParam(request, "commentType");
		int commentedId = StringUtil.getIntParam(request, "commentedId");
		
		if(commentType > 0 && commentedId > 0) {
			int count = commentServiceImpl.Count(commentType, commentedId);
			if(count > 0) {
				resultMap.put("success", true);
				resultMap.put("count", count);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到评论数");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
}
