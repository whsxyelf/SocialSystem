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
	@Select("select user_id,user_nick,user_photo,user_email,sex,phone,signature "
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
	public List<User> findUsersByList(@Param("userId")List<Integer> userId);
	
	@Insert("insert into user(user_nick,user_email,signature,password) values(#{userNick},#{userEmail},#{signature},#{password})")
	public int addOne(User user);
	
	@UpdateProvider(type=UserProvider.class,method="updateUser")
	public int updateUser(User user);
	
	@Insert("insert into user(user_nick,signature,phone,password) values(#{userNick},#{signature},#{phone},#{password})")
	public int addMobileUser(User user);
	
	
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
		
		public String findUsersByList(List<Integer> userId) {
			if(userId.size() == 0) {
				return "select * from user where 1=-1";
			} else {
				StringBuilder list = new StringBuilder();
				StringBuilder list2 = new StringBuilder();
				list.append("("+ userId.get(0));
				list2.append("(user_id,"+userId.get(0));
				int max = userId.size();
				for(int i=1;i<max;i++) {
					list.append(","+userId.get(i));
					list2.append(","+userId.get(i));
				}
				list.append(")");
				list2.append(")");
				return "select user_id,user_nick,user_photo,user_email,sex,phone,signature,permission,user_state,create_time "
						+ "from user where user_id in " + list.toString() +" order by field" +list2.toString();
			}
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
				if(user.getUserState() != null) {
					SET("user_state=#{userState}");
				}
				if(user.getUserPhoto() != null) {
					SET("user_photo=#{userPhoto}");
				}
				if(user.getPassword() != null) {
					SET("password=#{password}");
				}
				SET("last_edit_time=#{lastEditTime}");
				WHERE("user_id=#{userId}");
			}}.toString();
		}
	}
}
