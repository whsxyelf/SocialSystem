package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.mapper.ConcernMapper;
import com.whsxyelf.social.service.ConcernService;

@Service
public class ConcernServiceImpl implements ConcernService {
	@Autowired
	ConcernMapper mapper;
	
	@Override
	public List<User> GetConcernList(int userId) {
		List<User> concernList = mapper.findConcernListByUserId(userId);
		return concernList;
	}
	
	@Override
	public List<User> GetFansList(int userId) {
		List<User> fansList = mapper.findFansListByUserId(userId);
		return fansList;
	}

	@Override
	public boolean Add(int userId, int concernedId) {
		int result = 0;
		try {
			result = mapper.addOne(userId, concernedId);
		} catch (Exception e) {
			result = 0;
		}
		return result>0?true:false;
	}

	@Override
	public boolean Delete(int userId, int concernedId) {
		int result = mapper.deleteConcern(userId, concernedId);
		return result>0?true:false;
	}

	@Override
	public int CountConcern(int userId) {
		int count = mapper.countConcern(userId);
		return count;
	}

	@Override
	public int CountFans(int userId) {
		int count = mapper.countFans(userId);
		return count;
	}

}
