package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whsxyelf.social.bean.Collect;
import com.whsxyelf.social.packbean.Article;

@Mapper
public interface CollectMapper {
	@Select("select u.user_id,user_nick,user_photo,essay_id,essay_content,essay_photo " + 
			"from user as u join essay as e on u.user_id=e.user_id where essay_id in " + 
			"(select collected_id from collect where collect_type=2 and user_id=#{userId})")
	public List<Article> findCollectListByUserId(int userId);
	
	@Insert("insert into collect(user_id,collect_type,collected_id) "
			+ "values(#{userId},#{collectType},#{collectedId})")
	public int addOne(Collect collect);
	
	@Delete("delete from collect where collected_id=#{collectedId} and user_id=#{userId}")
	public int deleteCollectByCollectId(@Param("collectedId")int collectedId,@Param("userId")int userId);
	
	@Delete("delete from collect where user_id=#{userId}")
	public int deleteCollectByUserId(int userId);
	
	@Select("select count(collect_id) from collect where collect_type=#{collectType} "
			+ "and collected_id=#{collectedId}")
	public int countCollect(@Param("collectType")int collectType,@Param("collectedId")int collectedId);
	
	@Select("select collect_id,user_id from collect where user_id=#{userId} and "
			+ "collect_type=#{collectType} and collected_id=#{collectedId}")
	public Collect isExist(Collect collect);
}
