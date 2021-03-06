package com.whsxyelf.social.bean;

import java.sql.Timestamp;

public class Comment {
	private int commentId;
	private int commentType;
	private int userNo;
	private int commentedId;
	private String commentContent;
	private Timestamp createTime;
	
	public Comment() {
		
	}
	
	public Comment(int commentId, int commentType, int userNo, int commentedId, String commentContent,
			Timestamp createTime) {
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

	public int getCommentType() {
		return commentType;
	}

	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentType=" + commentType + ", userNo=" + userNo + ", commentedId="
				+ commentedId + ", commentContent=" + commentContent + ", createTime=" + createTime + "]";
	}
	
}
