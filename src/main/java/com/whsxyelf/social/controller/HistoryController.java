package com.whsxyelf.social.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.bean.History;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.HistoryServiceImpl;
import com.whsxyelf.social.util.StringUtil;

@Controller
@CrossOrigin
@RequestMapping("/history")
public class HistoryController {
	@Autowired
	HistoryServiceImpl historyServiceImpl;
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int newsId = StringUtil.getIntParam(request, "newsId");
		int length = StringUtil.getIntParam(request, "length");
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null) {
			History history = historyServiceImpl.isExist(user.getUserId(), newsId);
			if(history != null) {
				double originScore = history.getScore();
				double score = originScore + 150/(length+0.0);
				if(score <= 10) {
					history.setScore(score);
					history.setLastEditTime(new Date());
					boolean result = historyServiceImpl.update(history);
					if(result) {
						resultMap.put("success", true);
					} else {
						resultMap.put("success", false);
						resultMap.put("error", "更新历史记录失败");
					}
				} else {
					resultMap.put("success", false);
					resultMap.put("error", "无需更新");
				}
			} else {
				resultMap.put("success", false);
				resultMap.put("error", "未找到历史记录");
			}
		} else {
			resultMap.put("success", false);
			resultMap.put("error", "未登录");
		}
		return resultMap;
	}
}
