package com.whsxyelf.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Collect;


@Mapper
public interface CollectMapper {
	
	//添加收藏
	@InsertProvider(method = "addCollect",type = CollectDaoProvider.class)
	public int addCollect(Collect collect);
	class CollectDaoProvider{
		public String addCollect(Collect collect) {
			return new SQL() {{
				INSERT_INTO("collect");
				//VALUES("collection_id", "#{collectionId}");
				VALUES("user_no","#{userNo}");
				VALUES("collection_type","#{collectionType}");
				VALUES("collected_id","#{collectedId}");
			}}.toString();
			
		}
	}
	//取消收藏
	@DeleteProvider(method = "cancelCollect",type = CollectionDaoProviderCancel.class)
	public int cancelCollect();
	class CollectionDaoProviderCancel{
		public String cancelCollect() {
			return new SQL() {{
				DELETE_FROM("collect");
				WHERE("user_no=#{userNo}");
				WHERE("collection_type=#{collectionType}");
				WHERE("collected_id=#{collectedId}");
			}}.toString();
			 
		}
	}
	
	//展示收藏列表
	@SelectProvider(method = "showCollect",type = CollectionDaoProviderShow.class)
	public List<Collect>showCollect(Collect collect);
	class CollectionDaoProviderShow{
		public String showCollect(Collect collect){
			return new SQL() {{
				SELECT("user_no");
				SELECT("collection_type");
				SELECT("collected_id");
				FROM("collect");
				if(collect.getUserNo()!=null) {
					WHERE("user_no=#{userNO}");
				}
				
			}}.toString();
		}
		
	}
	
}
