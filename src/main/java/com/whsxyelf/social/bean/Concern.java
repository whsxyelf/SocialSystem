package com.whsxyelf.social.bean;

import java.util.Date;

public class Concern {
	private int concernId;
	private String userNo;
	//private User user;
	private String concernedId;
	private Date createTime;
	
	public Concern() {
		
	}
	
	public Concern(int concernId, String userNo, String concernedId, Date createTime) {
		super();
		this.concernId = concernId;
		this.userNo = userNo;
		this.concernedId = concernedId;
		this.createTime = createTime;
	}
	public int getConcernId() {
		return concernId;
	}
	public void setConcernId(int concernId) {
		this.concernId = concernId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getConcernedId() {
		return concernedId;
	}
	public void setConcernedId(String concernedId) {
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
		return "Concern concernId=" + concernId + ", userNo=" + userNo + ", concernedId=" + concernedId
				+ ", createTime=" + createTime ;
	}
	
}
