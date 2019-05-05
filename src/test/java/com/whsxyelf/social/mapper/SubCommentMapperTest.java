package com.whsxyelf.social.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.SubComment;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class SubCommentMapperTest {
	@Autowired
	SubCommentMapper mapper;
	
	@Test
	@Ignore
	public void findSubCommentListByCommentedId() {
		List<SubComment> subCommentList = mapper.findSubCommentListByCommentedId(2);
		System.out.println(subCommentList.size());
	}
	
	@Test
	@Ignore
	public void addOne() {
		SubComment subComment = new SubComment();
		subComment.setUserId(1);
		subComment.setCommentedId(1);
		subComment.setSubCommentContent("123");
		int result = mapper.addOne(subComment);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void updateSubComment() {
		SubComment subComment = new SubComment();
		subComment.setSubCommentId(1);
		subComment.setSubCommentContent("123111");
		subComment.setLastEditTime(new Date());
		int result = mapper.updateSubComment(subComment);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteSubCommentByCommentedId() {
//		int result = mapper.deleteSubCommentByCommentedId(1);
//		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void countCommentByCommentedId() {
		int result = mapper.countCommentByCommentedId(1);
		System.out.println(result);
	}
}
