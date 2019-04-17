package com.whsxyelf.social.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;

@Mapper
public interface ConcernMapper {
	
		//1.用户关注
		@InsertProvider(method = "concernUser",type = ConcernDaoProvider.class)
		public int concernUser(Concern concern);
		//2.取消关注
		@DeleteProvider(method = "concernCancel",type = ConcernDaoProvider.class)
		public int concernCancel(Concern concern);
		//3.好友列表
		@SelectProvider(method = "concernList",type = ConcernDaoProvider.class)
		public ArrayList<Concern> concernList(User user);
		//4.查询已关注对象==单个好友详情
		@SelectProvider(method = "haveConcern",type = ConcernDaoProvider.class)
		public Concern haveConcern(Concern concern);
		
		
		class ConcernDaoProvider{
			public String concernUser(Concern concern) {
				return new SQL() {{
					INSERT_INTO("concern");
					VALUES("user_no", "#{userNo}");
					VALUES("concerned_id", "#{concernedId}");
					VALUES("create_time", "#{createTime}");
				}}.toString();
			}
			
			public String concernCancel(Concern concern) {
				return new SQL() {{
					DELETE_FROM("concern");
					WHERE("user_no=#{userNo}");
					WHERE("concerned_id=#{concernedId}");
				}}.toString();
			}
			
			public String concernList(User user) {
				return new SQL() {{
					SELECT("user_no");
					SELECT("concerned_id");
					FROM("concern");
					if(user.getUserNo()!=0) {
						WHERE("user_no=#{userNo}");
					}
				}}.toString();
			}
			
			public String haveConcern(Concern concern) {
				return new SQL() {{
					SELECT("user_no");
					SELECT("concerned_id");
					FROM("concern");
					if(concern.getUserNo()!=0) {
						WHERE("user_no=#{userNo}");
					}
					if(concern.getConcernedId()!=0) {
						WHERE("concerned_id=#{concernedId}");
					}
				}}.toString();
				
			}
			
		}	
}


