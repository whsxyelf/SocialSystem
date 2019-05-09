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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.whsxyelf.social.bean.Collect;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.PraiseServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/praise")
public class PraiseController {
	@Autowired
	PraiseServiceImpl praiseServiceImpl;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int essayId = StringUtil.getIntParam(request, "essayId");
		
		User user = (User)request.getSession().getAttribute("user");
		
		if(essayId > 0 && user != null) {
			if(praiseServiceImpl.isExist(essayId,user.getUserId())) {
				boolean result = praiseServiceImpl.delete(essayId, user.getUserId());
				if(result) {
					resultMap.put("success", true);
					resultMap.put("flag", -1);
				} else {
					resultMap.put("success", false);
					resultMap.put("error", "取消点赞失败");
				}
			} else {
				boolean result = praiseServiceImpl.add(essayId, user.getUserId());
				if(result) {
					resultMap.put("success", true);
					resultMap.put("flag", 1);
				} else {
					resultMap.put("success", false);
					resultMap.put("error", "点赞失败");
				}
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "json字符串解析失败");
		}
		return resultMap;
	}
}
