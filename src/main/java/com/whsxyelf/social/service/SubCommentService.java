package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.SubComment;

public interface SubCommentService {
	public List<SubComment> GetSubCommentList(int commentedId);
	
	public boolean Add(SubComment subComment);
	
	public boolean Update(SubComment subComment);
	
	public boolean DeleteOne(int subCommentId,int userId);
	
	public boolean DeleteByComment(int commentId,int userId);
	
	public int Count(int commentedId);
}
