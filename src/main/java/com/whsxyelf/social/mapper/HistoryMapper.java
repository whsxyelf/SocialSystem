package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.History;

@Mapper
public interface HistoryMapper {
	@Select("select history_id,user_id,news_id,score,create_time,last_edit_time "
			+ "from history where history_id=#{historyId} and state=1}")
	public History findHistoryById(int historyId);
	
	@Select("select history_id,user_id,news_id,score,create_time,last_edit_time "
			+ "from history where user_id=#{userId} and state=1")
	public List<History> findHistoryListByUserId(int userId);
	
	@Insert("insert into history(user_id,news_id,create_time,last_edit_time) "
			+ "values(#{userId},#{newsId},#{createTime},#{lastEditTime})")
	public int addHistory(History history);
	
	@UpdateProvider(type=HistoryProvider.class,method="updateHistory")
	public int updateHistory(History history);
	
	class HistoryProvider {
		public String updateHistory(History history) {
			return new SQL() {{
				UPDATE("history");
				if(history.getScore() != null) {
					SET("score=#{score}");
				}
				if(history.getState() != null) {
					SET("state=#{state}");
				}
				SET("last_edit_time=#{lastEditTime}");
				WHERE("history_id=#{historyId}");
			}}.toString();
		}
	}
}
