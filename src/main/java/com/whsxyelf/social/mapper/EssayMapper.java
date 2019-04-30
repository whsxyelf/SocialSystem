package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import com.whsxyelf.social.bean.Essay;


@Mapper
public interface EssayMapper {
	
	//	1.增加一条动态数据
	@InsertProvider(method = "insertEssay",type= EssayDaoProvider.class)
	public int insertEssay(Essay essay);
	
	//	2.删除一条动态数据
	@DeleteProvider(method = "deleteOneEssay",type = EssayDaoProvider.class)
	public int deleteOneEssay(@Param("essayId")int essayId);
	
	//	3.根据用户编号和发布动态的时间查询动态表数据
	@SelectProvider(method = "selectSelf",type = EssayDaoProvider.class)
	public ArrayList<Essay> selectSelf(@Param("userNo")int userNo);
	
	//	4.根据关注的用户编号和发布的时间查询动态表数据
	@SelectProvider(method = "selectConcerned",type = EssayDaoProvider.class)
	public ArrayList<Essay> selectConcerned(@Param("userNo")int userNo);

		class EssayDaoProvider{
		
			//1.
			public String insertEssay(Essay essay) {
				return new SQL(){{
					INSERT_INTO("essay");
					VALUES("user_no","#{userNo}");//发布动态用户编号
					VALUES("essay_content","#{essayContent}");//动态内容
					VALUES("essay_photo","#{essayPhoto}");//动态图片
					VALUES("create_time","#{createTime}");//发布时间：获取当前时间
				}}.toString();
			}
			
			//2.
			public String deleteOneEssay(int essayId) {
				return new SQL() {{
					DELETE_FROM("essay");
					WHERE("essay_id=#{essayId}");
				}}.toString();
			}
			
			//3.
			public String selectSelf(int userNo) {
				return new SQL() {{
					SELECT("user_no,essay_content");
					SELECT("essay_photo");
					SELECT("create_time");
					FROM("essay");
					WHERE("user_no=#{userNo}");
				}}.toString();
			}
			
			//4.
			public String selectConcerned(int userNo) {
				return new SQL() {{
					SELECT("essay_id,user_no");
					SELECT("essay_content,essay_photo");
					FROM("essay");
					WHERE("user_no in(select concerned_id from concern where user_no=#{userNo})");
				}}.toString(); 
			}	
			
		}
}
