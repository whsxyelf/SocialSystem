package com.whsxyelf.social.bean;

public class Collect {
	private int collectionId;
	private String userNo;
	private int collectionType;
	private int collectedId;
	
	public Collect() {
		
	}
	
	public Collect(int collectionId, String userNo, int collectionType, int collectedId) {
		super();
		this.collectionId = collectionId;
		this.userNo = userNo;
		this.collectionType = collectionType;
		this.collectedId = collectedId;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public int getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(int collectionType) {
		this.collectionType = collectionType;
	}
	public int getCollectedId() {
		return collectedId;
	}
	public void setCollectedId(int collectedId) {
		this.collectedId = collectedId;
	}

	@Override
	public String toString() {
		return "Collection [collectionId=" + collectionId + ", userNo=" + userNo + ", collectionType=" + collectionType
				+ ", collectedId=" + collectedId + "]";
	}
	
}
