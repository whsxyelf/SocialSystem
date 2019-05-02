package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.User;

@Mapper
public interface UserMapper {
	@Select("select user_id,user_nick,user_photo,user_email,sex,phone,signature,permission,user_state,create_time "
			+ "from user where user_id=#{userId}")
	public User findUserById(int userId);
	
	@SelectProvider(type=UserProvider.class,method="findUserByParams")
	public User findUserByParams(User user);
	
	@SelectProvider(type=UserProvider.class,method="findUserByEmailOrPhone")
	public User findUserByEmailOrPhone(User user);
	
	@Select("select user_id,user_nick,user_photo,user_email,sex,phone,signature,permission,user_state,create_time "
			+ "from user where user_nick like '%${userNick}%'")
	public List<User> findUserByUserName(@Param("userNick")String userNick);
	
	@SelectProvider(type=UserProvider.class,method="findUsersByList")
	public List<User> findUsersByList(@Param("userId")int userId[]);
	
	@Insert("insert into user(user_email,phone,password) values(#{userEmail},#{phone},#{password})")
	public int addOne(User user);
	
	@UpdateProvider(type=UserProvider.class,method="updateUser")
	public int updateUser(User user);
	
	
	class UserProvider {
		public String findUserByParams(User user) {
			return new SQL() {{
				SELECT("user_id,user_nick,user_photo,user_email,sex,phone,signature,permission,user_state,create_time");
				FROM("user");
				if(user.getUserEmail() != null) {
					WHERE("user_email=#{userEmail}");
				} else if(user.getPhone() != null) {
					WHERE("phone=#{phone}");
				} else {
					WHERE("1 != 1");
				}
				WHERE("password=#{password}");
			}}.toString();
		}
		
		public String findUserByEmailOrPhone(User user) {
			return new SQL() {{
				SELECT("user_id,user_nick");
				FROM("user");
				if(user.getUserEmail() != null) {
					WHERE("user_email=#{userEmail}");
				} else if(user.getPhone() != null) {
					WHERE("phone=#{phone}");
				}  else if(user.getUserNick() != null) {
					WHERE("user_nick=#{userNick}");
				} else {
					WHERE("1 != 1");
				}
				
				if(user.getUserId() != null) {
					WHERE("user_id!=#{userId}");
				}
			}}.toString();
		}
		
		public String findUsersByList(int userId[]) {
			StringBuilder list = new StringBuilder();
			list.append("("+ userId[0]);
			int max = userId.length;
			for(int i=1;i<max;i++) {
				list.append(","+userId[i]);
			}
			list.append(")");
			return "select user_id,user_nick,user_photo,user_email,sex,phone,signature,permission,user_state,create_time "
					+ "from user where user_id in " + list.toString();
		}
		
		public String updateUser(User user) {
			return new SQL() {{
				UPDATE("user");
				if(user.getUserNick() != null) {
					SET("user_nick=#{userNick}");
				}
				if(user.getSex() != null) {
					SET("sex=#{sex}");
				}
				if(user.getSignature() != null) {
					SET("signature=#{signature}");
				}
				if(user.getPermission() != null) {
					SET("permission=#{permission}");
				}
				if(user.getPermission() != null) {
					SET("user_state=#{userState}");
				}
				SET("last_edit_time=#{lastEditTime}");
				WHERE("user_id=#{userId}");
			}}.toString();
		}
	}
}
