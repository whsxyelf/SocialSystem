package com.whsxyelf.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.mapper.CommentMapper;
import com.whsxyelf.social.mapper.EssayMapper;
import com.whsxyelf.social.mapper.SubCommentMapper;
import com.whsxyelf.social.service.EssayService;

@Service
public class EssayServiceImpl implements EssayService {
	@Autowired
	EssayMapper mapper;
	
	@Autowired
	CommentMapper cmapper;
	
	@Autowired
	SubCommentMapper scmapper;
	
	@Override
	public Essay GetEssay(int essayId) {
		Essay result = mapper.findEssayById(essayId);
		return result;
	}

	@Override
	public List<Essay> GetEssayList(int userId) {
		List<Essay> essayList = mapper.findEssayListByUserId(userId);
		return essayList;
	}

	@Override
	public boolean Publish(Essay essay) {
		int result = mapper.addOne(essay);
		return result>0?true:false;
	}

	@Override
	public boolean Update(Essay essay) {
		int result = mapper.updateEssay(essay);
		return result>0?true:false;
	}

	@Override
	@Transactional
	public boolean DeleteOne(int essayId,int userId) {
		int result = 0;
		try {
			int op1 = -1;
			op1 = scmapper.deleteSubCommentByEssayId(essayId, userId);
			int op2 = -1;
			op2 = cmapper.deleteCommentByCommentedId(essayId, userId);
			if(op1 >= 0 && op2 >= 0) {
				result = mapper.deleteEssay(essayId,userId);
			} else {
				throw new RuntimeException("事务异常");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return result>0?true:false;
	}

	@Override
	public int Count(int userId) {
		int count = mapper.countEssayByUserId(userId);
		return count;
	}

}
