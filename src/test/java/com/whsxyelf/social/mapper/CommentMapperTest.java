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
import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.packbean.CommentExtend;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class CommentMapperTest {
	@Autowired
	CommentMapper mapper;
	
	@Test
	@Ignore
	public void findTotalCommentById() {
		
	}
	
	@Test
	@Ignore
	public void addOne() {
		Comment comment = new Comment();
		comment.setCommentType(2);
		comment.setCommentContent("习大大好帅");
		comment.setCommentedId(1);
		comment.setUserId(1);
		int result = mapper.addOne(comment);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void updateComment() {
		Comment comment = new Comment();
		comment.setCommentContent("习大大");
		comment.setLastEditTime(new Date());
		comment.setCommentId(6);
		int result = mapper.updateComment(comment);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void deleteCommentByCommentedId() {
		int result = mapper.deleteCommentByCommentId(3,4);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void countCommentByCommentedId() {
		int count = mapper.deleteCommentByCommentId(1,1);
		System.out.println(count);
	}
}
