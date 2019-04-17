package com.whsxyelf.social.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Comment;

@Mapper
public interface CommentMapper {
	
	/*
	 * 1.动态：自我评论、评价关注对象;commentType==1
	 * 2.新闻：评论;commentType==2
	 * 3.动态评论回复：commentType==3
	 * 4.新闻评论回复：commentType==4
	 */
	@InsertProvider(method ="addComment",type= CommentDaoProvider.class)
	public int addComment(Comment comment);
	
	//2.*展示评论
	@SelectProvider(method = "showComment",type = CommentDaoProvider.class)
	public List<Comment> showComment(@Param("commentType")int commentType,@Param("commentedId")int commentedId);
	
	/*
	 * 3.用户删除自己动态中的评论
	 * 4.关注对象删除自己在好友动态中的评论
	 * 6.删除回复的评论
	 */
	@DeleteProvider(method = "deleteOneself",type = CommentDaoProvider.class)
	public int deleteOneself(int commentId);
	
	//5.动态被删除对应所有评论自动删除
	@DeleteProvider(method = "deleteAll",type = CommentDaoProvider.class)
	public int deleteAll(@Param("commentType")int commentType,@Param("commentedId")int commentedId);

	class CommentDaoProvider{
		public String addComment(Comment comment) {
			return new SQL() {
				{
					INSERT_INTO("comment");
					VALUES("comment_type", "#{commentType}");
					VALUES("user_no", "#{userNo}");
					VALUES("commented_id", "#{commentedId}");	
					VALUES("comment_content", "#{commentContent}");
					VALUES("create_time", "#{createTime}");
				}
			}.toString();		
		}
		
		public String showComment(int commentType,int commentedId) {
			return new SQL() {{
				SELECT("comment_id");
				SELECT("user_no");
				SELECT("comment_content");
				FROM("comment");
				WHERE("comment_type=#{commentType}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
		}
		
		public String deleteOneself(int commentId) {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_id=#{commentId}");
			}}.toString();	
		}
		
		public String deleteAll(int commentType,int commentedId) {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_type=#{commentType}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
		}	
	}
	
}
