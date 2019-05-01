package com.whsxyelf.social.bean;

import java.util.Date;

public class Collect {
	private int collectId;
	private int userId;
	private int collectType;
	private int collectedId;
	private Date createTime;
	public int getCollectId() {
		return collectId;
	}
	public void setCollectId(int collectId) {
		this.collectId = collectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCollectType() {
		return collectType;
	}
	public void setCollectType(int collectType) {
		this.collectType = collectType;
	}
	public int getCollectedId() {
		return collectedId;
	}
	public void setCollectedId(int collectedId) {
		this.collectedId = collectedId;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Collect [collectId=" + collectId + ", userId=" + userId + ", collectType=" + collectType
				+ ", collectedId=" + collectedId + "]";
	}
	
	
	
}
