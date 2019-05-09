package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Collect;
import com.whsxyelf.social.packbean.Article;

public interface CollectService {
	public List<Article> GetCollectList(int userId);
	public boolean Add(Collect collect);
	public boolean DeleteByCollectId(int collectId,int userId);
	public boolean DeleteByUserId(int userId);
	public int CountCollect(int collectType,int collectedId);
}
