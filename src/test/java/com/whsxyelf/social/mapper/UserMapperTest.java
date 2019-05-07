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
import com.whsxyelf.social.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class UserMapperTest {
	@Autowired
	UserMapper mapper;
	
	@Test
	@Ignore
	public void findUserById() {
		User user = mapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	@Ignore
	public void findUserByParams() {
		User params = new User();
//		params.setUserEmail("1");
		params.setPhone("1");
//		params.setPassword("1");
		User result = mapper.findUserByParams(params);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void findUserByEmailOrPhone() {
		User params = new User();
//		params.setUserEmail("zxyono@163.com");
		params.setPhone("123456789");
		User result = mapper.findUserByEmailOrPhone(params);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void findUserByUserName() {
		List<User> userList = mapper.findUserByUserName("1");
		System.out.println(userList.size());
	}
	
	@Test
	@Ignore
	public void findUsersByList() {
		int userId[] = {1,2};
		List<User> userList = mapper.findUsersByList(userId);
		System.out.println(userList.size());
	}
	
	@Test
//	@Ignore
	public void addOne() {
		User user = new User();
//		user.setUserEmail("123@163.com");
		user.setUserNick("11sdsd1");
		user.setSignature("1111");
		user.setPhone("12971423523");
		user.setPassword("123456");
		int result = mapper.addOne(user);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void updateUser() {
		User user = new User();
		user.setUserNick("therion");
		user.setUserId(1);
		user.setLastEditTime(new Date());
		int result = mapper.updateUser(user);
		System.out.println(result);
	}
}
