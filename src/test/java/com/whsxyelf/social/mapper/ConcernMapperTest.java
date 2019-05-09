package com.whsxyelf.social.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class ConcernMapperTest {
	@Autowired
	ConcernMapper mapper;
	
	@Test
	@Ignore
	public void findConcernListByUserId() {
		
	}
	
	@Test
	@Ignore
	public void addOne() {
		Concern concern = new Concern();
		concern.setUserId(1);
		concern.setConcernedId(4);
		int result = mapper.addOne(1,4);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteConcern() {
		Concern concern = new Concern();
		concern.setUserId(1);
		concern.setConcernId(4);
		int result = mapper.deleteConcern(1,4);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void countConcern() {
		int count = mapper.countConcern(1);
		System.out.println(count);
	}
	
	@Test
	@Ignore
	public void countConcerned() {
		int count = mapper.countFans(1);
		System.out.println(count);
	}

}
