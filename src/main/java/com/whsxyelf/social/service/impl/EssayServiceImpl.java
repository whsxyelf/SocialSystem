package com.whsxyelf.social.service.impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.exception.EssayException;
import com.whsxyelf.social.mapper.EssayMapper;
import com.whsxyelf.social.service.EssayService;

@Service
public class EssayServiceImpl implements EssayService{

	@Autowired
	private EssayMapper mapper ;
	
	@Override
	public int publish(Essay essay) {
		int num = 0;
		try {
			//发布动态
			num =mapper.insertEssay(essay);
			if(num==1) {
				throw new EssayException("发布成功!");
			}else {
				throw new EssayException("发布失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int cleanOne(int essayId) {
		int num = 0;
		try {
			//发布动态
			num =mapper.deleteOneEssay(essayId);
			if(num==1) {
				throw new EssayException("删除成功!");
			}else {
				throw new EssayException("删除失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	//##########
	@Override
	public ArrayList<Essay> showEssayList(int userNo) {
		
		ArrayList<Essay> list = null;
//		ArrayList<Essay> list2= null;
//		try {
//			//动态
//			list1 = mapper.selectSelf(userNo);
//			list2 = mapper.selectConcerned(userNo);
//			if(list1!=null&&list2==null) {
//				throw new EssayException("发布成功!");
//			}else {
//				throw new EssayException("发布失败!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return list;
	}

}
