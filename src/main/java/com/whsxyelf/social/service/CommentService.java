package com.whsxyelf.social.service;

import java.util.List;

import com.whsxyelf.social.bean.Comment;

public interface CommentService {
	
	
	//	1.评论动态或新闻
	public int review(Comment comment);
	
	
	//	2.删除动态的评论
	public int deleteReview(int commentId);
	
	
	//	3.显示评论
	public List<Comment> showReview(Comment comment);
	
	
	// 4.删除所有的评论
	public int clearReview(Comment comment);
	
	//5.获取评论数
	public int essayCommentNum(Comment comment);
}	
