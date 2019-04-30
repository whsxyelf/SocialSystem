package com.whsxyelf.social.bean;

import java.sql.Timestamp;


public class Concern {
	private int concernId;
	private int userNo;
	private int concernedId;
	private Timestamp createTime;
	
	public Concern() {
		
	}
	
	public Concern(int concernId, int userNo, int concernedId, Timestamp createTime) {
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
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getConcernedId() {
		return concernedId;
	}
	public void setConcernedId(int concernedId) {
		this.concernedId = concernedId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Concern concernId=" + concernId + ", userNo=" + userNo + ", concernedId=" + concernedId
				+ ", createTime=" + createTime ;
	}
	
}
