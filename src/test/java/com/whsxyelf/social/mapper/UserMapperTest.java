package com.whsxyelf.social.mapper;

import java.util.ArrayList;

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
	public void getsTest() {
		ArrayList<User> userList = mapper.getUsers();
		for(User user : userList) {
			System.out.println(user);
		}
	}

}
