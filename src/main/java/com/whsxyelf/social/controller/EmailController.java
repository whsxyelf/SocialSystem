package com.whsxyelf.social.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whsxyelf.social.util.EmailUtil;
import com.whsxyelf.social.util.StringUtil;

@Controller
@RequestMapping("/send")
public class EmailController {
	@Autowired
	EmailUtil email;
	
	@RequestMapping(value="/checkCode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendEmail(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String sendTo = StringUtil.getStringParam(request, "sendTo");
		String title = StringUtil.getStringParam(request, "title");
		String content = StringUtil.getStringParam(request, "content");
		
		email.sendSimpleEmail(sendTo, title, content);
		
		resultMap.put("success", true);
		return resultMap;
	}
}
