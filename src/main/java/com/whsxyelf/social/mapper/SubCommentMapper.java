package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.SubComment;

@Mapper
public interface SubCommentMapper {
	@Select("select sub_comment_id,user_id,sub_comment_content,create_time from sub_comment "
			+ "where commented_id=#{commentedId}")
	public List<SubComment> findSubCommentListByCommentedId(int commentedId);
	
	@Insert("insert into sub_comment(user_id,commented_id,sub_comment_content) "
			+ "values(#{userId},#{commentedId},#{subCommentContent})")
	public int addOne(SubComment subComment);
	
	@UpdateProvider(type=SubCommentProvider.class,method="updateSubComment")
	public int updateSubComment(SubComment subComment);
	
	@Delete("delete from sub_comment where sub_comment_id=#{subCommentId} and user_id=#{userId}")
	public int deleteSubCommentBySubCommentId(@Param("subCommentId")int subCommentId,@Param("userId")int userId);
	
	@Delete("delete from sub_comment where commented_id="
			+ "(select comment_id from comment where comment_id=#{commentId} and user_id=#{userId})")
	public int deleteSubCommentByComment(@Param("commentId")int commentId,@Param("userId")int userId);
	
	@Delete("delete from sub_comment where commented_id in (select comment_id from comment where commented_id="
			+ "(select essay_id from essay where essay_id=#{essayId} and user_id=#{userId}))")
	public int deleteSubCommentByEssayId(@Param("essayId") int essayId,@Param("userId") int userId);
	
	@Select("select count(sub_comment_id) from sub_comment where commented_id=#{commentedId}")
	public int countCommentByCommentedId(int commentedId);
	
	class SubCommentProvider {
		public String updateSubComment() {
			return new SQL() {{
				UPDATE("sub_comment");
				SET("sub_comment_content=#{subCommentContent}");
				SET("last_edit_time=#{lastEditTime}");
				WHERE("sub_comment_id=#{subCommentId}");
			}}.toString();
		}
	}
}
