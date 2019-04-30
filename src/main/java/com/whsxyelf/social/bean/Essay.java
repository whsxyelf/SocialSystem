package com.whsxyelf.social.bean;

import java.util.Date;

public class Essay {
	private int essayId;
	private int userId;
	private String essayContent;
	private String essayPhoto;
	private Date createTime;
	public int getEssayId() {
		return essayId;
	}
	public void setEssayId(int essayId) {
		this.essayId = essayId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Essay [essayId=" + essayId + ", userId=" + userId + ", essayContent=" + essayContent + ", essayPhoto="
				+ essayPhoto + ", createTime=" + createTime + "]";
	}
	
	
	
}
