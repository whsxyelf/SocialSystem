package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.SubComment;
import com.whsxyelf.social.mapper.SubCommentMapper;
import com.whsxyelf.social.service.SubCommentService;

@Service
public class SubCommentServiceImpl implements SubCommentService {
	@Autowired
	SubCommentMapper mapper;
	
	@Override
	public List<SubComment> GetSubCommentList(int commentedId) {
		List<SubComment> subCommentList = mapper.findSubCommentListByCommentedId(commentedId);
		return subCommentList;
	}

	@Override
	public boolean Add(SubComment subComment) {
		int result = mapper.addOne(subComment);
		return result>0?true:false;
	}

	@Override
	public boolean Update(SubComment subComment) {
		int result = mapper.updateSubComment(subComment);
		return result>0?true:false;
	}

	@Override
	public boolean DeleteOne(int subCommentId, int userId) {
		int result = mapper.deleteSubCommentBySubCommentId(subCommentId, userId);
		return result>0?true:false;
	}

	@Override
	public boolean DeleteByComment(int commentId,int userId) {
		int result = mapper.deleteSubCommentByComment(commentId, userId);
		return result>0?true:false;
	}

	@Override
	public int Count(int commentedId) {
		int count = mapper.countCommentByCommentedId(commentedId);
		return count;
	}

}
