package com.whsxyelf.social.bean;

import java.util.Date;

public class Comment {
	private int commentId;
	private String commentType;
	private String userNo;
	private int commentedId;
	private String commentContent;
	private Date createTime;
	
	public Comment() {
		
	}
	
	public Comment(int commentId, String commentType, String userNo, int commentedId, String commentContent,
			Date createTime) {
		super();
		this.commentId = commentId;
		this.commentType = commentType;
		this.userNo = userNo;
		this.commentedId = commentedId;
		this.commentContent = commentContent;
		this.createTime = createTime;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
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
	@Override
	public String toString() {
		return "Comment commentId=" + commentId + ", commentType=" + commentType + ", userNo=" + userNo
				+ ", commentedId=" + commentedId + ", commentContent=" + commentContent + ", createTime=" + createTime;
	}
	
}
