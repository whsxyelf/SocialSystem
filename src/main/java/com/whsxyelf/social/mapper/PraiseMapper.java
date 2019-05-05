package com.whsxyelf.social.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whsxyelf.social.bean.Praise;

@Mapper
public interface PraiseMapper {
	@Select("select count(praise_id) from praise where "
			+ "praise_type=#{praiseType} and praised_id=#{praisedId}")
	public int countPraise(@Param("praiseType")int praiseType,@Param("praisedId")int praisedId);
	
	@Insert("insert into praise(user_id,praise_type,praised_id) "
			+ "values(#{userId},#{praiseType},#{praisedId})")
	public int praiseUp(Praise praise);
	
	@Delete("delete from praise where user_id=#{userId} and praise_type=#{praiseType} "
			+ "and praised_id=#{prisedId}")
	public int praiseDown(Praise praise);
	
	@Select("select praise_id,user_id,create_time from praise where user_id=#{userId} "
			+ "and praise_type=#{praiseType} and praised_id=#{prisedId}")
	public Praise isExist(Praise praise);
}
