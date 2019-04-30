package com.whsxyelf.social.service;

import java.util.ArrayList;

import com.whsxyelf.social.bean.Essay;

public interface EssayService {
	
	//	1.发布动态
	public int publish(Essay essay);
	
	//	2.删除动态
	public int cleanOne(int essayId);
	
	//	3.显示动态列表：显示用户自己的动态和所关注对象的动态
	public ArrayList<Essay> showEssayList(int userNo);
	
	//	4.显示动态详情
	
}
