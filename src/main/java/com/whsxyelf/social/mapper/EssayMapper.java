package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Essay;

@Mapper
public interface EssayMapper {
	//通过id查询动态
	@Select("select essay_id,user_id,essay_content,essay_photo,praise,create_time,last_edit_time "
			+ "from essay where essay_id=#{essayId}")
	public Essay findEssayById(int essayId);
	
	//通过用户id查询用户所有动态
	@Select("select essay_id,user_id,essay_content,essay_photo,praise,create_time,last_edit_time "
			+ "from essay where user_id=#{userId}")
	public List<Essay> findEssayListByUserId(int userId);
	
	//添加一条动态
	@InsertProvider(type = EssayProvider.class,method="addOne")
	public int addOne(Essay essay);
	
	//修改动态
	@UpdateProvider(type = EssayProvider.class,method="updateEssay")
	public int updateEssay(Essay essay);
	
	//删除动态
	@Delete("delete from essay where essay_id=#{essayId} and user_id=#{userId}")
	public int deleteEssay(@Param("essayId") int essayId,@Param("userId") int userId);
	
	//统计用户的所有动态数
	@Select("select count(essay_id) from essay where user_id=#{userId}")
	public int countEssayByUserId(int userId);
	
	//统计多少条动态@了我
	@Select("select count(essay_id) from essay where essay_content like '%@${userNick}%'")
	public int countAtMeByUserNick(@Param("userNick") String userNick);
	
	//谁@了我
	@Select("select essay_id,user_id,essay_content,essay_photo,praise,create_time,last_edit_time "
			+ "from essay where essay_content like '%@${userNick}%'")
	public List<Essay> findEssayListByUserNick(@Param("userNick") String userNick);
	
	//通过用户id查询用户收藏的所有动态
	@Select("select essay_id,user_id,essay_content,essay_photo,praise,create_time,last_edit_time "
			+ "from essay where essay_id in (select collect_id from collect "
			+ "where user_id=#{userId})")
	public List<Essay> findEssayListByCollect(int userId);
	
	class EssayProvider {
		public String addOne(Essay essay) {
			return new SQL() {{
				INSERT_INTO("essay");
				VALUES("user_id", "#{userId}");
				VALUES("essay_content", "#{essayContent}");
				if(essay.getEssayPhoto() != null) {
					VALUES("essay_photo", "#{essayPhoto}");
				}
			}}.toString();
		}
		
		public String updateEssay(Essay essay) {
			return new SQL() {{
				UPDATE("essay");
				if(essay.getEssayContent() != null) {
					SET("essay_content=#{essayContent}");
				}
				if(essay.getEssayPhoto() != null) {
					SET("essay_photo=#{essayPhoto}");
				}
				SET("last_edit_time=#{lastEditTime}");
				WHERE("essay_id=#{essayId}");
				WHERE("user_id=#{userId}");
			}}.toString();
		}
	}
}
