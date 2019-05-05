package com.whsxyelf.social.bean;

import java.util.Date;

public class SubComment {
	private int subCommentId;
	private int userId;
	private int commentedId;
	private String subCommentContent;
	private Date createTime;
	private Date lastEditTime;
	public int getSubCommentId() {
		return subCommentId;
	}
	public void setSubCommentId(int subCommentId) {
		this.subCommentId = subCommentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCommentedId() {
		return commentedId;
	}
	public void setCommentedId(int commentedId) {
		this.commentedId = commentedId;
	}
	public String getSubCommentContent() {
		return subCommentContent;
	}
	public void setSubCommentContent(String subCommentContent) {
		this.subCommentContent = subCommentContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	@Override
	public String toString() {
		return "SubComment [subCommentId=" + subCommentId + ", userId=" + userId + ", commentedId=" + commentedId
				+ ", subCommentContent=" + subCommentContent + ", createTime=" + createTime
				+ ", lastEditTime=" + lastEditTime + "]";
	}

	

}
