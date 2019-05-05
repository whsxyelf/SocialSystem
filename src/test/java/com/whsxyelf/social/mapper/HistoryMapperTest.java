package com.whsxyelf.social.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.History;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class HistoryMapperTest {
	@Autowired
	HistoryMapper mapper;
	
	@Test
	@Ignore
	public void findHistoryById() {
		History history = mapper.findHistoryById(1);
		System.out.println(history);
	}
	
	@Test
	@Ignore
	public void findHistoryListByUserId() {
		List<History> historyList = mapper.findHistoryListByUserId(1);
		System.out.println(historyList.size());
	}
	
	@Test
	@Ignore
	public void addHistory() {
		History history = new History();
		history.setUserId(1);
		history.setNewsId(1);
		history.setCreateTime(new Date());
		history.setLastEditTime(new Date());
		int result = mapper.addHistory(history);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void updateHistory() {
		History history = new History();
		history.setHistoryId(1);
		history.setScore(10.0);
		history.setLastEditTime(new Date());
		int result = mapper.addHistory(history);
		System.out.println(result);
	}
}
