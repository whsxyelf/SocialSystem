package com.whsxyelf.social.mapper;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.whsxyelf.social.SocialApplication;
import com.whsxyelf.social.bean.Essay;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialApplication.class)
public class EssayMapperTest {

	@Autowired
	EssayMapper mapper;
	@Autowired
	CommentMapper comMapper;
	
	@Test
	@Ignore
	public void lauchEssayTest() throws ParseException {//发布动态
		Essay essay = new Essay();
		essay.setUserNo(5);
		essay.setEssayContent("fuck!");
		essay.setEssayPhoto("");
		Date now = new Date();
		//yyyy-MM-dd
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String current = df.format(now);
//		essay.setCreateTime(df.parse(current));
		//mapper.lauchEssay(essay);
	}
	
	@Test
	@Ignore
	public void deleteOneEssayTest() {//删除动态
		int essayId = 10;
		int num = mapper.deleteOneEssay(essayId);
		int essayType = 1;
		int commentedId = essayId;
		System.out.println(num);
//		if(num==1) {
//			comMapper.deleteAll(essayType, commentedId);
//			System.out.println("yes");
//		}
	}
	
	@Test
	@Ignore
	public void ShowSelf(){
//		ArrayList<Essay> list = mapper.ShowSelf(2);
//		for(Essay e:list) {
//			System.out.println(e.toString());
//		}
	}
	
	@Test
//	@Ignore
	public void ShowConcerneed() {

//		List<Essay> list = mapper.showConcerned(1);
//		for(Essay e:list) {
//			System.out.println(e.toString());
//		}

//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("userNo", "U00001");
//		List<Map<String,Object>> res = mapper.showConcerned(map);
//		System.out.println(res);
		
//		List<Essay> list = mapper.showConcerned(1);
//		for(Essay e:list) {
//			System.out.println(e.toString());
//		}
//		Essay essay = new Essay();
//		essay.setEssayId(11);
//		mapper.updateEssayCollection(essay);
//		
		
	}
}
