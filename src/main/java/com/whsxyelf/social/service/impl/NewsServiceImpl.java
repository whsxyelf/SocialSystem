package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.News;
import com.whsxyelf.social.mapper.NewsMapper;
import com.whsxyelf.social.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	NewsMapper mapper;

	@Override
	public News GetNews(int newsId) {
		News result = mapper.findNewsById(newsId);
		return result;
	}

	@Override
	public List<News> Recommend(int[] newsId) {
		List<News> newsList = mapper.findNewsByList(newsId); 
		return newsList;
	}

	@Override
	public int Count() {
		int count = mapper.countNews();
		return count;
	}

	@Override
	public List<News> GetNewsByUserId(int userId) {
		List<News> newsList = mapper.findNewsListByUserId(userId);
		return newsList;
	}

	@Override
	public List<News> newsTest() {
		List<News> newsList = mapper.newsListTest();
		return newsList;
	}
	
	
}
