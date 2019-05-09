package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;

public interface ConcernService {
	public List<User> GetConcernList(int userId);
	public List<User> GetFansList(int userId);
	public boolean Add(int userId,int concernedId);
	public boolean Delete(int userId,int concernId);
	public int CountConcern(int userId);
	public int CountFans(int userId);
	
}
