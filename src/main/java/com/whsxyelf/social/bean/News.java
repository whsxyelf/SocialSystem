package com.whsxyelf.social.bean;

import java.util.Date;

public class News {
		private int newsId;
		private String newsFrom;
		private String newsTitle;
		private String newsUrl;
		private int length;
		private Date createTime;
		
		public News() {
			
		}
		
		public News(int newsId, String newsFrom, String newsTitle, String newsUrl, int length, Date createTime) {
			super();
			this.newsId = newsId;
			this.newsFrom = newsFrom;
			this.newsTitle = newsTitle;
			this.newsUrl = newsUrl;
			this.length = length;
			this.createTime = createTime;
		}
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
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		@Override
		public String toString() {
			return "News [newsId=" + newsId + ", newsFrom=" + newsFrom + ", newsTitle=" + newsTitle + ", newsUrl="
					+ newsUrl + ", length=" + length + ", createTime=" + createTime + "]";
		}
		
		
		
}