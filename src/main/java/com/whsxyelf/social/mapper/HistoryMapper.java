package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whsxyelf.social.bean.History;

@Mapper
public interface HistoryMapper {
	@Select("select history_id,user_id,news_id,score,create_time,last_edit_time "
			+ "from history where history_id=#{historyId}")
	public History findHistoryById(int historyId);
	
	@Select("select history_id,user_id,news_id,score,create_time,last_edit_time "
			+ "from history where user_id=#{userId}")
	public List<History> findHistoryListByUserId(int userId);
	
	@Insert("insert into history(user_id,news_id,create_time,last_edit_time) "
			+ "values(#{userId},#{newsId},#{createTime},#{lastEditTime})")
	public int addHistory(History history);
	
	@Update("update history set score=#{score},last_edit_time=#{lastEditTime} "
			+ "where history_id=#{historyId}")
	public int updateHistory(History history);
}
