package com.whsxyelf.social.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.bean.History;
import com.whsxyelf.social.bean.News;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.CommentServiceImpl;
import com.whsxyelf.social.service.impl.HistoryServiceImpl;
import com.whsxyelf.social.service.impl.NewsServiceImpl;
import com.whsxyelf.social.util.JsoupUtil;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsServiceImpl newsServiceImpl;
	@Autowired
	CommentServiceImpl commentServiceImpl;
	@Autowired
	HistoryServiceImpl historyServiceImpl;
	
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
	
	@RequestMapping(value="/newsInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newsInfo(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int newsId = StringUtil.getIntParam(request, "newsId");
		
		if(newsId > 0) {
			News result = newsServiceImpl.GetNews(newsId);
			if(result != null) {
				String detail = "";
				try {
					if(result.getNewsFrom().equals("163")) {
						detail = JsoupUtil.get163News(result.getNewsUrl());
					} else if(result.getNewsFrom().equals("sina")) {
						detail = JsoupUtil.getSinaNews(result.getNewsUrl());
					} else if(result.getNewsFrom().equals("People")) {
						detail = JsoupUtil.getPeopleNews(result.getNewsUrl());
					} else {
						detail = "未知来源";
					}
				} catch (IOException e) {
					resultMap.put("success", false);
					resultMap.put("error", "新闻文本解析失败");
				} finally {
					User user = (User)request.getSession().getAttribute("user");
					if(user != null) {
						History history = historyServiceImpl.isExist(user.getUserId(), newsId);
						if(history == null) {
							History params = new History();
							params.setNewsId(newsId);
							params.setUserId(user.getUserId());
							boolean flag = historyServiceImpl.add(params);
							if(!flag) {
								resultMap.put("msg", "历史记录存储失败");
							}
						} 
					}
					resultMap.put("success", true);
					resultMap.put("newsContent", detail);
					resultMap.put("news", result);
				}
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到新闻");
			}
			
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/getNewsList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getNewsList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			List<News> result = newsServiceImpl.GetNewsByUserId(user.getUserId());
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("newsList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "为找到新闻列表");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/getRandomNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRandomNews(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int limit = StringUtil.getIntParam(request, "limit");
		
		if(limit > 0 ) {
			List<News> result = newsServiceImpl.GetRandomNewsList(limit);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("newsList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "为找到新闻列表");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/getNewsByKey",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getNewsByKey(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String key = StringUtil.getStringParam(request, "key");
		
		if(key != null) {
			key = URLDecoder.decode(key, "UTF-8");
			List<News> result = newsServiceImpl.GetNewsListByKey(key);
			if(result != null) {
				resultMap.put("success", true);
				resultMap.put("newsList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "为找到新闻列表");
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
	
	@RequestMapping(value="/newstest",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> newstest(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<News> result = newsServiceImpl.newsTest();
		resultMap.put("newsList", result);
		resultMap.put("success", true);
		return resultMap;
	}
	
	
}
