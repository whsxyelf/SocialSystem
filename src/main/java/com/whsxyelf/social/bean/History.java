package com.whsxyelf.social.bean;

import java.sql.Timestamp;

public class History {
	private int id;
	private int userNo;
	private int newsId;
	private float score;
	private Timestamp createTime;
	
	public History() {
		
	}
	
	public History(int id, int userNo, int newsId, float score, Timestamp createTime) {
		super();
		this.id = id;
		this.userNo = userNo;
		this.newsId = newsId;
		this.score = score;
		this.createTime = createTime;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", userNo=" + userNo + ", newsId=" + newsId + ", score=" + score + ", createTime="
				+ createTime + "]";
	}

	
}
