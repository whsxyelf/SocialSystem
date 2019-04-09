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
		comment.setUserNo("U00001");
		comment.setCommentedId(7);//==essay_id
		comment.setCommentContent("我也想你了！");
		Date now = new Date();
		//yyyy-MM-dd
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current = df.format(now);
		comment.setCreateTime(df.parse(current));
		mapper.addComment(comment);
	}
	
	@Test
	@Ignore
	public void deleteAll() {
		Comment comment = new Comment();
		comment.setCommentedId(3);
		mapper.deleteOneself(comment);
	}
	
	@Test
	@Ignore
	public void deleteOne() {
		Comment comment = new Comment();
		comment.setCommentedId(7);
		comment.setCommentId(6);
		mapper.deleteOneself(comment);
	}
	
	
	
	@Test
	@Ignore
	public void show() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> res = mapper.showComment(map);
		System.out.println(res);
	}
	
}
