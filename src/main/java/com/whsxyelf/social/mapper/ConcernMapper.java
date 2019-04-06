package com.whsxyelf.social.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Concern;

@Mapper
public interface ConcernMapper {
	
		//用户关注
		@InsertProvider(method = "concernUser",type = ConcernDaoProvider.class)
		public int concernUser(Concern concern);
		class ConcernDaoProvider{
			public String concernUser(Concern concern) {
				return new SQL() {{
					INSERT_INTO("concern");
					VALUES("user_no", "#{userNo}");
					VALUES("concerned_id", "#{concernedId}");
					VALUES("create_time", "#{createTime}");
				}}.toString();
				
			}
		}
		
		
		//取消关注
		@DeleteProvider(method = "concernCancel",type = ConcernDaoProviderDelete.class)
		public int concernCancel(Concern concern);
		class ConcernDaoProviderDelete{
			public String concernCancel(Concern concern) {
				return new SQL() {{
					DELETE_FROM("concern");
					WHERE("user_no=#{userNo}");
					WHERE("concerned_id=#{concernedId}");
				}}.toString();
				
			}
		}
		
		//查找已存在的数据
		@SelectProvider(method = "findHave",type = ConcernDaoProviderFind.class)
		public Concern findHave(Concern concern);
		class ConcernDaoProviderFind{
			public String findHave() {
				return new SQL() {{
					SELECT("user_no");
					SELECT("concerned_id");
					FROM("concern");
					WHERE("user_no=#{userNo}");
					WHERE("concerned_id=#{concernedId}");
				}}.toString();
				
			}
		}
	}


