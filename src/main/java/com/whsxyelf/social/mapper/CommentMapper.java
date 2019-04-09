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
	
	/*
	 * 1.动态：自我评论、评价关注对象;commentType==1
	 * 2.新闻：评论;commentType==2
	 */
	@InsertProvider(method ="addComment",type= CommentDaoProvider.class)
	public int addComment(Comment comment);
	
	//2.*展示评论
	@SelectProvider(method = "showComment",type = CommentDaoProvider.class)
	public List<Map <String,Object>>showComment(Map<String,Object> map);
	
	/*
	 * 3.用户删除自己动态中的评论
	 * 4.关注对象删除自己在好友动态中的评论
	 */
	@DeleteProvider(method = "deleteOneself",type = CommentDaoProvider.class)
	public int deleteOneself(Comment comment);
	
	//5.动态被删除或新闻被下架后,对应所有评论自动删除
	@DeleteProvider(method = "deleteAll",type =CommentDaoProvider.class )
	public int deleteAll(Comment comment);
	
	class CommentDaoProvider{
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
		
		public String showComment(Map<String,Object> map) {
			return new SQL() {{
				SELECT("essay.user_no AS launch");
				SELECT("user.user_nick AS launcher");
				SELECT("essay.essay_content AS dongtai");
				SELECT("comment.comment_type");
				SELECT("comment.user_no AS pinglunren");
				FROM("essay,comment,user");
				WHERE("commented_id=essay.essay_id");
				WHERE("essay.user_no=user.user_no");
			}}.toString();
		}
		
		public String deleteOneself() {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_id=#{commentId}");
				WHERE("comment_type#{commentType}");
				WHERE("user_no=#{userNo}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();	
		}
		
		public String deleteAll(Comment comment) {
			return new SQL() {{
				DELETE_FROM("comment");
				WHERE("comment_type#{commentType}");
				WHERE("commented_id=#{commentedId}");
			}}.toString();
		}	
	}
	
}
