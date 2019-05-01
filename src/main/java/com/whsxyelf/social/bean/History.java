package com.whsxyelf.social.bean;

import java.util.Date;

public class History {
	private int historyId;
	private int userId;
	private int newsId;
	private Double score;
	private Integer state;
	private Date createTime;
	private Date lastEditTime;

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
		return "History [historyId=" + historyId + ", userId=" + userId + ", newsId=" + newsId + ", score=" + score
				+ ", createTime=" + createTime + "]";
	}

}
