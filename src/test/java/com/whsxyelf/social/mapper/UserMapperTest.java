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
		ArrayList<User> userList = mapper.findAll();
		for(User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	@Ignore
	public void findOneTest() {//注册验证
		String email = "1352823595@qq.com";//输入框
		User user = mapper.findOne(email);
		if(user.getUserEmail()==null) {
			System.out.println("该账号暂未使用！");
		}
		if(user.getUserEmail()!=null&&user.getUserState()==2) {//user.getUserState()==2：注销;
			System.out.println("该账号已被用户注销！");
		}
	}
	
	@Test
	@Ignore
	public void addOne() {//注册
		String email = "451231@qq.com";
		String pass = "hujin12345";
		User u = mapper.findOne(email);
		if(u==null) {
			mapper.addOne(email,pass);
			System.out.println("注册成功！");
		}else {
			System.out.println("该邮箱已注册！");
		}
		
	}
	
	
	@Test
	@Ignore
	public void userLoginTest() {//登录
		//登录框输入
		String email = "1176851359@qq.com";
		String pass = "12345678";
		User u = mapper.userLogin(email,pass);
		if(u!=null) {
			System.out.println("登录成功！");
		}else {
			System.out.println("登录失败！或账号密码有误！");
		}		
	}
	
	@Test
	@Ignore
	public void editUserTest() {//编辑用户信息
		User user = new User();
		//登录的邮箱
		String email = "1176851359@qq.com"; 
		user.setUserNick("张小名");
		user.setUserPhoto("/1111003");
		user.setSex(1);
		user.setPhone("13419640634");
		user.setSignature("还在奋斗！");
		user.setUserEmail(email);
		mapper.editUser(user);
		System.out.println("编辑成功！");
	}
	
	@Test
	@Ignore
	public void operateUser() {
		User user = new User();
		user.setUserEmail("1176851359@qq.com");
		if(user.getUserState()==1) {
			mapper.operateUser(2,1);
		}else {
			System.out.println("该条数据已被删除或封禁！");
		}
		
	}
//	@Test
//	@Ignore
//	public void redisSave() {
//		User user = new User();
//		user.setUserNo(1);
//		User u = mapper.saveNick(user);
//		System.out.println(u.getUserNick().toString());
//	}
	
	@Test
	@Ignore
	public void mohufind() {//查询：模糊查询？
		//搜索框
		String nick = "小";
		List<User> list = mapper.findUserByNick(nick);
			if(list==null) {
				System.out.println("未找到！");
			}else {
				for(User u:list) {
					System.out.println(u.getUserNick().toString());
				}
			}		
	}
	
	@Test
	@Ignore
	public void gaimima() {
		String email = "";
		String pass = "";//新密码
		mapper.changePassword(email,pass);
	}
}
