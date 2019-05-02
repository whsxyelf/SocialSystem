package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whsxyelf.social.bean.Collect;

@Mapper
public interface CollectMapper {
	@Select("select collect_id,user_id,collect_type,collected_id,create_time from collect "
			+ "where user_id=#{userId}")
	public List<Collect> findCollectListByUserId(int userId);
	
	@Insert("insert into collect(user_id,collect_type,collected_id) "
			+ "values(#{userId},#{collectType},#{collectedId})")
	public int addOne(Collect collect);
	
	@Delete("delete from collect where collect_id=#{collectId} and user_id=#{userId}")
	public int deleteCollectByCollectId(@Param("collectId")int collectId,@Param("userId")int userId);
	
	@Delete("delete from collect where user_id=#{userId}")
	public int deleteCollectByUserId(int userId);
	
	@Select("select count(collect_id) from collect where collect_type=#{collectType} "
			+ "and collected_id=#{collectedId}")
	public int countCollect(@Param("collectType")int collectType,@Param("collectedId")int collectedId);
	
	
}
