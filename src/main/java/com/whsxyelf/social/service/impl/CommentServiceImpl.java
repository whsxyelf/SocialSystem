//package com.whsxyelf.social.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.whsxyelf.social.bean.Comment;
//import com.whsxyelf.social.exception.CommentException;
//import com.whsxyelf.social.mapper.CommentMapper;
//import com.whsxyelf.social.service.CommentService;
//
//@Service
//public class CommentServiceImpl implements CommentService{
//	
//	@Autowired
//	private	CommentMapper mapper;
//	
//	@Override
//	public int review(Comment comment) {
//		int num = 0;
//		try {
//			//评论动态或新闻
//			num = mapper.insertComment(comment);
//			if(num==1) {
//				throw new CommentException("评论成功!");
//			}else {
//				throw new CommentException("评论失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//	@Override
//	public int deleteReview(int commentId) {
//		int num = 0;
//		try {
//			//删除动态的评论
//			num = mapper.deleteOneComment(commentId);
//			if(num==1) {
//				throw new CommentException("删除成功!");
//			}else {
//				throw new CommentException("删除失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//	//#########
//	@Override
//	public List<Comment> showReview(Comment comment) {
//		List<Comment> list = null;
//		try {
//			//显示评论
//			list =  mapper.selectCommentList(comment);
//			if(list!=null) {
//				throw new CommentException("显示评论!");
//			}else {
//				throw new CommentException("评论显示失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//	
//	@Override
//	public int clearReview(Comment comment) {
//		int num = 0;
//		try {
//			//删除所有的评论
//			num = mapper.deleteAllComment(comment);
//			if(num==1) {
//				throw new CommentException("删除成功!");
//			}else {
//				throw new CommentException("删除失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//	@Override
//	public int essayCommentNum(Comment comment) {
//		int num = -1;
//		try {
//			//获取评论数
//			num = mapper.essayCommentCount(comment);
//			if(num>=0) {
//				throw new CommentException("动态评论数!");
//			}else {
//				throw new CommentException("获取失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
//
//}
