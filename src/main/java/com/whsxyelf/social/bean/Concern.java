package com.whsxyelf.social.bean;

import java.util.Date;

public class Concern {
	private int concernId;
	private int userId;
	private int concernedId;
	private Date createTime;
	public int getConcernId() {
		return concernId;
	}
	public void setConcernId(int concernId) {
		this.concernId = concernId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getConcernedId() {
		return concernedId;
	}
	public void setConcernedId(int concernedId) {
		this.concernedId = concernedId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Concern [concernId=" + concernId + ", userId=" + userId + ", concernedId=" + concernedId
				+ ", createTime=" + createTime + "]";
	}
	
	
	
}
