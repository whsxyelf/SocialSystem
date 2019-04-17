package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import com.whsxyelf.social.bean.Essay;


@Mapper
public interface EssayMapper {
	
	//1.用户发布动态
	@InsertProvider(method = "lauchEssay", type= EssayDaoProvider.class)
	public int lauchEssay(Essay essay);
	
	//2.展示用户动态
	@SelectProvider(method = "ShowSelf",type = EssayDaoProvider.class)
	public ArrayList<Essay> ShowSelf(int userNo);
	
	//3.展示用户关注对象的动态
	@SelectProvider(method = "showConcerned",type = EssayDaoProvider.class)
	public List<Essay> showConcerned(int userNo);
	
	//4.用户删除动态
	@DeleteProvider(method = "deleteOneEssay",type = EssayDaoProvider.class)
	public int deleteOneEssay(@Param("essayId")int essayId);
	
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
		
		public String ShowSelf(int userNo) {
			return new SQL() {{
				SELECT("user_no,essay_content");
				SELECT("essay_photo,essay_comment");
				SELECT("essay_collection,create_time");
				FROM("essay");
				WHERE("user_no=#{userNo}");
			}}.toString();
		}
		
		public String showConcerned(int userNo) {
			return new SQL() {{
				SELECT("essay_id,user_no");
				SELECT("essay_content,essay_photo");
				SELECT("essay_theme_no,essay_collection");
				FROM("essay");
				WHERE("user_no in(select concerned_id from concern where user_no=#{userNo})");
			}}.toString(); 
			
		}
		
		public String deleteOneEssay(int essayId) {
			return new SQL() {{
				DELETE_FROM("essay");
				WHERE("essay_id=#{essayId}");
			}}.toString();
		}
		
	}

}
