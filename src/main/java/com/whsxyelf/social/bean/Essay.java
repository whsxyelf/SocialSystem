package com.whsxyelf.social.bean;

import java.sql.Timestamp;

public class Essay {
	private int essayId;//被发布动态编号
	private int userNo;//发布动态的用户编号
	private String essayContent;//动态发布内容
	private String essayPhoto;//与动态同步的图片
	private Timestamp createTime;//动态发布时间
	
	public Essay() {
		
	}
	
	public Essay(int essayId, int userNo, String essayContent, String essayPhoto,Timestamp createTime) {
		super();
		this.essayId = essayId;
		this.userNo = userNo;
		this.essayContent = essayContent;
		this.essayPhoto = essayPhoto;
		this.createTime = createTime;
	}
	public int getEssayId() {
		return essayId;
	}
	public void setEssayId(int essayId) {
		this.essayId = essayId;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getEssayContent() {
		return essayContent;
	}
	public void setEssayContent(String essayContent) {
		this.essayContent = essayContent;
	}
	public String getEssayPhoto() {
		return essayPhoto;
	}
	public void setEssayPhoto(String essayPhoto) {
		this.essayPhoto = essayPhoto;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Essay [essayId=" + essayId + ", userNo=" + userNo + ", essayContent=" + essayContent + ", essayPhoto="
				+ essayPhoto + ", createTime=" + createTime + "]";
	}
	
}
