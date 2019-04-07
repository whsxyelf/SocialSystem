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
		User userOne = mapper.findOne(user);
		System.out.println(userOne.getUserEmail().toString());
		System.out.println(userOne.getPassword().toString());
	}
	
	@Test
	@Ignore
	public void addOne() {
		User user = new User();
		user.setUserNo("U00003");
		user.setUserEmail("1352823595@qq.com");
		user.setPassword("hujin12345");
		mapper.addOne(user);
	}
	
	@Test
	@Ignore
	public void editUserTest() {
		User user = new User();
		user.setUserNick("张小三");
		user.setUserPhoto("/1111003");
		//user.setUserEmail("");
		user.setSex(0);
		user.setPhone("");
		user.setSignature("一波退婚流！");
		user.setPermission("1");
		//user.setUserState(1);
		user.setUserNo("U00002");
		user.setUserState(1);
		mapper.editUser(user);
	}
	
	@Test
	@Ignore
	public void deleteUser() {
		User user = new User();
		user.setUserState(2);
		user.setUserEmail("1176851359@qq.com");
		mapper.deleteUser(user);
	}
	
	@Test
	@Ignore
	public void redisSave() {
		User user = new User();
		user.setUserNo("U00001");
		User u = mapper.saveNick(user);
		System.out.println(u.getUserNick().toString());
		
	}
}
