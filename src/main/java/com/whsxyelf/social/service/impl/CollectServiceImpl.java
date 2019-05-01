//package com.whsxyelf.social.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.whsxyelf.social.bean.Collect;
//import com.whsxyelf.social.exception.CollectException;
//import com.whsxyelf.social.mapper.CollectMapper;
//import com.whsxyelf.social.service.CollectService;
//
//@Service
//public class CollectServiceImpl implements CollectService{
//	
//	@Autowired
//	private	CollectMapper mapper;
//
//	@Override
//	public int addCollect(Collect collect) {
//		int num = 0;
//		try {
//			num = mapper.insertCollect(collect);
//			if(num==1) {
//				throw new CollectException("收藏成功!");
//			}else {
//				throw new CollectException("收藏失败!");
//			}
//		} catch (Exception e) {
//			
//		}
//		return num;
//	}
//
//	@Override
//	public int cancelCollect(Collect collect) {
//		int num = 0;
//		try {
//			num = mapper.deleteCollect(collect);
//			if(num==1) {
//				throw new CollectException("取消收藏成功!");
//			}else {
//				throw new CollectException("取消收藏失败!");
//			}
//		} catch (Exception e) {
//			
//		}
//		return num;
//	}
//
//	@Override
//	public int cancelAllCollect(int userNo) {
//		int num = 0;
//		try {
//			num = mapper.deleteAllCollect(userNo);
//			if(num==1) {
//				throw new CollectException("全部取消收藏!");
//			}else {
//				throw new CollectException("操作失败!");
//			}
//		} catch (Exception e) {
//			
//		}
//		return num;
//	}
//
//	@Override
//	public List<Collect> showCollectList(int userNo) {
//		
//		return null;
//	}
//
//	@Override
//	public void showCollectDetail(Collect collect) {
//		
//		
//	}
//
//	@Override
//	public int essayCollectedCount(Collect collect) {
//		int num = -1;
//		try {
//			num = mapper.essayCount(collect);
//			if(num>=0) {
//				throw new CollectException("评论收藏数：");
//			}else {
//				throw new CollectException("操作失败!");
//			}
//		} catch (Exception e) {
//			
//		}
//		return num;
//	}
//
//	
//	
//}
