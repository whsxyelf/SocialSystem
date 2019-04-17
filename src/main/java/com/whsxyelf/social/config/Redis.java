package com.whsxyelf.social.config;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.User;

@Mapper
public interface Redis {
	//1.保存所有有效用户的 编号、昵称 ——>redis
	@SelectProvider(method = "saveToRedis",type = UserDaoProvider.class)
	public List<User> saveToRedis();
	
	class UserDaoProvider{
		public String saveToRedis() {
			return new SQL() {{
				SELECT("user_no,user_nick,user_photo");
				FROM("user");
			}}.toString();	
		}
	}
}
