package com.whsxyelf.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.History;
import com.whsxyelf.social.mapper.HistoryMapper;
import com.whsxyelf.social.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	@Autowired
	HistoryMapper mapper;

	@Override
	public boolean add(History history) {
		int result = mapper.addHistory(history);
		return result>0?true:false;
	}

	@Override
	public History isExist(int userId, int newsId) {
		History history = mapper.isExist(userId, newsId);
		return history;
	}

	@Override
	public boolean update(History history) {
		int result = mapper.updateHistory(history);
		return result>0?true:false;
	}
	
}
