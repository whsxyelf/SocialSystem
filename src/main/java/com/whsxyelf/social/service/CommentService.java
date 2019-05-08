package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.packbean.CommentExtend;

public interface CommentService {
	public List<CommentExtend> GetCommentList(int commentType,int commentedId);
	
	public boolean Add(Comment comment);
	
	public boolean Update(Comment comment);
	
	public boolean Delete(int commentId,int userId);
	
	public boolean DeleteByCommentedId(int commentedId,int userId);
	
	public int Count(int commentType,int commentedId);
}
