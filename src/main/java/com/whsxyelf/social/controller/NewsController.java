package com.whsxyelf.social.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.bean.News;
import com.whsxyelf.social.service.impl.NewsServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsServiceImpl newsServiceImpl;
	
	@RequestMapping(value="/getNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getNews(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int newsId = StringUtil.getIntParam(request, "newsId");
		if(newsId > 0) {
			News result = newsServiceImpl.GetNews(newsId);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("news", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到本新闻");
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
		int count = newsServiceImpl.Count();
		resultMap.put("success", true);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@RequestMapping(value="/recommend",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> recommend(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
	}
}
