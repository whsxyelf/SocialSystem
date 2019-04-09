package com.whsxyelf.social.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.News;


@Mapper
public interface NewsMapper {
	
	//1.添加新闻
	@InsertProvider(method = "addNews",type = NewsDaoProvider.class)
	public int addNews();
	//2.删除单条新闻
	@DeleteProvider(method = "deleteNews",type = NewsDaoProvider.class)
	public int deleteNews();
	//3.展示单条新闻详情
	@SelectProvider(method = "showNewsDetail",type = NewsDaoProvider.class)
	public News showNewsDetail();
	//4.展示新闻列表
	@SelectProvider(method = "showNewsList",type = NewsDaoProvider.class)
	public ArrayList<News> showNewsList();
	//5.统计新闻条数
	@SelectProvider(method = "countNews",type = NewsDaoProvider.class)
	public int countNews();
	
	class NewsDaoProvider{
		
		public String addNews() {
			return new SQL() {{
				INSERT_INTO("news");
				VALUES("news_from", "newsFrom");
				VALUES("news_title", "newsTitle");
				VALUES("news_url", "newsUrl");
				VALUES("length", "length");
				VALUES("create_time", "createTime");
			}}.toString(); 	
		}
		
		public String deleteNews() {
			return new SQL() {{
				DELETE_FROM("news");
				WHERE("news_from=#{newsFrom}");
				WHERE("news_title=#{newsTitle}");
				WHERE("news_url=#{newsUrl}");
			}}.toString(); 	
		}
		
		public String showNewsDetail() {
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
				SELECT("news_from");
				SELECT("news_title");
				SELECT("news_url");
				SELECT("length");
				FROM("news");
				WHERE("news_from=#{newsFrom}");
				WHERE("news_title=#{newsTitle}");	
			}}.toString(); 	
		}
		
		public String countNews() {
			return new SQL() {{
			
				
				
			}}.toString(); 	
		}
	}
	
}
