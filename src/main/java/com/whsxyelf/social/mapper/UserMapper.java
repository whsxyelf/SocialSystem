
package com.whsxyelf.social.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.whsxyelf.social.bean.User;


@Mapper
public interface UserMapper {
	/*
	 * 需求：插入一条用户信息
	 */
	@Insert("INSERT INTO USER(user_no,user_email,password)VALUES(#{userNo},#{userEmail},#{password});")
	public int add(User user);
	
	@Select("SELECT * FROM user")
	public ArrayList<User> getUsers();
}
