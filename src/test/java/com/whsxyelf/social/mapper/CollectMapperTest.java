package com.whsxyelf.social.mapper;


import java.util.ArrayList;

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
	private CollectMapper mapper;
	@Test
	@Ignore
	public void addCollect() {
		Collect collect = new Collect();
		collect.setUserNo(2);
		collect.setCollectionType(1);
		collect.setCollectedId(7);
		//mapper.addCollect(collect);
	}
	
	@Test
	@Ignore
	public void cancelCollect() {
		Collect collect = new Collect();
	    collect.setUserNo(1);
	    collect.setCollectionType(1);
	    collect.setCollectedId(2);
	    //mapper.cancelCollect(collect);
	}
	
	@Test
	public void showCollectList() {
		//ArrayList<Collect> list = mapper.showCollectList(6);
//		if(list!=null) {
//			for(Collect c:list) {
//				System.out.println(c.getUserNo());
//				System.out.println(c.getCollectionType());
//				System.out.println(c.getCollectedId());
//			}
//		}else {
//			System.out.println("列表为空！");
//		}
		
	}
}
