package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Comment;
import com.whsxyelf.social.packbean.CommentExtend;

@Mapper
public interface CommentMapper {
	@Select("select comment_id,u.user_id,user_nick,user_photo,comment_content,c.create_time "+
			"from comment as c join user as u on c.user_id=u.user_id "+
			"where comment_type=#{commentType} and commented_id=#{commentedId} order by create_time desc")
	public List<CommentExtend> findTotalCommentById(@Param("commentType") int commentType,@Param("commentedId") int commentedId);
	
	@Insert("insert into comment(comment_type,commented_id,user_id,comment_content) "
			+ "values(#{commentType},#{commentedId},#{userId},#{commentContent})")
	public int addOne(Comment comment);
	
	@UpdateProvider(type=CommentProvider.class,method="updateComment")
	public int updateComment(Comment comment);
	
	@Delete("delete from comment where comment_Id=#{commentId} and user_id=#{userId}")
	public int deleteCommentByCommentId(@Param("commentId") int commentId,@Param("userId") int userId);
	
	@Delete("delete from comment where commented_id="
			+ "(select essay_id from essay where essay_id=#{commentedId} and user_id=#{userId})")
	public int deleteCommentByCommentedId(@Param("commentedId") int commentedId, @Param("userId") int userId);
	
	@Select("select count(comment_id) from comment where comment_type=#{commentType} and commented_id=#{commentedId}")
	public int countCommentByCommentedId(@Param("commentType") int commentType,@Param("commentedId") int commentedId);
	
	class CommentProvider {
		public String updateComment(Comment comment) {
			return new SQL() {{
				UPDATE("comment");
				SET("comment_content=#{commentContent}");
				SET("last_edit_time=#{lastEditTime}");
				WHERE("comment_id=#{commentId}");
			}}.toString();
		}
	}
	
}
