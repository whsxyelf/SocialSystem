package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.mapper.CommentMapper;
import com.whsxyelf.social.mapper.SubCommentMapper;
import com.whsxyelf.social.packbean.CommentExtend;
import com.whsxyelf.social.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentMapper mapper;
	
	@Autowired
	SubCommentMapper submapper;
	
	@Override
	public List<CommentExtend> GetCommentList(int commentType,int commentedId) {
		List<CommentExtend> commentList = mapper.findTotalCommentById(commentType,commentedId);
		return commentList;
	}

	@Override
	public boolean Add(Comment comment) {
		int result = mapper.addOne(comment);
		return result>0?true:false;
	}

	@Override
	public boolean Update(Comment comment) {
		int result = mapper.updateComment(comment);
		return result>0?true:false;
	}

	@Override
	@Transactional
	public boolean Delete(int commentId,int userId) {
		int result = 0;
		try {
			int op = -1;
			op = submapper.deleteSubCommentByComment(commentId, userId);
			if(op>=0) {
				result = mapper.deleteCommentByCommentId(commentId,userId);
			} else {
				throw new RuntimeException("事务异常");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
	@Override
	public boolean DeleteByCommentedId(int commentedId,int userId) {
		int result = mapper.deleteCommentByCommentedId(commentedId,userId);
		return result>0?true:false;
	}

	@Override
	public int Count(int commentType,int commentedId) {
		int count = mapper.countCommentByCommentedId(commentType,commentedId);
		return count;
	}
	
}
