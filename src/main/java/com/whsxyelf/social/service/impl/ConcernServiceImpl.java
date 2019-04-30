package com.whsxyelf.social.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.exception.concernException;
import com.whsxyelf.social.mapper.ConcernMapper;
import com.whsxyelf.social.service.ConcernService;

@Service
public class ConcernServiceImpl implements ConcernService{
	
	@Autowired
	private ConcernMapper mapper;
	
	@Override
	public int addConcern(Concern concern) {
		int num = 0;
		try {
			num = mapper.insertConcern(concern);
			if(num == 1) {
				throw new concernException("关注成功!");
			}else {
				throw new concernException("关注失败!");
			}
		} catch (Exception e) {
			
		}
		return num;
	}

	@Override
	public int cancelConcern(Concern concern) {
		int num = 0;
		try {
			num = mapper.deleteConcern(concern);
			if(num == 1) {
				throw new concernException("取消关注!");
			}else {
				throw new concernException("取关失败!");
			}
		} catch (Exception e) {
			
		}
		return num;
	}

	@Override
	public ArrayList<Concern> concernList(User user) {
		ArrayList<Concern> list = null;
		try {
			//获取关注列表
			list = mapper.selectList(user);
			if(list != null) {
				throw new concernException("关注列表如下!");
			}else {
				throw new concernException("列表获取失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int concernCount(int userNo) {
		int num = -1;
		try {
			//关注数
			num = mapper.selectCount(userNo);
			if(num>=0) {
				throw new concernException("关注数如下!");
			}else {
				throw new concernException("关注数获取失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}


	@Override
	public int fansCount(int concernedId) {
		int num = -1;
		try {
			//粉丝数
			num = mapper.selectFansCount(concernedId);
			if(num>=0) {
				throw new concernException("粉丝数如下!");
			}else {
				throw new concernException("粉丝数获取失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

}
