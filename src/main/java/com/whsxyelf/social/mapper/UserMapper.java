
package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.User;


@Mapper
public interface UserMapper {
	//1.1.根据邮箱查询用户信息是否存在
	@SelectProvider(method = "findByUserEmail", type = UserDaoProvider.class)
	public User findByUserEmail(@Param("userEmail")String userEmail);
	
	//2.增加用户信息到数据表
	@InsertProvider(method = "addOne",type =UserDaoProvider.class)
	public int addOne(User user);
	
	//3.根据邮箱、密码查询用户信息
	@SelectProvider(method = "userByEmailLogin", type =UserDaoProvider.class)
	public User userByEmailLogin(User user);
	
	//4.根据手机号、密码查询用户信息
	@SelectProvider(method = "userByPhoneLogin", type =UserDaoProvider.class)
	public User userByPhoneLogin(User user);
	
	//5.根据邮箱号更新密码
	@UpdateProvider(method = "changePassword",type =UserDaoProvider.class)
	public int changePassword(User user);
	
	//6.根据昵称模糊查询用户表
	@SelectProvider(method = "findUserByNick",type = UserDaoProvider.class)
	public List<User> findUserByNick(@Param("userNick")String userNick);
	
	//7.根据用户编号更新用户表
	@UpdateProvider(method = "editUser",type = UserDaoProvider.class)
	public int editUser(User user);
	
//	//8.
//	@SelectProvider(method = "selectInfor",type = UserDaoProvider.class)
//	public Social selectInfor(Social social);
	
	//9.查询用户列表
	@SelectProvider(method = "findAll",type = UserDaoProvider.class)
	public ArrayList<User> findAll();
	
	//10.根据邮箱号更新userState、permission
	@UpdateProvider(method = "operateUser",type = UserDaoProvider.class)
	public int operateUser(User user);
	
	//11.根据编号查询用户表
	@SelectProvider(method = "saveToRedis",type = UserDaoProvider.class)
	public User saveToRedis(@Param("userNo")int userNo);
	
		class UserDaoProvider{
				//1.
				public String findByUserEmail(String userEmail) {
					return new SQL(){{
						SELECT("user_no");
						SELECT("user_email");
						SELECT("user_state");
						FROM("user");
						WHERE("user_email=#{userEmail}");
					}}.toString();//其内部使用StringBuilder来实现拼接
				}
		
				//2.
				public String addOne(User user) {
					return new SQL() {{
						INSERT_INTO("user");
						VALUES("user_email","#{userEmail}");
						VALUES("password","#{password}");
				}}.toString();
				}
		
				//3.
				public String userByEmailLogin(User user) {
					return new SQL(){{
						SELECT("user_email,password");
						FROM("user");
						WHERE("user_email=#{userEmail}");
						WHERE("password=#{password}");
						}}.toString();//其内部使用StringBuilder来实现拼接
				}
		 
				//4.
				public String userByPhoneLogin(User user) {
					return new SQL(){{
						SELECT("phone,password");
						FROM("user");
						WHERE("phone=#{phone}");
						WHERE("password=#{password}");
						}}.toString();//其内部使用StringBuilder来实现拼接
				}
		
				//5.
				public String changePassword(User user) {
					return new SQL() {{
						UPDATE("user");
						SET("password=#{password}");//密码
						WHERE("user_email=#{userEmail}");//获取邮箱验证码
					}}.toString();	
				}
		 
				//6.
				public String findUserByNick(String userNick) {
					return new SQL() {{
						SELECT("user_nick");
						SELECT("user_photo");
						SELECT("sex");
						SELECT("signature");
						FROM("user");
						WHERE("user_nick Like CONCAT(CONCAT('%',#{userNick}),'%')");
					}}.toString();
		 } 
		 
				//7.
				public String editUser(User user) {
					return new SQL(){{
						UPDATE("user");
						SET("user_nick=#{userNick}");//昵称
						SET("user_photo=#{userPhoto}");//头像
						SET("sex=#{sex}");//性别
						SET("phone=#{phone}");//电话
						SET("signature=#{signature}");//签名
						/*
						 * 用户编号不为空
						 * 且用户状态为1：账号有效
						 * 用户身份：普通用户
						 * 普通用户可以编辑的信息：昵称、头像、性别、预留手机号、个性签名
						 */
					    WHERE("user_email=#{userEmail}");
				}}.toString();
		}
		 
//				//8.
//				public String selectInfor() {
//					return new SQL() {{
//						SELECT("");
//						SELECT("");
//						
//					}}.toString();
//				}
//				
				
				//9.
				public String findAll() {
					return new SQL() {{
						SELECT("user_no","user_nick","user_email","password");
						SELECT("sex","phone","permission","user_state");
						FROM("user");
					}}.toString();
		 }
		 
				//10.
				public String operateUser(User user) {
					return new SQL() {{
						UPDATE("user");
						/*
						 * 账号：有效==1;无效==2
						 * 身份：管理员；
						 * 管理员可以操作的用户信息：用户账号是否可用、用户权限
						 */
							SET("user_state=#{userState}");
							SET("permission=#{permission}");
							WHERE("user_email=#{userEmail}");
					}}.toString();
		}
		 
				//11.
				public String saveToRedis(int userNo) {
					return new SQL() {{
						SELECT("user_no,user_nick,user_photo,user_emial,signature");
						FROM("user");
						WHERE("user_no=#{userNo}");
					}}.toString();	
		}
		
		}

}
