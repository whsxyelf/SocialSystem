package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.User;

//import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class UserMapperTest {
	@Autowired
	UserMapper mapper;
	
	@Test
	@Ignore
	public void getsTest() {
		//ArrayList<User> userList = mapper.getUsers();
		ArrayList<User> userList = mapper.findAll();
		for(User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	@Ignore
	public void findOneTest() {
		User user = new User();
		//user.setUserNo("U00002");
		user.setUserEmail("1176851359@qq.com");
		if(user.getUserState()==1) {
			User userOne = mapper.findOne(user);
			System.out.println(userOne.getUserEmail().toString());
		}else {
			System.out.println("该用户不存在或遭封禁！");
		}
		
	}
	
	@Test
	@Ignore
	public void addOne() {
		User user = new User();
		user.setUserNo("U00003");
		user.setUserEmail("1352823595@qq.com");
		user.setPassword("hujin12345");
		User userOne = mapper.findOne(user);
		if(userOne.getUserEmail()==null) {
			mapper.addOne(user);
			System.out.println("注册成功！");
		}else {
			System.out.println("该用户已存在！");
		}
		
	}
	
	@Test
	@Ignore
	public void editUserTest() {
		User user = new User();
		user.setUserNick("张小三");
		user.setUserPhoto("/1111003");
		user.setSex(0);
		user.setPhone("");
		user.setSignature("一波退婚流！");
		user.setUserEmail("1176851359@qq.com");
		mapper.editUser(user);
		System.out.println("yes");
	}
	
	@Test
	@Ignore
	public void operateUser() {
		User user = new User();
		user.setUserState(2);//删除用户
		user.setPermission(1);
		user.setUserEmail("1176851359@qq.com");
		if(user.getUserState()==1) {
			mapper.operateUser(user);
		}else {
			System.out.println("该条数据已被删除或封禁！");
		}
		
	}
	@Test
	@Ignore
	public void redisSave() {
		User user = new User();
		user.setUserNo("U00001");
		User u = mapper.saveNick(user);
		System.out.println(u.getUserNick().toString());
		
	}
	
	@Test
	@Ignore
	public void mohufind() {
		User user = new User();
		user.setUserNick("小");
		if(user.getUserState()==1) {
			List<User> list = mapper.findUserByNick(user);
			for(User u:list) {
				System.out.println(u.getUserNick().toString());
			}
		}else {
			System.out.println("fail!");
		}
		
	}
	
	@Test
	@Ignore
	public void gaimima() {
		User user = new User();
		user.setPassword("##");
		mapper.changePassword(user);
	}
}
