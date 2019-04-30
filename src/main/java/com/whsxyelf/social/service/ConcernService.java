package com.whsxyelf.social.service;

import java.util.ArrayList;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;

public interface ConcernService {
	
	//	1.关注
	public int addConcern(Concern concern);
	
	//	2.取消关注
	public int cancelConcern(Concern concern);
	
	//	3.关注列表
	public ArrayList<Concern> concernList(User user);
	
	//	4.关注数
	public int concernCount(int userNo);
	
	//	5.粉丝数
	public int fansCount(int concernedId);
	
}
