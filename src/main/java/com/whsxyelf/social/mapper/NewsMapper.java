package com.whsxyelf.social.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.News;


@Mapper
public interface NewsMapper {
	
	//1.展示单条新闻详情
	@SelectProvider(method = "showNewsDetail",type = NewsDaoProvider.class)
	public News showNewsDetail(@Param("newsId")int newsId);
	//2.展示新闻列表
	@SelectProvider(method = "showNewsList",type = NewsDaoProvider.class)
	public ArrayList<News> showNewsList();
	//3.统计新闻条数
	@SelectProvider(method = "countNews",type = NewsDaoProvider.class)
	public int countNews();
	
	class NewsDaoProvider{
		
		public String showNewsDetail(String newsId) {
			return new SQL() {{
				SELECT("news_from");
				SELECT("news_title");
				SELECT("news_url");
				SELECT("length");
				WHERE("news_id=#{newsId}");	
			}}.toString(); 	
		}
		
		public String showNewsList() {
			return new SQL() {{
				SELECT("news_id");
				SELECT("news_from");
				SELECT("news_title");
				SELECT("news_url");
				SELECT("length");
				FROM("news");	
			}}.toString(); 	
		}
		
		public String countNews() {
			return new SQL() {{
			SELECT("count(*)");
			FROM("news");
			}}.toString(); 	
		}
	}
	
}
