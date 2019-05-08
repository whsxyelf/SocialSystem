package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.News;

public interface NewsService {
	public News GetNews(int newsId);
	public List<News> Recommend(int newsId[]);
	public int Count();
	public List<News> newsTest();
	public List<News> GetNewsByUserId(int userId);
	public List<News> GetRandomNewsList(int limit);
	public List<News> GetNewsListByKey(String key);
}
