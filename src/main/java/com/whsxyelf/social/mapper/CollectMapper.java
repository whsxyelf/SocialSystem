package com.whsxyelf.social.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Collect;


@Mapper
public interface CollectMapper {
	
	//1.增加一条收藏数据
	@InsertProvider(method = "insertCollect",type = CollectDaoProvider.class)
	public int insertCollect(Collect collect);
	
	//	2.删除一条收藏数据
	@DeleteProvider(method = "deleteCollect",type = CollectDaoProvider.class)
	public int deleteCollect(Collect collect);
	
	//	3.根据用户编号，删除所有收藏列表中的数据
	@DeleteProvider(method = "deleteAllCollect",type = CollectDaoProvider.class)
	public int deleteAllCollect(@Param("userNo")int userNo);
	
	//	4.查询收藏列表
	@SelectProvider(method = "selectCollectList",type = CollectDaoProvider.class)
	public ArrayList<Collect> selectCollectList(@Param("userNo")int userNo);
	
	//	5.查询收藏详情：收藏的新闻、动态等被删除的同时，不显示详情。
	@SelectProvider(method = "selectCollectDetail",type = CollectDaoProvider.class)
	public Collect selectCollectDetail(Collect collect);
	
	//	6.根据type+collected_id统计动态被收藏数
	@SelectProvider(method = "essayCount",type = CollectDaoProvider.class)
	public int essayCount(Collect collect);
	
		class CollectDaoProvider{
			
			//1.
			public String insertCollect(Collect collect) {
				return new SQL() {{
					INSERT_INTO("collect");
					VALUES("user_no","#{userNo}");
					VALUES("collection_type","#{collectionType}");
					VALUES("collected_id","#{collectedId}");
				}}.toString();			
			}
			
			//2.
			public String deleteCollect(Collect collect) {
				return new SQL() {{
					DELETE_FROM("collect");
					WHERE("user_no=#{userNo}");
					WHERE("collection_type=#{collectionType}");
					WHERE("collected_id=#{collectedId}");
				}}.toString();	 
			}
			
			//3.
			public String deleteAllCollect(int userNo) {
				return new SQL() {{
					DELETE_FROM("collect");
					WHERE("user_no=#{userNo}");
				}}.toString();
			}
			
			//4.
			public String selectCollectList(int userNo){
				return new SQL() {{
					SELECT("user_no");
					SELECT("collection_type");
					SELECT("collected_id");
					FROM("collect");
					WHERE("user_no=#{userNo}");
				}}.toString();
			}
			
			//5.
			public String selectCollectDetail(Collect collect){
				return new SQL() {{
//					SELECT("user_no");
//					SELECT("collection_type");
//					SELECT("collected_id");
//					FROM("collect");
//					WHERE("user_no=#{userNo}");
//					WHERE("collection_type=#{collectionType}");
//					WHERE("collected_id=#{collectedId}");
				}}.toString();
			}
			
			//6.
			public String essayCount(Collect collect){
				return new SQL() {{
					SELECT("count(*)");
					FROM("collect");
					WHERE("collection_type=#{collectionType}");
					WHERE("collected_id=#{collectedId}");
				}}.toString();
			}
	}	
}
