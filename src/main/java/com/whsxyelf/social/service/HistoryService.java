package com.whsxyelf.social.service;

import com.whsxyelf.social.bean.History;

public interface HistoryService {
	public boolean add(History history);
	public History isExist(int userId,int newsId);
	public boolean update(History history);
}
