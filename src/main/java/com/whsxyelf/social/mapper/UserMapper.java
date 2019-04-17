
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
	
		
//	//1.保存所有有效用户的 编号、昵称 ——>redis
//	@SelectProvider(method = "saveNick",type = UserDaoProvider.class)
//	public User saveNick(User user);
	
	// 2.注册
	@InsertProvider(method = "addOne",type =UserDaoProvider.class)
	public int addOne(@Param("userEmail")String userEmail,@Param("password")String password);
	
	// 2.1根据用户注册的邮箱验证是否邮箱已被注册
	@SelectProvider(method = "findOne", type = UserDaoProvider.class)
	public User findOne(String userEmail);
	
	//3.登录
	@SelectProvider(method = "userLogin", type =UserDaoProvider.class)
	public User userLogin(@Param("userEmail")String userEmail,@Param("password")String password);
	
	//4.登陆后根据用户昵称模糊查询——>找人
	@SelectProvider(method = "findUserByNick",type = UserDaoProvider.class)
	public List<User> findUserByNick(String userNick);
	
	//5.登录时忘记密码：用户更改密码
	@UpdateProvider(method = "changePassword",type =UserDaoProvider.class )
	public int changePassword(@Param("userEmail")String userEmail,@Param("password")String password);
	
	//6.普通用户登录后编辑用户基本信息
	@UpdateProvider(method = "editUser",type = UserDaoProvider.class)
	public int editUser(User user);
	
	//7.用户后台管理：展示所有用户信息
	@SelectProvider(method = "findAll",type = UserDaoProvider.class)
	public ArrayList<User> findAll();
	
	//8.用户后台管理：管理员操作用户信息
	@UpdateProvider(method = "operateUser",type = UserDaoProvider.class)
	public int operateUser(@Param("userState")int userState,@Param("permission")int permission);
	
	class UserDaoProvider{

//		public String saveNick(User user) {
//			return new SQL() {{
//				SELECT("user_no,user_nick");
//				FROM("user");
//			}}.toString();	
//		}
		

		public String addOne(String userEmail,String password) {
			return new SQL() {{
				INSERT_INTO("user");
				VALUES("user_email","#{userEmail}");
				VALUES("password","#{password}");
			}}.toString();
		}
		
	
		public String findOne(String userEmail) {
			return new SQL(){{
				SELECT("user_no");
				SELECT("user_email");
				SELECT("user_state");
				FROM("user");
				WHERE("user_email=#{userEmail}");
				}}.toString();//其内部使用StringBuilder来实现拼接
		 }
		
		 public String userLogin(String userEmail,String password) {
				return new SQL(){{
					SELECT("user_email,password");
					FROM("user");
						WHERE("user_email=#{userEmail}");
						WHERE("password=#{password}");
					}}.toString();//其内部使用StringBuilder来实现拼接
		 }
		

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
		 

		 public String changePassword(String userEmail,String password) {
				return new SQL() {{
					UPDATE("user");
					SET("password=#{password}");//密码
					WHERE("user_email=#{userEmail}");//获取邮箱验证码
				}}.toString();	
		}
		 

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
		 
		 
		 public String findAll() {
				return new SQL() {{
					SELECT("user_no","user_nick","user_email","password");
					SELECT("sex","phone","permission","user_state");
					FROM("user");
				}}.toString();
		 }
		 

		 public String operateUser(int userState,int permission) {
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
	}

}
