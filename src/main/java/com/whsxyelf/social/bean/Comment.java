package com.whsxyelf.social.bean;

import java.util.Date;

public class Comment {
	private int commentId;
	private int commentType;
	private int userId;
	private int commentedId;
	private String commentContent;
	private Date createTime;
	private Date lastEditTime;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getCommentType() {
		return commentType;
	}
	public void setCommentType(int commentType) {
		this.commentType = commentType;
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
		return "Comment [commentId=" + commentId + ", commentType=" + commentType + ", userId=" + userId
				+ ", commentedId=" + commentedId + ", commentContent=" + commentContent + 
				", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

	

}
