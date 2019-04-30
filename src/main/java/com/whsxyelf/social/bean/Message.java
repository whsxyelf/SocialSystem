package com.whsxyelf.social.bean;

import java.util.Date;

public class Message {
	private int messageId;
	private int userId;
	private int concernedId;
	private String content;
	private Date createTime;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
		return "Message [messageId=" + messageId + ", userId=" + userId + ", concernedId=" + concernedId + ", content="
				+ content + ", createTime=" + createTime + "]";
	}
	
	
	
}
