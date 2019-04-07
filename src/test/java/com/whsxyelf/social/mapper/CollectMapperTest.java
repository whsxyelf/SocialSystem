package com.whsxyelf.social.mapper;


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
	private CollectMapper mapper;
	@Test
	public void addCollect() {
		Collect collect = new Collect();
		collect.setUserNo("U00002");
		collect.setCollectionType(1);
		collect.setCollectedId(7);
		mapper.addCollect(collect);
	}

}
