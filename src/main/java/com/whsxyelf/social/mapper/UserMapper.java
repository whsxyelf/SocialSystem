
package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.User;


@Mapper
public interface UserMapper {
	/*
	 * 需求：插入一条用户信息
	 */
//	@Insert("INSERT INTO USER(user_no,user_email,password)VALUES(#{userNo},#{userEmail},#{password});")
//	public int add(User user);
	
//	@Select("SELECT * FROM user")
//	public ArrayList<User> getUsers();
	
	/*
	 * 改用结构化的sql注解
	 * 根据用户编号查询用户user_email,密码：password
	 */
	@SelectProvider(method = "findOne", type = UserDaoProviderFindOne.class)
	public User findOne(User userOne);
	class UserDaoProviderFindOne {
		 public String findOne(User user) {
			return new SQL(){{
				SELECT("user_email,password");
				FROM("user");
				if(user.getUserNo()!=null&&!user.equals("")) {
					WHERE("user_no=#{userNo}");
				}
				}}.toString();//其内部使用StringBuilder来实现拼接
		 }
	 }
	//查询所有用户信息(用户后台管理)
	@SelectProvider(method = "findAll",type = UserDaoProvicerFindAll.class)
	public ArrayList<User> findAll();
	class UserDaoProvicerFindAll{
		public String findAll() {
			return new SQL() {{
				SELECT("user_no","user_nick","user_email","password");
				SELECT("sex","phone","permission","user_state");
				FROM("user");
			}}.toString();
		}
	}
	
	//插入一条用户信息
	@InsertProvider(method = "addOne",type =UserDaoProviderAddOne.class)
	public int addOne(User user);
	class UserDaoProviderAddOne{
		public String addOne(User user) {
			return new SQL() {{
				INSERT_INTO("user");
				VALUES("user_no","#{userNo}");
				VALUES("user_email","#{userEmail}");
				VALUES("password","#{password}");
			}}.toString();
		}
	}
	
	//编辑用户信息
	@UpdateProvider(method = "editUser",type = UserDaoProvideEditUser.class)
	public int editUser(User user);
	class UserDaoProvideEditUser{
		public String editUser(User user) {
			return new SQL(){{
				UPDATE("user");
				SET("user_nick=#{userNick}");//昵称
				SET("user_photo=#{userPhoto}");//头像
				//SET("user_email=userEmail");//邮箱
				//SET("password=#{password}");//密码
				SET("sex=#{sex}");//性别
				SET("phone=#{phone}");//电话
				SET("signature=#{signature}");//签名
				if(user.getPermission()=="管理员") {
					SET("permission=#{permission}");//权限
				}
				//SET("user_state=#{userState}");//状态
				//用户编号不为空，且用户状态为1：账号有效
				if(user.getUserNo()!=null&&user.getUserState()==1) {
					WHERE("user_no=#{userNo}");
				}
//				if(user.getUserEmail()!=null){
//					WHERE("user_email=#{userEmail}");
//				}
			}}.toString();
		}
	}
	
	//删除用户信息;管理员操作
	@UpdateProvider(method = "deleteUser",type = UserDaoProviderDeleteUser.class)
	public int deleteUser(User user);
	class UserDaoProviderDeleteUser{
		public String deleteUser(User user) {
			return new SQL() {{
				UPDATE("user");
				if(user.getUserState()!=1) {
					SET("user_state=#{userState}");
				}
				WHERE("user_email=#{userEmail}");
			}}.toString();
			
		}
	}
	
}
