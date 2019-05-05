package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whsxyelf.social.bean.Concern;

@Mapper
public interface ConcernMapper {
	@Select("select concern_id,user_id,concerned_id,create_time from concern "
			+ "where user_id=#{userId}")
	public List<Concern> findConcernListByUserId(int userId);
	
	@Insert("insert into concern(user_id,concerned_id) values(#{userId},#{concernedId})")
	public int addOne(@Param("userId") int userId,@Param("concernedId") int concernedId);
	
	@Delete("delete from concern where concern_id=#{concernId} and user_id=#{userId}")
	public int deleteConcern(@Param("userId") int userId,@Param("concernId") int concernId);
	
	@Select("select count(concern_id) from concern where user_id=#{userId}")
	public int countConcern(int userId);
	
	@Select("select count(concern_id) from concern where concerned_id=#{userId}")
	public int countFans(int userId);
	
	@Select("select concern_id,user_id from concern where user_id=#{userId} and "
			+ "concerned_id=#{concernedId}")
	public Concern isExist(@Param("userId") int userId, @Param("concernedId") int concernedId);
}
