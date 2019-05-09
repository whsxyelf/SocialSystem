package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Collect;
import com.whsxyelf.social.mapper.CollectMapper;
import com.whsxyelf.social.packbean.Article;
import com.whsxyelf.social.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService {
	@Autowired
	CollectMapper mapper;
	
	@Override
	public List<Article> GetCollectList(int userId) {
		List<Article> collectList = mapper.findCollectListByUserId(userId);
		return collectList;
	}

	@Override
	public boolean Add(Collect collect) {
		int result = mapper.addOne(collect);
		return result>0?true:false;
	}

	@Override
	public boolean DeleteByCollectId(int collectedId,int userId) {
		int result = mapper.deleteCollectByCollectId(collectedId,userId);
		return result>0?true:false;
	}

	@Override
	public boolean DeleteByUserId(int userId) {
		int result = mapper.deleteCollectByUserId(userId);
		return result>0?true:false;
	}

	@Override
	public int CountCollect(int collectType, int collectedId) {
		int count = mapper.countCollect(collectType, collectedId);
		return count;
	}

	@Override
	public boolean isExist(Collect collect) {
		Collect result = mapper.isExist(collect);
		return result!=null?true:false;
	}
	
}
