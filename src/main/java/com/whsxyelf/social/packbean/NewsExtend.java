package com.whsxyelf.social.packbean;

import java.util.Date;

public class NewsExtend {
	private int newsId;
	private String newsFrom;
	private String newsTitle;
	private String newsUrl;
	private int commentCount;
	private Date createTime;
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
