package com.whsxyelf.social.mapper;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Concern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class ConcernMapperTest {
	
	
	
	@Autowired
	ConcernMapper mapper;
	//@Test
	public void concernTest() throws ParseException {
		Concern concern = new Concern();
		concern.setUserNo("U00003");
		concern.setConcernedId("U00002");
		Date now = new Date();
		//yyyy-MM-dd
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current = df.format(now);
		concern.setCreateTime(df.parse(current));
		Concern con = mapper.findHave(concern);
		if(con==null) {
			mapper.concernUser(concern);	
		}else {
			System.out.println("关注失败！");
		}
		
	}
	
	//@Test
	public void cancelConcernTest() {
		Concern concern = new Concern();
		concern.setUserNo("U00003");
		concern.setConcernedId("U00002");
		Concern con =  mapper.findHave(concern);
		if(con!=null) {
			mapper.concernCancel(concern);
		}else {
			System.out.println("取关失败！");
		}
		
	}
}
