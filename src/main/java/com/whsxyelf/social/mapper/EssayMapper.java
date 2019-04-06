package com.whsxyelf.social.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Essay;

@Mapper
public interface EssayMapper {
	
	/*
	 * 用结构化的sql注解
	 * 用户发布动态：文字+图片/文字/图片
	*/
	@InsertProvider(method = "lauchEssay", type= EssayDaoProviderLauch.class)
	public int lauchEssay(Essay essay);
	class EssayDaoProviderLauch{
		public String lauchEssay(Essay essay) {
			return new SQL(){{
				INSERT_INTO("essay");
				VALUES("user_no","#{userNo}");//发布动态用户编号
				VALUES("essay_content","#{essayContent}");//动态内容
				VALUES("essay_photo","#{essayPhoto}");//动态图片
				VALUES("create_time","#{createTime}");//发布时间：获取当前时间
			}}.toString();
		}
	}
	
	//用户删除发布动态
	@DeleteProvider(method = "deleteOneEssay",type = EssayDaoProviderDelete.class)
	public int deleteOneEssay(Essay essay);
	class EssayDaoProviderDelete {
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
	
	//用户展示自己的动态：用户A登录成功后，展示用户A所关注的用户的动态和自己的动态！
	//用户展示自己动态:essay+user:userNick、essayContent、essayPhoto
	//XXXXXXXXX这个有问题
	@SelectProvider(method = "showSelf",type = EssayDaoProviderShowSelf.class)
	public List<Map <String,Object>>showSelf(Map<String,Object> map);
	//public Essay ShowSelf(String userNo);
	class EssayDaoProviderShowSelf{
		public String showSelf(Map<String,Object> map) {
			return new SQL() {{
				SELECT("user_nick,essay_content");
				SELECT("essay_photo,essay_comment");
				SELECT("essay_collection,e.create_time as createTime");
				FROM("user u");
				LEFT_OUTER_JOIN("essay e on u.user_no=e.user_no");
				//JOIN("essay e on u.user_no=e.user_no");
				//if(essay.getUserNo()!=null) {
					WHERE("e.user_no=#{userNo}");
				//}
			}}.toString();
			
		}
	}
	
	//向用户展示用户关注对象的动态：
	//userNo+concernedId+userNick+essayContent+essayPhoto
	//+essay_comment+essay_collection+create_time
	//用户编号+被关注编号+被关注用户昵称+被关注用户动态/图片/评论数/收藏数/时间
	//concern+essay+user
	@SelectProvider(method = "showConcerned",type = EssayDaoProviderShowConcerned.class)
	public List<Map <String,Object>>showConcerned(Map<String,Object> map);
	class EssayDaoProviderShowConcerned{
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
	}
	
	
}
