package com.whsxyelf.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Praise;
import com.whsxyelf.social.mapper.PraiseMapper;
import com.whsxyelf.social.service.PraiseService;

@Service
public class PraiseServiceImpl implements PraiseService {
	@Autowired
	PraiseMapper mapper;

	@Override
	public int countPraise(int essayId) {
		int count = mapper.countPraise(2, essayId);
		return count;
	}

	@Override
	public boolean add(int essayId, int userId) {
		Praise praise = new Praise();
		praise.setPraisedId(essayId);
		praise.setPraisedType(2);
		praise.setUserId(userId);
		int result = mapper.praiseUp(praise);
		return result>0?true:false;
	}

	@Override
	public boolean delete(int essayId, int userId) {
		Praise praise = new Praise();
		praise.setPraisedId(essayId);
		praise.setPraisedType(2);
		praise.setUserId(userId);
		int result = mapper.praiseDown(praise);
		return result>0?true:false;
	}

	@Override
	public boolean isExist(int essayId, int userId) {
		Praise praise = new Praise();
		praise.setPraisedId(essayId);
		praise.setPraisedType(2);
		praise.setUserId(userId);
		Praise result = mapper.isExist(praise);
		return result!=null?true:false;
	}

}
