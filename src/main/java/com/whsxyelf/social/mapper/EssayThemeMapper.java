package com.whsxyelf.social.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.EssayTheme;

@Mapper
public interface EssayThemeMapper {
	/*
	 * 动态被删除,主题也被删除;
	 */
	
	//1.根据动态主题编号获取评论主题名
	@SelectProvider(method = "findEssayTheme",type = EssayThemeDaoProvider.class)
	public EssayTheme findEssayTheme();
	
	//2.插入动态主题编号
	@InsertProvider(method = "addEssayTheme",type = EssayThemeDaoProvider.class)
	public int addEssayTheme();
	//3.删除动态主题
	@DeleteProvider(method = "deleteEssayTheme",type =  EssayThemeDaoProvider.class)
	public int deleteEssayTheme();
	
	class EssayThemeDaoProvider{
		public String findEssayTheme(){
			return new SQL() {{
				SELECT("essay_theme_no");
				SELECT("essay_theme_content");
				FROM("essay_theme");
				WHERE("essay_theme_no=#{essayThemeNo}");
			}}.toString();
			
		}
		
		public String addEssayTheme() {
			return new SQL() {{
				INSERT_INTO("essay_theme");
				VALUES("essay_theme_content","#{essayThemeContent}");
			}}.toString();		
		}
		
		public String deleteEssayTheme(){
			return new SQL() {{
				DELETE_FROM("essay_theme");
				WHERE("essay_theme_no=#{essayThemeNo}");	
			}}.toString(); 	
		}	
	}
		
}
