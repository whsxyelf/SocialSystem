//package com.whsxyelf.social.mapper;
//
//
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.whsxyelf.social.SocialApplication;
//import com.whsxyelf.social.bean.Concern;
//import com.whsxyelf.social.bean.User;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SocialApplication.class)
//public class ConcernMapperTest {
//	
//	
//	
//	@Autowired
//	ConcernMapper mapper;
//	
//	@Test
//	@Ignore
//	public void concernTest() throws ParseException {
//		Concern concern = new Concern();
//		concern.setUserNo(6);
//		concern.setConcernedId(5);
//		Date now = new Date();
//		//yyyy-MM-dd
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String current = df.format(now);
////		concern.setCreateTime(df.parse(current));
//		//Concern con = mapper.haveConcern(concern);
////		if(con==null) {
////			mapper.concernUser(concern);	
////		}else {
////			System.out.println("关注失败！");
////		}
//		
//	}
//	
//	@Test
//	@Ignore
//	public void cancelConcernTest() {
//		Concern concern = new Concern();
//		concern.setUserNo(5);
//		concern.setConcernedId(6);
//		//Concern con =  mapper.haveConcern(concern);
////		if(con!=null) {
////			mapper.concernCancel(concern);
////			System.out.println("取关成功！");
////		}else {
////			System.out.println("取关失败！");
////		}
//	}
//	
//	@Test
//	@Ignore
//	public void concernList() {
//		User user = new User();
//		user.setUserNo(1);
//		//ArrayList<Concern> list = mapper.concernList(user);
//		System.out.println("列表");
////		if(list!=null) {
////			for(Concern con:list) {
////				System.out.println(con.getConcernedId());
////			}	
////		}else {
////			System.out.println("列表为空！");
////		}
//		
//	}
//	
//	@Test
//	public void haveConcern() {
////		Concern concern = new Concern();
////		concern.setUserNo(1);
////		concern.setConcernedId(2);
//		//Concern con = mapper.haveConcern(concern);
////		if(con!=null) {
////			System.out.println(con.getUserNo());
////			System.out.println(con.getConcernedId());
////		}else {
////			System.out.println("未关注该用户！");
////		}
////		 int num = mapper.selectCount(5);
////		 System.out.println(num);
//	}
//	
//}
