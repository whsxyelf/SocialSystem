package com.whsxyelf.social.service;

public interface PraiseService {
	public int countPraise(int essayId);
	public boolean add(int essayId,int userId);
	public boolean delete(int essayid,int userId);
	public boolean isExist(int essayId,int userId);
}
