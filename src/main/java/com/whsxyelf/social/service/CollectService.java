package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Collect;

public interface CollectService {
	
	//	1.添加收藏：添加一条动态的收藏,同时更新动态中的收藏数
	public int addCollect(Collect collect);
	
	//	2.取消收藏：取消一条收藏，同时更新动态中的收藏数
	public int cancelCollect(Collect collect);
	
	//	3.全选取消收藏
	public int cancelAllCollect(int userNo);
	
	//	4.显示收藏列表
	public List<Collect> showCollectList(int userNo);
	
	//	5.显示收藏详情：收藏的新闻、动态等被删除的同时，不显示详情。
	public void showCollectDetail(Collect collect);
	
	//	6. 获取动态的收藏数：type+collected_id
	public int essayCollectedCount(Collect collect);
}
