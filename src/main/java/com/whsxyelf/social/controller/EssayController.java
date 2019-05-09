package com.whsxyelf.social.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.packbean.Article;
import com.whsxyelf.social.service.impl.CollectServiceImpl;
import com.whsxyelf.social.service.impl.EssayServiceImpl;
import com.whsxyelf.social.service.impl.FileUpLoadServiceImpl;
import com.whsxyelf.social.service.impl.PraiseServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/essay")
public class EssayController {
	@Autowired
	EssayServiceImpl essayServiceImpl;
	@Autowired
	CollectServiceImpl collectServiceImpl;
	@Autowired
	PraiseServiceImpl praiseServiceImpl;
	@Autowired
	FileUpLoadServiceImpl fileUploadServiceImpl;

	@RequestMapping(value = "/getEssay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEssay(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int essayId = StringUtil.getIntParam(request, "essayId");

		if (essayId > 0) {
			Essay result = essayServiceImpl.GetEssay(essayId);
			if (result != null) {
				resultMap.put("success", true);
				resultMap.put("essay", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到本动态");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}

	@RequestMapping(value = "/getEssayList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEssayList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int userId = StringUtil.getIntParam(request, "userId");
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			List<Article> result = null;
			if (userId == -1) {
				result = essayServiceImpl.GetEssayList(user.getUserId());
				for(Article a: result) {
					try {
						int collectCount = collectServiceImpl.CountCollect(2, a.getEssayId());
						a.setCollectCount(collectCount);
						int praiseCount = praiseServiceImpl.countPraise(a.getEssayId());
						a.setPraiseCount(praiseCount);
					} catch (Exception e) {
						
					}
				}
			} else {
				result = essayServiceImpl.GetEssayList(userId);
			}
			if (result != null) {
				resultMap.put("success", true);
				resultMap.put("essayList", result);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未查询到用户的动态");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}

	@RequestMapping(value = "/matchEssayList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> matchEssayList(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String key = StringUtil.getStringParam(request, "key");

		if (key != null) {
			key = URLDecoder.decode(key, "UTF-8");
			List<Article> essayList = essayServiceImpl.MatchEssayList(key);
			if (essayList != null) {
				resultMap.put("success", true);
				resultMap.put("essayList", essayList);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未查询到动态");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数异常");
		}
		return resultMap;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> publish(@RequestParam("file") MultipartFile[] imgList,
			@RequestParam("essayContent") String essayContent, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			String filesUrl = null;
			Essay params = new Essay();
			if (imgList.length > 0) {
				try {
					filesUrl = fileUploadServiceImpl.saveImageList(imgList);
				} catch (IOException e) {
					resultMap.put("error", "图片存储失败");
				} finally {
					params.setEssayPhoto(filesUrl);
				}
			}
			params.setEssayContent(essayContent);
			params.setUserId(user.getUserId());

			boolean result = essayServiceImpl.Publish(params);
			if (result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "发布失败");
			}

		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数异常");
		}
		return resultMap;
	}

	@RequestMapping(value = "/wechat/publish", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> wechatPublish(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String essayStr = StringUtil.getStringParam(request, "essayStr");
		Essay params = null;
		try {
			params = JSON.parseObject(essayStr, Essay.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
		}

		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			Essay essay = new Essay();
			essay.setUserId(user.getUserId());
			essay.setEssayContent(params.getEssayContent());
			essay.setEssayPhoto(params.getEssayPhoto());
			boolean result = essayServiceImpl.Publish(essay);
			if (result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "发布失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "未登录");
		}
		return resultMap;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String essayStr = StringUtil.getStringParam(request, "essayStr");
		Essay params = null;
		try {
			params = JSON.parseObject(essayStr, Essay.class);
		} catch (JSONException e) {
			resultMap.put("success", false);
			resultMap.put("error", "JSONException");
			return resultMap;
		}

		User user = (User) request.getSession().getAttribute("user");

		if (params != null && user != null) {
			params.setUserId(user.getUserId());
			params.setLastEditTime(new Date());
			boolean result = essayServiceImpl.Update(params);
			if (result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "修改动态失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int essayId = StringUtil.getIntParam(request, "essayId");

		User user = (User) request.getSession().getAttribute("user");

		if (essayId > 0 && user != null) {
			boolean result = essayServiceImpl.DeleteOne(essayId, user.getUserId());
			if (result) {
				resultMap.put("success", true);
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "删除动态失败");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> count(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int userId = StringUtil.getIntParam(request, "userId");

		if (userId > 0) {
			int result = essayServiceImpl.Count(userId);
			resultMap.put("success", true);
			resultMap.put("count", result);

		} else {
			resultMap.put("success", false);
			resultMap.put("error", "参数错误");
		}
		return resultMap;
	}

}
