package com.whsxyelf.social.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Collect;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class CollectMapperTest {
	@Autowired
	CollectMapper mapper;
	
	@Test
	@Ignore
	public void findCollectListByUserId() {
		
	}
	
	@Test
	@Ignore
	public void addOne() {
		Collect collect = new Collect();
		collect.setUserId(1);
		collect.setCollectType(1);
		collect.setCollectedId(3);
		int result =  mapper.addOne(collect);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteCollectByCollectId() {
		int result = mapper.deleteCollectByCollectId(1,1);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteCollectByUserId() {
		int result = mapper.deleteCollectByUserId(1);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void countCollect() {
		Collect collect = new Collect();
		collect.setCollectType(1);
		collect.setCollectedId(3);
		int count = mapper.countCollect(1,3);
		System.out.println(count);
	}
}
