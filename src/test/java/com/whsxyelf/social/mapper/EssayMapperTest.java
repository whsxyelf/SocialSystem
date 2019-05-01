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
import com.whsxyelf.social.bean.Essay;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class EssayMapperTest {
	@Autowired
	EssayMapper mapper;
	
	@Test
	@Ignore
	public void findEssayById() {
		Essay essay = mapper.findEssayById(6);
		System.out.println(essay);
	}
	
	@Test
	@Ignore
	public void findEssayListByUserId() {
		List<Essay> essayList = mapper.findEssayListByUserId(1);
		System.out.println(essayList.size());
	}
	
	@Test
	@Ignore
	public void addOne() {
		Essay essay = new Essay();
		essay.setUserId(1);
		essay.setEssayContent("123");
		essay.setEssayPhoto("345");
		int result = mapper.addOne(essay);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void updateEssay() {
		Essay essay = new Essay();
		essay.setEssayId(1);
		essay.setEssayContent("666");
		essay.setEssayPhoto("666");
		int result = mapper.updateEssay(essay);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteEssay() {
		int result = mapper.deleteEssay(7);
		System.out.println(result);
	}
	
	@Test
//	@Ignore
	public void countEssayByUserId() {
		int count = mapper.countEssayByUserId(1);
		System.out.println(count);
	}

}
