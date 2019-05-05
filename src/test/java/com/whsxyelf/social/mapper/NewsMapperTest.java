package com.whsxyelf.social.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.News;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class NewsMapperTest {
	@Autowired
	NewsMapper mapper;
	
	@Test
	@Ignore
	public void findNewsById() {
		News news = mapper.findNewsById(10);
		System.out.println(news);
	}
	
	@Test
	@Ignore
	public void findNewsByList() {
		int newsId[] = {6,34,5,8,22};
		List<News> newsList = mapper.findNewsByList(newsId);
		System.out.println(newsList.size());
	}
	
	@Test
	@Ignore
	public void countNews() {
		System.out.println(mapper.countNews());
	}
}
