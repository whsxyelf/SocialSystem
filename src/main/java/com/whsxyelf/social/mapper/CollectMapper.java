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
	
	//1.添加收藏
	@InsertProvider(method = "addCollect",type = CollectDaoProvider.class)
	public int addCollect(Collect collect);
	//2.取消单个收藏
	@DeleteProvider(method = "cancelCollect",type = CollectDaoProvider.class)
	public int cancelCollect(Collect collect);
	//2.1取消所有收藏
	@DeleteProvider(method = "cancelAllCollect",type = CollectDaoProvider.class)
	public int cancelAllCollect(@Param("userNo")int userNo);
	//3.展示收藏列表
	@SelectProvider(method = "showCollectList",type = CollectDaoProvider.class)
	public ArrayList<Collect>showCollectList(@Param("userNo")int userNo);
	//4.展示单个收藏内容详情——>进入
	@SelectProvider(method = "showCollectDetail",type = CollectDaoProvider.class)
	public ArrayList<Collect>showCollectDetail(Collect collect);
	
	class CollectDaoProvider{
		public String addCollect(Collect collect) {
			return new SQL() {{
				INSERT_INTO("collect");
				VALUES("user_no","#{userNo}");
				VALUES("collection_type","#{collectionType}");
				VALUES("collected_id","#{collectedId}");
			}}.toString();			
		}
		
		public String cancelCollect(Collect collect) {
			return new SQL() {{
				DELETE_FROM("collect");
				WHERE("user_no=#{userNo}");
				WHERE("collection_type=#{collectionType}");
				WHERE("collected_id=#{collectedId}");
			}}.toString();	 
		}
		
		public String cancelAllCollect(int userNo) {
			return new SQL() {{
				DELETE_FROM("collect");
				WHERE("user_no=#{userNo}");
			}}.toString();
		}
		
		public String showCollectList(int userNo){
			return new SQL() {{
				SELECT("user_no");
				SELECT("collection_type");
				SELECT("collected_id");
				FROM("collect");
				WHERE("user_no=#{userNo}");
			}}.toString();
		}
	
		public String showCollectDetail(Collect collect){
			return new SQL() {{
				SELECT("user_no");
				SELECT("collection_type");
				SELECT("collected_id");
				FROM("collect");
				WHERE("user_no=#{userNo}");
				WHERE("collection_type=#{collectionType}");
				WHERE("collected_id=#{collectedId}");
			}}.toString();
		}
	}	
}
