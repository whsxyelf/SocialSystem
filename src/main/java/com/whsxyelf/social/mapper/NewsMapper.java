package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.whsxyelf.social.bean.News;

@Mapper
public interface NewsMapper {
	@Select("select news_id,news_from,news_title,news_url,create_time from news where news_id=#{newsId}")
	public News findNewsById(int newsId);
	
	@Select("select news_id,news_from,news_title,news_url,create_time from news limit 3,10")
	public List<News> newsListTest();
	
	@SelectProvider(type=NewsProvider.class,method="findNewsByList")
	public List<News> findNewsByList(@Param("newsId")int newsId[]);
	
	@Select("select count(news_id) from news")
	public int countNews();
	
	@Select("select n.news_id,news_from,news_title,h.create_time,h.last_edit_time " + 
			"from news as n join history as h on n.news_id=h.news_id " + 
			"where user_id=#{userId} order by h.last_edit_time desc")
	public List<News> findNewsListByUserId(int userId);
	
	@Select("select * from news order by rand() limit #{limitNews}")
	public List<News> findRandomNewsList(int limitNews);
	
	@Select("select * from news where news_title like '%${key}%'")
	public List<News> findNewsByKey(@Param("key") String key);
	
	class NewsProvider {
		public String findNewsByList(int newsId[]) {
			StringBuilder list = new StringBuilder();
			list.append("("+ newsId[0]);
			int max = newsId.length;
			for(int i=1;i<max;i++) {
				list.append(","+newsId[i]);
			}
			list.append(")");
			return "select news_id,news_from,news_title,news_url,create_time "
					+ "from news where news_id in " + list.toString();
		}
	}
}
