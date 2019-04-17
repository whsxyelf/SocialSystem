package com.whsxyelf.social.bean;

import java.util.Date;

public class Essay {
	private int essayId;//被发布动态编号
	private int userNo;//发布动态的用户编号
	private String essayContent;//动态发布内容
	private String essayPhoto;//与动态同步的图片
	private String essayThemeNo;//动态所属主题编号
	private int essayCollection;//被收藏次数
	private int essayComment;//被评论次数
	private Date createTime;//动态发布时间
	
	public Essay() {
		
	}
	
	public Essay(int essayId, int userNo, String essayContent, String essayPhoto, String essayThemeNo,
			int essayCollection, int essayComment, Date createTime) {
		super();
		this.essayId = essayId;
		this.userNo = userNo;
		this.essayContent = essayContent;
		this.essayPhoto = essayPhoto;
		this.essayThemeNo = essayThemeNo;
		this.essayCollection = essayCollection;
		this.essayComment = essayComment;
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
	public String getEssayThemeNo() {
		return essayThemeNo;
	}
	public void setEssayThemeNo(String essayThemeNo) {
		this.essayThemeNo = essayThemeNo;
	}
	public int getEssayCollection() {
		return essayCollection;
	}
	public void setEssayCollection(int essayCollection) {
		this.essayCollection = essayCollection;
	}
	public int getEssayComment() {
		return essayComment;
	}
	public void setEssayComment(int essayComment) {
		this.essayComment = essayComment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Essay essayId=" + essayId + ", userNo=" + userNo + ", essayContent=" + essayContent + ", essayPhoto="
				+ essayPhoto + ", essayThemeNo=" + essayThemeNo + ", essayCollection=" + essayCollection
				+ ", essayComment=" + essayComment + ", createTime=" + createTime;
	}
	
}
