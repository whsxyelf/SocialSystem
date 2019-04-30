package com.whsxyelf.social.bean;

import java.sql.Timestamp;

public class Message {
	private int messageId;
	private int userNo;
	private int concernedNo;
	private String content;
	private Timestamp createTime;
	
	
	public Message() {
		
	}
	
	public Message(int messageId, int userNo, int concernedNo, String content,Timestamp createTime) {
		super();
		this.messageId = messageId;
		this.userNo = userNo;
		this.concernedNo = concernedNo;
		this.content = content;
		this.createTime = createTime;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getConcernedNo() {
		return concernedNo;
	}
	public void setConcernedNo(int concernedNo) {
		this.concernedNo = concernedNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userNo=" + userNo + ", concernedNo=" + concernedNo + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
	
	
	
}
