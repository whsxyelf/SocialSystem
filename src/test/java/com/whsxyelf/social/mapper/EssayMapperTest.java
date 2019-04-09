package com.whsxyelf.social.mapper;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.mysql.cj.Session;
import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class EssayMapperTest {

	@Autowired
	EssayMapper mapper;
	@Autowired
	CommentMapper comMapper;
	
	@Test
	@Ignore
	public void lauchEssayTest() throws ParseException {
		Essay essay = new Essay();
		essay.setUserNo("U00001");
		essay.setEssayContent("fuck!");
		essay.setEssayPhoto("");
		Date now = new Date();
		//yyyy-MM-dd
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current = df.format(now);
		essay.setCreateTime(df.parse(current));
		mapper.lauchEssay(essay);
	}
	
	@Test
	@Ignore
	public void deleteOneEssayTest() {
		Essay essay = new Essay();
		essay.setEssayId(7);
		essay.setUserNo("U00001");
		int num =mapper.deleteOneEssay(essay);
		System.out.println(num);
		if(num==1) {
			Comment comment = new Comment();
			comment.setCommentedId(7);
			comMapper.deleteOneself(comment);
		}
	}
	
	@Test
	@Ignore
	public void ShowSelf(){
		User user = new User();
		user.setUserNo("U00002");
		ArrayList<Essay> list = mapper.ShowSelf(user);
		for(Essay e:list) {
			System.out.println(e.toString());
		}
	}
	
	@Test
	@Ignore
	public void ShowConcerneed() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userNo", "U00002");
		List<Map<String,Object>> res = mapper.showConcerned(map);
		System.out.println(res);
		
	}
}
