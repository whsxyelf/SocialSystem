package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.mapper.ConcernMapper;
import com.whsxyelf.social.service.ConcernService;

@Service
public class ConcernServiceImpl implements ConcernService {
	@Autowired
	ConcernMapper mapper;
	
	@Override
	public List<Concern> GetConcernList(int userId) {
		List<Concern> concernList = mapper.findConcernListByUserId(userId);
		return concernList;
	}

	@Override
	public boolean Add(int userId, int concernedId) {
		int result = mapper.addOne(userId, concernedId);
		return result>0?true:false;
	}

	@Override
	public boolean Delete(int userId, int concernId) {
		int result = mapper.deleteConcern(userId, concernId);
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
