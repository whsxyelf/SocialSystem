package com.whsxyelf.social.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userNo;
	private String userNick;
	private String userPhoto;
	private String userEmail;
	private String password;
	private int sex;
	private String phone;
	private String signature;
	private int permission;
	private int userState;
	private Date createTime;
	
	public User() {
		
	}
	
	public User(int userNo, String userNick, String userPhoto, String userEmail, String password, int sex, String phone,
			String signature, int permission, int userState, Date createTime) {
		super();
		this.userNo = userNo;
		this.userNick = userNick;
		this.userPhoto = userPhoto;
		this.userEmail = userEmail;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.signature = signature;
		this.permission = permission;
		this.userState = userState;
		this.createTime = createTime;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userNick=" + userNick + ", userPhoto=" + userPhoto + ", userEmail="
				+ userEmail + ", password=" + password + ", sex=" + sex + ", phone=" + phone + ", signature="
				+ signature + ", permission=" + permission + ", userState=" + userState + ", createTime=" + createTime
				+ "]";
	}
	
	
}
