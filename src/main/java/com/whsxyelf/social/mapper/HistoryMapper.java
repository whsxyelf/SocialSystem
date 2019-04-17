package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.History;

@Mapper
public interface HistoryMapper {
	
	//1.存入历史浏览记录
	@InsertProvider(method = "addHistory",type = HistoryDaoProvider.class)
	public int addHistory(History history);
	//2.取出单条历史浏览记录
	@SelectProvider(method = "findHistory",type = HistoryDaoProvider.class)
	public History findHistory(@Param("userNo")int userNo,@Param("newsId")int newsId);
	//3.取出某个用户的新闻历史浏览记录列表
	@SelectProvider(method = "findHistoryList",type = HistoryDaoProvider.class)
	public List<History> findHistoryList(@Param("userNo")int userNo);
	
	
	class HistoryDaoProvider{
		
		public String addHistory() {
			return new SQL() {{
				INSERT_INTO("history");
				VALUES("user_no", "#{userNo}");
				VALUES("news_id", "#{newsId}");
				VALUES("score", "#{score}");
				VALUES("create_time", "#{createTime}");
			}}.toString();
			
		}
		
		public String findHistory() {
			return new SQL() {{
				SELECT("user_no");
				SELECT("news_id");
				SELECT("score");
				SELECT("create_time");
				FROM("history");
				WHERE("user_no=#{userNo}");
				WHERE("news_id=#{newsId}");
			}}.toString();
			
		}
		
		public String findHistoryList() {
			return new SQL() {{
				SELECT("user_no");
				SELECT("news_id");
				SELECT("score");
				SELECT("create_time");
				FROM("history");
				WHERE("user_no=#{userNo}");
			}}.toString();
			
		}
	}
	
}
