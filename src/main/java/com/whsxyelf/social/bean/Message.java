package com.whsxyelf.social.bean;

import java.util.Date;

public class Message {
	private int messageId;
	private String userNo;
	private String concernedNo;
	private String content;
	private Date createTime;
	
	
	public Message() {
		
	}
	
	public Message(int messageId, String userNo, String concernedNo, String content, Date createTime) {
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
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getConcernedNo() {
		return concernedNo;
	}
	public void setConcernedNo(String concernedNo) {
		this.concernedNo = concernedNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userNo=" + userNo + ", concernedNo=" + concernedNo + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
	
	
	
}
