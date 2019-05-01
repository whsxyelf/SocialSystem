package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Comment;

@Mapper
public interface CommentMapper {
	@Select("select comment_id,user_id,comment_content,create_time from comment "
			+ "where comment_type=#{commentType} and commented_id=#{commentedId}")
	public List<Comment> findTotalCommentById(Comment comment);
	
	@Insert("insert into comment(comment_type,commented_id,user_id,comment_content) "
			+ "values(#{commentType},#{commentedId},#{userId},#{commentContent})")
	public int addOne(Comment comment);
	
	@UpdateProvider(type=CommentProvider.class,method="updateComment")
	public int updateComment(Comment comment);
	
	@DeleteProvider(type=CommentProvider.class,method="deleteCommentByCommentedId")
	public int deleteCommentByCommentedId(Comment comment);
	
	@SelectProvider(type=CommentProvider.class,method="countCommentByCommentedId")
	public int countCommentByCommentedId(Comment comment);
	
	class CommentProvider {
		public String updateComment(Comment comment) {
			return new SQL() {{
				UPDATE("comment");
				SET("comment_content=#{commentContent}");
				SET("last_edit_time=#{lastEditTime}");
				WHERE("comment_id=#{commentId}");
			}}.toString();
		}
		
		public String deleteCommentByCommentedId(Comment comment) {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_type=#{commentType}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
		}
		
		public String countCommentByCommentedId(Comment comment) {
			return new SQL() {{
				SELECT("count(comment_id)");
				FROM("comment");
				WHERE("comment_type=#{commentType}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
		}
	}
	
}
