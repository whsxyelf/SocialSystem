package com.whsxyelf.social.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Comment;

@Mapper
public interface CommentMapper {
	
	//添加评论：自评和关注对象评价
	@InsertProvider(method ="addComment",type= AddCommentDaoProvider.class)
	public int addComment(Comment comment);
	class AddCommentDaoProvider{
		public String addComment(Comment comment) {
			return new SQL() {
				{
					INSERT_INTO("comment");
					VALUES("comment_type", "#{commentType}");
					VALUES("user_no", "#{userNo}");
					if(comment.getCommentedId()!=0) {
						VALUES("commented_id", "#{commentedId}");	
					}
					VALUES("comment_content", "#{commentContent}");
					VALUES("create_time", "#{createTime}");
				}
			}.toString();
			
		}
	}
	
//	//删除评论:评论者删除自己的评论,发布动态者删除对应被评论的所有评论
//	@DeleteProvider(method = "deleteComment",type=deleteCommemtDaoProvider.class)
//	public int deleteComment(Comment comment);
//	class deleteCommemtDaoProvider{
//		public String deleteComment(Comment comment) {
//			return new SQL() {{
//				DELETE_FROM("comment");
//				WHERE("commented_id=#{commentedId}");//动态被删除后对应的评论全部删除
//			}}.toString();
//			
//		}
//	}
	
	
	//删除评论:评论者删除自己的评论,发布动态者可删除对应被评论的所有评论
	//用户删除自己动态的指定评论
	//评论者删除自己的评论
	@DeleteProvider(method = "deleteOne",type = CommentDaoProvider.class)
	public int deleteOne(Comment comment);
	class CommentDaoProvider{
		public String deleteOne() {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_id=#{commentId}");
				WHERE("comment_type#{commentType}");
				WHERE("user_no=#{userNo}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
				
		}
	}
	
	//动态的评论展示
	//user+essay+comment
	@SelectProvider(method = "showComment",type = showCommentDaoProvider.class)
	public List<Map <String,Object>>showComment(Map<String,Object> map);
	class showCommentDaoProvider{
		public String showComment(Map<String,Object> map) {
			return new SQL() {{
/*
 * SELECT
 * user.user_nick,comment.comment_content
 *  FROM COMMENT,USER WHERE user.user_no=comment.user_no
 */
				
//		SELECT("user.user_nick");
//		SELECT("comment.comment_content");
//		FROM("comment,user");
//		WHERE("user.user_no=comment.user_no");
			/*
			 * essay.user_no AS launch,user.user_nick AS launcher,
			 * essay.essay_content AS dongtai,comment.comment_type,
			 * comment.user_no AS pinglunren,
			 * comment.comment_content 	
			 */
		SELECT("essay.user_no AS launch");
		SELECT("user.user_nick AS launcher");
		SELECT("essay.essay_content AS dongtai");
		SELECT("comment.comment_type");
		SELECT("comment.user_no AS pinglunren");
		//SELECT("comment.comment_content");
		FROM("essay,comment,user");
		WHERE("commented_id=essay.essay_id");
		WHERE("essay.user_no=user.user_no");
			}}.toString();
			
			
		}
	}

}
