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
	
	//	1.增加一条评论数据
	@InsertProvider(method ="insertComment",type= CommentDaoProvider.class)
	public int insertComment(Comment comment);
	
	//	2.根据评论id删除一条动态的评论数据
	@DeleteProvider(method = "deleteOneComment",type = CommentDaoProvider.class)
	public int deleteOneComment(@Param("commentId")int commentId);
	
	//	3.根据评论类型和被评论id查询评论列表
	@SelectProvider(method = "selectCommentList",type = CommentDaoProvider.class)
	public List<Comment> selectCommentList(Comment comment);
	
	//	4.删除动态所对应的所有评论
	@DeleteProvider(method = "deleteAllComment",type = CommentDaoProvider.class)
	public int deleteAllComment(Comment comment);
	
	//	5.统计动态评论的次数
	@SelectProvider(method = "essayCommentCount",type = CommentDaoProvider.class)
	public int essayCommentCount(Comment comment);
	
		class CommentDaoProvider{
			
			//1.
			public String insertComment(Comment comment) {
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
			
			//2.
			public String deleteOneComment(int commentId) {
				return new SQL() {{
					DELETE_FROM("comment");
					WHERE("comment_id=#{commentId}");
				}}.toString();	
			}
			
			//3.
			public String selectCommentList(Comment comment) {
				return new SQL() {{
					SELECT("comment_id");
					SELECT("user_no");
					SELECT("comment_content");
					FROM("comment");
					WHERE("comment_type=#{commentType}");
					WHERE("commented_id=#{commentedId}");
				}}.toString();
			}
			
			//4.
			public String deleteAllComment(Comment comment) {
				return new SQL() {{
					DELETE_FROM("comment");
					WHERE("comment_type=#{commentType}");
					WHERE("commented_id=#{commentedId}");
				}}.toString();
			}	
			
			//5.
			public String essayCommentCount(Comment comment) {
				return new SQL() {{
					SELECT("count(*)");
					FROM("comment");
					WHERE("comment_type=#{commentType}");
					WHERE("commented_id=#{commentedId}");
				}}.toString();
			}
	}
	
}
