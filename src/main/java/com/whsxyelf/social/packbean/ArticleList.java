package com.whsxyelf.social.packbean;

import java.util.List;

import com.whsxyelf.social.bean.Essay;
import com.whsxyelf.social.bean.User;

public class ArticleList {
	private List<User> userList;
	private List<Essay> essayList;
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Essay> getEssayList() {
		return essayList;
	}
	public void setEssayList(List<Essay> essayList) {
		this.essayList = essayList;
	}
	
	
}
