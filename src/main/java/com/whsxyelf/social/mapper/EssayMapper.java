package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.bean.User;

@Mapper
public interface EssayMapper {
	
	//1.用户发布动态
	@InsertProvider(method = "lauchEssay", type= EssayDaoProvider.class)
	public int lauchEssay(Essay essay);
	
	//2.展示用户动态
	@SelectProvider(method = "ShowSelf",type = EssayDaoProvider.class)
	public ArrayList<Essay> ShowSelf(User user);
	
	//3.展示用户关注对象的动态
	@SelectProvider(method = "showConcerned",type = EssayDaoProvider.class)
	public List<Map <String,Object>>showConcerned(Map<String,Object> map);
	
	//4.用户删除动态
	@DeleteProvider(method = "deleteOneEssay",type = EssayDaoProvider.class)
	public int deleteOneEssay(Essay essay);
	
	class EssayDaoProvider{
		
		public String lauchEssay(Essay essay) {
			return new SQL(){{
				INSERT_INTO("essay");
				VALUES("user_no","#{userNo}");//发布动态用户编号
				VALUES("essay_content","#{essayContent}");//动态内容
				VALUES("essay_photo","#{essayPhoto}");//动态图片
				VALUES("create_time","#{createTime}");//发布时间：获取当前时间
			}}.toString();
		}
		
		public String ShowSelf(User user) {
			return new SQL() {{
				SELECT("user_no,essay_content");
				SELECT("essay_photo,essay_comment");
				SELECT("essay_collection,create_time");
				FROM("essay");
				if(user.getUserNo()!=null) {
					WHERE("user_no=#{userNo}");
				}
			}}.toString();
		}
		
		public String showConcerned(Map<String,Object> map) {
			return new SQL() {
				{
					SELECT("concern.user_no,concern.concerned_id");
					SELECT("user_nick AS B_nick,essay_content");
					SELECT("essay_photo,essay_comment");
					SELECT("essay_collection,essay.create_time as launchTime");
					FROM("concern,essay,user");
					WHERE("concern.concerned_id=essay.user_no");
					WHERE("user.user_no=concern.concerned_id");
					WHERE("concern.user_no=#{userNo}");
				}
			}.toString();
		}
		
		public String deleteOneEssay(Essay essay) {
			return new SQL() {{
				DELETE_FROM("essay");
				if(essay.getEssayId()!=0) {
					WHERE("essay_id=#{essayId}");
				}
				if(essay.getUserNo()!=null) {
					WHERE("user_no=#{userNo}");
				}
			}}.toString();
		}
		
	}

}
