package com.whsxyelf.social.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class UserServiceTest {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Test
	@Ignore
	public void Register() {
		User user = new User();
		boolean result = userServiceImpl.Register(user);
		System.out.println(result);
	};
	
	@Test
	@Ignore
	public void Login() {
		User user = new User();
//		user.setPhone("123456789");
//		user.setPassword("zxyono");
		User result = userServiceImpl.Login(user);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void isExist() {
		User user = new User();
//		user.setUserEmail("zxyono@163.com");
//		user.setPhone("123456789");
		boolean result = userServiceImpl.isExist(user);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void Update() {
		User user = new User();
		user.setUserNick("猴王");
		user.setUserId(2);
		
		boolean result = userServiceImpl.Update(user);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void Search() {
		List<User> userList = userServiceImpl.Search("猴");
		System.out.println(userList.size());
	}
	
	@Test
	@Ignore
	public void Recommend() {
	}
	
}
