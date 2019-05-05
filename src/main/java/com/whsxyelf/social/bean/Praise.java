package com.whsxyelf.social.bean;

import java.util.Date;

public class Praise {
	private int praiseId;
	private int userId;
	private int praisedType;
	private int praisedId;
	private Date createTime;
	public int getPraiseId() {
		return praiseId;
	}
	public void setPraiseId(int praiseId) {
		this.praiseId = praiseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPraisedType() {
		return praisedType;
	}
	public void setPraisedType(int praisedType) {
		this.praisedType = praisedType;
	}
	public int getPraisedId() {
		return praisedId;
	}
	public void setPraisedId(int praisedId) {
		this.praisedId = praisedId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Praise [praiseId=" + praiseId + ", userId=" + userId + ", praisedType=" + praisedType + ", praisedId="
				+ praisedId + "]";
	}
	
	
	
}
