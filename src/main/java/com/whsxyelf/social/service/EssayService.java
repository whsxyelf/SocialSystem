package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.packbean.Article;

public interface EssayService {
	public Essay GetEssay(int essayId);
	public List<Article> GetEssayList(int userId);
	public boolean Publish(Essay essay);
	public boolean Update(Essay essay);
	public boolean DeleteOne(int essayId,int userId);
	public int Count(int userId);
	
}
