package com.whsxyelf.social.mapper;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class CommentMapperTest {
	
	@Autowired
	private CommentMapper mapper;
	
	@Test
	@Ignore
	public void addTest() throws ParseException {
		Comment comment = new Comment();
		comment.setCommentType(1);//1表示动态
		comment.setUserNo(5);
		comment.setCommentedId(11);//==essay_id
		comment.setCommentContent("难受！");
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current = df.format(now);
		comment.setCreateTime(df.parse(current));
		mapper.addComment(comment);
	}
	
	@Test
	@Ignore
	public void deleteAll() {
		mapper.deleteAll(1,11);
	}
	
	@Test
	@Ignore
	public void deleteOne() {
		mapper.deleteOneself(12);
	}

	@Test
	@Ignore
	public void show() {
		List<Comment> list = mapper.showComment(1, 11);
		for(Comment com:list) {
			System.out.println(com.getUserNo());
			System.out.println(com.getCommentContent().toString());
		}
	}
	
}
