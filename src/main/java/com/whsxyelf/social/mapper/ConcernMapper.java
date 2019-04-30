package com.whsxyelf.social.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Concern;
import com.whsxyelf.social.bean.User;

@Mapper
public interface ConcernMapper {
	
		//1.增加一条关注信息
		@InsertProvider(method = "insertConcern",type = ConcernDaoProvider.class)
		public int insertConcern(Concern concern);
		
		//2.根据关注id删除一条关注数据
		@DeleteProvider(method = "deleteConcern",type = ConcernDaoProvider.class)
		public int deleteConcern(Concern concern);
		
		//3.根据用户编号查询关注列表
		@SelectProvider(method = "selectList",type = ConcernDaoProvider.class)
		public ArrayList<Concern> selectList(User user);
		
		//4.根据用户编号统计查询被关注人数量
		@SelectProvider(method = "selectCount",type = ConcernDaoProvider.class)
		public int selectCount(@Param("userNo")int userNo);
		
		//5.根据被关注人id统计粉丝数
		@SelectProvider(method = "selectFansCount",type = ConcernDaoProvider.class)
		public int selectFansCount(@Param("concernedId")int concernedId);

			
			class ConcernDaoProvider{
				
				//1.
				public String insertConcern(Concern concern) {
					return new SQL() {{
						INSERT_INTO("concern");
						VALUES("user_no", "#{userNo}");
						VALUES("concerned_id", "#{concernedId}");
						VALUES("create_time", "#{createTime}");
					}}.toString();
				}
				
				//2.
				public String deleteConcern(Concern concern) {
					return new SQL() {{
						DELETE_FROM("concern");
						WHERE("user_no=#{userNo}");
						WHERE("concerned_id=#{concernedId}");
					}}.toString();
				}
				
				//3.
				public String selectList(User user) {
					return new SQL() {{

						SELECT("user_no as concerned_id");
						SELECT("user_nick");
						SELECT("user_photo");
						SELECT("signature");
						FROM("user");
						WHERE("user_no in(select concerned_id from concern where user_no=#{userNo})");
					}}.toString();
				}
			

				//4.
				public String selectCount(int userNo) {
					return new SQL() {{
						SELECT("count(user_no)");
						FROM("concern");
						WHERE("user_no=#{userNo}");
					}}.toString();
				}
			
				//5.
				public String selectFansCount(int concernedId) {
					return new SQL() {{
						SELECT("count(concerned_id)");
						FROM("concern");
						WHERE("concerned_id=#{concernedId}");
					}}.toString();
				}
				
		}	
}


