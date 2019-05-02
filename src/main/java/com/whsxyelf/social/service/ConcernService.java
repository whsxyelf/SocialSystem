package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Concern;

public interface ConcernService {
	public List<Concern> GetConcernList(int userId);
	public boolean Add(int userId,int concernedId);
	public boolean Delete(int userId,int concernId);
	public int CountConcern(int userId);
	public int CountFans(int userId);
}
