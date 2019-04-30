//package com.whsxyelf.social.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.whsxyelf.social.bean.User;
//import com.whsxyelf.social.exception.userException;
//import com.whsxyelf.social.mapper.UserMapper;
//import com.whsxyelf.social.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService{
//	@Autowired
//	private UserMapper mapper;
//
//	@Override
//	public int register(User user) {
//		int num = 0;
//		try {
//			
//			//查询是否已注册
//			User existUser = mapper.findByUserEmail(user.getUserEmail());
//			if(existUser!=null) {
//				throw new userException("注册邮箱已存在!");
//			}else {
//				//插入注册数据
//					num = mapper.addOne(user);
//					if(num==1) {
//						throw new userException("注册成功!");
//					}else {
//						throw new userException("注册失败!");
//					}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//	@Override
//	public User loginByEmail(User user) {
//		
//		try {
//			//查询邮箱、密码
//			User u = mapper.userByEmailLogin(user);
//			if(u==null) {
//				throw new userException("登录失败!");
//			}else {
//				throw new userException("登录成功!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	@Override
//	public User loginByPhone(User user) {
//		
//		try {
//			//查询邮箱、密码
//			User u = mapper.userByPhoneLogin(user);
//			if(u==null) {
//				throw new userException("登录失败!");
//			}else {
//				throw new userException("登录成功!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	@Override
//	public int changePassword(User user) {
//		int num = 0;
//		try {
//			//更新密码
//			num =  mapper.changePassword(user);
//			if(num==1) {
//				throw new userException("更改密码成功!");
//			}else {
//				throw new userException("更改密码失败！");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//	@Override
//	public List<User> findUser(String userNick) {
//		List<User> list = null;
//		try {
//			//找人
//			list = mapper.findUserByNick(userNick);
//			if(list==null) {
//				throw new userException("未查询到相关信息!");
//			}else {
//				throw new userException("相关信息如下!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//}
